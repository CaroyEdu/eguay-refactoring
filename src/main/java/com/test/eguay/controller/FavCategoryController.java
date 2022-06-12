package com.test.eguay.controller;

import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.AuxFavCategory;
import com.test.eguay.entity.Category;
import com.test.eguay.entity.User;
import com.test.eguay.service.CategoryService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/EditFavCategory")
public class FavCategoryController {

    protected CategoryService categoryService;

    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected UserService userService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String doListar (Model model , HttpSession session) {

        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

        UserDTO user = (UserDTO) session.getAttribute("user");
        List<CategoryDTO> favCategories = this.userService.showFavCategories(user);
        AuxFavCategory auxFavCategory = new AuxFavCategory(favCategories);

        model.addAttribute("auxFavCategory",auxFavCategory);


        return "addFavCategory";
    }

    @PostMapping("/save")
    public String doSave (@ModelAttribute("auxFavCategory") AuxFavCategory auxFavCategory, HttpSession session ){
        List<CategoryDTO> categoryList =  categoryService.getAllCategories();
        UserDTO user = (UserDTO) session.getAttribute("user");
        List<String>categoryDTOS =  auxFavCategory.getFavCategories();
        
        userService.addFavCategories(user, categoryDTOS);

        return "redirect:/";

    }

}
