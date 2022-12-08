package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Address {
    private String addr1;
    private String addr2;
    private String city;
    private String state;
    private String zip;
    private String country;
}
