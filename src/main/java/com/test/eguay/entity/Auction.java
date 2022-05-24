package com.test.eguay.entity;

import com.test.eguay.dto.AuctionDTO;
import com.test.eguay.service.CategoryService;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class Auction {
    @Basic
    @Column(name = "startdate", nullable = false)
    private Timestamp startdate;
    @Basic
    @Column(name = "maxbid", nullable = true, precision = 0)
    private Float maxbid;
    @Basic
    @Column(name = "title", nullable = false, length = -1)
    private String title;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "fotourl", nullable = true, length = -1)
    private String fotourl;
    @Basic
    @Column(name = "closeprice", nullable = true, precision = 0)
    private Float closeprice;
    @Basic
    @Column(name = "closedate", nullable = true)
    private Timestamp closedate;
    @Basic
    @Column(name = "closenumberofbids", nullable = true)
    private Integer closenumberofbids;
    @Basic
    @Column(name = "sellerid", nullable = false)
    private Integer sellerid;
    @Basic
    @Column(name = "startprice", nullable = true, precision = 0)
    private Float startprice;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "auctionid", nullable = false)
    private Long auctionid;
    @Basic
    @Column(name = "active", nullable = true)
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "sellerid", referencedColumnName = "userid", nullable = false)
    private User usersBySellerid;
    @OneToMany(mappedBy = "auctionByAuctionid")
    private List<AuctionCategory> auctioncategoriesByAuctionid;
    @OneToMany(mappedBy = "auctionByAuctionid")
    private Collection<Bid> bidsByAuctionid;
    @OneToMany(mappedBy = "auctionByAuctionid")
    private Collection<FavoriteAuction> favoriteauctionsByAuctionid;
    @OneToMany(mappedBy = "auctionByAuctionid")
    private Collection<PurchasedAuction> purchasedauctionsByAuctionid;
    @OneToMany(mappedBy = "auctionByAuctionid")
    private Collection<Suggestedauction> suggestedauctionsByAuctionid;

    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    public Float getMaxbid() {
        return maxbid;
    }

    public void setMaxbid(Float maxbid) {
        this.maxbid = maxbid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFotourl() {
        return fotourl;
    }

    public void setFotourl(String fotourl) {
        this.fotourl = fotourl;
    }

    public Float getCloseprice() {
        return closeprice;
    }

    public void setCloseprice(Float closeprice) {
        this.closeprice = closeprice;
    }

    public Timestamp getClosedate() {
        return closedate;
    }

    public void setClosedate(Timestamp closedate) {
        this.closedate = closedate;
    }

    public Integer getClosenumberofbids() {
        return closenumberofbids;
    }

    public void setClosenumberofbids(Integer closenumberofbids) {
        this.closenumberofbids = closenumberofbids;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Float getStartprice() {
        return startprice;
    }

    public void setStartprice(Float startprice) {
        this.startprice = startprice;
    }

    public Long getAuctionid() {
        return auctionid;
    }

    public void setAuctionid(Long auctionid) {
        this.auctionid = auctionid;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auction auction = (Auction) o;

        if (startdate != null ? !startdate.equals(auction.startdate) : auction.startdate != null) return false;
        if (maxbid != null ? !maxbid.equals(auction.maxbid) : auction.maxbid != null) return false;
        if (title != null ? !title.equals(auction.title) : auction.title != null) return false;
        if (description != null ? !description.equals(auction.description) : auction.description != null) return false;
        if (fotourl != null ? !fotourl.equals(auction.fotourl) : auction.fotourl != null) return false;
        if (closeprice != null ? !closeprice.equals(auction.closeprice) : auction.closeprice != null) return false;
        if (closedate != null ? !closedate.equals(auction.closedate) : auction.closedate != null) return false;
        if (closenumberofbids != null ? !closenumberofbids.equals(auction.closenumberofbids) : auction.closenumberofbids != null)
            return false;
        if (sellerid != null ? !sellerid.equals(auction.sellerid) : auction.sellerid != null) return false;
        if (startprice != null ? !startprice.equals(auction.startprice) : auction.startprice != null) return false;
        if (auctionid != null ? !auctionid.equals(auction.auctionid) : auction.auctionid != null) return false;
        if (active != null ? !active.equals(auction.active) : auction.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startdate != null ? startdate.hashCode() : 0;
        result = 31 * result + (maxbid != null ? maxbid.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fotourl != null ? fotourl.hashCode() : 0);
        result = 31 * result + (closeprice != null ? closeprice.hashCode() : 0);
        result = 31 * result + (closedate != null ? closedate.hashCode() : 0);
        result = 31 * result + (closenumberofbids != null ? closenumberofbids.hashCode() : 0);
        result = 31 * result + (sellerid != null ? sellerid.hashCode() : 0);
        result = 31 * result + (startprice != null ? startprice.hashCode() : 0);
        result = 31 * result + (auctionid != null ? auctionid.hashCode() : 0);
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    public User getUsersBySellerid() {
        return usersBySellerid;
    }

    public void setUsersBySellerid(User usersBySellerid) {
        this.usersBySellerid = usersBySellerid;
    }

    public List<AuctionCategory> getAuctioncategoriesByAuctionid() {
        return auctioncategoriesByAuctionid;
    }

    public void setAuctioncategoriesByAuctionid(List<AuctionCategory> auctioncategoriesByAuctionid) {
        this.auctioncategoriesByAuctionid = auctioncategoriesByAuctionid;
    }

    public Collection<Bid> getBidsByAuctionid() {
        return bidsByAuctionid;
    }

    public void setBidsByAuctionid(Collection<Bid> bidsByAuctionid) {
        this.bidsByAuctionid = bidsByAuctionid;
    }

    public Collection<FavoriteAuction> getFavoriteauctionsByAuctionid() {
        return favoriteauctionsByAuctionid;
    }

    public void setFavoriteauctionsByAuctionid(Collection<FavoriteAuction> favoriteauctionsByAuctionid) {
        this.favoriteauctionsByAuctionid = favoriteauctionsByAuctionid;
    }

    public Collection<PurchasedAuction> getPurchasedauctionsByAuctionid() {
        return purchasedauctionsByAuctionid;
    }

    public void setPurchasedauctionsByAuctionid(Collection<PurchasedAuction> purchasedauctionsByAuctionid) {
        this.purchasedauctionsByAuctionid = purchasedauctionsByAuctionid;
    }

    public Collection<Suggestedauction> getSuggestedauctionsByAuctionid() {
        return suggestedauctionsByAuctionid;
    }

    public void setSuggestedauctionsByAuctionid(Collection<Suggestedauction> suggestedauctionsByAuctionid) {
        this.suggestedauctionsByAuctionid = suggestedauctionsByAuctionid;
    }

    public AuctionDTO toDTO(){
        AuctionDTO dto = new AuctionDTO();

        dto.setId(auctionid);

        dto.setName(title);
        dto.setCategory(auctioncategoriesByAuctionid.get(0).getName());
        dto.setStartPrice(startprice);
        dto.setActive(active);
        dto.setCloseDate(closedate);
        dto.setCloseNumberofBids(closenumberofbids);
        dto.setClosePrice(closeprice);
        dto.setMaxBid(maxbid);
        dto.setStartDate(startdate);
        dto.setFotourl(fotourl);
        dto.setCategoryList(CategoryService.toDTO(auctioncategoriesByAuctionid));
        dto.setStartPrice(startprice);

        //dto.setUserList(UserService.toDTO(usersList));
        dto.setSellerID(Long.parseLong(usersBySellerid.getUserid().toString()));
        dto.setDescription(description);
        dto.setCategoryId(auctioncategoriesByAuctionid.get(0).getCategoryid());

        dto.setSeller(String.format("%s %s", usersBySellerid.getName(), usersBySellerid.getSurname()));

        return dto;
    }

    public static List<AuctionDTO> toDTO(List<Auction> auctions){
        List<AuctionDTO> dtos = new ArrayList<>(auctions.size());

        for(Auction auction : auctions){
            dtos.add(auction.toDTO());
        }

        return dtos;
    }
}
