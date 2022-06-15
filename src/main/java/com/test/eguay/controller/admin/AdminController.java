package com.test.eguay.controller.admin;

import com.test.eguay.dto.CategoryDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/")
    public String redirectUsers (Model model) {
        return "redirect:/admin/users/";
    }
}
