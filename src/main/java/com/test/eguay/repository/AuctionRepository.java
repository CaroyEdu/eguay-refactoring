package com.test.eguay.repository;

import com.test.eguay.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    public List<Auction> findAuctionsByTitle(String title);

    public List<Auction> findAuctionsByTitleAndUsersBySellerid(String title, int userid);

    public List<Auction> findAuctionsByUsersBySelleridOrderByStartdateDesc(int userid);

    public <List>Auction findAuctionsByActiveTrue();

    public <List>Auction findAuctionsByUsersBySelleridOrderByUsersBySellerid(int userid);

}
