package com.test.eguay.service;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.*;
import com.test.eguay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserCategoryRepository userCategoryRepository;

    public BidRepository getBidRepository() {
        return bidRepository;
    }
    @Autowired
    public void setBidRepository(BidRepository bidRepository) {
        this.bidRepository = bidRepository;
    }

    private BidRepository bidRepository;

    public PurchasedAuctionRepository getPurchasedAuctionRepository() {
        return purchasedAuctionRepository;
    }
    @Autowired
    public void setPurchasedAuctionRepository(PurchasedAuctionRepository purchasedAuctionRepository) {
        this.purchasedAuctionRepository = purchasedAuctionRepository;
    }

    private PurchasedAuctionRepository purchasedAuctionRepository;

    public FavAuctionRepository getFavAuctionRepository() {
        return favAuctionRepository;
    }

    @Autowired
    public void setFavAuctionRepository(FavAuctionRepository favAuctionRepository) {
        this.favAuctionRepository = favAuctionRepository;
    }

    private FavAuctionRepository favAuctionRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    CategoryRepository categoryRepository;

    public AuctionRepository getAuctionRepository() {
        return auctionRepository;
    }
    @Autowired
    public void setAuctionRepository(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    AuctionRepository auctionRepository;
   // MailService mailService;
    private AuctionService auctionService;
    CategoryService categoryService;

    public AuctionService getAuctionService() {
        return auctionService;
    }

    @Autowired
    public void setAuctionService(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    public UserDTO loginUser(String username, String password)
    {
        User user = this.userRepository.findUserByusernameAndPassword(username, password);
        if(user != null)
        {
            return user.toDtoLinked();
        }else{
            return null;
        }
    }
//
//    public List<UserDTO> getAllUsersDTO(){
//        return toDTO(getAllUsers());
//    }
//
//    public List<User> getAllUsers(){
//        return this.userRepository.findAll();
//    }
//

//
//    public List<User> getUsersInterestedIn(Category category){
//        List<User> userList = this.userRepository.findAll();
//
//        for(User user : userList){
//            if(isInterestedIn(user, category))
//                userList.remove(user);
//        }
//
//        return userList;
//    }
//
//    // Extra functionalities
//
//    public boolean isInterestedIn(User user, Category category) {
//        return user.getCategoryList().contains(category);
//    }
//
//    public void addToGroup(User user, Group group){
//        user.getGroupsList().add(group);
//    }
//
//    public static List<UserDTO> toDTO(List<User> users){
//        List<UserDTO> dtos = new ArrayList<>(users.size());
//
//        for(User user : users){
//            dtos.add(user.toDTO());
//        }
//
//        return dtos;
//    }
//
//    // Logic
//
    public void createUser(String username , String name , String surname , String address ,
                           String city , String email , String country , String password , Date birthday , int sex){

        User user = new User();
        user.setName(name);
        user.setAddress(address);
        user.setSurname(surname);
        user.setCity(city);
        user.setEmail(email);
        user.setCountry(country);
        user.setPassword(password);
        user.setUsername(username);
        java.sql.Date sqlDate = new java.sql.Date(birthday.getTime());
        user.setBirthyear(sqlDate);
        user.setSex(sex);
        user.setAuctionsByUserid(null);
        user.setBidsByUserid(null);
        user.setFavoriteauctionsByUserid(null);
        user.setPurchasedauctionsByUserid(null);
        user.setUserscategoriesByUserid(null);
        user.setUsersgroupsByUserid(null);
        user.setMailByUserid(null);
        user.setUsersrolsByUserid(null);

        userRepository.save(user);
    }

    public List<AuctionDTO> showFavAuctions(UserDTO userDTO){
        User user = this.userRepository.findById(userDTO.getId()).orElse(null);
        List<Auction> favoriteAuctions = this.userRepository.findFavAuctions(user);
        return Auction.toDtoLinked(favoriteAuctions) ;
    }

    public List<AuctionDTO> filterFavAuctions(UserDTO userDTO , String filter){
        User user = this.userRepository.findById(userDTO.getId()).orElse(null);
        if (filter == null) filter = "";
        filter = "%" + filter + "%" ;
        List<Auction> favoriteAuctions = this.userRepository.filterFavAuctions(user,filter.toLowerCase(Locale.ROOT));
        return Auction.toDtoLinked(favoriteAuctions) ;
    }

    public List<AuctionDTO> showPurchasedAuctions(UserDTO userDTO){
        User user = this.userRepository.findById(userDTO.getId()).orElse(null);
        List<Auction> purchasedAuctions = this.userRepository.findPurchasedAuctions(user);
        return Auction.toDtoLinked(purchasedAuctions) ;
    }

    public List<AuctionDTO> filterPurchasedAuctions(UserDTO userDTO , String filter){
        User user = this.userRepository.findById(userDTO.getId()).orElse(null);
        if (filter == null) filter = "";
        filter = "%" + filter + "%" ;
        List<Auction> purchasedAuctions = this.userRepository.filterPurchasedAuctions(user,filter.toLowerCase(Locale.ROOT));
        return Auction.toDtoLinked(purchasedAuctions) ;
    }

    public List<CategoryDTO> showFavCategories(UserDTO userDTO){
        User user = this.userRepository.findById(userDTO.getId()).orElse(null);
        List<Category> favCategories = this.userRepository.findFavCategories(user);
        return Category.toDTO(favCategories) ;
    }

    public void addFavAuction(UserDTO userDTO , AuctionDTO auctionDTO){
        User user = this.userRepository.findUserByUserid(userDTO.getId());
        Auction auction = this.auctionRepository.findAuctionByAuctionid(auctionDTO.getId());

        FavoriteAuction newFavoriteAuction = new FavoriteAuction();

        newFavoriteAuction.setAuctionByAuctionid(auction);
        newFavoriteAuction.setAuctionid(auction.getAuctionid());
        newFavoriteAuction.setUsersByUserid(user);
        newFavoriteAuction.setUserid(Long.valueOf(user.getUserid()));

        this.favAuctionRepository.save(newFavoriteAuction);
    }

    public void deleteFavAuction (UserDTO userDTO , AuctionDTO auctionDTO){
        User user = this.userRepository.findUserByUserid(userDTO.getId());
        Auction auction = this.auctionRepository.findAuctionByAuctionid(auctionDTO.getId());

        List<FavoriteAuction> favs =  this.favAuctionRepository.FindFavoriteAuctionOfUser(auction,user);
        for(FavoriteAuction fav : favs){
            this.favAuctionRepository.delete(fav);
        }
    }

    public void purchaseAuction (UserDTO userDTO, AuctionDTO auctionDTO){
        User user = this.userRepository.findUserByUserid(userDTO.getId());
        Auction auction = this.auctionRepository.findAuctionByAuctionid(auctionDTO.getId());

        PurchasedAuction purchasedAuction = new PurchasedAuction();
        purchasedAuction.setUsersByUserid(user);
        purchasedAuction.setAuctionByAuctionid(auction);
        purchasedAuction.setUserid(Long.valueOf(user.getUserid()));
        purchasedAuction.setAuctionid(auction.getAuctionid());

        this.purchasedAuctionRepository.save(purchasedAuction);

        auction.setActive(Boolean.FALSE);

        this.auctionRepository.save(auction);

    }

    public void purchaseAuction (User user, Auction auction){

        PurchasedAuction purchasedAuction = new PurchasedAuction();
        purchasedAuction.setUsersByUserid(user);
        purchasedAuction.setAuctionByAuctionid(auction);
        purchasedAuction.setUserid(Long.valueOf(user.getUserid()));
        purchasedAuction.setAuctionid(auction.getAuctionid());

        this.purchasedAuctionRepository.save(purchasedAuction);

        auction.setActive(Boolean.FALSE);

        this.auctionRepository.save(auction);

    }

    public void deletepurchasedAuction ( UserDTO userDTO , AuctionDTO auctionDTO){
        User user = this.userRepository.findUserByUserid(userDTO.getId());
        Auction auction = this.auctionRepository.findAuctionByAuctionid(auctionDTO.getId());

        List<PurchasedAuction> purchasedAuctionList =  this.purchasedAuctionRepository.FindPurchasedAuctionOfUser(auction,user);

        for(PurchasedAuction purchasedAuction : purchasedAuctionList){
            this.purchasedAuctionRepository.delete(purchasedAuction);
        }
    }

    public void submitBid(UserDTO userDTO , AuctionDTO auctionDTO , Double amount){
        User user = this.userRepository.findUserByUserid(userDTO.getId());
        Auction auction = this.auctionRepository.findAuctionByAuctionid(auctionDTO.getId());
        Bid bid = new Bid();
        bid.setBid(amount);
        bid.setAuctionid(auctionDTO.getId());
        bid.setUsersByBiderid(user);
        bid.setBiderid(Long.valueOf(user.getUserid()));
        bid.setAuctionByAuctionid(auction);

        this.bidRepository.save(bid);
    }

    public void addFavCategories(UserDTO userDTO , List<String>categoryIds){
        User user = this.userRepository.findUserByUserid(userDTO.getId());
        List<UserCategory> existentCategories = this.userCategoryRepository.findAllByUsersByUserid(user);

        if(existentCategories != null || !existentCategories.isEmpty()){
            for(UserCategory userCategory : existentCategories ){
                this.userCategoryRepository.delete(userCategory);
            }
        }

        for(String categoryID : categoryIds){
            Long catId = Long.valueOf(categoryID);
            List<UserCategory> categoryList = this.userCategoryRepository.findAllFavUserCategories(catId ,user);
            if(categoryList.isEmpty()){
                this.createFavCategory(userDTO , catId);
            }
        }
    }

    public void createFavCategory(UserDTO userDTO , Long catID){
        UserCategory userCategory = new UserCategory();
        User user = this.userRepository.findUserByUserid(userDTO.getId());
       Category category = this.categoryRepository.findById(catID).orElse(null);

        userCategory.setCategoryByCategoryid(category);
        userCategory.setCategoryid(catID);
        userCategory.setUserid(Long.valueOf(user.getUserid()));
        userCategory.setUsersByUserid(user);

        this.userCategoryRepository.save(userCategory);
    }

//
    public User toDAO(UserDTO user)
    {

        User u = this.userRepository.findUserByUserid(user.getId());
        u.setAuctionsByUserid(this.auctionService.listToDAO(user.getUserAuctions()));
        return u;

    }

    public void editUser(UserDTO user)
    {
        this.userRepository.save(toDAO(user));
    }

    public void editUserAdmin(UserDTO u)
    {
        User user = userRepository.findById(u.getId()).get();
        user.setName(u.getName());
        user.setAddress(u.getAddress());
        user.setSurname(u.getSurname());
        user.setCity(u.getCity());
        user.setEmail(u.getEmail());
        user.setCountry(u.getCountry());
        user.setPassword(u.getPassword());
        user.setUsername(u.getUsername());
        this.userRepository.save(user);
    }

    public UserCategoryRepository getUserCategoryRepository() {
        return userCategoryRepository;
    }
    @Autowired
    public void setUserCategoryRepository(UserCategoryRepository userCategoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
    }

    public List<UserDTO> getAll() {
        return this.userRepository.findAll().stream().map(user -> user.toDto()).collect(Collectors.toList());
    }

    public UserDTO findById(Integer id) {
        return this.userRepository.findById(id).get().toDto();
    }
}

