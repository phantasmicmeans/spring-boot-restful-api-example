package com.sw.project.controller;

import java.net.URI;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sw.project.domain.Project;
import com.sw.project.exception.DataFormatException;
import com.sw.project.exception.ResourceNotFoundException;
import com.sw.project.repository.ProblemRepository;
import com.sw.project.service.ProjectService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping(value = "api/project")
@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ProblemRepository problemRepository;
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET,
			produces = {"application/json", "application/xml"})
	@ApiOperation(value = "code로 프로젝트 조회", protocols = "http", notes = "code는 6자리 영문과 숫자조합 ")
	public ResponseEntity<?> getProject(@Valid @PathVariable("code") final String code){ //code로 프로젝트 찾음(code unique). 

		
		if(code.length() < 6 || code.equals("")) 
			throw new DataFormatException("Please Check your code");
				
		Project project = projectService.findProjectByCode(code)
								.orElseThrow(() -> new ResourceNotFoundException("No Project with that code"));
								//find project -> 404

		return new ResponseEntity<Project> (project, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST,
			consumes = {"application/json"},
			produces = {"application/json"})
	@ApiOperation(value = "프로젝트 생성", protocols = "http", notes = "code는 자동생성, title(String) 필요")
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

	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	@ApiOperation(value = "code로 프로젝트 삭제", protocols = "http", notes = "code 필요")
	ResponseEntity<?> deleteProject(@Valid @PathVariable("code") final String code){
	
		if(code.length() < 6 || code.equals(""))
			throw new DataFormatException("Please Check your code");
		
		if(!projectService.deleteProject(code)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(HttpStatus.OK); //project delete
		
	}
	
	@RequestMapping(value ="/{code}", method = RequestMethod.PUT,
			consumes = {"application/json"},
			produces = {"application/json"})
	@ApiOperation(value = "project 업데이트", protocols = "http", notes = "code로 project 변경, title 필요")
	public ResponseEntity<?> updateProject(@Valid @PathVariable("code") final String code, @RequestBody Project project)
	{
		if(code.length() < 6 || code.equals(""))
			throw new DataFormatException("Please Check your code");

		Project pjt = projectService.findProjectByCode(code)
								.orElseThrow(() -> new ResourceNotFoundException("No Project with that code"));
		
		pjt.setTitle(project.getTitle());
		
		if(!projectService.updateProject(pjt)) 
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	static String getJson(String ipt) { /*String to Json Converter*/ 
		
		JsonObject object = new JsonObject();
		object.addProperty("result", ipt);
		return new Gson().toJson(object);
	}		
	

}
