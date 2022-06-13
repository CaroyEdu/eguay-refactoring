package com.test.eguay.controller;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.CategoryService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/PurchasedAuctions")
public class PurchasedAuctionController {
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

    protected CategoryService categoryService;

    public AuctionService getAuctionService() {
        return auctionService;
    }
    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    protected AuctionService auctionService;

    @GetMapping("")
    public String doShow(Model model , HttpSession session){
        UserDTO user = (UserDTO) session.getAttribute("user");

        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("purchasedAucs", this.userService.showPurchasedAuctions(user));
        model.addAttribute("user",user);
        return "purchasedAuctions";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model , HttpSession session , @PathVariable("id") Long id){
        UserDTO user = (UserDTO) session.getAttribute("user");
        AuctionDTO auctionDTO = this.auctionService.findById(id);
        this.userService.deletepurchasedAuction(user , auctionDTO);
        return "redirect:/PurchasedAuctions";
    }


    @PostMapping("/filter")
    public String doFilter(Model model, HttpSession session , @RequestParam("filter") String filter){
        UserDTO user = (UserDTO) session.getAttribute("user");

        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        if(filter == null || filter.isEmpty()){
            model.addAttribute("purchasedAucs",this.userService.showPurchasedAuctions(user));
        }else{
            model.addAttribute("purchasedAucs",this.userService.filterPurchasedAuctions(user,filter));
        }
        model.addAttribute("user",user);
        return "purchasedAuctions";
    }
}
