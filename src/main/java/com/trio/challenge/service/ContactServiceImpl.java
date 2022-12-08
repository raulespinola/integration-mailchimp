package com.trio.challenge.service;

import com.trio.challenge.integration.mailchimp.MailChimpProvider;
import com.trio.challenge.integration.mailchimp.dto.*;
import com.trio.challenge.integration.trio.TrioDataProvider;
import com.trio.challenge.integration.trio.dto.ContactsMockDto;
import com.trio.challenge.mapper.IContactMapper;
import com.trio.challenge.model.ContactTrioModel;
import com.trio.challenge.model.SyncContactModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private TrioDataProvider trioDataProvider;
    @Autowired
    private MailChimpProvider mailChimpProvider;

    @Autowired
    private IContactMapper iContactMapper;


    /**
     * Synchronize the contacts from mock api to mailchimp, before the sync
     * the service clean the contacts that are already in the mail list
     * @param listId
     * @return
     */
    public SyncContactModel syncContact(String listId, boolean addRandomEmail) {
        List<ContactsMockDto> contactsMockDto=trioDataProvider.getContactsFromTrio();
        if(addRandomEmail){
           contactsMockDto = modifyEmailForServiceRestriction(contactsMockDto);
        }
        List<Member> actualMembersList = mailChimpProvider.getMembersFromList(listId).getMembers();

        contactsMockDto.stream()
                    .filter(contact -> actualMembersList.stream()
                            .map(member -> member.getEmail_address()).collect(Collectors.toList())
                            .contains(contact.getEmail()));

        List<MemberMailChimpDto> membersUpdate = contactsMockDto
                .stream()
                .map(contact -> mailChimpProvider
                        .syncMembersFromList(listId,trioContactToMailChimpContact(contact)))
                .collect(Collectors.toList());

        return SyncContactModel.builder()
                .syncedContacts(membersUpdate.size())
                .contacts(membersUpdate.stream().map(member-> ContactTrioModel.builder()
                                        .email(member.getEmail_address())
                                        .firstName(member.getMergeFields().getFname())
                                        .lastName(member.getMergeFields().getLname()).build())
                                .collect(Collectors.toList())
                        ).build();
    }

    private List<ContactsMockDto> modifyEmailForServiceRestriction(List<ContactsMockDto> contactsMockDto) {
        Random random= new Random();
        int value= random.nextInt(3);
        return contactsMockDto.stream()
            .map(contact->contact.withEmail(contact.getEmail()
                    .concat(contact.getFirstName().substring(value)))).collect(Collectors.toList());
    }

    public int cleanMailChimpList(String listId){
         MembersList memberList= mailChimpProvider.getMembersFromList(listId);
         return memberList.getMembers().stream()
                 .peek(contact-> mailChimpProvider.deleteMembersFromList(listId, contact.getContact_id()))
                 .collect(Collectors.toList()).size();
    }

    public MembersList getMailChimpList(String listId){
        return mailChimpProvider.getMembersFromList(listId);
    }

    private RequestMemberMailChimpDto trioContactToMailChimpContact(ContactsMockDto contactTrio){
        return RequestMemberMailChimpDto.builder()
                .status("subscribed")
                .emailAddress(contactTrio.getEmail())
                .mergeFieldsDto(MergeFieldsDto.builder()
                        .fname(contactTrio.getFirstName())
                        .lname(contactTrio.getLastName())
                        .phone("")
                        .address(Address.builder().addr1("").state("").zip("").city("").build())
                        .birthday("").build())
                .build();
    }


    @Override
    public ResponseListDto createNewList(RequestListDto requestListDto){
        return mailChimpProvider.createNewList(requestListDto);
    }

    @Override
    public ResponseListsDto getAllList(){
        return mailChimpProvider.getAllList();
    }
}
