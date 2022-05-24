package com.test.eguay.service;

import com.test.eguay.dto.MailDTO;
import com.test.eguay.dto.UserDTO;
import com.test.eguay.entity.Auction;
import com.test.eguay.entity.Group;
import com.test.eguay.entity.Mail;
import com.test.eguay.entity.User;
import com.test.eguay.repository.AuctionRepository;
import com.test.eguay.repository.GroupRepository;
import com.test.eguay.repository.MailRepository;
import com.test.eguay.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {
    MailRepository mailRepository;
    AuctionRepository auctionRepository;
    GroupRepository groupRepository;
    UserRepository userRepository;

    public List<MailDTO> getAllMails(Integer userId){
        return Mail.toDTO(mailFacade.findAllMailsToUser(userId));
    }

    public List<MailDTO> getAllMails() {
        return Mail.toDTO(mailFacade.findAll());
    }

    public void sendMailToGroup(UserDTO sender, String asunto, List<Long> auctionIds, List<Long> groupIds) {
        Mail mail = new Mail();

        List<Auction> auctions = auctionFacade.findAll(auctionIds);
        List<Group> groups = groupsFacade.findAll(groupIds);


        mail.setSenderid(usersFacade.find(sender.getId()));
        mail.setSubject(asunto);
        mail.setBody(asunto);
        mail.setSentDate(new Date());
        mail.setAuctionList(auctions);
        mail.setGroupsList(groups);

        mailFacade.create(mail);

        addMailToAuctions(mail, auctions);
        addMailToGroups(mail, groups);
    }

    public void sendMailToUsers(UserDTO sender, String asunto, List<Long> auctionIds, List<Integer> userIds) {
        Mail mail = new Mail();

        List<Auction> auctions = auctionFacade.findAll(auctionIds);
        List<User> users = usersFacade.findAll(userIds);

        mail.setSenderid(usersFacade.find(sender.getId()));
        mail.setSubject(asunto);
        mail.setBody(asunto);
        mail.setSentDate(new Date());
        mail.setAuctionList(auctions);
        mail.setUsersList(users);

        mailFacade.create(mail);

        addMailToAuctions(mail, auctions);
        addMailToUsers(mail, users);
    }

    public void sendMailToAuctionWinner(String asunto, Long auctionId, Integer userId) {
        List<Long> auctionIdAsList = new ArrayList<>(1);
        auctionIdAsList.add(auctionId);
        List<Integer> userIdAsList = new ArrayList<>(1);
        userIdAsList.add(userId);
        sendMailToUsers(usersFacade.findMarketing().toDTO(), asunto, auctionIdAsList, userIdAsList);
    }

    private void addMailToAuctions(Mail mail, List<Auction> auctions) {
        for(Auction auction : auctions){
            List<Mail> auctionMails = auction.getMailList();
            auctionMails.add(mail);
            auction.setMailList(auctionMails);
            auctionFacade.edit(auction);
        }
    }

    private void addMailToGroups(Mail mail, List<Group> groups) {
        for(Group group : groups){
            List<Mail> groupMails = group.getMailList();
            groupMails.add(mail);
            group.setMailList(groupMails);
            groupsFacade.edit(group);
        }
    }

    private void addMailToUsers(Mail mail, List<User> users) {
        for(User user : users){
            List<Mail> userMails = user.getMailList();
            userMails.add(mail);
            user.setMailList(userMails);
            usersFacade.edit(user);
        }
    }

    public void sendMailToUsersInterestedIn(Auction auction) {

    }
}
