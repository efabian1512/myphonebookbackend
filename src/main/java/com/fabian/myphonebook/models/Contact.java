package com.fabian.myphonebook.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;


public class Contact {
    private int id;
    @NotBlank
    private final String name;
    private final String telephone;
    private final String email;
    private final String address;
    private final String city;
    private final String country;



    public Contact(@JsonProperty("id") int id,
                    @JsonProperty("name") String name,
                    @JsonProperty("telephone") String telephone,
                    @JsonProperty("email") String email,
                   @JsonProperty("address") String address,
                   @JsonProperty("city") String city,
                   @JsonProperty("country") String country
                   ){
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.country = country;


    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getTelephone(){
        return telephone;
    }

    public String getEmail(){
        return email;
    }

    public String getAddress(){return address; }

    public String getCity(){return city; }

    public String getCountry(){return country;}

    public void setId(int id) {
        this.id = id;
    }
}
