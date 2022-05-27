package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "usersgroups", schema = "public", catalog = "da1knun38jg1va")
public class UserGroups {
    @Basic
    @Column(name = "userid", nullable = true)
    private Long userid;
    @Basic
    @Column(name = "groupid", nullable = true)
    private Long groupid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "usersgroupsid", nullable = false)
    private Long usersgroupsid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "groupid", referencedColumnName = "groupid", insertable = false, updatable = false)
    private Group groupsByGroupid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getUsersgroupsid() {
        return usersgroupsid;
    }

    public void setUsersgroupsid(Long usersgroupsid) {
        this.usersgroupsid = usersgroupsid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroups that = (UserGroups) o;

        if (userid != null ? !userid.equals(that.userid) : that.userid != null) return false;
        if (groupid != null ? !groupid.equals(that.groupid) : that.groupid != null) return false;
        if (usersgroupsid != null ? !usersgroupsid.equals(that.usersgroupsid) : that.usersgroupsid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (groupid != null ? groupid.hashCode() : 0);
        result = 31 * result + (usersgroupsid != null ? usersgroupsid.hashCode() : 0);
        return result;
    }

    public User getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(User usersByUserid) {
        this.usersByUserid = usersByUserid;
    }

    public Group getGroupsByGroupid() {
        return groupsByGroupid;
    }

    public void setGroupsByGroupid(Group groupsByGroupid) {
        this.groupsByGroupid = groupsByGroupid;
    }
}
