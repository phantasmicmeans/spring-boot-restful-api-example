package com.sw.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "Problem")
public class Problem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idx")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idx;
	
	@Column(name = "title")
	private String title;
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "code", referencedColumnName = "code", nullable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private Project project; //Problem은 Project에 Many to one이고, 
	
	public Problem() { }//JPA constructor
	
	public Problem(String title, final Project project) {
		
		this.project = project;
		this.title = title;
	}
	
	public Long getIdx() {
		return idx;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getProblemCode() {
		return this.project.getCode();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idx : "+ idx + " title : "+ title + "code : "+ project.getCode();
	}
}
