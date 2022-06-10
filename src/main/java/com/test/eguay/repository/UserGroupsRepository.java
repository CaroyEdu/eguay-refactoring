package com.test.eguay.repository;

import com.test.eguay.entity.UserGroups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserGroupsRepository extends JpaRepository<UserGroups, Long> {
    @Transactional
    public void deleteAllByGroupid(long id);
}