package com.test.eguay.entity;

import javax.persistence.*;

@Entity
public class AuctionCategory {
    @Basic
    @Column(name = "categoryid", nullable = false)
    private Long categoryid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private Long auctionid;
    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid", nullable = false)
    private Category categoryByCategoryid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", nullable = false)
    private Auction auctionByAuctionid;

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuctionCategory that = (AuctionCategory) o;

        if (categoryid != null ? !categoryid.equals(that.categoryid) : that.categoryid != null) return false;
        if (auctionid != null ? !auctionid.equals(that.auctionid) : that.auctionid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryid != null ? categoryid.hashCode() : 0;
        result = 31 * result + (auctionid != null ? auctionid.hashCode() : 0);
        return result;
    }

    public Category getCategoryByCategoryid() {
        return categoryByCategoryid;
    }

    public void setCategoryByCategoryid(Category categoryByCategoryid) {
        this.categoryByCategoryid = categoryByCategoryid;
    }

    public Auction getAuctionByAuctionid() {
        return auctionByAuctionid;
    }

    public void setAuctionByAuctionid(Auction auctionByAuctionid) {
        this.auctionByAuctionid = auctionByAuctionid;
    }
}
