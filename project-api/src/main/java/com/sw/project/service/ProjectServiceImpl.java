package com.sw.project.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw.project.domain.Project;
import com.sw.project.repository.ProjectRepository;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public String saveProject(Project project) {

		//validateProjectCode(project);
		try {
			projectRepository.saveAndFlush(project);
			return "1";
		}catch(Exception e) {
			
			return e.getMessage();
		}

	}

	@Override
	public Optional<Project> findProjectByCode(String code) { //code를 ipt로 해당 Project 정보 추출s
		
		return Optional.ofNullable(projectRepository.getProject(code));
	}
	
	/*
	public void validateProjectCode(Project project) {		//random한 code생성, unique해야함.
	//code is unique, must check necessary
		while(projectRepository.getProject(project.getCode())!=null) {
			project.setCode();
		}
	}*/
}
