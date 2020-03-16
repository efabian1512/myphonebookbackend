package com.fabian.myphonebook.dao;

import com.fabian.myphonebook.models.Contact;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("MySql")
public class ContactDataAccess implements ContactDao {
    private final JdbcTemplate jdbcTemplate;

    public ContactDataAccess(JdbcTemplate jdbcTemplate){
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
        Contact contact = null;
        try {
            contact = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (resultSet, index) -> {

                        int contactId = (resultSet.getInt("id"));
                        String name = resultSet.getString("name");
                        String telephone = resultSet.getString("telephone");
                        String email = resultSet.getString("email");
                        String address = resultSet.getString("address");
                        String city = resultSet.getString("city");
                        String country = resultSet.getString("country");
                        return new Contact(contactId, name, telephone, email, address, city, country);


                    });
        }catch (EmptyResultDataAccessException exception){
            return null;
        }
        return Optional.ofNullable(contact);
    }

    @Override
    public void deleteContactById(String id) {
        String sqlDelete = "DELETE from contacts WHERE id=?";

        jdbcTemplate.update(sqlDelete,id);


    }

    @Override
    public void updateContactById(String id, Contact contact)  {
        String sqlUpdate = "UPDATE contacts SET name= ?, telephone=?,email=?,address=?,city=?,country=? WHERE id=?";

        jdbcTemplate.update(sqlUpdate,contact.getName(),contact.getTelephone(),contact.getEmail(),contact.getAddress(),
                contact.getCity(),contact.getCountry(),id);


    }

    @Override
    public boolean exists(String id) {
       Optional<Contact> contact = selectContactById(id);

       if(contact.isPresent()){
           return true;
       }

       return false;
    }


}

