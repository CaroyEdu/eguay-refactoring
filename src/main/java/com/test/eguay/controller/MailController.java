package com.test.eguay.controller;

import com.test.eguay.dto.MailDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.GroupService;
import com.test.eguay.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("mail")
public class MailController {
    private MailService mailService;
    private AuctionService auctionService;
    private GroupService groupService;

    public AuctionService getAuctionService() {
        return auctionService;
    }

    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public MailService getMailService() {
        return mailService;
    }

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("")
    public String doShow(Model model, @SessionAttribute("user") UserDTO user){
        model.addAttribute("mails", this.mailService.getMails(user.getId()));
        return "mail/box";
    }

    @GetMapping("new")
    public String doShowNew(Model model){
        model.addAttribute("mail", new MailDTO());
        model.addAttribute("auctions", this.auctionService.getAll());
        model.addAttribute("groups", this.groupService.getAll());
        return "mail/new";
    }

    @PostMapping("new")
    public String doNew(Model model, @SessionAttribute("user") UserDTO user, @ModelAttribute("mail") MailDTO mail, @RequestParam("auction") List<Long> auctionIds, @RequestParam("group") List<Long> groupIds){
        this.mailService.sendMailToGroups(user, mail.getSubject(), auctionIds, groupIds);
        return "redirect:/mail";
    }
}
