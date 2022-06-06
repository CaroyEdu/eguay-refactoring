package com.test.eguay.repository;

import com.test.eguay.entity.PurchasedAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchasedAuctionRepository extends JpaRepository<PurchasedAuction, Integer> {
}
