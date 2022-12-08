package com.trio.challenge.integration.mailchimp;

import com.trio.challenge.integration.mailchimp.dto.*;


public interface MailChimpProvider {
    MemberMailChimpDto syncMembersFromList(String listId,
                                           RequestMemberMailChimpDto requestMemberMailChimpDto);
    void deleteMembersFromList(String listId, String memberId);
    MembersList getMembersFromList(String listId);
    ResponseListDto createNewList(RequestListDto requestListDto);

    ResponseListsDto getAllList();
}
