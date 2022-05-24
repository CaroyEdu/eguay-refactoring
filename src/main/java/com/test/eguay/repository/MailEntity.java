package com.test.eguay.repository;

import com.test.eguay.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailEntity extends JpaRepository<Mail, Long> {
}
