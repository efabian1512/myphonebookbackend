package com.fabian.myphonebook.dao;

import com.fabian.myphonebook.models.Contact;
import java.util.List;
import java.util.Optional;


public interface ContactDao {

    int addContact(Contact contact);

   /* default int addContact(Contact1 contact){
        //UUID id = int.randomUUID();
        return addContact(id, contact);
    }*/

    List<Contact> selectAllContacts();

    Optional<Contact> selectContactById(String id);

    int deleteContactById(String id);

    int updateContactById(String id, Contact contact);
}