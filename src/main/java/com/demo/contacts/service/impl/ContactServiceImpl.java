package com.demo.contacts.service.impl;

import com.demo.contacts.domain.Contact;
import com.demo.contacts.repository.ContactRepository;
import com.demo.contacts.service.ContactService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return Lists.newArrayList(contactRepository.findAll());
    }

    @Override
    public Contact getContactById(Integer id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact updateContact(Contact contact) {
        Contact existingContact = getContactById(contact.getId());
        return (existingContact == null) ? null : contactRepository.save(contact);
    }

    @Override
    public List<Contact> getContactsByName(String name) {
        return contactRepository.findByName(name);
    }

    @Override
    public List<Contact> getContactsByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    @Override
    public List<Contact> getContactsByProfession(String profession) {
        return contactRepository.findByProfession(profession);
    }

    @Override
    public List<Contact> getContactsByNameAndEmail(String name, String email) {
        return contactRepository.findByNameAndEmail(name, email);
    }

    @Override
    public List<Contact> getContactsByNameAndProfession(String name, String profession) {
        return contactRepository.findByNameAndProfession(name, profession);
    }

    @Override
    public List<Contact> getContactsByEmailAndProfession(String email, String profession) {
        return contactRepository.findByEmailAndProfession(email, profession);
    }

    @Override
    public List<Contact> getContactsByNameAndEmailAndProfession(String name, String email, String profession) {
        return contactRepository.findByNameAndEmailAndProfession(name, email, profession);
    }
}
