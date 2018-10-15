package com.demo.contacts.rest.services;

import com.demo.contacts.rest.mediatypes.Contact;
import org.springframework.hateoas.Resource;

public interface ResourceService {

    Resource<Contact> getContactResource(Integer contactId, Contact contactMediaType, boolean generateBackLink);

    Contact getContactMediaType(com.demo.contacts.domain.Contact contact);

    com.demo.contacts.domain.Contact getContactDomain(Contact contact) throws Throwable;
}