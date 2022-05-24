package com.test.eguay.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    @EJB UsersFacade usersFacade;
    @EJB CategoryFacade categoryFacade;
    @EJB AuctionFacade auctionFacade;
    @EJB MailService mailService;
    @EJB AuctionService auctionService;
    @EJB CategoryService categoryService;

    // Query

    Users getUser(Integer id) {
        return usersFacade.find(id);
    }

    public List<CategoryDTO> getFavCategories(UserDTO userDTO){
        Users user = this.toDAO(userDTO);
        return Category.toDTO(usersFacade.userFavCategory(user));
    }

    public UserDTO loginUser(String username, String password)
    {
        Users user = this.usersFacade.userLogin(username, password);
        if(user != null)
        {
            return user.toDTO();
        }else{
            return null;
        }
    }

    public List<UserDTO> getAllUsersDTO(){
        return toDTO(getAllUsers());
    }

    public List<Users> getAllUsers(){
        return this.usersFacade.findAll();
    }

    public UserDTO login(String username, String password) {
        Users user = this.usersFacade.userLogin(username, password);
        if(user == null) {
            return null;
        }

        return user.toDTO();
    }

    public List<Users> getUsersInterestedIn(Category category){
        List<Users> userList = this.usersFacade.findAll();

        for(Users user : userList){
            if(isInterestedIn(user, category))
                userList.remove(user);
        }

        return userList;
    }

    // Extra functionalities

    public boolean isInterestedIn(Users user, Category category) {
        return user.getCategoryList().contains(category);
    }

    public void addToGroup(Users user, Groups group){
        user.getGroupsList().add(group);
    }

    public static List<UserDTO> toDTO(List<Users> users){
        List<UserDTO> dtos = new ArrayList<>(users.size());

        for(Users user : users){
            dtos.add(user.toDTO());
        }

        return dtos;
    }

    // Logic

    public void createUser( String username ,String name ,String surname , String address ,
                            String city , String email , String country , String password , Date birthday , int sex){

        Users user = new Users();
        user.setName(name);
        user.setAddress(address);
        user.setSurname(surname);
        user.setCity(city);
        user.setEmail(email);
        user.setCountry(country);
        user.setPassword(password);
        user.setUsername(username);
        user.setBirthyear(birthday);
        user.setSex(sex);
        user.setAuctionList(null);
        user.setAuctionList1(null);
        user.setAuctionList2(null);
        user.setBidList(null);
        user.setCategoryList(null);
        user.setMailList(null);
        user.setMailList1(null);
        user.setRolList(null);

        usersFacade.create(user);
    }

    public void editFavCategories(UserDTO userDTO , CategoryDTO categoryDTO ,String check ){

        Users user = this.toDAO(userDTO);
        Category category = categoryService.toDAO(categoryDTO);
        List<Category> categoryFavList = user.getCategoryList();
        if(categoryFavList == null) categoryFavList = new ArrayList() ;

        if(check != null )
        {
            if(!categoryFavList.contains(category)){
                categoryFavList.add(category);
                category.getUsersList().add(user);
                categoryFacade.edit(category);

                user.setCategoryList(categoryFavList) ;
                usersFacade.edit(user);
            }
        }else{
            if(categoryFavList.contains(category)){
                categoryFavList.remove(category);
                category.getUsersList().remove(user);
                categoryFacade.edit(category);

                user.setCategoryList(categoryFavList) ;
                usersFacade.edit(user);
            }
        }
    }

    public void editFavAuctions(UserDTO user , AuctionDTO auction){

        List<AuctionDTO> auctionFavList = this.filterFavAuctionByUser("", user);


        if(auction != null )
        {
            if(!auctionFavList.contains(auction)){

                Auction auctionDao = auctionService.toDAO(auction);

                List<Users> auctionUserFav = auctionDao.getUsersList();
                if(auctionUserFav == null) auctionUserFav = new ArrayList() ;
                Users userTest = toDAO(user);
                auctionUserFav.add(userTest);
                auctionDao.setUsersList(auctionUserFav);
                auctionFacade.edit(auctionDao);


                auctionFavList.add(auction);
                this.registerUserFavAuction(user, auctionFavList);




            }else {

                Auction auctionDao = auctionFacade.find(auction.getId());

                List<Users> auctionUserFav = auctionDao.getUsersList();
                Users userTest = toDAO(user);
                auctionUserFav.remove(userTest) ;
                auctionDao.setUsersList(auctionUserFav);
                auctionFacade.edit(auctionDao);

                auctionFavList.remove(auction);
                this.registerUserFavAuction(user,auctionFavList);



            }
        }
    }

    public void removePurchasedAuction(AuctionDTO auctionDTO , UserDTO userDTO){

        Users user = this.toDAO(userDTO);
        Auction auction = auctionFacade.find(auctionDTO.getId());

        List<Auction> userPurchased = user.getAuctionList1();
        userPurchased.remove(auction);
        user.setAuctionList1(userPurchased);

        List <Users> auctions = auction.getUsersList1();
        auctions.remove(user);
        auction.setUsersList1(auctions);

        usersFacade.edit(user);
        auctionFacade.edit(auction);
    }

    public void finilizeBuyingAuction(UserDTO userDTO , AuctionDTO auction){

        List<Users> clientList = new ArrayList();
        Users user = this.toDAO(userDTO);
        clientList.add(0, user);
        Auction auctionDao = auctionService.toDAO(auction);
        auctionDao.setUsersList1(clientList);
        auctionDao.setActive(Boolean.FALSE);

        auctionFacade.edit(auctionDao);

        List<Auction> purchasedAuction = user.getAuctionList1() ;
        if(purchasedAuction == null) purchasedAuction = new ArrayList() ;
        purchasedAuction.add(auctionDao);
        user.setAuctionList1(purchasedAuction);

        usersFacade.edit(user);

        //    mailService.sendMailToAuctionWinner(String.format("Has ganado la subasta %s", auction.getTitle()), auction.getAuctionid(), user.getUserid());
    }

    public List<AuctionDTO> filterPurchasedAuctionByUser(String filter, UserDTO user){
        List<Auction> auctions ;
        Users userid = this.toDAO(user);

        if(filter == null || filter.isEmpty())
        {
            auctions = this.usersFacade.findPurchasedAuctionsByTitleAndUser("", userid);
        }
        else
        {
            auctions = this.usersFacade.findPurchasedAuctionsByTitleAndUser(filter, userid);
        }
        return Auction.toDTO(auctions);
    }

    public List<AuctionDTO> filterFavAuctionByUser(String filter, UserDTO userid){
        List<Auction> auctions ;
        if(filter == null || filter.isEmpty())
        {
            auctions = this.usersFacade.findFavAuctionsByTitleAndUser("", toDAO(userid));
        }
        else
        {
            auctions = this.usersFacade.findFavAuctionsByTitleAndUser(filter, toDAO(userid));
        }
        return AuctionService.toDTO(auctions);
    }



    public List<UserDTO> getUsersDTO(List<Integer> userIds) {
        return toDTO(getUsersByIds(userIds));
    }

    public List<Users> getUsersByIds(List<Integer> userIds) {
        return usersFacade.findAll(userIds);
    }

    public UserDTO getSessionUser(HttpSession session) {
        return (UserDTO) session.getAttribute("user");
    }

    public Users toDAO(UserDTO user)
    {

        Users u = new Users();
        u = this.usersFacade.getUserByID(Long.parseLong(user.getId().toString()));
        return u;

    }

    private void registerUserFavAuction(UserDTO user , List<AuctionDTO> auctions){
        Users userDao = usersFacade.find(user.getId()) ;
        List<Auction> favAuctions = new ArrayList() ;
        for(AuctionDTO a : auctions){
            favAuctions.add(auctionService.toDAO(a));
        }
        userDao.setAuctionList(favAuctions);
        this.usersFacade.edit(userDao);
    }

    public void editUser(UserDTO user)
    {
        this.usersFacade.edit(toDAO(user));
    }

    public void AddAuctionToOwner(UserDTO userDto, AuctionDTO auctionDto) {

        Users user = this.toDAO(userDto);
        Auction auction = auctionService.toDAO(auctionDto);

        List<Auction> usersSubmitedAuctions = user.getAuctionList2();
        if(usersSubmitedAuctions == null) usersSubmitedAuctions = new ArrayList();
        usersSubmitedAuctions.add(auction);
        user.setAuctionList2(usersSubmitedAuctions);

        usersFacade.edit(user);
    }

    public UserDTO getUserById(Long id)
    {
        Users user = this.usersFacade.find(id);
        return user.toDTO();
    }
}

