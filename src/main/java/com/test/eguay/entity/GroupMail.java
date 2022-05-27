package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "groupsmail", schema = "public", catalog = "da1knun38jg1va")
public class GroupMail {
    @Basic
    @Column(name = "groupid", nullable = false)
    private Long groupid;
    @Basic
    @Column(name = "mailid", nullable = false)
    private Long mailid;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "groupsmailid", nullable = false)
    private Long groupsmailid;
    @ManyToOne
    @JoinColumn(name = "groupid", referencedColumnName = "groupid", nullable = false, insertable = false, updatable = false)
    private Group groupsByGroupid;
    @ManyToOne
    @JoinColumn(name = "mailid", referencedColumnName = "mailid", nullable = false, insertable = false, updatable = false)
    private Mail mailByMailid;

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public Long getMailid() {
        return mailid;
    }

    public void setMailid(Long mailid) {
        this.mailid = mailid;
    }

    public Long getGroupsmailid() {
        return groupsmailid;
    }

    public void setGroupsmailid(Long groupsmailid) {
        this.groupsmailid = groupsmailid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupMail groupMail = (GroupMail) o;

        if (groupid != null ? !groupid.equals(groupMail.groupid) : groupMail.groupid != null) return false;
        if (mailid != null ? !mailid.equals(groupMail.mailid) : groupMail.mailid != null) return false;
        if (groupsmailid != null ? !groupsmailid.equals(groupMail.groupsmailid) : groupMail.groupsmailid != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupid != null ? groupid.hashCode() : 0;
        result = 31 * result + (mailid != null ? mailid.hashCode() : 0);
        result = 31 * result + (groupsmailid != null ? groupsmailid.hashCode() : 0);
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
