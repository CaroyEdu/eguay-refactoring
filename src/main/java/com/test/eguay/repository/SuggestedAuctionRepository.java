package com.test.eguay.repository;

import com.test.eguay.entity.SuggestedAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuggestedAuctionRepository extends JpaRepository<SuggestedAuction, Long> {
}