package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "favoriteauction", schema = "public", catalog = "da1knun38jg1va")
public class FavoriteAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "userid", nullable = false)
    private long userid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private long auctionid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", nullable = false, insertable = false, updatable = false)
    private Auction auctionByAuctionid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
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

        FavoriteAuction that = (FavoriteAuction) o;

        if (userid != that.userid) return false;
        if (auctionid != that.auctionid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + (int) (auctionid ^ (auctionid >>> 32));
        return result;
    }

    public User getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(User usersByUserid) {
        this.usersByUserid = usersByUserid;
    }

    public Auction getAuctionByAuctionid() {
        return auctionByAuctionid;
    }

    public void setAuctionByAuctionid(Auction auctionByAuctionid) {
        this.auctionByAuctionid = auctionByAuctionid;
    }
}
