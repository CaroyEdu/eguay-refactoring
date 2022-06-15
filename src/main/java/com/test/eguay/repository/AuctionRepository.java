package com.test.eguay.repository;

import com.test.eguay.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query("SELECT a FROM Auction a WHERE a.title LIKE CONCAT('%', :title, '%')")
    public List<Auction> findBySimilarTitle (@Param("title") String title);

    public List<Auction> findAuctionByTitleAndSellerid(String title, int userid);

    public List<Auction> findAuctionsByActiveTrue();

    public List<Auction> findAuctionsBySellerid(int userid);

    public Auction findAuctionByAuctionid(Long id);

    public List<Auction> findAllByActiveIsTrue() ;

    List<Auction> findByTitleContainingIgnoreCase(String filter);

}
