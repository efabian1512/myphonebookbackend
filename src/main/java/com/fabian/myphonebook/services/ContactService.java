package com.fabian.myphonebook.services;



import com.fabian.myphonebook.dao.ContactDao;
import com.fabian.myphonebook.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactDao contactDao;

    @Autowired
    public ContactService(@Qualifier("MySql") ContactDao contactDao){
        this.contactDao = contactDao;
    }

    public String addPerson(Contact contact){
        return contactDao.addContact(contact);
    }

    public List<Contact> getAllContacts(){
        return contactDao.selectAllContacts();
    }

    public Optional<Contact> getContactById(String id){
        return contactDao.selectContactById(id);
    }

    public String deleteContactById(String id){
        return contactDao.deleteContactById(id);
    }

    public String updateContactById(String id, Contact newContact){ return contactDao.updateContactById(id,newContact);}



}

