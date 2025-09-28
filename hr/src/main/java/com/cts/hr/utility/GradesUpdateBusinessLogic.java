package com.cts.hr.utility;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.cts.hr.entity.GradesHistory;

@Component
public class GradesUpdateBusinessLogic {

	public boolean checkIfGradesIsLesserThanCurrentGrade(int current_id, int new_grade_id) throws GradeUpdateRuleViolationException {
		if(current_id < new_grade_id) {
			throw new GradeUpdateRuleViolationException("You can not demote an employee");
		}
		else
		{
			return false;
		}
	}
	
	public boolean checkIfEmployeeIsNewAndEligible(List<GradesHistory> gradesHistoryList) throws GradeUpdateRuleViolationException {
		long yearsDifference = ChronoUnit.YEARS.between(gradesHistoryList.get(0).getAssignedon(), LocalDate.now());
		if(yearsDifference < 2) {
			throw new GradeUpdateRuleViolationException("For a new employee you can not change grade before 2 years");
		}
		return true;
	}
	
	public boolean checkIfOldEmployeeIsEligibleForPromotion(List<GradesHistory> gradesHistoryList) throws GradeUpdateRuleViolationException {
			for(GradesHistory g : gradesHistoryList) {
				if(ChronoUnit.YEARS.between(g.getAssignedon(), LocalDate.now()) < 1) {
					throw new GradeUpdateRuleViolationException("You can not change employee's grade twice in a year");
				}
			}
			return false;
	}
	
	public List<GradesHistory> giveMeListOfGradesHistory(Iterable<GradesHistory> gradesHistory, int id){
		List<GradesHistory> gradesHistoryList = new ArrayList<>();
		for(GradesHistory g : gradesHistory) {
			if(g.getUsers().getEmployeeId() == id) {
				gradesHistoryList.add(g);
			}
		}
		return gradesHistoryList;
	}
}
