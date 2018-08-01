package com.sw.project.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sw.project.domain.Problem;
import com.sw.project.exception.ResourceNotFoundException;
import com.sw.project.repository.ProblemRepository;
import com.sw.project.service.ProjectService;

@RestController
@RequestMapping(value = "/problem")
public class ProblemController {
	//problem 리팩토링 필요 
	final private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProblemRepository problemRepository;
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET,
			produces = {"application/json"})
	public Collection<Problem> getProblemByCode(@Valid @PathVariable("code") final String code){
		
		return problemRepository.findByProjectCode(checkResourceNull(code));
		
	}
	
	@RequestMapping(value = "/code/{codes}", method = RequestMethod.GET
				,produces = {"application/json"})
	public ResponseEntity<?> getProblemByCodes(@Valid @PathVariable("codes") final String code){
		
		if(!code.equals("aa")) {
			Collection<Problem> pro = problemRepository.findByProjectCode(code);
			return new ResponseEntity<>(pro, HttpStatus.FOUND);
		}
		
		HttpHeaders he = new HttpHeaders();
		he.add("Bad Request", "Data Not Valid");
		logger.info(he.toString());
		return ResponseEntity.badRequest().headers(he).build();
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	private <T> T checkResourceNull(final T resource) {

		return resource;
			
	}
}
