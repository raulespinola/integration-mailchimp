package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LinkListDto {
        private String rel;
        private String href;
        private String method;
        private String targetSchema;
        private String schema;

}
