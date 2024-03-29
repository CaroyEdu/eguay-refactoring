package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "userscategory", schema = "public", catalog = "da1knun38jg1va")
public class UserCategory {
    @Basic
    @Column(name = "userid", nullable = false)
    private Long userid;
    @Basic
    @Column(name = "categoryid", nullable = false)
    private Long categoryid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid", nullable = false)
    private Category categoryByCategoryid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCategory that = (UserCategory) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (categoryid != null ? !categoryid.equals(that.categoryid) : that.categoryid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (categoryid != null ? categoryid.hashCode() : 0);
        return result;
    }

    public User getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(User usersByUserid) {
        this.usersByUserid = usersByUserid;
    }

    public Category getCategoryByCategoryid() {
        return categoryByCategoryid;
    }

    public void setCategoryByCategoryid(Category categoryByCategoryid) {
        this.categoryByCategoryid = categoryByCategoryid;
    }
}
