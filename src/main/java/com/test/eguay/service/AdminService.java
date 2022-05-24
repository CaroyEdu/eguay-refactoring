package com.test.eguay.service;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.CategoryDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.Auction;
import com.test.eguay.entity.Category;
import com.test.eguay.entity.Rol;
import com.test.eguay.entity.User;
import com.test.eguay.repository.AuctionRepository;
import com.test.eguay.repository.CategoryRepository;
import com.test.eguay.repository.RolRepository;
import com.test.eguay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private UserRepository userRepository;
    private RolRepository rolRepository;
    private AuctionRepository auctionRepository;
    private CategoryRepository categoryRepository;

    public List<UserDTO> getAllUsers(){
        return User.toDTO(userRepository.getAllOrdered());
    }

    public List<UserDTO> filterUsers(String username) {
        return User.toDTO(userRepository.filter(username));
    }

    public void createUser(String username, String name, String surname,
                           String address, String city, String email, String country,
                           String password, Date birthday, int sex, List<Integer> roleIds){
        List<Rol> roles = roleIds.stream()
                .map((rId) -> rolRepository.find(rId))
                .collect(Collectors.toList());

        User user = new User();
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
        user.setRolList(roles);

        userRepository.create(user);
    }

    public UserDTO getUser(int id) {
        return userRepository.find(id).toDTO();
    }

    public void saveUser(UserDTO user) {
        List<Rol> roles = user.getRoleIds().stream()
                .map((rId) -> rolRepository.find(rId))
                .collect(Collectors.toList());
        User u = userRepository.find(user.getId());
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAddress(user.getAddress());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
        u.setCountry(user.getCountry());
        u.setPassword(user.getPassword());
        u.setBirthyear(user.getBirthyear());
        u.setSex(user.getSex());
        u.setRolList(roles);
        userRepository.edit(u);
    }

    public void deleteUser(Integer id) {
        User u = userRepository.find(id);
        userRepository.remove(u);
    }

    public void createUser(UserDTO user) {
        List<Rol> roles = user.getRoleIds().stream()
                .map((rId) -> rolRepository.find(rId))
                .collect(Collectors.toList());
        User u = new User();
        u.setUsername(user.getUsername());
        u.setName(user.getName());
        u.setSurname(user.getSurname());
        u.setAddress(user.getAddress());
        u.setCity(user.getCity());
        u.setEmail(user.getEmail());
        u.setCountry(user.getCountry());
        u.setPassword(user.getPassword());
        u.setBirthyear(user.getBirthyear());
        u.setSex(user.getSex());
        u.setRolList(roles);
        userRepository.create(u);
    }

    public List<AuctionDTO> filterProducts(String product) {
        return Auction.toDTO(auctionRepository.filter(product));
    }

    public List<AuctionDTO> getAllProducts() {
        return Auction.toDTO(auctionRepository.getAllOrdered());
    }

    public AuctionDTO getProduct(long id) {
        return auctionRepository.find(id).toDTO();
    }

    public void saveProduct(AuctionDTO auction) {
        Auction a = auctionRepository.find(auction.getId());
        Category c = categoryRepository.find(auction.getCategoryId());
        List<Category> cl = new ArrayList<>();
        cl.add(c);
        a.setCategoryList(cl);
        a.setStartprice(auction.getStartPrice());
        a.setFotourl(auction.getFotourl());
        a.setTitle(auction.getName());
        a.setDescription(auction.getDescription());
        a.setCloseprice(auction.getClosePrice());
        a.setClosenumberofbids(auction.getCloseNumberofBids());
        a.setClosedate(auction.getCloseDate());
        auctionRepository.edit(a);
    }

    public void deleteAuction(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CategoryDTO> getAllCategories() {
        return Category.toDTO(categoryRepository.getAllOrdered());
    }

    public List<CategoryDTO> filterCategories(String category) {
        return Category.toDTO(categoryRepository.filter(category));
    }

    public CategoryDTO getCategory(long id) {
        return categoryRepository.find(id).toDTO();
    }

    public void createCategory(CategoryDTO category) {
        Category c = new Category();
        c.setName(category.getName());
        categoryRepository.create(c);
    }

    public void saveCategory(CategoryDTO category) {
        Category c = categoryRepository.find(category.getId());
        c.setCategoryid(category.getId());
        c.setName(category.getName());
        categoryRepository.edit(c);
    }
}
