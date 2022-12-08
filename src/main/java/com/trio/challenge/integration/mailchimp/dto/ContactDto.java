package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ContactDto {

        private String company;
        private String address1;
        private String address2;
        private String city;
        private String state;
        private String zip;
        private String country;
        private String phone;

}
