package com.test.eguay.repository;

import com.test.eguay.entity.Rol;
import com.test.eguay.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
   // public List<UserCategory> findUserCategoryByCategoryByCategoryidAndOrderByUsersByUserid(Long CategoryId , Integer Userid );
}
