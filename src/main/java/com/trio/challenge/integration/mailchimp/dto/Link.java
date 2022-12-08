package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Link {
        private String rel;
        private String href;
        private String method;
        private String targetSchema;
        private String schema;
}
