package com.gabrieldeoliveira.cursospring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gabrieldeoliveira.cursospring.domain.Category;
import com.gabrieldeoliveira.cursospring.repositories.CategoryRepository;
import com.gabrieldeoliveira.cursospring.services.exceptions.DataIntegrityException;
import com.gabrieldeoliveira.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {
    
    @Autowired 
    private CategoryRepository categoryRepository;

    @Transactional
    public Category insert(Category obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Error when saving category: object was null");
        }
        obj.setId(null);
        return categoryRepository.save(obj);
    }

    @Transactional(readOnly = true)
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Category> listPageable(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Category findById(Integer id) {
        Category obj = categoryRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException(
                            "Object not found ("+ Category.class.getSimpleName() +") Id: " + id
                        ));
        return obj;
    }

    @Transactional
    public Category update(Category obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Error when updating category: object was null");
        }
        Category newObj = findById(obj.getId());
        updateData(newObj, obj);
        return categoryRepository.save(newObj);
    }

    private void updateData(Category newObj, Category obj) {
        newObj.setName(obj.getName());
    }

    @Transactional
    public void deleteById(Integer id) {
        findById(id);
        try {
            categoryRepository.deleteById(id);
        } catch(DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can't delete category that has products");
        }
    }
}
