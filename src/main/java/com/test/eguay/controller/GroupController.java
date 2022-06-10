package com.test.eguay.controller;

import com.test.eguay.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

@Controller
@RequestMapping("group")
public class GroupController {
    private GroupService groupService;

    public GroupService getGroupService() {
        return groupService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("")
    public String doShowList(Model model){
        model.addAttribute("groups", this.groupService.getAll());
        return "group/groupList";
    }

    @GetMapping("delete")
    public String doShowList(Model model, @RequestParam(name = "checked") long[] ids){
        this.groupService.delete(ids);
        return "redirect:/group";
    }
}
