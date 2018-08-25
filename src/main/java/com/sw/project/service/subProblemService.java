package com.sw.project.service;

import java.util.Optional;

import com.sw.project.domain.subProblem;

public interface subProblemService {

	Boolean saveSubProblem(subProblem subproblem);
	Optional<subProblem> getSubProblemById(int idx);
	
}
