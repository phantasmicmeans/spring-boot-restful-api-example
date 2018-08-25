package com.sw.project.service;

import java.util.Optional;

import com.sw.project.domain.Problem;

public interface ProblemService {

	Optional<Problem> getProblemById(Long idx);
	
	Boolean saveProblem(Problem problem);
	//Proejct에 연결해서 저장만 하면 됨.
		
	Boolean deleteAllProblemWithCode(String code); //Problem삭제 -> Foreign key 문제
 
	Boolean deleteSubProblemByCodeInQuery(String code); //-> Subproblem 만 삭제

	Boolean deleteAllProblemAndSubWithCode(String code); //둘다 삭제 
}
