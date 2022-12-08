package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MarketingPermission {
        private String marketing_permission_id;
        private String text;
        private boolean enabled;
}
