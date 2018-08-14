package com.sw.project.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw.project.domain.subProblem;
import com.sw.project.repository.subProblemRepository;

@Service("subProblemService")
public class subProblemServiceImpl implements subProblemService{

	
	@Autowired
	private subProblemRepository subproblemRepository;
	
	@Override
	public Boolean saveSubProblem(subProblem subproblem) {

		try {
			subproblemRepository.saveAndFlush(subproblem);
			return true;
			
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public Optional<subProblem> getSubProblemById(int idx) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(subproblemRepository.getSubProblem(idx));
	}

}
