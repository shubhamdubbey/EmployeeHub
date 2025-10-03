package com.cts.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.hr.entity.Users;


@Repository
public interface UsersRepository extends CrudRepository<Users,Integer> {
    boolean existsByEmailAddress(String emailAddress);

}
