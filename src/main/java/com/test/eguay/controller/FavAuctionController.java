package com.test.eguay.controller;


import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.CategoryService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/FavAuctions")
@Controller
public class FavAuctionController {

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

    @GetMapping("")
    public String doShow(Model model , HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");

        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("favCats",this.userService.showFavAuctions(user));


        return "favAuction";
    }

    @PostMapping("/filter")
    public String doFilter(Model model, HttpSession session , @RequestParam("filter") String filter){
        UserDTO user = (UserDTO) session.getAttribute("user");

        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        if(filter == null || filter.isEmpty()){
            model.addAttribute("favCats",this.userService.showFavAuctions(user));
        }else{
            model.addAttribute("favCats",this.userService.filterFavAuctions(user,filter));
        }
        return "favAuction";
    }
}
