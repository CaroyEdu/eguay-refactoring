package com.test.eguay.controller.admin;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/products")
public class AdminProductsController {
    final AuctionService auctionService;
    final CategoryService categoryService;

    public AdminProductsController(AuctionService auctionService, CategoryService categoryService) {
        this.auctionService = auctionService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String doList (@RequestParam(value = "filter", required = false) String filter, Model model) {
        if(filter != null && !filter.isEmpty() && !filter.isBlank()) {
            model.addAttribute("products", this.auctionService.getAllAuctionsFilter(filter));
        } else {
            model.addAttribute("products", this.auctionService.getAllAuctions());
        }

        return "admin/products";
    }

    @GetMapping("/new")
    public String doNew(Model model) {
        model.addAttribute("category", new AuctionDTO());
        return "admin/newProduct";
    }

    @PostMapping("/new")
    public String saveNew(@ModelAttribute("product") AuctionDTO product) {
        auctionService.createAuction(product);
        return "redirect:/admin/products/";
    }

    @GetMapping("/edit")
    public String doEdit (@RequestParam("id") Long id, Model model) {
        model.addAttribute("product", this.auctionService.findById(id));
        model.addAttribute("categories", this.categoryService.getAllCategories());
        return "admin/editProduct";
    }

    @PostMapping("/edit")
    public String save(@ModelAttribute("product")AuctionDTO auction, Model model) {
        auctionService.editAdminAcution(auction);
        return "redirect:/admin/products/";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id) {
        auctionService.deleteById(id);
        return "redirect:/admin/products/";
    }
}
