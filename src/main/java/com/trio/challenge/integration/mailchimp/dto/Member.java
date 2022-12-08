package com.trio.challenge.integration.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Member {

    private String id;
    private String email_address;
    private String unique_email_id;
    private String contact_id;
    private String full_name;
    private int web_id;
    private String email_type;
    private String status;
    private String unsubscribe_reason;
    private boolean consents_to_one_to_one_messaging;
    @JsonProperty(value="merge_fields")
    private MergeFieldsDto mergeFieldsDto;
    private Interests interests;
    private Stats stats;
    private String ip_signup;
    @JsonIgnore
    private LocalDateTime timestamp_signup;
    private String ip_opt;
    @JsonIgnore
    private LocalDateTime timestamp_opt;
    private int member_rating;
    @JsonIgnore
    private LocalDateTime last_changed;
    private String language;
    private boolean vip;
    private String email_client;
    private Location location;
    private List<MarketingPermission> marketing_permissions;
    private LastNote last_note;
    private String source;
    private int tags_count;
    private List<Tag> tags;
    private String list_id;
    @JsonIgnore
    private List<Link> _links;
}
