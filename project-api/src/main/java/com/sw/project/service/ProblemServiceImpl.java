package com.sw.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw.project.domain.Problem;
import com.sw.project.repository.ProblemRepository;

@Service("problemService")
public class ProblemServiceImpl implements ProblemService{

	@Autowired
	private ProblemRepository problemRepository;

	@Override
	public Boolean saveProblem(Problem problem) {
	
		if(problem.getTitle() == null) { //have to fix it !!!!!!!!!!!!!!!!!!!!!
			return false;
		}
		
		problemRepository.saveAndFlush(problem);
		return true;
		
	}
	
}
