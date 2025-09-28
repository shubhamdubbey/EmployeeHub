package com.shubham.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shubham.hr.entity.GradesHistory;


@Repository
public interface GradesHistoryRepository extends CrudRepository<GradesHistory,Integer> {

	
}
