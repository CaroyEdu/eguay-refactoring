package com.test.eguay.controller;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.BidDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.CategoryService;
import com.test.eguay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    protected CategoryService categoryService;
    protected AuctionService auctionService;

    public UserService getUserService() {
        return userService;
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    protected UserService userService;

    public CategoryService getCategoryService() {
        return categoryService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public AuctionService getAuctionService() {
        return auctionService;
    }

    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/")
    public String index(Model model , HttpSession session )
    {
        List<AuctionDTO> auctionList = this.auctionService.getAllAuctions();
        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        List<AuctionDTO> auctionFavList = null;
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user != null){
            auctionFavList = this.userService.showFavAuctions(user);
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("auctionList", auctionList);
        model.addAttribute("favAuctions", auctionFavList);
        return "index";
    }

    @GetMapping("/addFav/{id}")
    public String addFav(Model model , HttpSession session , @PathVariable("id") Long id)
    {
        List<AuctionDTO> auctionList = this.auctionService.getAllAuctions();
        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        List<AuctionDTO> auctionFavList = null;
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user != null){
            auctionFavList = this.userService.showFavAuctions(user);
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("auctionList", auctionList);
        model.addAttribute("favAuctions", auctionFavList);



        AuctionDTO auctionDTO = this.auctionService.findById(id);
        this.userService.addFavAuction(user , auctionDTO);

        return "redirect:/";
    }
    @GetMapping("/deleteFav/{id}")
    public String deleteFav(Model model , HttpSession session , @PathVariable("id") Long id)
    {
        List<AuctionDTO> auctionList = this.auctionService.getAllAuctions();
        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        List<AuctionDTO> auctionFavList = null;
        UserDTO user = (UserDTO) session.getAttribute("user");
        if(user != null){
            auctionFavList = this.userService.showFavAuctions(user);
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("auctionList", auctionList);
        model.addAttribute("favAuctions", auctionFavList);

        AuctionDTO auctionDTO = this.auctionService.findById(id);
        this.userService.deleteFavAuction(user , auctionDTO);

        return "redirect:/";
    }

    @GetMapping("/showAuction/{id}")
    public String showAuction(Model model, @PathVariable("id") Long id){
        List<CategoryDTO> categoryList =  this.categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);
        AuctionDTO auctionDTO = this.auctionService.findById(id);
        model.addAttribute("auction",auctionDTO);
        return "product";
    }

    @GetMapping("/showAuction/purchaseAuction/{id}")
    public String purchaseAuction(Model model,HttpSession session, @PathVariable("id") Long id){
        AuctionDTO auctionDTO = this.auctionService.findById(id);
        model.addAttribute("auction",auctionDTO);
        UserDTO user = (UserDTO) session.getAttribute("user");
        this.userService.purchaseAuction(user, auctionDTO);
        return "redirect:/";
    }
    @GetMapping("/showAuction/submitBid/{id}")
    public String submitBid(Model model,HttpSession session, @PathVariable("id") Long id){
        AuctionDTO auctionDTO = this.auctionService.findById(id);
        model.addAttribute("auction",auctionDTO);
        UserDTO user = (UserDTO) session.getAttribute("user");
        model.addAttribute("user",user);
        BidDTO bidDTO = auctionService.getHighestBid(auctionDTO);
        model.addAttribute("highestBid",bidDTO);
        return "puja";
    }
    @PostMapping("/showAuction/submitBid/finilizeSubmitingBid")
    public String finilizeSubmittingBid(Model model,HttpSession session, @RequestParam("Bid") Double amount,
                                                                    @RequestParam("AuctionID") Long auctionID){
        AuctionDTO auctionDTO = this.auctionService.findById(auctionID);
        UserDTO user = (UserDTO) session.getAttribute("user");
        this.userService.submitBid(user,auctionDTO,amount);

        return "redirect:/";
    }

}
