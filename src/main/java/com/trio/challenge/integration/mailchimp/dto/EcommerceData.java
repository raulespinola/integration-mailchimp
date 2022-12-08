package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class EcommerceData {
   
        private int total_revenue;
        private int number_of_orders;
        private String currency_code;
        
}
