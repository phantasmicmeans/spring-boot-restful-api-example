package com.sw.project.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sw.project.domain.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, String>{
	
	@Query(value = "SELECT * FROM problem WHERE code=?1",  nativeQuery = true)
	Collection<Problem> findByProblemWithCode(@Param("code") String code);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM problem WHERE code=?1", nativeQuery = true)
	void deleteProblem(@Param("code") String code);
}	

	