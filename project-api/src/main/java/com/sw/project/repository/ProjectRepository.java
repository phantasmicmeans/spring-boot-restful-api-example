package com.sw.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sw.project.domain.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {
	

}
