package com.sw.project.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sw.project.exception.DataFormatException;
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
	
		if(code.length() < 6 || code.equals("")) 
			throw new DataFormatException("Please Check your code");
				
		Project project = projectService.findProjectByCode(code)
								.orElseThrow(() -> new ResourceNotFoundException("No Project with that code"));
								//find project -> 404

		
		return new ResponseEntity<Project> (project, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/", method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"})
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createProject(@Valid @RequestBody Project project) {

		String title = project.getTitle();

		if(title.length() < 5)
			throw new DataFormatException("Title must be more than length 5");
		
		project = new Project(title);
		
		String result = projectService.saveProject(project);
		
		if(result.equals("1"))	{ 	//title만 받고, code는 생성자가 랜덤으로 생성.
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().buildAndExpand(project.getTitle()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		return new ResponseEntity<String> (getJson(result), HttpStatus.BAD_REQUEST);

	}

	static String getJson(String ipt) { /*String to Json Converter*/ 
		
		JsonObject object = new JsonObject();
		object.addProperty("result", ipt);
		return new Gson().toJson(object);
	}		

}
