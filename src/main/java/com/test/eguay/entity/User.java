package com.test.eguay.entity;

import com.test.eguay.dto.UserDTO;
import com.test.eguay.service.CategoryService;
import com.test.eguay.service.UserService;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", schema = "public", catalog = "da1knun38jg1va")
public class User {
    @Basic
    @Column(name = "username", nullable = false, length = -1)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = -1)
    private String password;
    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;
    @Basic
    @Column(name = "joineddate", nullable = true)
    private Date joineddate;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "surname", nullable = true, length = -1)
    private String surname;
    @Basic
    @Column(name = "sex", nullable = true)
    private Integer sex;
    @Basic
    @Column(name = "birthyear", nullable = false)
    private Date birthyear;
    @Basic
    @Column(name = "country", nullable = true, length = -1)
    private String country;
    @Basic
    @Column(name = "city", nullable = true, length = -1)
    private String city;
    @Basic
    @Column(name = "address", nullable = true, length = -1)
    private String address;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "userid", nullable = false)
    private Integer userid;
    @OneToMany(mappedBy = "usersBySellerid")
    private List<Auction> auctionsByUserid;
    @OneToMany(mappedBy = "usersByBiderid")
    private List<Bid> bidsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private List<FavoriteAuction> favoriteauctionsByUserid;
    @OneToMany(mappedBy = "usersBySenderid")
    private List<Mail> mailByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private List<PurchasedAuction> purchasedauctionsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private List<UserCategory> userscategoriesByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private List<UserGroups> usersgroupsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private List<UserMail> usersmailsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private List<UserRol> usersrolsByUserid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getJoineddate() {
        return joineddate;
    }

    public void setJoineddate(Date joineddate) {
        this.joineddate = joineddate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(Date birthyear) {
        this.birthyear = birthyear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (joineddate != null ? !joineddate.equals(user.joineddate) : user.joineddate != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (surname != null ? !surname.equals(user.surname) : user.surname != null) return false;
        if (sex != null ? !sex.equals(user.sex) : user.sex != null) return false;
        if (birthyear != null ? !birthyear.equals(user.birthyear) : user.birthyear != null) return false;
        if (country != null ? !country.equals(user.country) : user.country != null) return false;
        if (city != null ? !city.equals(user.city) : user.city != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (userid != null ? !userid.equals(user.userid) : user.userid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (joineddate != null ? joineddate.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthyear != null ? birthyear.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        return result;
    }

    public List<Auction> getAuctionsByUserid() {
        return auctionsByUserid;
    }

    public void setAuctionsByUserid(List<Auction> auctionsByUserid) {
        this.auctionsByUserid = auctionsByUserid;
    }

    public List<Bid> getBidsByUserid() {
        return bidsByUserid;
    }

    public void setBidsByUserid(List<Bid> bidsByUserid) {
        this.bidsByUserid = bidsByUserid;
    }

    public List<FavoriteAuction> getFavoriteauctionsByUserid() {
        return favoriteauctionsByUserid;
    }

    public void setFavoriteauctionsByUserid(List<FavoriteAuction> favoriteauctionsByUserid) {
        this.favoriteauctionsByUserid = favoriteauctionsByUserid;
    }

    public List<Mail> getMailByUserid() {
        return mailByUserid;
    }

    public void setMailByUserid(List<Mail> mailByUserid) {
        this.mailByUserid = mailByUserid;
    }

    public List<PurchasedAuction> getPurchasedauctionsByUserid() {
        return purchasedauctionsByUserid;
    }

    public void setPurchasedauctionsByUserid(List<PurchasedAuction> purchasedauctionsByUserid) {
        this.purchasedauctionsByUserid = purchasedauctionsByUserid;
    }

    public List<UserCategory> getUserscategoriesByUserid() {
        return userscategoriesByUserid;
    }

    public void setUserscategoriesByUserid(List<UserCategory> userscategoriesByUserid) {
        this.userscategoriesByUserid = userscategoriesByUserid;
    }

    public List<UserGroups> getUsersgroupsByUserid() {
        return usersgroupsByUserid;
    }

    public void setUsersgroupsByUserid(List<UserGroups> usersgroupsByUserid) {
        this.usersgroupsByUserid = usersgroupsByUserid;
    }

    public List<UserMail> getUsersmailsByUserid() {
        return usersmailsByUserid;
    }

    public void setUsersmailsByUserid(List<UserMail> usersmailsByUserid) {
        this.usersmailsByUserid = usersmailsByUserid;
    }

    public List<UserRol> getUsersrolsByUserid() {
        return usersrolsByUserid;
    }

    public void setUsersrolsByUserid(List<UserRol> usersrolsByUserid) {
        this.usersrolsByUserid = usersrolsByUserid;
    }

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setBirthyear(this.birthyear);
        userDTO.setAddress(this.address);
        userDTO.setCity(this.city);
        userDTO.setPassword(this.password);
        userDTO.setCountry(this.country);
        userDTO.setEmail(this.email);
        userDTO.setId(this.userid);
        userDTO.setSex(this.sex);
        userDTO.setSurname(this.surname);
        userDTO.setUsername(this.username);
        userDTO.setName(this.name);
        userDTO.setUserAuctions(Auction.toDTO(auctionsByUserid));
        userDTO.setIsMarketing(isMarketing());

        //relations ( dummy )
        List<Category> categoryList = new ArrayList<>();
        Category categoryFav = new Category();
        categoryList.add(categoryFav);
        userDTO.setFavCategories(CategoryService.toDTO(categoryList));

//        List<Auction> favAuction = new ArrayList<>();
//        Auction auction = new Auction();
//        favAuction.add(auction);
//        userDTO.setFavAuctions(Auction.toDTO(favAuction));

//

        return userDTO;
    }

    private boolean isMarketing(){
        return this.username.equals("marketing"); //hasRole("marketing");
    }

    private boolean hasRole (String rol) {
        // No funciona debido a que la consulta sql no detecta la relacion y no sé por qué
        boolean isRol =
                this.usersrolsByUserid != null &&
                !this.usersrolsByUserid.isEmpty() &&
                this.usersrolsByUserid
                    .stream()
                    .map(userRol -> userRol.getRolByRolid().getName())
                    .collect(Collectors.toList())
                    .contains(rol);
        return isRol;
    }
}
