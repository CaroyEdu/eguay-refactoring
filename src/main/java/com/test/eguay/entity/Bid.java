package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "bid", schema = "public", catalog = "da1knun38jg1va")
public class Bid {
    @Basic
    @Column(name = "bid", nullable = true, precision = 0)
    private Double bid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bidid", nullable = false)
    private long bidid;
    @Basic
    @Column(name = "biderid", nullable = false)
    private long biderid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private long auctionid;
    @ManyToOne
    @JoinColumn(name = "biderid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    private User usersByBiderid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", nullable = false, insertable = false, updatable = false)
    private Auction auctionByAuctionid;

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public long getBidid() {
        return bidid;
    }

    public void setBidid(long bidid) {
        this.bidid = bidid;
    }

    public long getBiderid() {
        return biderid;
    }

    public void setBiderid(long biderid) {
        this.biderid = biderid;
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

        Bid bid1 = (Bid) o;

        if (bidid != bid1.bidid) return false;
        if (biderid != bid1.biderid) return false;
        if (auctionid != bid1.auctionid) return false;
        if (bid != null ? !bid.equals(bid1.bid) : bid1.bid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid != null ? bid.hashCode() : 0;
        result = 31 * result + (int) (bidid ^ (bidid >>> 32));
        result = 31 * result + (int) (biderid ^ (biderid >>> 32));
        result = 31 * result + (int) (auctionid ^ (auctionid >>> 32));
        return result;
    }

    public User getUsersByBiderid() {
        return usersByBiderid;
    }

    public void setUsersByBiderid(User usersByBiderid) {
        this.usersByBiderid = usersByBiderid;
    }

    public Auction getAuctionByAuctionid() {
        return auctionByAuctionid;
    }

    public void setAuctionByAuctionid(Auction auctionByAuctionid) {
        this.auctionByAuctionid = auctionByAuctionid;
    }
}
