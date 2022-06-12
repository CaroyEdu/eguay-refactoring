package com.test.eguay.dto;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class MailDTO {
    // DB
    private Long id;

    // Coceptual
    private String subject;
    private String body;
    private Date sentDate;
    private String seller;

    // Relationships
    private List<AuctionDTO> auctions;

    public MailDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public List<AuctionDTO> getAuctions() {
        return auctions;
    }

    public void setAuctions(List<AuctionDTO> auctions) {
        this.auctions = auctions;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}

