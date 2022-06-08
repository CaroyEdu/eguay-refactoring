package com.test.eguay.service;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.BidDTO;
import com.test.eguay.entity.*;
import com.test.eguay.repository.AuctionCategoryRepository;
import com.test.eguay.repository.AuctionRepository;
import com.test.eguay.repository.BidRepository;
import com.test.eguay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionService {
    
    private AuctionRepository auctionRepository;
    private UserRepository userRepository;
    private AuctionCategoryRepository auctionCategoryRepository;
    private BidRepository bidRepository;

    public AuctionCategoryRepository getAuctionCategoryRepository() {
        return auctionCategoryRepository;
    }

    @Autowired
    public void setAuctionCategoryRepository(AuctionCategoryRepository auctionCategoryRepository) {
        this.auctionCategoryRepository = auctionCategoryRepository;
    }

    public AuctionRepository getAuctionRepository() {
        return auctionRepository;
    }

    @Autowired
    public void setAuctionRepository(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Query
    public AuctionDTO findById(Long id){

        Auction auction = auctionRepository.findAuctionByAuctionid(id);

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
            auctions = this.auctionRepository.findAuctionsByTitle(filter);
        }
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionByUser(String filter, int userid){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
        {
            auctions = this.auctionRepository.findAuctionByTitleAndSellerid("", userid);
        }
        else
        {
            auctions = this.auctionRepository.findAuctionByTitleAndSellerid(filter, userid);
        }
        return AuctionService.toDTO(auctions);
    }

    public List<AuctionDTO> filterAuctionOrderedByUser(int userid){
        List<Auction> auctions ;
        auctions = (List<Auction>) this.auctionRepository.findAuctionsBySellerid(userid);
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

    private Timestamp getTimestamp(java.util.Date date){
        return date == null ? null : new java.sql.Timestamp(date.getTime());
    }

    public List<Auction> listToDAO(List<AuctionDTO> auctionList)
    {
        List<Auction> list = new ArrayList<>();
        for(AuctionDTO a : auctionList)
        {
            list.add(toDAO(a));
        }
        return list;
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
        a.setClosedate(this.getTimestamp(auction.getCloseDate()));
        a.setCloseprice(auction.getClosePrice());
        a.setClosenumberofbids(auction.getCloseNumberofBids());
        a.setSellerid(Math.toIntExact(auction.getSellerID()));
        a.setStartdate(this.getTimestamp(auction.getStartDate()));

        // Añadimos la categoría
        List<AuctionCategory> auctionList = new ArrayList<>();
        AuctionCategory auctionCategory = new AuctionCategory();
        Category category = new Category();

        category.setCategoryid(auction.getCategoryList().get(0).getId());
        auctionCategory.setCategoryid(auction.getCategoryList().get(0).getId());
        auctionCategory.setCategoryByCategoryid(category);

        auctionList.add(auctionCategory);

        a.setAuctioncategoriesByAuctionid(auctionList);
        auctionCategory.setAuctionByAuctionid(a);

        return a;
    }

    public void createAuction(AuctionDTO auction)
    {
        Auction a = auctionRepository.save(toDAO(auction));

        //Añadimos una nueva entrada en la tabla AuctionCategory
        AuctionCategory auctionCategory = new AuctionCategory();
        List<AuctionCategory> auctionList = new ArrayList<>();
        Category category = new Category();

        category.setCategoryid(a.getAuctioncategoriesByAuctionid().get(0).getCategoryid());
        auctionCategory.setCategoryid(auction.getCategoryList().get(0).getId());
        auctionCategory.setAuctionid(a.getAuctionid());
        auctionList.add(auctionCategory);
        auctionCategory.setAuctionByAuctionid(a);
        category.setAuctioncategoriesByCategoryid(auctionList);
        auctionCategory.setCategoryByCategoryid(category);

        this.auctionCategoryRepository.save(auctionCategory);
    }

    public BidDTO getHighestBid(AuctionDTO auctionDTO ) {
        Auction auction = this.auctionRepository.findAuctionByAuctionid(auctionDTO.getId());
        List<Bid> highestBids = this.bidRepository.findHighestBid(auction);
        Bid bid = null;
        BidDTO returnBid = null ;
        if(highestBids != null){
            bid = highestBids.get(0);
            returnBid = bid.toDTO();
        }
        return returnBid;
    }

    public void editAuction(AuctionDTO auction)
    {
        auctionRepository.save(toDAO(auction));
    }

    public void removeAuction(AuctionDTO auction)
    {
        auctionRepository.delete(toDAO(auction));
    }

    public BidRepository getBidRepository() {
        return bidRepository;
    }

    @Autowired
    public void setBidRepository(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }


}

