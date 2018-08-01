package com.sw.project.controller;

import java.net.URI;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sw.project.domain.Project;
import com.sw.project.exception.ResourceNotFoundException;
import com.sw.project.service.ProjectService;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/project")
@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET,
			produces = {"application/json", "application/xml"})
	public ResponseEntity<?> GetProject(@Valid @PathVariable("code") final String code) { //code를 인자로 프로젝트 찾음. 
	
		Project project = projectService.findProjectByCode(code);

		try{
			checkNullPointer(project); //project가 null이면 -> throw NoSuchElement

			return new ResponseEntity<Project> (project, HttpStatus.OK);

		}catch(NoSuchElementException e) {
			String result = "Cannot find Project with Your code";
			return new ResponseEntity<String> (getJson(result), HttpStatus.NOT_FOUND);
		}
	}

	
	@RequestMapping(value = "/", method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project) {

		project = new Project(project.getTitle());

		if(projectService.saveProject(project))	{ 	//title만 받고, code는 생성자가 랜덤으로 생성.
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(project.getTitle()).toUri();
			
			return ResponseEntity.created(location).build();
		}
		String result = "Data Not Valid, Please Check Yout title";
		return new ResponseEntity<String> (getJson(result), HttpStatus.BAD_REQUEST);
		/*
		 * 
		HttpHeaders he = new HttpHeaders();
		he.add("Bad Request", "Data Not Valid, Please Check Your Title");
		return ResponseEntity.badRequest().headers(he).body(project);

		*/
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	private <T> T checkResourceNull(final T resource) { /* ResourceNotFoundException Handler */

		return resource;
	}
	
	@ExceptionHandler(NullPointerException.class)
	private <T> T checkNullPointer(final T resource) {
		
		if(resource==null) {
			throw new NoSuchElementException();
		}
		
		return resource;
	}
	
	public String getJson(String ipt) { /*String to Json Converter*/ 
		
		JsonObject object = new JsonObject();
		object.addProperty("result", ipt);
		return new Gson().toJson(object);
	}		

}
