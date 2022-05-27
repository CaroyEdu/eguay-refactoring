package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "suggestedauction", schema = "public", catalog = "da1knun38jg1va")
public class SuggestedAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "mailid", nullable = false)
    private long mailid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private long auctionid;
    @ManyToOne
    @JoinColumn(name = "mailid", referencedColumnName = "mailid", nullable = false, insertable = false, updatable = false)
    private Mail mailByMailid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", nullable = false, insertable = false, updatable = false)
    private Auction auctionByAuctionid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMailid() {
        return mailid;
    }

    public void setMailid(long mailid) {
        this.mailid = mailid;
    }

    public long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(long auctionid) {
        this.auctionid = auctionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuggestedAuction that = (SuggestedAuction) o;

        if (mailid != that.mailid) return false;
        if (auctionid != that.auctionid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (mailid ^ (mailid >>> 32));
        result = 31 * result + (int) (auctionid ^ (auctionid >>> 32));
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
