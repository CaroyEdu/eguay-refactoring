package com.test.eguay.controller.admin;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.User;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Controller
@RequestMapping("admin/users")
public class AdminUsersController {
    final UserService userService;

    public AdminUsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String doList (Model model) {
        model.addAttribute("users", this.userService.getAll());
        return "admin/users";
    }

    @GetMapping("/edit")
    public String doEdit (@RequestParam("id") Integer id, Model model) {
        model.addAttribute("user", this.userService.findById(id));
        return "admin/editUser";
    }

    @PostMapping("/edit")
    public String save(@ModelAttribute("user") UserDTO user, Model model) {
        userService.editUserAdmin(user);
        return "redirect:/admin/users/";
    }

    @PostMapping("/new")
    public String newUser(@ModelAttribute("user") UserDTO user) {
        userService.createUser(user.getUsername(), user.getName(), user.getSurname(), user.getAddress(), user.getCity(), user.getEmail(), user.getCountry(), user.getPassword(), new Date(), 0);
        return "redirect:/admin/users/";
    }

    @GetMapping("/new")
    public String doNew (@RequestParam("id") Integer id, Model model) {
        model.addAttribute("user", this.userService.findById(id));
        return "admin/newUser";
    }


}
