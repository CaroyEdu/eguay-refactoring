package com.test.eguay.service;

//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class BidService {
//    BidRepository bidRepository ;
//    AuctionRepository auctionRepository;
//    UserRepository userRepository ;
//    UserService userService;
//    AuctionService auctionService;
//
//    public List<BidDTO> getHighestBid(AuctionDTO auction){
//        List<Bid> highest = bidFacade.highestBid(auction);
//        return BidService.toDTO(highest) ;
//    }
//
//    public void createBid( Double BidAmount , AuctionDTO auction , UserDTO user){
//        Bid newBid = new Bid();
//
//        newBid.setAuctionid(auctionFacade.find(auction.getId()));
//        newBid.setBid(BidAmount);
//        newBid.setBiderid(userFacade.find(user.getId()));
//        bidFacade.create(newBid);
//    }
//    // Add business logic below. (Right-click in editor and choose
//    // "Insert Code > Add Business Method")
//
//    // Logic
//    public static List<BidDTO> toDTO(List<Bid> bids){
//        List<BidDTO> dtos = new ArrayList<>(bids.size());
//
//        for(Bid bid : bids){
//            dtos.add(bid.toDTO());
//        }
//
//        return dtos;
//    }
//
//
//}
