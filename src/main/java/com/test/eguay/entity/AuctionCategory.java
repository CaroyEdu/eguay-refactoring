package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "auctioncategory", schema = "public", catalog = "da1knun38jg1va")
public class AuctionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "categoryid", nullable = false)
    private long categoryid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private long auctionid;
    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid", nullable = false, insertable = false, updatable = false)
    private Category categoryByCategoryid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", nullable = false, insertable = false, updatable = false)
    private Auction auctionByAuctionid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    public long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(long auctionid) {
        this.auctionid = auctionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuctionCategory that = (AuctionCategory) o;

        if (categoryid != that.categoryid) return false;
        if (auctionid != that.auctionid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (categoryid ^ (categoryid >>> 32));
        result = 31 * result + (int) (auctionid ^ (auctionid >>> 32));
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
