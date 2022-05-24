package com.test.eguay.service;

import org.springframework.stereotype.Service;

@Service
public class MailService {
    @EJB MailFacade mailFacade;
    @EJB AuctionFacade auctionFacade;
    @EJB GroupsFacade groupsFacade;
    @EJB UsersFacade usersFacade;

    public List<MailDTO> getAllMails(Integer userId){
        return Mail.toDTO(mailFacade.findAllMailsToUser(userId));
    }

    public List<MailDTO> getAllMails() {
        return Mail.toDTO(mailFacade.findAll());
    }

    public void sendMailToGroup(UserDTO sender, String asunto, List<Long> auctionIds, List<Long> groupIds) {
        Mail mail = new Mail();

        List<Auction> auctions = auctionFacade.findAll(auctionIds);
        List<Groups> groups = groupsFacade.findAll(groupIds);


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
        List<Users> users = usersFacade.findAll(userIds);

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

    private void addMailToGroups(Mail mail, List<Groups> groups) {
        for(Groups group : groups){
            List<Mail> groupMails = group.getMailList();
            groupMails.add(mail);
            group.setMailList(groupMails);
            groupsFacade.edit(group);
        }
    }

    private void addMailToUsers(Mail mail, List<Users> users) {
        for(Users user : users){
            List<Mail> userMails = user.getMailList();
            userMails.add(mail);
            user.setMailList(userMails);
            usersFacade.edit(user);
        }
    }

    public void sendMailToUsersInterestedIn(Auction auction) {

    }
}
