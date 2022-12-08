package com.trio.challenge.integration.mailchimp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trio.challenge.integration.mailchimp.dto.*;
import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;



@Component("mailchimpclient")
@Slf4j
public class MailChimpClient implements MailChimpProvider{
    protected final WebClient webClient;
    private final String apiKey;
    private final String serverApi;

    private final ObjectMapper objectMapper;

    @Autowired
    public MailChimpClient (ObjectMapper objectMapper,
                            @Value("${mailchimp.apikey}") String apiKey,
                            @Value("${mailchimp.server}") String serverApi) {
        this.apiKey = apiKey;
        this.serverApi = serverApi;
        this.objectMapper= objectMapper;

        var exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(objectMapper));
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(objectMapper));
                    configurer.defaultCodecs().maxInMemorySize(500 * 1024 * 1024);
                })
                .build();

        HttpClient httpClient = HttpClient
                .create()
                .wiretap("reactor.netty.http.client.HttpClient",
                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);

        webClient = WebClient.builder()
                .baseUrl(serverApi)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .filter(ExchangeFilterFunctions
                        .basicAuthentication("anystring", apiKey))
                .exchangeStrategies(exchangeStrategies)
                .build();
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
                    .onErrorResume(e -> {
                        if (e instanceof WebClientResponseException) {
                            var responseException = (WebClientResponseException) e;
                            if (responseException.getStatusCode().is4xxClientError()) {
                                return Mono.error(new ResponseStatusException(responseException.getStatusCode(),
                                        e.getLocalizedMessage().concat(String.format("- Posible the member %s: was already sync", requestMemberMailChimpDto.getEmailAddress()))));
                            }
                        }
                        return Mono.error(e);})
                    .log()
                    .block();
        } catch (Exception e) {
            log.error("TrioHttpClient error", e);
            throw(e);
        }
    }

    @Override
    public void deleteMembersFromList(String listId, String memberId){
        try {
            webClient
                .delete()
                .uri(builder -> builder
                        .path("/lists/%s/members/%s".formatted(listId,memberId)).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        } catch (Exception e) {
            log.error("TrioHttpClient error", e);
            throw(e);
        }
    }

    @Override
    public ResponseListDto createNewList(RequestListDto requestListDto){
        try {
           return webClient
                    .post()
                    .uri(builder -> builder
                            .path("/lists/").build())
                    .body(BodyInserters.fromValue(requestListDto))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(ResponseListDto.class)
                    .block();
        } catch (Exception e) {
            log.error("TrioHttpClient error", e);
            throw(e);
        }
    }

    @Override
    public ResponseListsDto getAllList(){
        try {
            return webClient
                    .get()
                    .uri(builder -> builder
                            .path("/lists/").build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(ResponseListsDto.class)
                    .block();
        } catch (Exception e) {
            log.error("TrioHttpClient error", e);
            throw(e);
        }
    }

    @Override
    public MembersList getMembersFromList(String listId){
        try {
            return webClient
                    .get()
                    .uri(builder -> builder
                            .path("/lists/%s/members?count=100".formatted(listId)).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(MembersList.class)
                    .log()
                    .block();
        } catch (Exception e) {
            log.error("TrioHttpClient error", e);
            throw(e);
        }
    }
}
