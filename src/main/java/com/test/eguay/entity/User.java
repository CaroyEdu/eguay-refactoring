package com.test.eguay.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

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
    private Collection<Auction> auctionsByUserid;
    @OneToMany(mappedBy = "usersByBiderid")
    private Collection<Bid> bidsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private Collection<FavoriteAuction> favoriteauctionsByUserid;
    @OneToMany(mappedBy = "usersBySenderid")
    private Collection<Mail> mailByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private Collection<PurchasedAuction> purchasedauctionsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private Collection<UserCategory> userscategoriesByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private Collection<UserGroups> usersgroupsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private Collection<UserMail> usersmailsByUserid;
    @OneToMany(mappedBy = "usersByUserid")
    private Collection<UserRol> usersrolsByUserid;

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

    public Collection<Auction> getAuctionsByUserid() {
        return auctionsByUserid;
    }

    public void setAuctionsByUserid(Collection<Auction> auctionsByUserid) {
        this.auctionsByUserid = auctionsByUserid;
    }

    public Collection<Bid> getBidsByUserid() {
        return bidsByUserid;
    }

    public void setBidsByUserid(Collection<Bid> bidsByUserid) {
        this.bidsByUserid = bidsByUserid;
    }

    public Collection<FavoriteAuction> getFavoriteauctionsByUserid() {
        return favoriteauctionsByUserid;
    }

    public void setFavoriteauctionsByUserid(Collection<FavoriteAuction> favoriteauctionsByUserid) {
        this.favoriteauctionsByUserid = favoriteauctionsByUserid;
    }

    public Collection<Mail> getMailByUserid() {
        return mailByUserid;
    }

    public void setMailByUserid(Collection<Mail> mailByUserid) {
        this.mailByUserid = mailByUserid;
    }

    public Collection<PurchasedAuction> getPurchasedauctionsByUserid() {
        return purchasedauctionsByUserid;
    }

    public void setPurchasedauctionsByUserid(Collection<PurchasedAuction> purchasedauctionsByUserid) {
        this.purchasedauctionsByUserid = purchasedauctionsByUserid;
    }

    public Collection<UserCategory> getUserscategoriesByUserid() {
        return userscategoriesByUserid;
    }

    public void setUserscategoriesByUserid(Collection<UserCategory> userscategoriesByUserid) {
        this.userscategoriesByUserid = userscategoriesByUserid;
    }

    public Collection<UserGroups> getUsersgroupsByUserid() {
        return usersgroupsByUserid;
    }

    public void setUsersgroupsByUserid(Collection<UserGroups> usersgroupsByUserid) {
        this.usersgroupsByUserid = usersgroupsByUserid;
    }

    public Collection<UserMail> getUsersmailsByUserid() {
        return usersmailsByUserid;
    }

    public void setUsersmailsByUserid(Collection<UserMail> usersmailsByUserid) {
        this.usersmailsByUserid = usersmailsByUserid;
    }

    public Collection<UserRol> getUsersrolsByUserid() {
        return usersrolsByUserid;
    }

    public void setUsersrolsByUserid(Collection<UserRol> usersrolsByUserid) {
        this.usersrolsByUserid = usersrolsByUserid;
    }
}
