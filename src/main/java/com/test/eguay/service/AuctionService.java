package com.test.eguay.service;

import com.test.eguay.entity.Auction;
import com.test.eguay.entity.AuctionCategory;
import com.test.eguay.entity.Category;
import com.test.eguay.entity.User;
import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.repository.AuctionRepository;
import com.test.eguay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuctionService {
    
    private AuctionRepository auctionRepository;
    private UserRepository userRepository;

    // Query
    public AuctionDTO findById(Long id){

        List<Auction> auctionList = auctionRepository.findAll();

        return auctionList.get(0).toDTO();
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
            auctions = this.auctionRepository.findAuctionsByTitle(filter);
        }
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionByUser(String filter, int userid){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
        {
            auctions = this.auctionRepository.findAuctionsByTitleAndUsersBySellerid("", userid);
        }
        else
        {
            auctions = this.auctionRepository.findAuctionsByTitleAndUsersBySellerid(filter, userid);
        }
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionOrederedByUser(int userid){
        List<Auction> auctions ;
        auctions = (List<Auction>) this.auctionRepository.findAuctionsByUsersBySelleridOrderByUsersBySellerid(userid);
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionByActive()
    {
        return toDTO((List<Auction>) this.auctionRepository.findAuctionsByActiveTrue());
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
        a.setClosedate((Timestamp) auction.getCloseDate());
        a.setCloseprice(auction.getClosePrice());
        a.setClosenumberofbids(auction.getCloseNumberofBids());
        a.setSellerid(Math.toIntExact(auction.getSellerID()));
        a.setStartdate((Timestamp) auction.getStartDate());

        // Añadimos la categoría
        List<Category> categoryList = new ArrayList<>();

        Category category = new Category();
        category.setCategoryid(auction.getCategoryList().get(0).getId());

        List<AuctionCategory> auctionList = new ArrayList<>();
        auctionList.add(a.getAuctioncategoriesByAuctionid().get(0));

        category.setAuctioncategoriesByCategoryid(auctionList);
        categoryList.add(category);

        a.setAuctioncategoriesByAuctionid(auctionList);

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

