package com.test.eguay.service;

import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.entity.Category;
import com.test.eguay.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // Queries

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        return CategoryService.toDTO(categories);
    }

    public List<CategoryDTO> getAllCategoriesDTO(){
        List<Category> categories = categoryRepository.findAll();
        return Category.toDTO(categories) ;
    }

    // Logic
    public static List<CategoryDTO> toDTO(List<Category> categories){
        List<CategoryDTO> dtos = new ArrayList<>(categories.size());

        for(Category category : categories){
            dtos.add(category.toDTO());
        }

        return dtos;
    }

    public void editCategory(CategoryDTO category) {
        Category c = categoryRepository.findById(category.getId()).get();
        c.setName(category.getName());
        categoryRepository.save(c);
    }

    public Category toDAO(CategoryDTO user)
    {

        Optional<Category> c = Optional.of(new Category());
        c = this.categoryRepository.findById(user.getId());
        return c.get();

    }

    public CategoryDTO getById(Long id) {
        return this.categoryRepository.findById(id).get().toDTO();
    }

    public void createNew(CategoryDTO category) {
        Category c = new Category();
        c.setName(category.getName());
        categoryRepository.save(c);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDTO> getAllCategoriesFilter(String filter) {
        return categoryRepository.findByNameContainingIgnoreCase(filter).stream().map(c -> c.toDTO()).collect(Collectors.toList());
    }
}

