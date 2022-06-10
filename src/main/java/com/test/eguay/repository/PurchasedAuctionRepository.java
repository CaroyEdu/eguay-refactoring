package com.test.eguay.repository;

import com.test.eguay.entity.Auction;
import com.test.eguay.entity.FavoriteAuction;
import com.test.eguay.entity.PurchasedAuction;
import com.test.eguay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchasedAuctionRepository extends JpaRepository<PurchasedAuction, Integer> {

    @Query("select p from PurchasedAuction p where p.auctionByAuctionid = :auctionid and p.usersByUserid = :userid")
    public List<PurchasedAuction> FindPurchasedAuctionOfUser(@Param("auctionid") Auction auctionid , @Param("userid") User userid);

    public List<PurchasedAuction> findPurchasedAuctionByAuctionByAuctionid(Auction id);
}
