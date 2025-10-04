package com.cts.hr.repository;

import com.cts.hr.entity.LoginDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDetailsRepository extends CrudRepository<LoginDetails, String> {
}
