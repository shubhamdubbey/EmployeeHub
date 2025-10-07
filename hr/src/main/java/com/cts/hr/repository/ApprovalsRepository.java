package com.cts.hr.repository;

import com.cts.hr.entity.Approvals;
import com.cts.hr.utility.ApprovalStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalsRepository extends CrudRepository<Approvals, String> {
    List<Approvals> findByApproverIdAndStatus(int approverId, ApprovalStatus status);

}