package com.test.eguay.controller.admin;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("admin/categories")
public class AdminCategoryController {
    final CategoryService categoryService;

    public AdminCategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String doList (@RequestParam(value = "filter", required = false) String filter, Model model) {

        if(filter != null && !filter.isEmpty() && !filter.isBlank()) {
            model.addAttribute("categories", this.categoryService.getAllCategoriesFilter(filter));
        } else {
            model.addAttribute("categories", this.categoryService.getAllCategories());
        }

        return "admin/categories";
    }

    @GetMapping("/new")
    public String doNew(Model model) {
        model.addAttribute("category", new CategoryDTO());
        return "admin/newCategory";
    }

    @PostMapping("/new")
    public String saveNew(@ModelAttribute("category") CategoryDTO category) {
        categoryService.createNew(category);
        return "redirect:/admin/categories/";
    }

    @GetMapping("/delete/{id}")
    public String delete (@PathVariable("id") Long id) {
        categoryService.deleteById(id);
        return "redirect:/admin/categories/";
    }

    @GetMapping("/edit")
    public String doEdit (@RequestParam("id") Long id, Model model) {
        model.addAttribute("category", this.categoryService.getById(id));
        return "admin/editCategory";
    }

    @PostMapping("/edit")
    public String save(@ModelAttribute("category") CategoryDTO category, Model model) {
        categoryService.editCategory(category);
        return "redirect:/admin/categories/";
    }
}
