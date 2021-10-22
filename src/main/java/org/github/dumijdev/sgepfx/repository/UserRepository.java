package org.github.dumijdev.sgepfx.repository;

import org.github.dumijdev.sgepfx.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("select u from User u order by u.login")
    List<User> buscaTodos();

}