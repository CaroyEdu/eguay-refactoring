package com.test.eguay.repository;

import com.test.eguay.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    @Query("select m " +
            "from Mail m " +
            "join UserMail um on m.mailid = um.mailid " +
            "join User u on um.userid = u.userid " +
            "where u.userid = :userId")
    public List<Mail> findMailsSentDirectlyToUser(@Param("userId") int userId);

    @Query("select m " +
            "from Mail m " +
            "join GroupMail gm on m.mailid = gm.mailid " +
            "join Group g on gm.groupid = g.groupid " +
            "join UserGroups ug on g.groupid = ug.groupid " +
            "join User u on ug.userid = u.userid " +
            "where u.userid = :userId")
    public List<Mail> findMailsSentToUserByAGroup(@Param("userId") int userId);
}
