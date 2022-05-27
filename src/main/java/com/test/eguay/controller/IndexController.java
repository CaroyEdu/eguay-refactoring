package com.test.eguay.controller;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    protected CategoryService categoryService;
    protected AuctionService auctionService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public AuctionService getAuctionService() {
        return auctionService;
    }

    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String index(Model model)
    {
        List<AuctionDTO> auctionList = this.auctionService.getAllAuctions();
        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        List<AuctionDTO> auctionFavList = null;

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("auctionList", auctionList);
        model.addAttribute("favAuctions", auctionFavList);
        return "index";
    }
}
