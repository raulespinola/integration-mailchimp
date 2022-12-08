package com.trio.challenge.controller;


import com.trio.challenge.controller.dto.CleanListDto;
import com.trio.challenge.integration.mailchimp.dto.*;
import com.trio.challenge.mapper.IContactMapper;
import com.trio.challenge.service.ContactServiceImpl;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContactController {

    @Value("${mailchimp.listId.default}")
    private String defaultList;

    @Autowired
    private ContactServiceImpl contactServiceImpl;

    @Autowired
    private IContactMapper iContactMapper;

    @GetMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SyncContactDto> syncContactsToList(@RequestParam(required = false) String listId,
                                                              @RequestParam(required = false)
                                                              @ApiParam(name =  "addRandomEmail",
                                                                      type = "boolean",
                                                                      value = "When the service reject the emails, add a new random char",
                                                                      example = "false") boolean addRandomEmail) {
        return new ResponseEntity<>(iContactMapper.modelToDto(contactServiceImpl.syncContact(listId!=null?listId:defaultList, addRandomEmail)),
                HttpStatus.OK);
    }

    @DeleteMapping(value = "/clean", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CleanListDto> cleanContactsFromList(@RequestParam(required = false) String listId){
        return new ResponseEntity<>(CleanListDto.builder()
                .memberDelete(contactServiceImpl.cleanMailChimpList(listId!=null?listId:defaultList)).build(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/member-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MembersList> getContactsFromList(@RequestParam(required = false) String listId){
        return new ResponseEntity<>(contactServiceImpl.getMailChimpList(listId!=null?listId:defaultList),
                HttpStatus.OK);
    }

    @PostMapping(value = "/new-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseListDto> createNewList(@RequestParam(required = false) String nameList){
        return new ResponseEntity<>(contactServiceImpl.createNewList(RequestListDto.builder()
                .contact(ContactDto.builder()
                        .address1("new").company("").country("").zip("").city("")
                        .build())
                .campaign_defaults(CampaignDefaultsDto.builder()
                        .subject("").language("")
                        .from_email("new@email.com").from_name("new").build())
                .permission_reminder("new").name(nameList).build()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/all-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseListsDto> getAllList(){
        return new ResponseEntity<>(contactServiceImpl.getAllList(),
                HttpStatus.OK);
    }

}
