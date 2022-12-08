package com.trio.challenge.integration.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class LastNote {
        private int note_id;
        @JsonIgnore
        private LocalDateTime created_at;
        private String created_by;
        private String note;
}
