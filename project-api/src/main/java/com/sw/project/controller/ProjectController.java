package com.sw.project.controller;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

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
import com.sw.project.domain.Problem;
import com.sw.project.domain.Project;
import com.sw.project.exception.DataFormatException;
import com.sw.project.exception.ResourceNotFoundException;
import com.sw.project.repository.ProblemRepository;
import com.sw.project.service.ProjectService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/project")
@RestController
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
		
	@Autowired
	private ProblemRepository problemRepository;
	
	@RequestMapping(value = "/{code}", method = RequestMethod.GET,
			produces = {"application/json", "application/xml"})
	@ApiOperation(value = "code로 프로젝트 조회", notes = "code는 6자리 영문과 숫자조합 ")
	@ApiParam(name = "code", value = "code to send", required = true)
	public ResponseEntity<?> getProject(@Valid @PathVariable("code") final String code) { //code로 프로젝트 찾음(code unique). 
												//sub-problem도 포함(Set)
		//@ApiParam(name = "code", value = "code to send", required = true)
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
	@ApiOperation(value = "프로젝트 생성", notes = "code는 자동생성, title(String) 필요")
	@ApiParam(name = "title", value = "title to send(json)", required = true)
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
	@ApiOperation(value = "프로젝트 삭제", notes = "code 필요")
	@ApiParam(name = "code", value = "code to send", required = true)
	ResponseEntity<?> deleteProject(@Valid @PathVariable("code") final String code){
	
		if(code.length() < 6 || code.equals(""))
			throw new DataFormatException("Please Check your code");
		
		Collection<Problem> problemCollection = problemRepository.findByProblemWithCode((code));
		
		problemRepository.deleteInBatch(problemCollection); //problem부터 지우고
		
		if(projectService.deleteProjectByCode(code))
			return new ResponseEntity<>(HttpStatus.OK); //project delete
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	static String getJson(String ipt) { /*String to Json Converter*/ 
		
		JsonObject object = new JsonObject();
		object.addProperty("result", ipt);
		return new Gson().toJson(object);
	}		

}
