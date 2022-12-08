package com.trio.challenge.integration.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseListDto {


        private String id;
        private int web_id;
        private String name;
        private ContactDto contact;
        private String permission_reminder;
        private boolean use_archive_bar;
        private CampaignDefaultsDto campaign_defaults;
        private boolean notify_on_subscribe;
        private boolean notify_on_unsubscribe;
        @JsonIgnore
        private LocalDateTime date_created;
        private int list_rating;
        private boolean email_type_option;
        private String subscribe_url_short;
        private String subscribe_url_long;
        private String beamer_address;
        private String visibility;
        private boolean double_optin;
        private boolean has_welcome;
        private boolean marketing_permissions;
        private List<String> modules;
        private Stats stats;
        private List<Link> _links;
}
