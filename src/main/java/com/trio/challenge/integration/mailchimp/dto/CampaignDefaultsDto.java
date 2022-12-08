package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class CampaignDefaultsDto {
        public String from_name;
        public String from_email;
        public String subject;
        public String language;
}
