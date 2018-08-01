package com.sw.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw.project.domain.Project;
import com.sw.project.repository.ProjectRepository;


@Service("projectService")
public class ProjectServiceImpl implements ProjectService{
	
	@Autowired
	private ProjectRepository projectRepository;
	

	@Override
	public Boolean saveProject(Project project) {

		if(project.getTitle() == null) //title 미 입력시 유효성검사
			return false;
		
		validateProjectCode(project);
		projectRepository.saveAndFlush(project);
		return true;
	}

	@Override
	public Project findProjectByCode(String code) { //code를 ipt로 해당 Project 정보 추출s
		
		try { //code와 일치하는 project 유효성검사 
			return projectRepository.getProject(code);

		}catch(Exception e) {
			return null;
		}
	}
	
	public void validateProjectCode(Project project) {		//random한 code생성, unique해야함.

		while(projectRepository.getProject(project.getCode())!=null) {
			project.setCode();
		}
	}
}
