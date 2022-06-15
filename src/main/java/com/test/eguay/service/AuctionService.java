// Autores: Pedro Antonio Benito Rojano 5%

package com.test.eguay.service;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.BidDTO;
import com.test.eguay.entity.*;
import com.test.eguay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionService {
    
    private AuctionRepository auctionRepository;
    private UserRepository userRepository;
    private AuctionCategoryRepository auctionCategoryRepository;
    private BidRepository bidRepository;
    private CategoryRepository categoryRepository;
    private PurchasedAuctionRepository purchasedAuctionRepository;

    public FavAuctionRepository getFavAuctionRepository() {
        return favAuctionRepository;
    }

    @Autowired
    public void setFavAuctionRepository(FavAuctionRepository favAuctionRepository) {
        this.favAuctionRepository = favAuctionRepository;
    }

    private FavAuctionRepository favAuctionRepository;


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
    public List<AuctionDTO> getAll(){
        return this.auctionRepository.findAll().stream().map(auction -> auction.toDTO()).collect(Collectors.toList());
    }

    public AuctionDTO findById(Long id){

        Auction auction = auctionRepository.findAuctionByAuctionid(id);

        return auction.toDtoLinked();
    }

    // Auxiliary functions
    public List<AuctionDTO> filterAuction(String filter){
        List<Auction> auctions = new ArrayList<>();
        if(filter!=null && filter.length()>0)
        {
            auctions = this.auctionRepository.findBySimilarTitle(filter);
        }
        else
        {
            auctions = this.auctionRepository.findAll();
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
        if(auctions == null || auctions.isEmpty())
            return null;
        List<AuctionDTO> dtos = new ArrayList<>(auctions.size());

        for(Auction auction : auctions){
            try {
                AuctionDTO auctionDTO = auction.toDtoLinked();
                dtos.add(auctionDTO);
            }catch (Exception e) {
                System.out.println(String.format("La subasta %s no está bien en la base de datos.", auction.getTitle()));
            }
        }

        return dtos;
    }

    public List<AuctionDTO> getAllAuctions() {
        return Auction.toDtoLinked(auctionRepository.findAll());
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

    public void editAuctionForm(AuctionDTO auctionDTO){
        Auction auction = this.auctionRepository.findAuctionByAuctionid(auctionDTO.getId());
        Category category = this.categoryRepository.findById(auctionDTO.getAuctionCategory()).orElse(null);
        auction.setStartprice(auctionDTO.getStartPrice());
        auction.setFotourl(auctionDTO.getFotourl());
        auction.setTitle(auctionDTO.getName());
        auction.setDescription(auctionDTO.getDescription());

        //nueva auctionCategory
        AuctionCategory auctionCategory = new AuctionCategory();

        auctionCategory.setAuctionid(auction.getAuctionid());
        auctionCategory.setCategoryid(category.getCategoryid());
        auctionCategory.setAuctionByAuctionid(auction);
        auctionCategory.setCategoryByCategoryid(category);


        this.auctionCategoryRepository.save(auctionCategory);
        //-------------------
        //borramos todos los auctionCategories anteriores
        List<AuctionCategory> anteriorCategories =  this.auctionCategoryRepository.findAuctionCategoryByAuctionByAuctionid(auction);
        for(AuctionCategory a : anteriorCategories){
            this.auctionCategoryRepository.delete(a);
        }
        //-------------------
        List<AuctionCategory> nuevaAuctionCategry = new ArrayList<>();
        nuevaAuctionCategry.add(auctionCategory);
       // auction.setAuctioncategoriesByAuctionid(nuevaAuctionCategry);

        this.auctionRepository.save(auction);
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
        if(highestBids != null && !highestBids.isEmpty()){
            bid = highestBids.get(0);
            returnBid = bid.toDTO();
        }
        return returnBid;
    }

    public void editAuction(AuctionDTO auction)
    {
        auctionRepository.save(toDAO(auction));
    }

    public void editAdminAcution(AuctionDTO auction) {
        Auction a = auctionRepository.getById(auction.getId());
        a.setFotourl(auction.getFotourl());
        a.setStartprice(auction.getStartPrice());
        a.setActive(auction.isActive());
        a.setAuctionid(auction.getId());
        a.setTitle(auction.getName());
        a.setDescription(auction.getDescription());
        a.setCloseprice(auction.getClosePrice());
        a.setClosenumberofbids(auction.getCloseNumberofBids());


        // Añadimos la categoría
        List<AuctionCategory> auctionList = new ArrayList<>();
        AuctionCategory auctionCategory = new AuctionCategory();
        Category category = new Category();

        category.setCategoryid(auction.getAuctionCategory());
        auctionCategory.setCategoryid(auction.getAuctionCategory());
        auctionCategory.setCategoryByCategoryid(category);

        auctionList.add(auctionCategory);
        a.setAuctioncategoriesByAuctionid(auctionList);
        auctionCategory.setAuctionByAuctionid(a);

        auctionRepository.save(a);
    }

    public void removeAuction(AuctionDTO auction)
    {
        Auction auction1 = this.auctionRepository.findAuctionByAuctionid(auction.getId());
        List<FavoriteAuction> favs =  this.favAuctionRepository.findFavoriteAuctionByAuctionByAuctionid(auction1) ;
        if(favs!= null){
            for(FavoriteAuction fav : favs){
                this.favAuctionRepository.delete(fav);
            }
        }

        List<PurchasedAuction> purchaseds =  this.purchasedAuctionRepository.findPurchasedAuctionByAuctionByAuctionid(auction1);
        if(favs!= null){
            for(PurchasedAuction purs : purchaseds){
                this.purchasedAuctionRepository.delete(purs);
            }
        }

        List<Bid> bids = this.bidRepository.findBidByAuctionByAuctionid(auction1);
        if(bids!= null){
            for(Bid bid : bids){
                this.bidRepository.delete(bid);
            }
        }

        auctionRepository.delete(toDAO(auction));
    }

    public BidRepository getBidRepository() {
        return bidRepository;
    }

    @Autowired
    public void setBidRepository(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    @Scheduled(initialDelay = 1000, fixedDelay=10200)
    public void cacheRefresh() {
        Date now = new Date();
        List<Auction> activeAuctions = this.auctionRepository.findAllByActiveIsTrue();
        for(Auction a : activeAuctions)
        {
            if(a.getClosedate() != null){
                if(now.compareTo(a.getClosedate()) >= 0)
                {
                    List<Bid> bidList = this.bidRepository.findHighestBid(a);
                    if(bidList.size()>0)
                    {
                        Bid higherBid = bidList.get(0);
                        User user = higherBid.getUsersByBiderid();

                        PurchasedAuction purchasedAuction = new PurchasedAuction();
                        purchasedAuction.setUsersByUserid(user);
                        purchasedAuction.setAuctionByAuctionid(a);
                        purchasedAuction.setUserid(Long.valueOf(user.getUserid()));
                        purchasedAuction.setAuctionid(a.getAuctionid());

                        this.purchasedAuctionRepository.save(purchasedAuction);

                        a.setActive(Boolean.FALSE);

                        this.auctionRepository.save(a);

                        System.out.println("La subasta " + a.getAuctionid() + " con titulo:  " + a.getTitle() + " ha sido ganada por " + user.getName() );
                    }else{
                        a.setActive(Boolean.FALSE);
                        auctionRepository.save(a);
                        System.out.println("La subasta " + a.getAuctionid() + " con titulo:  " + a.getTitle() + " ha sido cerrada sin ganador");
                    }
                }
            }
        }

    }


    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public PurchasedAuctionRepository getPurchasedAuctionRepository() {
        return purchasedAuctionRepository;
    }

    @Autowired
    public void setPurchasedAuctionRepository(PurchasedAuctionRepository purchasedAuctionRepository) {
        this.purchasedAuctionRepository = purchasedAuctionRepository;
    }

    public void deleteById(Long id) {
        auctionRepository.deleteById(id);
    }

    public List<AuctionDTO> getAllAuctionsFilter(String filter) {
        return Auction.toDtoLinked(auctionRepository.findByTitleContainingIgnoreCase(filter));
    }
}

