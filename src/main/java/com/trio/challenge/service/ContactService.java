package com.trio.challenge.service;

import com.trio.challenge.integration.client.mailchimp.MailChimpProvider;
import com.trio.challenge.integration.client.mailchimp.dto.MemberMailChimpDto;
import com.trio.challenge.integration.client.mailchimp.dto.MergeFieldsDto;
import com.trio.challenge.integration.client.mailchimp.dto.PingDto;
import com.trio.challenge.integration.client.mailchimp.dto.RequestMemberMailChimpDto;
import com.trio.challenge.integration.client.trio.TrioDataProvider;
import com.trio.challenge.integration.client.trio.dto.Root;
import com.trio.challenge.mapper.IContactMapper;
import com.trio.challenge.model.ContactTrioModel;
import com.trio.challenge.model.SyncContactModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private TrioDataProvider trioDataProvider;
    @Autowired
    private MailChimpProvider mailChimpProvider;

    @Autowired
    private IContactMapper iContactMapper;


    public SyncContactModel syncContact(String listId) throws ServiceUnavailableException {
        List<Root> listContactsTrio= trioDataProvider.getContactsFromTrio();
        PingDto pingDto= mailChimpProvider.getList();
        List<MemberMailChimpDto> membersUpdate = listContactsTrio
                .stream()
                .map(contact -> mailChimpProvider
                        .syncMembersFromList(listId,trioContactToMailChimpContact(contact)))
                .toList();

        return SyncContactModel.builder()
                .syncedContacts(membersUpdate.size())
                .contacts(membersUpdate).build();
    }

    private RequestMemberMailChimpDto trioContactToMailChimpContact(Root contactTrio){
        return RequestMemberMailChimpDto.builder()
                .status("subscribed")
                .emailAddress(contactTrio.getEmail())
                .mergeFieldsDto(MergeFieldsDto.builder()
                        .fname(contactTrio.getFirstName())
                        .lname(contactTrio.getLastName())
                        .phone("")
                        .address("")
                        .birthday("").build())
                .build();
    }
}
