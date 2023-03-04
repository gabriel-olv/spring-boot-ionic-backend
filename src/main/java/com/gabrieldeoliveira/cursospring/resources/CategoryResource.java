package com.gabrieldeoliveira.cursospring.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldeoliveira.cursospring.domain.Category;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @GetMapping
    public List<Category> list() {
        Category c1 = new Category(1, "Informática");
        Category c2 = new Category(2, "Escritório");
        return Arrays.asList(c1, c2);
    }
}
