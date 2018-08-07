package com.sw.project.service;

import java.util.Collection;

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
	public Boolean saveProblem(Problem problem) {
	
		if(problem.getTitle() == null) { //have to fix it !!!!!!!!!!!!!!!!!!!!!
			return false;
		}
		problemRepository.saveAndFlush(problem);
		return true;
	}

	@Override
	public void deleteProblem(String code) {
		
		problemRepository.deleteProblem(code);
	}

	@Override
	public Boolean deleteAllProblemWithCode(String code) {
		// TODO Auto-generated method stub
		
		Collection<Problem> list = problemRepository.findByProblemWithCode(code);

		if(list.isEmpty()) return false;
		else {
			problemRepository.deleteInBatch(list);
			return true;
		}
	}
	
	

	
}
