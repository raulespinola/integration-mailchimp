package com.trio.challenge.integration.mailchimp.dto;

import com.trio.challenge.model.ContactTrioModel;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SyncContactDto {
    private int syncedContacts;
    private List<ContactTrioModel> contacts;
}
