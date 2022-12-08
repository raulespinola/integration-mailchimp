package com.trio.challenge.integration.mailchimp.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResponseListsDto {
    private List<ListDto> lists;
    private int total_items;
    private ConstraintsDto constraints;
    private List<Link> _links;

}
