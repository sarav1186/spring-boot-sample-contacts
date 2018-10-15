package com.demo.contacts.repository;

import com.demo.contacts.domain.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
    List<Contact> findByName(@Param("name") String name);

    List<Contact> findByEmail(@Param("email") String email);

    List<Contact> findByProfession(@Param("profession") String profession);

    List<Contact> findByNameAndEmail(@Param("name") String name, @Param("email") String email);

    List<Contact> findByNameAndProfession(@Param("name") String name, @Param("profession") String profession);

    List<Contact> findByEmailAndProfession(@Param("email") String email, @Param("profession") String profession);

    List<Contact> findByNameAndEmailAndProfession(@Param("name") String name, @Param("email") String email, @Param("profession") String profession);

    Contact findById(@Param("id") Integer id);
}
