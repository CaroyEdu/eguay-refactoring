package com.test.eguay.service;

import com.test.eguay.entity.Auction;
import com.test.eguay.entity.AuctionCategory;
import com.test.eguay.entity.Category;
import com.test.eguay.repository.AuctionCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuctionCategoryService {

    private AuctionCategoryRepository auctionCategoryRepository;

    public AuctionCategoryRepository getAuctionCategoryRepository() {
        return auctionCategoryRepository;
    }

    @Autowired
    public void setAuctionCategoryRepository(AuctionCategoryRepository auctionCategoryRepository) {
        this.auctionCategoryRepository = auctionCategoryRepository;
    }

    public void removeAuctionCategoryByCategoryIdAndAuctionId(Long categoryId, Long auctionId){
        AuctionCategory ac = this.auctionCategoryRepository.findAuctionCategoryByCategoryidAndAuctionid(categoryId, auctionId);
        this.auctionCategoryRepository.delete(ac);
    }

    public AuctionCategory newAuctionCategory(Auction auction , Category category){
        AuctionCategory auctionCategory = new AuctionCategory();
        auctionCategory.setAuctionid(auction.getAuctionid());
        auctionCategory.setCategoryid(category.getCategoryid());
        auctionCategory.setAuctionByAuctionid(auction);
        auctionCategory.setCategoryByCategoryid(category);
        return auctionCategory;
    }
}
