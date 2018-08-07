package com.sw.project.controller;

import java.net.URI;
import java.util.Collection;

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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sw.project.domain.Problem;
import com.sw.project.domain.Project;
import com.sw.project.exception.DataFormatException;
import com.sw.project.exception.ElementNullException;
import com.sw.project.exception.ResourceNotFoundException;
import com.sw.project.repository.ProblemRepository;
import com.sw.project.service.ProblemService;
import com.sw.project.service.ProjectService;

@RestController
@RequestMapping(value = "/problem")
public class ProblemController {

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
	
	
	@RequestMapping(value = "/{code}" , method = RequestMethod.POST
			,consumes = "application/json")
	public ResponseEntity<?> saveProblem(@Valid @RequestBody Problem problem ,@PathVariable("code") final String code){ //save
		
		if(code.length() < 6 || code.equals(""))
			throw new DataFormatException("Please check your code");
		
		Project project = projectService.findProjectByCode(code)
								.orElseThrow(() -> new ResourceNotFoundException("No Project with that Code"));
		
		problem.setProject(project);
		
		if(problemService.saveProblem(problem)) {
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(problem.getTitle()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		
		String result = "Data Not Valid, Please Check Yout title";
		return new ResponseEntity<String> (getJson(result), HttpStatus.BAD_REQUEST);
	}
	
	static String getJson(String result) {
		
		JsonObject object = new JsonObject();
		object.addProperty("result", result);
		return new Gson().toJson(object);
	}

	
}
