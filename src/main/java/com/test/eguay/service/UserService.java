package com.test.eguay.service;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.Auction;
import com.test.eguay.entity.Category;
import com.test.eguay.entity.Group;
import com.test.eguay.entity.User;
import com.test.eguay.repository.AuctionRepository;
import com.test.eguay.repository.CategoryRepository;
import com.test.eguay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    UserRepository userRepository;
    CategoryRepository categoryRepository;
    AuctionRepository auctionRepository;
   // MailService mailService;
    AuctionService auctionService;
    CategoryService categoryService;

    // Query



//    public List<CategoryDTO> getFavCategories(UserDTO userDTO){
//        User user = this.toDAO(userDTO);
//        return Category.toDTO(userRepository.userFavCategory(user));
//    }

    public UserDTO loginUser(String username, String password)
    {
        User user = this.userRepository.findUserByusernameAndPassword(username, password);
        if(user != null)
        {
            return user.toDTO();
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
//
//    public void editFavCategories(UserDTO userDTO , CategoryDTO categoryDTO ,String check ){
//
//        User user = this.toDAO(userDTO);
//        Category category = categoryService.toDAO(categoryDTO);
//        List<Category> categoryFavList = user.getCategoryList();
//        if(categoryFavList == null) categoryFavList = new ArrayList() ;
//
//        if(check != null )
//        {
//            if(!categoryFavList.contains(category)){
//                categoryFavList.add(category);
//                category.getUsersList().add(user);
//                categoryRepository.save(category);
//
//                user.setCategoryList(categoryFavList) ;
//                userRepository.save(user);
//            }
//        }else{
//            if(categoryFavList.contains(category)){
//                categoryFavList.remove(category);
//                category.getUsersList().remove(user);
//                categoryRepository.save(category);
//
//                user.setCategoryList(categoryFavList) ;
//                userRepository.save(user);
//            }
//        }
//    }
//
//    public void editFavAuctions(UserDTO user , AuctionDTO auction){
//
//        List<AuctionDTO> auctionFavList = this.filterFavAuctionByUser("", user);
//
//
//        if(auction != null )
//        {
//            if(!auctionFavList.contains(auction)){
//
//                Auction auctionDao = auctionService.toDAO(auction);
//
//                List<User> auctionUserFav = auctionDao.getUsersList();
//                if(auctionUserFav == null) auctionUserFav = new ArrayList() ;
//                User userTest = toDAO(user);
//                auctionUserFav.add(userTest);
//                auctionDao.setUsersList(auctionUserFav);
//                auctionRepository.save(auctionDao);
//
//
//                auctionFavList.add(auction);
//                this.registerUserFavAuction(user, auctionFavList);
//
//
//
//
//            }else {
//
//                Auction auctionDao = auctionRepository.find(auction.getId());
//
//                List<User> auctionUserFav = auctionDao.getUsersList();
//                User userTest = toDAO(user);
//                auctionUserFav.remove(userTest) ;
//                auctionDao.setUsersList(auctionUserFav);
//                auctionRepository.save(auctionDao);
//
//                auctionFavList.remove(auction);
//                this.registerUserFavAuction(user,auctionFavList);
//
//
//
//            }
//        }
//    }
//
//    public void removePurchasedAuction(AuctionDTO auctionDTO , UserDTO userDTO){
//
//        User user = this.toDAO(userDTO);
//        Auction auction = auctionRepository.find(auctionDTO.getId());
//
//        List<Auction> userPurchased = user.getAuctionList1();
//        userPurchased.remove(auction);
//        user.setAuctionList1(userPurchased);
//
//        List <User> auctions = auction.getUsersList1();
//        auctions.remove(user);
//        auction.setUsersList1(auctions);
//
//        userRepository.save(user);
//        auctionRepository.save(auction);
//    }
//
//    public void finilizeBuyingAuction(UserDTO userDTO , AuctionDTO auction){
//
//        List<User> clientList = new ArrayList();
//        User user = this.toDAO(userDTO);
//        clientList.add(0, user);
//        Auction auctionDao = auctionService.toDAO(auction);
//        auctionDao.setUsersList1(clientList);
//        auctionDao.setActive(Boolean.FALSE);
//
//        auctionRepository.edit(auctionDao);
//
//        List<Auction> purchasedAuction = user.getAuctionList1() ;
//        if(purchasedAuction == null) purchasedAuction = new ArrayList() ;
//        purchasedAuction.add(auctionDao);
//        user.setAuctionList1(purchasedAuction);
//
//        userRepository.save(user);
//
//        //    mailService.sendMailToAuctionWinner(String.format("Has ganado la subasta %s", auction.getTitle()), auction.getAuctionid(), user.getUserid());
//    }
//
//    public List<AuctionDTO> filterPurchasedAuctionByUser(String filter, UserDTO user){
//        List<Auction> auctions ;
//        User userid = this.toDAO(user);
//
//        if(filter == null || filter.isEmpty())
//        {
//            auctions = this.userRepository.findPurchasedAuctionsByTitleAndUser("", userid);
//        }
//        else
//        {
//            auctions = this.userRepository.findPurchasedAuctionsByTitleAndUser(filter, userid);
//        }
//        return Auction.toDTO(auctions);
//    }
//
//    public List<AuctionDTO> filterFavAuctionByUser(String filter, UserDTO userid){
//        List<Auction> auctions ;
//        if(filter == null || filter.isEmpty())
//        {
//            auctions = this.userRepository.findFavAuctionsByTitleAndUser("", toDAO(userid));
//        }
//        else
//        {
//            auctions = this.userRepository.findFavAuctionsByTitleAndUser(filter, toDAO(userid));
//        }
//        return AuctionService.toDTO(auctions);
//    }
//
//
//
//    public List<UserDTO> getUsersDTO(List<Integer> userIds) {
//        return toDTO(getUsersByIds(userIds));
//    }
//
//    public List<User> getUsersByIds(List<Integer> userIds) {
//        return userRepository.findAll(userIds);
//    }
//
//    public UserDTO getSessionUser(HttpSession session) {
//        return (UserDTO) session.getAttribute("user");
//    }
//
//    public User toDAO(UserDTO user)
//    {
//
//        User u = new User();
//        u = this.userRepository.getUserByID(Long.parseLong(user.getId().toString()));
//        return u;
//
//    }
//
//    private void registerUserFavAuction(UserDTO user , List<AuctionDTO> auctions){
//        User userDao = userRepository.find(user.getId()) ;
//        List<Auction> favAuctions = new ArrayList() ;
//        for(AuctionDTO a : auctions){
//            favAuctions.add(auctionService.toDAO(a));
//        }
//        userDao.setAuctionList(favAuctions);
//        this.userRepository.save(userDao);
//    }
//
//    public void editUser(UserDTO user)
//    {
//        this.userRepository.save(toDAO(user));
//    }
//
//    public void AddAuctionToOwner(UserDTO userDto, AuctionDTO auctionDto) {
//
//        User user = this.toDAO(userDto);
//        Auction auction = auctionService.toDAO(auctionDto);
//
//        List<Auction> usersSubmitedAuctions = user.getAuctionList2();
//        if(usersSubmitedAuctions == null) usersSubmitedAuctions = new ArrayList();
//        usersSubmitedAuctions.add(auction);
//        user.setAuctionList2(usersSubmitedAuctions);
//
//        userRepository.edit(user);
//    }
//
//    public UserDTO getUserById(Long id)
//    {
//        User user = this.userRepository.find(id);
//        return user.toDTO();
//    }
}

