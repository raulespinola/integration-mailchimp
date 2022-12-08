package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Stats {

        private int avg_open_rate;
        private int avg_click_rate;
        private EcommerceData ecommerce_data;

}
