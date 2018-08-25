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
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
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


	@Override
	public Boolean deleteProject(String code) {
		// TODO Auto-generated method stub
		try {
			projectRepository.deleteByCode(code);
			logger.info("Code !!");
			return true;
		}catch(Exception e) {
			logger.info(e.getMessage());
			return false;
		}
	}

	@Override
	public Boolean updateProject(Project project) {
		
		if(projectRepository.saveAndFlush(project).equals(null)) 
			return false;
		
		return true;
	}

}





