package com.test.eguay.controller;

import com.test.eguay.dto.UserDTO;
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

@Controller
@RequestMapping("/")
public class ProfileController {

    protected UserService userService ;


    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/login")
    public String doListar (Model model) {


        return "login";
    }

        @PostMapping("/authenticate")
        public String doAutentica (Model model, HttpSession session,
                               @RequestParam("username") String user, @RequestParam("password") String password) {
        String goTo = "redirect:/";
        UserDTO admin = this.userService.loginUser(user, password);
        session.setAttribute("user", admin);
        if (admin == null) {
            model.addAttribute("error", "Credenciales incorrectas");
            goTo = "login";
        }


        return goTo;
    }

}
