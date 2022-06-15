// Autores: Pedro Antonio Benito Rojano

package com.test.eguay.repository;

import com.test.eguay.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Transactional
    public void deleteByGroupid(long id);

    List<Group> findAllByGroupidIn(long[] ids);
}
