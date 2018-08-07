package com.sw.project.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Project")
public class Project implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idx")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idx;
	
    @Column(name = "code", unique=true, nullable=false) 
	private String code;
	
	@Column(name = "title",nullable=false)
	private String title;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set <Problem> problems = new HashSet<>();

	public Project() {
	}

	public Project(final String title) {
		
		this.code = firstCode();
		this.title = title;
		// TODO Auto-generated constructor stub
	}

	public Long getIdx() {
		return idx;
	}

	public String getTitle() {
		return title;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode() {
		
		this.code = randomCode();
	}

	public void addProblem(Problem problem) {
		problems.add(problem);
	}
	public Set<Problem> getProblem() {
		return problems;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idx : " + idx + " title : " + title + " code :" + code;
				
	}
	
	public String firstCode() {
		
		return randomCode();
	}	
	
	public String randomCode() { //6자리 인증
		
		Random rnd = new Random();
		StringBuffer buf = new StringBuffer();
		for(int i=0;i<6;i++){
		    if(rnd.nextBoolean())
		        buf.append((char)((int)(rnd.nextInt(26))+97));
		    else
		        buf.append((rnd.nextInt(10)));
		}
		return buf.toString();
	}
	
	/*
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update_time", nullable = false)
	@LastModifiedDate
	private Date last_update_time;
	*/
	
	
}
