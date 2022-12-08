package com.trio.challenge.integration.trio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trio.challenge.integration.trio.dto.ContactsMockDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component("trioclient")
@Slf4j
public class TrioHttpClient implements TrioDataProvider {
    protected final WebClient webClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(TrioHttpClient.class);
    private static final String TRIO_API_CONTACTS = "https://challenge.trio.dev/api/v1/contacts";

    public TrioHttpClient(ObjectMapper mapper) {

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> {
                    configurer.defaultCodecs().jackson2JsonEncoder(new Jackson2JsonEncoder(mapper));
                    configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper));
                    configurer.defaultCodecs().maxInMemorySize(500 * 1024 * 1024);
                })
                .build();
        webClient = WebClient.builder().filter(logRequest())
                .baseUrl(TRIO_API_CONTACTS).exchangeStrategies(exchangeStrategies).build();
    }

    // This method returns filter function which will log request data
    private static ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
            return Mono.just(clientRequest);
        });
    }

    public List<ContactsMockDto> getContactsFromTrio(){
        try {
            return Arrays.asList(Objects.requireNonNull(webClient
                    .get()
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(ContactsMockDto[].class).log()
                    .block()));
        } catch (Exception e) {
            LOGGER.error("TrioHttpClient error", e);
            throw(e);
        }
    }
}
