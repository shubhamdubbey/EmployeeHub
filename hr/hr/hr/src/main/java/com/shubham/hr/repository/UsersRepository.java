package com.shubham.hr.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shubham.hr.entity.Users;


@Repository
public interface UsersRepository extends CrudRepository<Users,Integer> {

	
}
