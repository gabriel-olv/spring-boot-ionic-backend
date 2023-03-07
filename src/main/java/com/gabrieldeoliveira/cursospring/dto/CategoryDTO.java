package com.gabrieldeoliveira.cursospring.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.gabrieldeoliveira.cursospring.domain.Category;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "required field")
    @Length(min = 5, max = 80, message = "must be between 5 and 80 characters")
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
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

    public Category toObj() {
        return new Category(id, name);
    }
}
