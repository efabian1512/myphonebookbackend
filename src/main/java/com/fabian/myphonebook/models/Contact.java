package com.fabian.myphonebook.models;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;


public class Contact {
    private final int id;
    @NotBlank
    private final String name;
    private final String telephone;
    private final String email;



    public Contact(@JsonProperty("id") int id,
                    @JsonProperty("name") String name,
                    @JsonProperty("telephone") String telephone,
                    @JsonProperty("email") String email){
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;

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
}
