package com.gabrieldeoliveira.cursospring.services;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.domain.Client;
import com.gabrieldeoliveira.cursospring.dto.CategoryDTO;
import com.gabrieldeoliveira.cursospring.dto.ClientDTO;

public class DtoConverter {

    public static CategoryDTO fromObj(Category obj) {
        return new CategoryDTO(obj.getId(), obj.getName());
    }

    public static ClientDTO fromObj(Client obj) {
        return new ClientDTO(obj.getId(), obj.getName(), obj.getEmail());
    }
}
