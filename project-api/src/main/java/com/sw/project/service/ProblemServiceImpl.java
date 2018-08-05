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
	
		//problem은 멤버변수로 project(code), title을 가짐
		//여기서도 또 code가 있는지 확인해야하나? 너무 IO가 많은데
		
		if(problem.getTitle() == null) { //have to fix it !!!!!!!!!!!!!!!!!!!!!
			return false;
		}
		
		problemRepository.saveAndFlush(problem);
		return true;
		
		
		
	}
	
	
	
}
