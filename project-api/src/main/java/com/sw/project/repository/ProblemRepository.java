package com.sw.project.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sw.project.domain.Problem;

public interface ProblemRepository extends JpaRepository<Problem, String>{
	
	@Query(value = "SELECT * FROM problem WHERE code=?1",  nativeQuery = true)
	Collection<Problem> findByProjectWithCode(@Param("code") String code);
	
}
	