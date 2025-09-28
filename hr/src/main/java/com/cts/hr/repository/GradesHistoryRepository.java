package com.cts.hr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cts.hr.entity.GradesHistory;


@Repository
public interface GradesHistoryRepository extends CrudRepository<GradesHistory,Integer> {

}
