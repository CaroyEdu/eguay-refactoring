package com.test.eguay.dto;

import com.test.eguay.entity.Auction;
import com.test.eguay.entity.User;

import java.util.Objects;

public class BidDTO {
    public Long getBidid() {
        return bidid;
    }

    public void setBidid(Long bidid) {
        this.bidid = bidid;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public UserDTO getUsersByBiderid() {
        return usersByBiderid;
    }

    public void setUsersByBiderid(UserDTO usersByBiderid) {
        this.usersByBiderid = usersByBiderid;
    }

    public AuctionDTO getAuctionByAuctionid() {
        return auctionByAuctionid;
    }

    public void setAuctionByAuctionid(AuctionDTO auctionByAuctionid) {
        this.auctionByAuctionid = auctionByAuctionid;
    }

    // DB
    private Long bidid;

    // Conceptual
    private Double bid;
    private UserDTO usersByBiderid;
    private AuctionDTO auctionByAuctionid;

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
        final BidDTO other = (BidDTO) obj;
        return Objects.equals(this.bidid, other.getBidid());
    }
}

