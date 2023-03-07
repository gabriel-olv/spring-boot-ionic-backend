package com.gabrieldeoliveira.cursospring.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.services.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        List<Category> list = categoryService.list();
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Category obj) {
        Category newObj = categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {
        Category findedObj = categoryService.findById(id);
        return ResponseEntity.ok(findedObj);
    }
}
