package com.test.eguay.entity;

import javax.persistence.*;

@Entity
public class PurchasedAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "userid", nullable = true)
    private Long userid;
    @Basic
    @Column(name = "auctionid", nullable = true)
    private Long auctionid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", insertable = false, updatable = false)
    private Auction auctionByAuctionid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchasedAuction that = (PurchasedAuction) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (auctionid != null ? !auctionid.equals(that.auctionid) : that.auctionid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (auctionid != null ? auctionid.hashCode() : 0);
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
