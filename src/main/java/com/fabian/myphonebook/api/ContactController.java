package com.fabian.myphonebook.api;

import com.fabian.myphonebook.exception.ContactNotFoundException;
import com.fabian.myphonebook.models.Contact;
import com.fabian.myphonebook.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/phonebook/contact")
@RestController
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PostMapping
    public void addContact(@Valid @NonNull @RequestBody Contact contact){
        contactService.addPerson(contact);
    }

    @GetMapping
    public List<Contact> getAllContacts(){
        return contactService.getAllContacts();
    }

    @GetMapping(path = "{id}")
    public Contact getContactById( @PathVariable("id") String id){
        return contactService.getContactById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteContactById(@PathVariable("id") String id){

        boolean contactExist = contactService.exists(id);
        if(contactExist){
            contactService.deleteContactById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ContactNotFoundException();
        }


    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateContact(@PathVariable("id") String id, @Valid @NonNull @RequestBody Contact contact ){

        boolean contactExist = contactService.exists(id);

        if(contactExist){
            contactService.updateContactById(id,contact);
            return new ResponseEntity<>(contact, HttpStatus.OK);
        }else{
            throw new ContactNotFoundException();
        }




    }

}

