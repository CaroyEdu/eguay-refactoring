package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "userscategory", schema = "public", catalog = "da1knun38jg1va")
public class UserCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "userid", nullable = false)
    private long userid;
    @Basic
    @Column(name = "categoryid", nullable = false)
    private long categoryid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "categoryid", referencedColumnName = "categoryid", nullable = false, insertable = false, updatable = false)
    private Category categoryByCategoryid;

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

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCategory that = (UserCategory) o;

        if (userid != that.userid) return false;
        if (categoryid != that.categoryid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + (int) (categoryid ^ (categoryid >>> 32));
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
