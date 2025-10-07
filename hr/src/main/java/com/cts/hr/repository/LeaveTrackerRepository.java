package com.cts.hr.repository;

import com.cts.hr.entity.LeaveTracker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeaveTrackerRepository extends CrudRepository<LeaveTracker, String> {
    List<LeaveTracker> findByEmployeeId(int employeeId);
}
