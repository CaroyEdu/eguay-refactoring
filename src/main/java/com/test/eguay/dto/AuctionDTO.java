package com.test.eguay.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AuctionDTO {
    // DB
    private Long id;

    // Conceptual
    private String name;
    private String category;
    private Float startPrice;
    private Date startDate;
    private Date closeDate;
    private Integer closeNumberofBids;
    private Float closePrice;
    private Float maxBid;
    private Boolean active;
    private String fotourl;
    private List<CategoryDTO> categoryList;
    private String description;
    private List<UserDTO> userList;
    private Long sellerID;
    private Long categoryId;
    private Long auctionCategory;





    // Relationships
    private String seller;

    public void setCategoryId(Long categoryID){
        this.categoryId = categoryID;
    }

    public Long getCategoryId()
    {
        return this.categoryId;
    }

    public Long getSellerID()
    {
        return sellerID;
    }

    public void setSellerID(Long sellerID)
    {
        this.sellerID = sellerID;
    }

    public List<UserDTO> getUserList()
    {
        return this.userList;
    }

    public void setUserList(List<UserDTO> userList)
    {
        this.userList = userList;
    }

    public Float getStartPrice()
    {
        return startPrice;
    }

    public void setStartPrice(Float startPrice)
    {
        this.startPrice = startPrice;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setCategoryList(List<CategoryDTO> categoryList){
        this.categoryList = categoryList;
    }

    public List<CategoryDTO> getCategoryList()
    {
        return this.categoryList;
    }

    public String getFotourl()
    {
        return fotourl;
    }

    public void setFotourl(String fotourl){
        this.fotourl = fotourl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Integer getCloseNumberofBids() {
        return closeNumberofBids;
    }

    public void setCloseNumberofBids(Integer closeNumberofBids) {
        this.closeNumberofBids = closeNumberofBids;
    }

    public Float getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Float closePrice) {
        this.closePrice = closePrice;
    }

    public Float getMaxBid() {
        return maxBid;
    }

    public void setMaxBid(Float maxBid) {
        this.maxBid = maxBid;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Long getAuctionCategory() {
        return auctionCategory;
    }

    public void setAuctionCategory(Long auctionCategory) {
        this.auctionCategory = auctionCategory;
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
        final AuctionDTO other = (AuctionDTO) obj;
        return Objects.equals(this.getId() , other.getId());
    }
}

