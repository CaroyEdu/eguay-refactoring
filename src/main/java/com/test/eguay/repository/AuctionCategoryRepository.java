package com.test.eguay.repository;

import com.test.eguay.entity.AuctionCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuctionCategoryRepository extends JpaRepository<AuctionCategory, Long> {

    public AuctionCategory findAuctionCategoryByCategoryidAndAuctionid(Long categoryId, Long auctionId);
}
