package com.shubham.hr.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shubham.hr.entity.Grades;


@Repository
public interface GradesRepository extends JpaRepository<Grades,Integer> {

	
}
