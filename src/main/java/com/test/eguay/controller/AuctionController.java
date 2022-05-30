package com.test.eguay.controller;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.AuctionService;
import com.test.eguay.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class AuctionController {

    private CategoryService categoryService;
    private AuctionService auctionService;

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

    @GetMapping("/addAuction")
    public String goAddAuction(Model model){
        List<CategoryDTO> categoryList =  categoryService.getAllCategories();
        model.addAttribute("categoryList", categoryList);

//        if(idParameter!=null)
//        {
//            Long id = Long.parseLong(idParameter);
//            AuctionDTO auction = auctionService.findById(id);
//            model.addAttribute("auction", auction);
//            model.addAttribute("active", auction.isActive());
//        }

        return "addEditAuction";
    }

    @PostMapping("/insertAuction")
    public String goInsertAuction(Model model, HttpSession session, @RequestParam("title") String title,
                                  @RequestParam(name="description", required = false) String description, @RequestParam(name="fotourl", required = false) String fotourl,
                                  @RequestParam("startprice") Float startPrice, @RequestParam(name="checkBoxClosePrice", required = false) String checkBCP,
                                  @RequestParam(name="inputClosePrice", required = false) String inputClosePrice,
                                  @RequestParam(name="checkBoxCloseNumberOfBids", required = false) String checkBCNOB,
                                  @RequestParam(name="inputCloseNumberOfBids", required = false) String inputCloseNumberOfBids,
                                  @RequestParam(name="checkBoxCloseDate", required = false) String checkBCD,
                                  @RequestParam(name="inputCloseDate", required = false) String inputCloseDate,
                                  @RequestParam(name="inputCloseDateTime", required = false) String inputCloseDateTime,
                                  @RequestParam("category") String category){

        AuctionDTO auction = new AuctionDTO();

        // Definimos el usuario, título, descrición, URL de la foto y precio inicial
        UserDTO user = (UserDTO) session.getAttribute("user");
        auction.setSellerID(Long.parseLong(user.getId().toString()));
        auction.setName(title);
        auction.setDescription(description);
        auction.setFotourl(fotourl);
        auction.setStartPrice(startPrice);
        if(checkBCP != null && checkBCP.equals("on"))
        {
            if(!inputClosePrice.equals(""))
            {
                auction.setClosePrice(Float.parseFloat(inputClosePrice));
            }
        }

        if(checkBCNOB != null && checkBCNOB.equals("on"))
        {
            if(!inputCloseNumberOfBids.equals(""))
            {
                auction.setCloseNumberofBids(Integer.parseInt(inputCloseNumberOfBids));
            }
        }

        if(checkBCD != null && checkBCD.equals("on"))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateInString = inputCloseDate + " " + inputCloseDateTime + ":00";
            try {
                Date date = sdf.parse(dateInString);
                auction.setCloseDate(date);
            } catch (ParseException ex) {

            }
        }

        // Conseguimos la fecha de hoy en formato yyyy/MM/dd
        Calendar now = new GregorianCalendar();
        Date nowDate = now.getTime();
        auction.setStartDate(nowDate);
        auction.setActive(true);

        // Añadimos las diferentes categorías como una lista
        List<CategoryDTO> categoryList = new ArrayList();
        CategoryDTO cat = new CategoryDTO();
        cat.setId(Long.parseLong(category));
        categoryList.add(cat);
        auction.setCategoryList(categoryList);

        // Creamos/Editamos el objeto y lo insertamos en la base de datos
        auction.setActive(Boolean.TRUE);
        auctionService.createAuction(auction);

        List<AuctionDTO> usersSubmitedAuctions = user.getPurchasedAuction();
        if(usersSubmitedAuctions == null) usersSubmitedAuctions = new ArrayList();
        usersSubmitedAuctions.add(auction);
        user.setPurchasedAuction(usersSubmitedAuctions);

        // Una vez creado e insertado el objeto, nos volvemos a la página de inicio
        return "redirect:/";
    }

}
