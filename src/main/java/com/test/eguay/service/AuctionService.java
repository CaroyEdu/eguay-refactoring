package com.test.eguay.service;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.repository.AuctionRepository;
import com.test.eguay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {
    
    private AuctionRepository auctionRepository;
    private UserRepository userRepository;

    // Query
    public AuctionDTO findById(Long id){
        Auction auction = auctionRepository.find(id);

        return auction.toDTO();
    }

    // Auxiliary functions
    public List<AuctionDTO> filterAuction(String filter){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
        {
            auctions = this.auctionRepository.findAll();
        }
        else
        {
            auctions = this.auctionRepository.findByTitle(filter);
        }
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionByUser(String filter, int userid){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
        {
            auctions = this.auctionRepository.findByTitleAndUser("", userid);
        }
        else
        {
            auctions = this.auctionRepository.findByTitleAndUser(filter, userid);
        }
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionOrederedByUser(int userid){
        List<Auction> auctions ;
        auctions = this.auctionRepository.findOrderedByUser(userid);
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionByActive()
    {
        return toDTO(this.auctionRepository.findByActive());
    }

    // Logic
    public static List<AuctionDTO> toDTO(List<Auction> auctions){
        List<AuctionDTO> dtos = new ArrayList<>(auctions.size());

        for(Auction auction : auctions){
            dtos.add(auction.toDTO());
        }

        return dtos;
    }

    public List<AuctionDTO> getAllAuctions() {
        return Auction.toDTO(auctionRepository.findAll());
    }

    public  Auction toDAO(AuctionDTO auction)
    {
        Auction a = new Auction();
        a.setFotourl(auction.getFotourl());
        a.setStartprice(auction.getStartPrice());
        a.setActive(auction.isActive());
        a.setAuctionid(auction.getId());
        a.setTitle(auction.getName());
        a.setDescription(auction.getDescription());
        a.setClosedate(auction.getCloseDate());
        a.setCloseprice(auction.getClosePrice());
        a.setClosenumberofbids(auction.getCloseNumberofBids());
        User user = this.userRepository.getUserByID(auction.getSellerID());
        a.setSellerid(user);
        a.setStartdate(auction.getStartDate());

        // Añadimos la categoría
        List<Category> categoryList = new ArrayList<>();
        Category category = new Category();
        category.setCategoryid(auction.getCategoryList().get(0).getId());
       /* List<Users> userList = new ArrayList<>();
        userList.add(user);
        category.setUsersList(userList);*/
        List<Auction> auctionList = new ArrayList<>();
        auctionList.add(a);
        category.setAuctionList(auctionList);
        categoryList.add(category);

        a.setCategoryList(categoryList);
        // a.setUsersList(userList);

        return a;
    }

    public void createAuction(AuctionDTO auction)
    {
        auctionRepository.save(toDAO(auction));
    }

    public void editAuction(AuctionDTO auction)
    {
        auctionRepository.save(toDAO(auction));
    }

    public void removeAuction(AuctionDTO auction)
    {
        auctionRepository.delete(toDAO(auction));
    }
}

