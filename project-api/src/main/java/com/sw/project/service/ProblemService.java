package com.sw.project.service;

import com.sw.project.domain.Problem;

public interface ProblemService {

	Boolean saveProblem(Problem problem);
	//Proejct에 연결해서 저장만 하면 됨.
	
	void deleteProblem(String code); //삭제
	Boolean deleteAllProblemWithCode(String code);
	
}
