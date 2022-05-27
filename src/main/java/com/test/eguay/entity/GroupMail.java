package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "groupsmail", schema = "public", catalog = "da1knun38jg1va")
public class GroupMail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "groupid", nullable = false)
    private long groupid;
    @Basic
    @Column(name = "mailid", nullable = false)
    private long mailid;
    @ManyToOne
    @JoinColumn(name = "groupid", referencedColumnName = "groupid", nullable = false, insertable = false, updatable = false)
    private Group groupsByGroupid;
    @ManyToOne
    @JoinColumn(name = "mailid", referencedColumnName = "mailid", nullable = false, insertable = false, updatable = false)
    private Mail mailByMailid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getGroupid() {
        return groupid;
    }

    public void setGroupid(long groupid) {
        this.groupid = groupid;
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

        GroupMail groupMail = (GroupMail) o;

        if (groupid != groupMail.groupid) return false;
        if (mailid != groupMail.mailid) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (groupid ^ (groupid >>> 32));
        result = 31 * result + (int) (mailid ^ (mailid >>> 32));
        return result;
    }

    public Group getGroupsByGroupid() {
        return groupsByGroupid;
    }

    public void setGroupsByGroupid(Group groupsByGroupid) {
        this.groupsByGroupid = groupsByGroupid;
    }

    public Mail getMailByMailid() {
        return mailByMailid;
    }

    public void setMailByMailid(Mail mailByMailid) {
        this.mailByMailid = mailByMailid;
    }
}
