package com.trio.challenge.integration.mailchimp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class MembersList {
        private int totalItems;
        private String listId;
        private List<Member> members;
        @JsonIgnore
        private List<Link> links;
}
