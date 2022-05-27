package com.test.eguay.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class UserDTO {
    // DB
    private Integer id;

    // Conceptual
    private String username;
    private String password;
    private String email;
    private String name;
    private String surname;
    private Integer sex;
    private Date birthyear;
    private String country;
    private String city;
    private String address;
    private List<AuctionDTO> favAuctions;
    private List<CategoryDTO> favCategories;

    public List<AuctionDTO> getPurchasedAuction() {
        return purchasedAuction;
    }

    public void setPurchasedAuction(List<AuctionDTO> purchasedAuction) {
        this.purchasedAuction = purchasedAuction;
    }

    private List<AuctionDTO> purchasedAuction;

    public void setFavCategories(List<CategoryDTO> favCategories)
    {
        this.favCategories = favCategories;
    }

    public List<CategoryDTO> getFavCategories()
    {
        return this.favCategories;
    }

    public void setFavAuctions(List<AuctionDTO> favAuctions)
    {
        this.favAuctions = favAuctions;
    }

    public List<AuctionDTO> getFavAuctions()
    {
        return this.favAuctions;
    }
    private boolean isAdmin;
    private boolean isMarketing;
    private boolean isAnalista;
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public boolean isAnalista() {
        return isAnalista;
    }

    public void setIsAnalista(boolean isAnalista) {
        this.isAnalista = isAnalista;
    }

    public boolean isMarketing() {
        return isMarketing;
    }

    public void setIsMarketing(boolean isMarketing) {
        this.isMarketing = isMarketing;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserDTO other = (UserDTO) obj;
        return Objects.equals(this.id, other.id);
    }
}
