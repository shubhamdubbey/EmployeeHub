package com.cts.hr.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.hr.entity.Grades;


@Repository
public interface GradesRepository extends JpaRepository<Grades,Integer> {
	
}
