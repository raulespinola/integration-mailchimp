package com.trio.challenge.integration.client.mailchimp.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberMailChimpDto {
        private String id;
        private String email_address;
        private String unique_email_id;
        private String contact_id;
        private String full_name;
        private float web_id;
        private String email_type;
        private String status;
        private boolean consents_to_one_to_one_messaging;
        private MergeFieldsDto Merge_fieldsObject;
        private StatsDto statsDtoObject;
        private String ip_signup;
        private String timestamp_signup;
        private String ip_opt;
        private String timestamp_opt;
        private float member_rating;
        private String last_changed;
        private String language;
        private boolean vip;
        private String email_client;
        private LocationDto LocationObject;
        private String source;
        private float tags_count;
        private List<String> tags ;
        private String list_id;
        private List <Object> _links;
}
