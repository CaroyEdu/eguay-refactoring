package com.test.eguay.service;

import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    @EJB CategoryFacade categoryFacade;

    // Queries

    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryFacade.findAll();
        return CategoryService.toDTO(categories);
    }

    public List<CategoryDTO> getAllCategoriesDTO(){
        List<Category> categories = categoryFacade.findAll();
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

    public Category toDAO(CategoryDTO user)
    {

        Category c = new Category();
        c = this.categoryFacade.find(user.getId());
        return c;

    }
}

