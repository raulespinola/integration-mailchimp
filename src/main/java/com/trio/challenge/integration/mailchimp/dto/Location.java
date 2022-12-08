package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Location {

        private int latitude;
        private int longitude;
        private int gmtoff;
        private int dstoff;
        private String country_code;
        private String timezone;
        private String region;


}
