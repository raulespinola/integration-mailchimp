package com.trio.challenge.integration.client.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MergeFieldsDto {
        @JsonProperty("FNAME")
        private String fname;
        @JsonProperty("LNAME")
        private String lname;
        @JsonProperty("ADDRESS")
        private String address;
        @JsonProperty("PHONE")
        private String phone;
        @JsonProperty("BIRTHDAY")
        private String birthday;
}
