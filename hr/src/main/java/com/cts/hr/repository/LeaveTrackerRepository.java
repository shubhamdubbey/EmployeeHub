package com.cts.hr.repository;

import com.cts.hr.entity.LeaveTracker;
import org.springframework.data.repository.CrudRepository;

public interface LeaveTrackerRepository extends CrudRepository<LeaveTracker, Integer> {
}
