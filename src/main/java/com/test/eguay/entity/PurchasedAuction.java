package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "purchasedauction", schema = "public", catalog = "da1knun38jg1va")
public class PurchasedAuction {
    @Basic
    @Column(name = "userid", nullable = true)
    private Long userid;
    @Basic
    @Column(name = "auctionid", nullable = true)
    private Long auctionid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "purchasedauctionid", nullable = false)
    private Long purchasedauctionid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", insertable = false, updatable = false)
    private Auction auctionByAuctionid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
    }

    public Long getPurchasedauctionid() {
        return purchasedauctionid;
    }

    public void setPurchasedauctionid(Long purchasedauctionid) {
        this.purchasedauctionid = purchasedauctionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchasedAuction that = (PurchasedAuction) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (auctionid != null ? !auctionid.equals(that.auctionid) : that.auctionid != null) return false;
        if (purchasedauctionid != null ? !purchasedauctionid.equals(that.purchasedauctionid) : that.purchasedauctionid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (auctionid != null ? auctionid.hashCode() : 0);
        result = 31 * result + (purchasedauctionid != null ? purchasedauctionid.hashCode() : 0);
        return result;
    }

    public User getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(User usersByUserid) {
        this.usersByUserid = usersByUserid;
    }

    public Auction getAuctionByAuctionid() {
        return auctionByAuctionid;
    }

    public void setAuctionByAuctionid(Auction auctionByAuctionid) {
        this.auctionByAuctionid = auctionByAuctionid;
    }
}
