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
public interface ProblemRepository extends JpaRepository<Problem, Long>{
	
	@Query(value = "SELECT * FROM problem WHERE code=?1",  nativeQuery = true)
	Collection<Problem> findByProblemWithCode(@Param("code") String code);
	
	@Query(value = "SELECT * FROM problem WHERE idx=?1", nativeQuery = true)
	Problem getProblem(Long idx);
	
	Problem findByIdx(Long idx);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM problem WHERE code=?1", nativeQuery = true)
	void deleteAllProblemByCodeInQuery(@Param("code") String code);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM subproblem WHERE pro_idx = (SELECT idx FROM problem WHERE code=?1)", nativeQuery = true)
	void deleteAllSubProblemByCodeInQuery(@Param("code") String code);
	
}	

	