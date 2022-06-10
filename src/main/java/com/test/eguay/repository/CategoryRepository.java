package com.test.eguay.repository;

import com.test.eguay.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("select distinct c from Category c join UserCategory uc on (c.categoryid = uc.categoryid) where uc.userid = :user")
    List<Category> findUserFavCategory(@Param("user") Long id);


}
