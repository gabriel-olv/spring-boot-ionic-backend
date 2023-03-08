package com.gabrieldeoliveira.cursospring.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.services.validation.UpdateClient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@UpdateClient
public class ClientDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "required field")
    @Length(min = 5, max = 120, message = "must have between 5 and 120 characters")
    private String name;
    
    @NotEmpty(message = "required field")
    @Email(message = "invalid email")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Client toObj() {
        return new Client(id, name, email, null, null);
    }
}
