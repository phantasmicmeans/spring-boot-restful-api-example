package com.sw.project.domain;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String code;
	private int idx;
	private String title;
	
	@OneToMany(mappedBy = "Project", cascade = CascadeType.REMOVE)
	private List<Problem> problem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time", nullable = false, updatable = false)
	@CreatedDate
	private Date createdAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_update_time", nullable = false)
	@LastModifiedDate
	private Date last_update_time;
	
	public Project(String title, String code) {
		this.title = title;
		// TODO Auto-generated constructor stub
	}

	public int getIdx() {
		return idx;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idx : " + idx + " title : " + title + " code :" + code;
				
	}
	
	
}
