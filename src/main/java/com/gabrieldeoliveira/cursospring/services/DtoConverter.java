package com.gabrieldeoliveira.cursospring.services;

import java.util.List;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.dto.CategoryDTO;

public class DtoConverter {
    
    public static CategoryDTO fromObj(Category obj) {
        return new CategoryDTO(obj.getId(), obj.getName());
    }

    public static List<CategoryDTO> fromObjList(List<Category> objList) {
        return objList.stream().map(x -> fromObj(x)).toList();
    }
}
