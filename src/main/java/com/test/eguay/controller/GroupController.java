package com.test.eguay.controller;

import com.test.eguay.dto.GroupDTO;
import com.test.eguay.service.GroupService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String doDelete(@RequestParam(name = "checked") long[] ids){
        this.groupService.delete(ids);
        return "redirect:/group";
    }

    @GetMapping("join")
    public String doJoin(@RequestParam(name = "checked") long[] ids){
        this.groupService.join(ids);
        return "redirect:/group";
    }

    @GetMapping("new")
    public String doShowNew(Model model){
        model.addAttribute("group", new GroupDTO());
        model.addAttribute("users", this.userService.getAll());
        return "group/new";
    }

    @PostMapping("new")
    public String doNew(@ModelAttribute("group") GroupDTO group, @RequestParam("checked") int[] userIds){
        return doSave(group, userIds);
    }

    private String doSave(GroupDTO group, int[] userIds) {
        this.groupService.save(group, userIds);
        return "redirect:/group";
    }

    @GetMapping("{id}/edit")
    public String doShowEdit(Model model, @PathVariable("id") long id){
        model.addAttribute("group", this.groupService.get(id));
        model.addAttribute("users", this.userService.getAll());
        return "/group/edit";
    }

    @PostMapping("edit")
    public String doEdit(@ModelAttribute("group") GroupDTO group, @RequestParam("checked") int[] userIds){
        return doSave(group, userIds);
    }
}
