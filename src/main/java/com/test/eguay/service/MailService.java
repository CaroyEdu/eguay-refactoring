package com.test.eguay.service;

import com.test.eguay.dto.MailDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.*;
import com.test.eguay.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MailService {
    private MailRepository mailRepository;
    private GroupRepository groupRepository;
    private AuctionRepository auctionRepository;
    private GroupMailRepository groupMailRepository;

    public GroupMailRepository getGroupMailRepository() {
        return groupMailRepository;
    }

    @Autowired
    public void setGroupMailRepository(GroupMailRepository groupMailRepository) {
        this.groupMailRepository = groupMailRepository;
    }

    public SuggestedAuctionRepository getSuggestedAuctionRepository() {
        return suggestedAuctionRepository;
    }

    @Autowired
    public void setSuggestedAuctionRepository(SuggestedAuctionRepository suggestedAuctionRepository) {
        this.suggestedAuctionRepository = suggestedAuctionRepository;
    }

    private SuggestedAuctionRepository suggestedAuctionRepository;

    public MailRepository getMailRepository() {
        return mailRepository;
    }

    @Autowired
    public void setMailRepository(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public AuctionRepository getAuctionRepository() {
        return auctionRepository;
    }

    @Autowired
    public void setAuctionRepository(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<MailDTO> getMails(int userId){
        List<MailDTO> mails;
        mails = this.mailRepository.findMailsSentDirectlyToUser(userId).stream().map(mail -> mail.toDtoLinked()).collect(Collectors.toList());
        mails.addAll(this.mailRepository.findMailsSentToUserByAGroup(userId).stream().map(mail -> mail.toDtoLinked()).collect(Collectors.toList()));
        return mails;
    }

    public void sendMail(UserDTO sender, String subject, List<Long> auctionIds, List<Long> groupIds){
        List<Auction> auctions = this.auctionRepository.findAllById(auctionIds);
        List<Group> groups = this.groupRepository.findAllById(groupIds);

        Mail mail = new Mail();
        mail.setSenderid(sender.getId().longValue());
        mail.setSentdate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        mail.setSubject(subject);
        mail.setBody(subject);
        this.mailRepository.save(mail);

        GroupMail groupMail;
        for(Group group : groups){
            groupMail = new GroupMail();
            groupMail.setGroupid(group.getId());
            groupMail.setMailid(mail.getMailid());
            this.groupMailRepository.save(groupMail);
        }

        SuggestedAuction suggestedAuction;
        for(Auction auction : auctions){
            suggestedAuction = new SuggestedAuction();
            suggestedAuction.setAuctionid(auction.getAuctionid());
            suggestedAuction.setMailid(mail.getMailid());
            this.suggestedAuctionRepository.save(suggestedAuction);
        }
    }

}
