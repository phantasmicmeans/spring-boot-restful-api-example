package com.sw.project.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="subproblem")
public class subProblem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idx")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "pro_idx", referencedColumnName = "idx", nullable = false)
	private Problem problem;

	@Column(name="content", nullable = false)
	private String content;
	
	@Column(name="count")
	private long count=0;
	
	public subProblem() {}
	
	public subProblem(String content, Problem problem) {
		
		this.problem = problem;
		this.content = content;
	}
	
	public Long getIdx() {
		return idx;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}
	
	
	
}




















