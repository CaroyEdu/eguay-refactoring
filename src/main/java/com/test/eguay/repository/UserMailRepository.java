package com.test.eguay.repository;

import com.test.eguay.entity.UserMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMailRepository extends JpaRepository<UserMail, Long> {
}