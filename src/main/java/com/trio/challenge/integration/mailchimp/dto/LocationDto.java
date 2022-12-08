package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class LocationDto {

        private float latitude;
        private float longitude;
        private float gmtoff;
        private float dstoff;
        private String country_code;
        private String timezone;
        private String region;
}
