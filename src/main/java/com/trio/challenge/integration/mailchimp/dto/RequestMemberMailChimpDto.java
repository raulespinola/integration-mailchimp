package com.trio.challenge.integration.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RequestMemberMailChimpDto {
    @JsonProperty("email_address")
    private String emailAddress;
    private String status;
    @JsonProperty("merge_fields")
    private MergeFieldsDto mergeFieldsDto;
}
