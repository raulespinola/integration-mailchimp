package com.trio.challenge.service;

import com.trio.challenge.integration.mailchimp.dto.*;
import com.trio.challenge.model.SyncContactModel;

import javax.naming.ServiceUnavailableException;

public interface ContactService {
    SyncContactModel syncContact(String listId, boolean addRandomEmail) throws ServiceUnavailableException;

    int cleanMailChimpList(String listId);

    MembersList getMailChimpList(String listId);

    ResponseListsDto getAllList();

    ResponseListDto createNewList(RequestListDto requestListDto);

}
