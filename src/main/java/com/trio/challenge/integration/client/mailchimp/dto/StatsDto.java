package com.trio.challenge.integration.client.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class StatsDto {
        private float avg_open_rate;
        private float avg_click_rate;
}
