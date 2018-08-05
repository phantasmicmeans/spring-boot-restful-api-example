package com.sw.project.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sw.project.domain.Problem;
import com.sw.project.exception.DataFormatException;
import com.sw.project.exception.ElementNullException;
import com.sw.project.exception.ResourceNotFoundException;
import com.sw.project.repository.ProblemRepository;
import com.sw.project.service.ProblemService;
import com.sw.project.service.ProjectService;

@RestController
@RequestMapping(value = "/problem")
public class ProblemController {
	//problem 리팩토링 필요 
	final private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProjectService projectService;
	
	@Autowired 
	ProblemService problemService;
	
	@Autowired
	ProblemRepository problemRepository;
	
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET,
			produces = {"application/json"})
	public ResponseEntity<Collection<Problem>> getProblemByCode(@Valid @PathVariable("code") final String code){
		
		if(code.length() < 6 || code.equals(""))
			throw new DataFormatException("Please check your code");
		
		Collection<Problem> problemCollection = problemRepository.findByProjectWithCode((code));
		
		if(problemCollection.isEmpty())
			throw new ElementNullException("No data with this code");
		
		return new ResponseEntity<Collection<Problem>> (problemCollection, HttpStatus.OK);
			
	}
	
	
	@RequestMapping(value = "/" , method = RequestMethod.POST
			,consumes = "application/json")
	public ResponseEntity<?> saveProblem(@Valid @RequestBody Problem problem){ //save
		
		if(problemService.saveProblem(problem)) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(problem.getTitle()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		
		String result = "Data Not Valid, Please Check Yout title";
		return new ResponseEntity<String> (getJson(result), HttpStatus.BAD_REQUEST);
	}
		

	public String getJson(String ipt) { /*String to Json Converter*/ 
		
		JsonObject object = new JsonObject();
		object.addProperty("result", ipt);
		return new Gson().toJson(object);
	}		
	
}
