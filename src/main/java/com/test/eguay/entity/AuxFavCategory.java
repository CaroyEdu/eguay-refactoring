package com.test.eguay.entity;

import com.test.eguay.dto.CategoryDTO;

import java.util.List;

//Esta clase solo sirve para coger datos desde el formulario que esta en la pagina AddFavCategory.jsp

public class AuxFavCategory {
    public List<String> getFavCategories() {
        return favCategories;
    }

    public void setFavCategories(List<String> favCategories) {
        this.favCategories = favCategories;
    }


    List<String> favCategories ;

    public AuxFavCategory() {

    }


}