package com.test.eguay.entity;

import javax.persistence.*;

@Entity
public class Suggestedauction {
    @Basic
    @Column(name = "mailid", nullable = false)
    private Long mailid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private Long auctionid;
    @ManyToOne
    @JoinColumn(name = "mailid", referencedColumnName = "mailid", nullable = false)
    private Mail mailByMailid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", nullable = false)
    private Auction auctionByAuctionid;

    public Long getMailid() {
        return mailid;
    }

    public void setMailid(Long mailid) {
        this.mailid = mailid;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Suggestedauction that = (Suggestedauction) o;

        if (mailid != null ? !mailid.equals(that.mailid) : that.mailid != null) return false;
        if (auctionid != null ? !auctionid.equals(that.auctionid) : that.auctionid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mailid != null ? mailid.hashCode() : 0;
        result = 31 * result + (auctionid != null ? auctionid.hashCode() : 0);
        return result;
    }

    public Mail getMailByMailid() {
        return mailByMailid;
    }

    public void setMailByMailid(Mail mailByMailid) {
        this.mailByMailid = mailByMailid;
    }

    public Auction getAuctionByAuctionid() {
        return auctionByAuctionid;
    }

    public void setAuctionByAuctionid(Auction auctionByAuctionid) {
        this.auctionByAuctionid = auctionByAuctionid;
    }
}
