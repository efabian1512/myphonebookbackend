package com.fabian.myphonebook.dao;

import com.fabian.myphonebook.exception.ApiRequestException;
import com.fabian.myphonebook.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("MySql")
public class ContactDataAccessService implements ContactDao {
    private final JdbcTemplate jdbcTemplate;

    public ContactDataAccessService(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String addContact(Contact contact) {
        String sqlContact = "INSERT into contacts (name,telephone,email,address,city,country) VALUES (?,?,?,?,?,?)";


        jdbcTemplate.update(sqlContact,contact.getName(),contact.getTelephone(),contact.getEmail(),contact.getAddress(),
        contact.getCity(),contact.getCountry());



        return "Contact successfully added.";
    }

    @Override
    public List<Contact> selectAllContacts() {
        final String sql = "SELECT id, name, telephone, email,address,city,country FROM contacts ORDER BY name asc";
        return jdbcTemplate.query(sql,(resultSet, index) -> {
            int id = (resultSet.getInt("id"));
            String name = resultSet.getString("name");
            String telephone = resultSet.getString("telephone");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String city = resultSet.getString("city");
            String country = resultSet.getString("country");
            return new Contact(id,name,telephone,email,address,city,country);
        });


    }

    @Override
    public Optional<Contact> selectContactById(String id) {
        final String sql = "SELECT id,name,telephone,email,address,city,country FROM contacts WHERE id = ?";

        Contact contact = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet,index) ->{
                    int contactId = (resultSet.getInt("id"));
                    String name = resultSet.getString("name");
                    String telephone = resultSet.getString("telephone");
                    String email = resultSet.getString("email");
                    String address = resultSet.getString("address");
                    String city = resultSet.getString("city");
                    String country = resultSet.getString("country");
                    return new Contact(contactId,name,telephone,email,address,city,country);


                });
        return Optional.ofNullable(contact);
    }

    @Override
    public String deleteContactById(String id) {
        String sqlDelete = "DELETE from contacts WHERE id=?";
        Optional<Contact> contactMaybe = selectContactById(id);
        if(!contactMaybe.isPresent()){
            return "The contact you specify doesn't exist.";
        }
        jdbcTemplate.update(sqlDelete,id);
        return "Contact successfully deleted.";

    }

    @Override
    public String updateContactById(String id, Contact contact)  {
        String sqlUpdate = "UPDATE contacts SET name= ?, telephone=?,email=?,address=?,city=?,country=? WHERE id=?";
        Optional<Contact> contactMaybe = selectContactById(id);

        if(!contactMaybe.isPresent()){
            return "Contact doesn't exists.";
        }
        jdbcTemplate.update(sqlUpdate,contact.getName(),contact.getTelephone(),contact.getEmail(),contact.getAddress(),
                contact.getCity(),contact.getCountry(),id);
        return "Contact successfully updated.";

    }


}

