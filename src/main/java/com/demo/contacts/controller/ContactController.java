package com.demo.contacts.controller;

import com.demo.contacts.rest.mediatypes.Contact;
import com.demo.contacts.rest.services.ResourceService;
import com.demo.contacts.service.ContactService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/contacts", produces = {MediaTypes.HAL_JSON_VALUE})
@ExposesResourceFor(Contact.class)
@Slf4j
public class ContactController {

    private final Logger logger = LoggerFactory.getLogger(ContactController.class);

    private EntityLinks entityLinks;

    private ContactService contactService;

    private ResourceService resourceService;

    //Constructor based dependency injection
    @Autowired
    public ContactController(EntityLinks entityLinks, ContactService contactService, ResourceService resourceService) {
        this.entityLinks = entityLinks;
        this.contactService = contactService;
        this.resourceService = resourceService;
    }

//    @Autowired
//    private EntityLinks entityLinks;
//
//    @Autowired
//    private ContactService contactService;
//
//    @Autowired
//    private ResourceService resourceService;

    @GetMapping
    HttpEntity<Resources<Resource<Contact>>> getContacts() {
        List<com.demo.contacts.domain.Contact> contactList = contactService.getAllContacts();
        List<Resource<Contact>> temp = new ArrayList<>();

        for (com.demo.contacts.domain.Contact contact : contactList) {
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), resourceService.getContactMediaType(contact), false);
            temp.add(contactResource);
        }
        LinkBuilder lb = entityLinks.linkFor(Contact.class);
        return new ResponseEntity<>(new Resources<>(temp, lb.withSelfRel()), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public HttpEntity<Resource<Contact>> createContact(@RequestBody Contact contact) throws Throwable {

        if (StringUtils.isEmpty(contact.getName())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No name provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No name provided");
        }
        if (StringUtils.isEmpty(contact.getEmail())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No email provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No email provided");
        }

        com.demo.contacts.domain.Contact newContact = resourceService.getContactDomain(contact);
        newContact.setName(contact.getName());
        newContact.setEmail(contact.getEmail());
        newContact.setProfession(contact.getProfession());

        newContact = contactService.createContact(newContact);

        Contact contactMediaType = resourceService.getContactMediaType(newContact);
        Resource<Contact> contactResource = resourceService.getContactResource(newContact.getId(), contactMediaType, false);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(entityLinks.linkToSingleResource(contactMediaType).getHref()));

        return new ResponseEntity<>(contactResource, headers, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(value = "/{contactId}")
    public HttpEntity<?> updateContact(@PathVariable String contactId, @RequestBody Contact contact) {

        if (StringUtils.isEmpty(contact.getName())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No name provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No name provided");
        }
        if (StringUtils.isEmpty(contact.getEmail())) {
            if (logger.isDebugEnabled()) {
                logger.debug("No email provided for contact, raising a bad request");
            }
            throw new IllegalArgumentException("No email provided");
        }

        com.demo.contacts.domain.Contact existingContact = contactService.getContactById(Integer.valueOf(contactId));

        if (existingContact != null) {
            existingContact.setName(contact.getName());
            existingContact.setEmail(contact.getEmail());
            existingContact.setProfession(contact.getProfession());
            contactService.updateContact(existingContact);

            Contact contactMediaType = resourceService.getContactMediaType(existingContact);
            Resource<Contact> contactResource = resourceService.getContactResource(existingContact.getId(), contactMediaType, false);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(entityLinks.linkToSingleResource(contactMediaType).getHref()));

            return new ResponseEntity<>(contactResource, headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{contactId}")
    public HttpEntity<?> getContactDetails(@PathVariable String contactId) {

        com.demo.contacts.domain.Contact contact = contactService.getContactById(Integer.valueOf(contactId));

        if (contact != null) {
            Contact contactMediaType = resourceService.getContactMediaType(contact);
            Resource<Contact> contactResource = resourceService.getContactResource(contact.getId(), contactMediaType, false);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(entityLinks.linkToSingleResource(contactMediaType).getHref()));

            return new ResponseEntity<>(contactResource, headers, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




}
