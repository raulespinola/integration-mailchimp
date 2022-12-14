package com.trio.challenge.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SyncContactModel {

    private int syncedContacts;
    private List<ContactTrioModel> contacts;
}
