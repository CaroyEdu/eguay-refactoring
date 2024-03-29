package com.test.eguay.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
public class Mail {
    @Basic
    @Column(name = "subject", nullable = true, length = -1)
    private String subject;
    @Basic
    @Column(name = "body", nullable = true, length = -1)
    private String body;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "mailid", nullable = false)
    private Long mailid;
    @Basic
    @Column(name = "senderid", nullable = false)
    private Long senderid;
    @Basic
    @Column(name = "sentdate", nullable = true)
    private Date sentdate;
    @OneToMany(mappedBy = "mailByMailid")
    private Collection<GroupMail> groupsmailsByMailid;
    @ManyToOne
    @JoinColumn(name = "senderid", referencedColumnName = "userid", nullable = false)
    private User usersBySenderid;
    @OneToMany(mappedBy = "mailByMailid")
    private Collection<Suggestedauction> suggestedauctionsByMailid;
    @OneToMany(mappedBy = "mailByMailid")
    private Collection<UserMail> usersmailsByMailid;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getMailid() {
        return mailid;
    }

    public void setMailid(Long mailid) {
        this.mailid = mailid;
    }

    public Long getSenderid() {
        return senderid;
    }

    public void setSenderid(Long senderid) {
        this.senderid = senderid;
    }

    public Date getSentdate() {
        return sentdate;
    }

    public void setSentdate(Date sentdate) {
        this.sentdate = sentdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail mail = (Mail) o;

        if (subject != null ? !subject.equals(mail.subject) : mail.subject != null) return false;
        if (body != null ? !body.equals(mail.body) : mail.body != null) return false;
        if (mailid != null ? !mailid.equals(mail.mailid) : mail.mailid != null) return false;
        if (senderid != null ? !senderid.equals(mail.senderid) : mail.senderid != null) return false;
        if (sentdate != null ? !sentdate.equals(mail.sentdate) : mail.sentdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = subject != null ? subject.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (mailid != null ? mailid.hashCode() : 0);
        result = 31 * result + (senderid != null ? senderid.hashCode() : 0);
        result = 31 * result + (sentdate != null ? sentdate.hashCode() : 0);
        return result;
    }

    public Collection<GroupMail> getGroupsmailsByMailid() {
        return groupsmailsByMailid;
    }

    public void setGroupsmailsByMailid(Collection<GroupMail> groupsmailsByMailid) {
        this.groupsmailsByMailid = groupsmailsByMailid;
    }

    public User getUsersBySenderid() {
        return usersBySenderid;
    }

    public void setUsersBySenderid(User usersBySenderid) {
        this.usersBySenderid = usersBySenderid;
    }

    public Collection<Suggestedauction> getSuggestedauctionsByMailid() {
        return suggestedauctionsByMailid;
    }

    public void setSuggestedauctionsByMailid(Collection<Suggestedauction> suggestedauctionsByMailid) {
        this.suggestedauctionsByMailid = suggestedauctionsByMailid;
    }

    public Collection<UserMail> getUsersmailsByMailid() {
        return usersmailsByMailid;
    }

    public void setUsersmailsByMailid(Collection<UserMail> usersmailsByMailid) {
        this.usersmailsByMailid = usersmailsByMailid;
    }
}
