package com.test.eguay.repository;

import com.test.eguay.entity.FavoriteAuction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavAuctionRepository extends JpaRepository<FavoriteAuction , Long> {
}
