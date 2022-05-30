package com.test.eguay.repository;

import com.test.eguay.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findUserByusernameAndPassword(String username , String password);

    public User findUserByUserid(int id);
}
