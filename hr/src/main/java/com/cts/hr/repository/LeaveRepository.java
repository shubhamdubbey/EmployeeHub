package com.cts.hr.repository;

import com.cts.hr.entity.Leaves;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends CrudRepository<Leaves, Integer> {
}
