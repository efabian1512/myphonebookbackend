package com.fabian.myphonebook.dao;

import com.fabian.myphonebook.models.Contact;
import java.util.List;
import java.util.Optional;


public interface ContactDao {

    String addContact(Contact contact);

    List<Contact> selectAllContacts();

    Optional<Contact> selectContactById(String id);

    void deleteContactById(String id);

    void updateContactById(String id, Contact contact);

    boolean exists(String Id);


}