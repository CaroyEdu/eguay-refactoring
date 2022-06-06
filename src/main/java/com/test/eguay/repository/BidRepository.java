package com.test.eguay.repository;

import com.test.eguay.entity.Auction;
import com.test.eguay.entity.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query("SELECT bi FROM Bid bi where bi.bidid = ( SELECT MAX(bii.bidid) from Bid bii WHERE bii.auctionByAuctionid = :auction) ")
    public List<Bid> findHighestBid(@Param("auction") Auction auction );


}
