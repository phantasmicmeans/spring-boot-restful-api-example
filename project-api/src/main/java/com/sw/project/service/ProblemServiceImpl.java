package com.sw.project.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw.project.domain.Problem;
import com.sw.project.repository.ProblemRepository;

@Service("problemService")
public class ProblemServiceImpl implements ProblemService{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProblemRepository problemRepository;
	
	@Override
	public Optional<Problem> getProblemById(Long idx) {
		// TODO Auto-generated method stub
		
		return Optional.ofNullable(problemRepository.getProblem(idx));
	}

	
	@Override
	public Boolean saveProblem(Problem problem) {
	
		if(problem.getTitle() == null) { //have to fix it !!!!!!!!!!!!!!!!!!!!!
			return false;
		}
		problemRepository.saveAndFlush(problem);
		return true;
	}

	@Override
	public Boolean deleteAllProblemWithCode(String code) {
		// TODO Auto-generated method stub
		
		try {
			problemRepository.deleteAllProblemByCodeInQuery(code);
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deleteSubProblemByCodeInQuery(String code) {
		// TODO Auto-generated method stub
		try {
			problemRepository.deleteAllSubProblemByCodeInQuery(code);
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override 
	public Boolean deleteAllProblemAndSubWithCode(String code) {
		
		try{
			problemRepository.deleteAllSubProblemByCodeInQuery(code);
			problemRepository.deleteAllProblemByCodeInQuery(code);
			return true;
			
		}catch(Exception e) {
			return false;
		}
		
		
	}
}























