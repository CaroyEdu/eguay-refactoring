package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "usersmail", schema = "public", catalog = "da1knun38jg1va")
public class UserMail {
    @Basic
    @Column(name = "userid", nullable = false)
    private Long userid;
    @Basic
    @Column(name = "mailid", nullable = false)
    private Long mailid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "usersmailid", nullable = false)
    private Long usersmailid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "mailid", referencedColumnName = "mailid", nullable = false, insertable = false, updatable = false)
    private Mail mailByMailid;

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getMailid() {
        return mailid;
    }

    public void setMailid(Long mailid) {
        this.mailid = mailid;
    }

    public Long getUsersmailid() {
        return usersmailid;
    }

    public void setUsersmailid(Long usersmailid) {
        this.usersmailid = usersmailid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMail userMail = (UserMail) o;

        if (userid != null ? !userid.equals(userMail.userid) : userMail.userid != null) return false;
        if (mailid != null ? !mailid.equals(userMail.mailid) : userMail.mailid != null) return false;
        if (usersmailid != null ? !usersmailid.equals(userMail.usersmailid) : userMail.usersmailid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (mailid != null ? mailid.hashCode() : 0);
        result = 31 * result + (usersmailid != null ? usersmailid.hashCode() : 0);
        return result;
    }

    public User getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(User usersByUserid) {
        this.usersByUserid = usersByUserid;
    }

    public Mail getMailByMailid() {
        return mailByMailid;
    }

    public void setMailByMailid(Mail mailByMailid) {
        this.mailByMailid = mailByMailid;
    }
}
