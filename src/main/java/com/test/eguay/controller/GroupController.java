package com.test.eguay.controller;

import com.test.eguay.dto.GroupDTO;
import com.test.eguay.service.GroupService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("group")
public class GroupController {
    private GroupService groupService;

    private UserService userService;

    public GroupService getGroupService() {
        return groupService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String doShowList(Model model){
        model.addAttribute("groups", this.groupService.getAll());
        return "group/list";
    }

    @GetMapping("delete")
    public String doDelete(Model model, @RequestParam(name = "checked") long[] ids){
        this.groupService.delete(ids);
        return "redirect:/group";
    }

    @GetMapping("new")
    public String doShowNew(Model model){
        GroupDTO dummy = new GroupDTO();
        //dummy.setName("name");
        model.addAttribute("group", dummy);
        model.addAttribute("users", this.userService.getAll());
        return "group/new";
    }
}
