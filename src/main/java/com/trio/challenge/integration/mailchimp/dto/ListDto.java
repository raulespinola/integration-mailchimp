package com.trio.challenge.integration.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ListDto {
        private String id;
        private int web_id;
        private String name;
        private ContactDto contact;
        private String permission_reminder;
        @JsonIgnore
        private boolean use_archive_bar;
        private CampaignDefaultsDto campaign_defaults;
        @JsonIgnore
        private boolean notify_on_subscribe;
        @JsonIgnore
        private boolean notify_on_unsubscribe;
        @JsonIgnore
        private Date date_created;
        private int list_rating;
        @JsonIgnore
        private boolean email_type_option;
        private String subscribe_url_short;
        private String subscribe_url_long;
        private String beamer_address;
        private String visibility;
        @JsonIgnore
        private boolean double_optin;
        @JsonIgnore
        private boolean has_welcome;
        @JsonIgnore
        private boolean marketing_permissions;
        private List<String> modules;
        private Stats stats;
        @JsonIgnore
        private List<Link> _links;

}
