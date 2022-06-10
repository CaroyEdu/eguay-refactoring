package com.test.eguay.repository;

import com.test.eguay.entity.Auction;
import com.test.eguay.entity.AuctionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionCategoryRepository extends JpaRepository<AuctionCategory, Long> {

    public AuctionCategory findAuctionCategoryByCategoryidAndAuctionid(Long categoryId, Long auctionId);

    public List<AuctionCategory> findAuctionCategoryByAuctionByAuctionid(Auction auction);
}
