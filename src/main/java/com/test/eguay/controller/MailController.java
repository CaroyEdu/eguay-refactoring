package com.test.eguay.controller;

import com.test.eguay.dto.MailDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.Mail;
import com.test.eguay.repository.MailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("mail")
public class MailController {
    private MailRepository mailRepository;

    public MailRepository getMailRepository() {
        return mailRepository;
    }

    @Autowired
    public void setMailRepository(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    @GetMapping("")
    public String doShow(Model model, @SessionAttribute("user") UserDTO user){
        List<MailDTO> mails;
        mails = this.mailRepository.findMailsSentDirectlyToUser(user.getId()).stream().map(mail -> mail.toDtoLinked()).collect(Collectors.toList());
        mails.addAll(this.mailRepository.findMailsSentToUserByAGroup(user.getId()).stream().map(mail -> mail.toDtoLinked()).collect(Collectors.toList()));
        model.addAttribute("mails", mails);
        return "mail/box";
    }
}
