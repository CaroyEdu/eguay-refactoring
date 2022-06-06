package com.test.eguay.repository;

import com.test.eguay.entity.Auction;
import com.test.eguay.entity.FavoriteAuction;
import com.test.eguay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavAuctionRepository extends JpaRepository<FavoriteAuction , Long> {

    @Query("select f from FavoriteAuction f where f.auctionByAuctionid = :auctionid and f.usersByUserid = :userid")
    public List<FavoriteAuction> FindFavoriteAuctionOfUser(@Param("auctionid") Auction auctionid ,@Param("userid") User userid);
}
