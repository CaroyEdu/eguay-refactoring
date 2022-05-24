package com.test.eguay.controller;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    private CategoryService categoryService;
    private AuctionService auctionService;

    @GetMapping("/")
    public String index(Model model, @RequestParam("searchbar") String filter)
    {
        List<AuctionDTO> auctionList = auctionService.getAllAuctions();
        List<CategoryDTO> categoryList =  categoryService.getAllCategories();
        List<AuctionDTO> auctionFavList = null;
        auctionList = this.auctionService.filterAuction(filter);

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("auctionList", auctionList);
        model.addAttribute("favAuctions", auctionFavList);
        return "index";
    }
}
