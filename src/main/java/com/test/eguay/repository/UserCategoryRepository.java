package com.test.eguay.repository;

import com.test.eguay.entity.Rol;
import com.test.eguay.entity.User;
import com.test.eguay.entity.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

    @Query("select c from UserCategory c where c.categoryid = :categoryId AND c.usersByUserid <> :user")
    public List<UserCategory> findAllFavUserCategories(@Param("categoryId") long categoryId , @Param("user") User user);

    public List<UserCategory> findAllByUsersByUserid(User Userid);
}
