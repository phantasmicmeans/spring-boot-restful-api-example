package com.sw.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sw.project.domain.subProblem;

@Repository
public interface subProblemRepository extends JpaRepository<subProblem, Long>{

	@Query(value = "SELECT * FROM subproblem WHERE code=?1",  nativeQuery = true)
	subProblem getSubProblem(int idx); 

	
}
