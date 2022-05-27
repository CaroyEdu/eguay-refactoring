package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "usersmail", schema = "public", catalog = "da1knun38jg1va")
public class UserMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "userid", nullable = false)
    private long userid;
    @Basic
    @Column(name = "mailid", nullable = false)
    private long mailid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false, insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "mailid", referencedColumnName = "mailid", nullable = false, insertable = false, updatable = false)
    private Mail mailByMailid;

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

    public long getMailid() {
        return mailid;
    }

    public void setMailid(long mailid) {
        this.mailid = mailid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMail userMail = (UserMail) o;

        if (userid != userMail.userid) return false;
        if (mailid != userMail.mailid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (userid ^ (userid >>> 32));
        result = 31 * result + (int) (mailid ^ (mailid >>> 32));
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
