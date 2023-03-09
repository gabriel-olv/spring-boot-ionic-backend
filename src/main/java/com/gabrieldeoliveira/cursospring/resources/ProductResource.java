package com.gabrieldeoliveira.cursospring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrieldeoliveira.cursospring.domain.Product;
import com.gabrieldeoliveira.cursospring.dto.ProductDTO;
import com.gabrieldeoliveira.cursospring.resources.utils.URL;
import com.gabrieldeoliveira.cursospring.services.DtoConverter;
import com.gabrieldeoliveira.cursospring.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String categoryIds,
            Pageable pageable) {
        name = URL.decodeParam(name);
        List<Integer> categoryIdsList = URL.decodeIntList(categoryIds);
        Page<Product> objPage = productService.search(name, categoryIdsList, pageable);
        Page<ProductDTO> objPageDto = objPage.map(x -> DtoConverter.fromObj(x));
        return ResponseEntity.ok(objPageDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Integer id) {
        Product findedObj = productService.findById(id);
        return ResponseEntity.ok(findedObj);
    }
}
