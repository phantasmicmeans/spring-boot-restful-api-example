package com.sw.project.service;

import com.sw.project.domain.Project;

public interface ProjectService {

	Boolean saveProject(Project project);
	Project findProjectByCode(String code);
	
}
