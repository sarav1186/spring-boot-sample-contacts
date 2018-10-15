package com.demo.contacts.service;

import com.demo.contacts.domain.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact(Contact contact);

    List<Contact> getAllContacts();

    Contact getContactById(Integer id);

    Contact updateContact(Contact contact);

    List<Contact> getContactsByName(String name);

    List<Contact> getContactsByEmail(String email);

    List<Contact> getContactsByProfession(String profession);

    List<Contact> getContactsByNameAndEmail(String name, String email);

    List<Contact> getContactsByNameAndProfession(String name, String profession);

    List<Contact> getContactsByEmailAndProfession(String email, String profession);

    List<Contact> getContactsByNameAndEmailAndProfession(String name, String email, String profession);
}
