package com.test.eguay.repository;

import com.test.eguay.entity.GroupMail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMailRepository extends JpaRepository<GroupMail, Long> {
}