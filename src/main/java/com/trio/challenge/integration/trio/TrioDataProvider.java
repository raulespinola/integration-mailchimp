package com.trio.challenge.integration.trio;

import com.trio.challenge.integration.trio.dto.ContactsMockDto;

import java.util.List;

public interface TrioDataProvider {
    List<ContactsMockDto> getContactsFromTrio();
}
