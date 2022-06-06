package com.test.eguay.controller;


import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.CategoryService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

        model.addAttribute("favCats",this.userService.showFavAuctions(user));

        return "favAuction";
    }
}
