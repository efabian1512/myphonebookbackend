package com.fabian.myphonebook.dao;

import com.fabian.myphonebook.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ContactDao {

    String addContact(Contact contact);

    List<Contact> selectAllContacts();

    Optional<Contact> selectContactById(String id);

    String deleteContactById(String id);

    String updateContactById(String id, Contact contact);


}