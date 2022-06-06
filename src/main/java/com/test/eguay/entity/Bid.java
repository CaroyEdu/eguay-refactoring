package com.test.eguay.entity;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.dto.BidDTO;

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
    private Long bidid;
    @Basic
    @Column(name = "biderid", nullable = false)
    private Long biderid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private Long auctionid;
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

    public Long getBidid() {
        return bidid;
    }

    public void setBidid(Long bidid) {
        this.bidid = bidid;
    }

    public Long getBiderid() {
        return biderid;
    }

    public void setBiderid(Long biderid) {
        this.biderid = biderid;
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

        Bid bid1 = (Bid) o;

        if (bid != null ? !bid.equals(bid1.bid) : bid1.bid != null) return false;
        if (bidid != null ? !bidid.equals(bid1.bidid) : bid1.bidid != null) return false;
        if (biderid != null ? !biderid.equals(bid1.biderid) : bid1.biderid != null) return false;
        if (auctionid != null ? !auctionid.equals(bid1.auctionid) : bid1.auctionid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid != null ? bid.hashCode() : 0;
        result = 31 * result + (bidid != null ? bidid.hashCode() : 0);
        result = 31 * result + (biderid != null ? biderid.hashCode() : 0);
        result = 31 * result + (auctionid != null ? auctionid.hashCode() : 0);
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

    public BidDTO toDTO(){
        BidDTO bidDTO = new BidDTO();
        bidDTO.setBid(this.bid);
        bidDTO.setBidid(this.bidid);
        bidDTO.setAuctionByAuctionid(this.getAuctionByAuctionid().toDTO());
        bidDTO.setUsersByBiderid(this.usersByBiderid.toDTO());
        return bidDTO;
    }
}
