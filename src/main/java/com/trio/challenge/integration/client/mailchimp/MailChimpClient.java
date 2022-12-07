package com.trio.challenge.integration.client.mailchimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trio.challenge.integration.client.mailchimp.dto.MemberMailChimpDto;
import com.trio.challenge.integration.client.mailchimp.dto.PingDto;
import com.trio.challenge.integration.client.mailchimp.dto.RequestMemberMailChimpDto;
import com.trio.challenge.integration.client.mailchimp.dto.SyncContactDto;
import com.trio.challenge.integration.client.trio.TrioHttpClient;
import com.trio.challenge.integration.client.trio.dto.Root;
import com.trio.challenge.model.ContactTrioModel;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component("mailchimpclient")
@Slf4j
public class MailChimpClient implements MailChimpProvider{
    protected final WebClient webClient;
    private static final String API_KEY = "f669402b9daa2ad60d45a2886036cbfb-us21";
    private static final String SERVER_API="https://us21.api.mailchimp.com/3.0/lists/";

    private static final Logger LOGGER = LoggerFactory.getLogger(TrioHttpClient.class);

    /*
    @Value("${trio-api.url}")

     */
    public MailChimpClient ( ObjectMapper mapper) {

        var exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper));
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper));
                    configurer.defaultCodecs().maxInMemorySize(500 * 1024 * 1024);
                })
                .build();

        HttpClient httpClient = HttpClient
                .create()
                .wiretap("reactor.netty.http.client.HttpClient",
                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);

        webClient = WebClient.builder()
                .baseUrl("https://us21.api.mailchimp.com/3.0")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(ExchangeFilterFunctions
                        .basicAuthentication("anystring", API_KEY))
                .exchangeStrategies(exchangeStrategies)
                .build();
    }



    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {} {}", clientRequest.method(), clientRequest.url(), clientRequest.body());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    @Override
    public MemberMailChimpDto syncMembersFromList(String listId, RequestMemberMailChimpDto requestMemberMailChimpDto  ){

        try {
            return webClient
                    .post()
                    .uri(builder -> builder
                            .path("/lists/%s/members".formatted(listId)).build())
                    .body(BodyInserters.fromValue(requestMemberMailChimpDto))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(MemberMailChimpDto.class)
                    .log()
                    .block();
        } catch (Exception e) {
            LOGGER.error("TrioHttpClient error", e);
            throw(e);
        }
    }

    @Override
    public PingDto getList(){
        try {
            return webClient
                    .get()
                    .uri(uriBuilder ->
                            uriBuilder.path(("/ping")).build())
                    .retrieve()
                    .bodyToMono(PingDto.class)
                    .log()
                    .block();
        } catch (Exception e) {
            LOGGER.error("TrioHttpClient error", e);
            throw(e);
        }
    }
}
