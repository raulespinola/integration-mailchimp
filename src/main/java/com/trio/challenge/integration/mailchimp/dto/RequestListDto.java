package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class RequestListDto {
        private String name;
        private ContactDto contact;
        private String permission_reminder;
        private boolean use_archive_bar;
        private CampaignDefaultsDto campaign_defaults;
        private String notify_on_subscribe;
        private String notify_on_unsubscribe;
        private boolean email_type_option;
        private boolean double_optin;
        private boolean marketing_permissions;
}
