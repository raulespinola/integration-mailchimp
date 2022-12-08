package com.trio.challenge.integration.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StatsListDto {
        private int member_count;
        private int total_contacts;
        private int unsubscribe_count;
        private int cleaned_count;
        private int member_count_since_send;
        private int unsubscribe_count_since_send;
        private int cleaned_count_since_send;
        private int campaign_count;
        @JsonIgnore
        private LocalDateTime campaign_last_sent;
        private int merge_field_count;
        private int avg_sub_rate;
        private int avg_unsub_rate;
        private int target_sub_rate;
        private int open_rate;
        private int click_rate;
        @JsonIgnore
        private LocalDateTime last_sub_date;
        @JsonIgnore
        private LocalDateTime last_unsub_date;

}
