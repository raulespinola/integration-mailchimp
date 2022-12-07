package com.trio.challenge.integration.client.mailchimp;

import com.trio.challenge.integration.client.mailchimp.dto.MemberMailChimpDto;
import com.trio.challenge.integration.client.mailchimp.dto.PingDto;
import com.trio.challenge.integration.client.mailchimp.dto.RequestMemberMailChimpDto;

public interface MailChimpProvider {
    MemberMailChimpDto syncMembersFromList(String listId,
                                           RequestMemberMailChimpDto requestMemberMailChimpDto);
    PingDto getList();
}
