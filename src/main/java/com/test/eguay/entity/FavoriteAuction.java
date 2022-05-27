package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "favoriteauction", schema = "public", catalog = "da1knun38jg1va")
public class FavoriteAuction {
    @Basic
    @Column(name = "userid", nullable = false)
    private Long userid;
    @Basic
    @Column(name = "auctionid", nullable = false)
    private Long auctionid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "favoriteauctionid", nullable = false)
    private Long favoriteauctionid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "auctionid", referencedColumnName = "auctionid", nullable = false, insertable = false, updatable = false)
    private Auction auctionByAuctionid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
    }

    public Long getFavoriteauctionid() {
        return favoriteauctionid;
    }

    public void setFavoriteauctionid(Long favoriteauctionid) {
        this.favoriteauctionid = favoriteauctionid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteAuction that = (FavoriteAuction) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (auctionid != null ? !auctionid.equals(that.auctionid) : that.auctionid != null) return false;
        if (favoriteauctionid != null ? !favoriteauctionid.equals(that.favoriteauctionid) : that.favoriteauctionid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (auctionid != null ? auctionid.hashCode() : 0);
        result = 31 * result + (favoriteauctionid != null ? favoriteauctionid.hashCode() : 0);
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
