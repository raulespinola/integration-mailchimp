package com.trio.challenge.model;

import com.trio.challenge.integration.client.mailchimp.dto.MemberMailChimpDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SyncContactModel {

    private int syncedContacts;
    private List<MemberMailChimpDto> contacts;
}
