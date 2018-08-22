package com.sw.project.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sw.project.domain.Problem;
import com.sw.project.domain.subProblem;
import com.sw.project.domain.subProblemBody;
import com.sw.project.exception.DataFormatException;
import com.sw.project.exception.ResourceNotFoundException;
import com.sw.project.service.ProblemService;
import com.sw.project.service.subProblemService;

@RestController
@RequestMapping(value = "api/subproblem")
public class subProblemController {
	@Autowired
	subProblemService subproblemService;
	
	@Autowired
	ProblemService problemService;
	
	
	@RequestMapping(value="", method = RequestMethod.POST,
			produces = {"application/json"})
	public ResponseEntity<?> saveSubProblem(@Valid @RequestBody subProblemBody problemBody/*subProblem subproblem*/){
		
	
		if(problemBody.getContent().length() < 10 || problemBody.getContent().equals("")) {
			throw new DataFormatException("Please check your content, content must be more than 10 length");
		} //content length check
		
		
		Problem problem = problemService.getProblemById(problemBody.getPro_idx()) // problem search with idx 
				.orElseThrow(()-> new ResourceNotFoundException("Cannot found problem with that idx"));
		
		subProblem subproblem = new subProblem(problemBody.getContent(), problem);
		
		if(subproblemService.saveSubProblem(subproblem)) { //save
			problem.addSubProblem(subproblem);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(subproblem.getIdx()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		return new ResponseEntity<Void> (HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/*@RequestMapping(value="/{idx}/all", method = RequestMethod.GET,
			produces = {"application/json"})
	public ResponseEntity<?> getSubProblem(@Valid @PathVariable final String idx){
		
		if(idx == null) throw new DataFormatException("Please check your content, content must have idx");
		
		
	}*/
	
	
	
	
	
	

}
