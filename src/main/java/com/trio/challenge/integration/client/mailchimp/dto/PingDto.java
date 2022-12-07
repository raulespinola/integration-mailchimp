package com.trio.challenge.integration.client.mailchimp.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class PingDto {
        private String healthStatus;
}
