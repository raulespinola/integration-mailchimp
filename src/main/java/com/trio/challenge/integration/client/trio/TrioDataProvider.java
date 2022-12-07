package com.trio.challenge.integration.client.trio;

import com.trio.challenge.integration.client.trio.dto.Root;

import java.util.List;

public interface TrioDataProvider {
    List<Root> getContactsFromTrio();
}
