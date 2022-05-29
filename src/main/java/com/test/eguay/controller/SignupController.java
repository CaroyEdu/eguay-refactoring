package com.test.eguay.controller;

import com.test.eguay.repository.UserRepository;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/")
public class SignupController {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/signup")
    public String doListar (Model model) {


        return "registro";
    }

    @PostMapping("/register")
    public String doRegistrar (Model model, @RequestParam("username") String username, @RequestParam("password") String password,
                               @RequestParam("email") String email , @RequestParam("name") String name ,
                               @RequestParam("surname") String surname , @RequestParam("address") String address,
                               @RequestParam("country") String country , @RequestParam("city") String city,
                               @RequestParam("birthday") String birthday , @RequestParam("sex") String sex ){

            Date birthdayDate ;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            birthdayDate = sdf.parse(birthday);

        } catch (ParseException e) {
            birthdayDate = new Date();
        }
        userService.createUser(username,name,surname,address,city,email,country,password,birthdayDate,0);

        return "redirect:/";

    }



}
