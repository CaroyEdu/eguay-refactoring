package com.test.eguay.repository;

import com.test.eguay.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Transactional
    public void deleteByGroupid(long id);
}
