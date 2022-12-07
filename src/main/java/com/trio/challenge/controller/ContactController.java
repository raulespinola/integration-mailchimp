package com.trio.challenge.controller;


import com.trio.challenge.integration.client.mailchimp.dto.SyncContactDto;
import com.trio.challenge.mapper.IContactMapper;
import com.trio.challenge.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private IContactMapper iContactMapper;

    @GetMapping(value = "/sync", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SyncContactDto> syncContactsWithMailChimp(@RequestParam String listId) throws ServiceUnavailableException {
        return new ResponseEntity<>(iContactMapper.modelToDto(contactService.syncContact(listId)),
                HttpStatus.OK);
    }

}
