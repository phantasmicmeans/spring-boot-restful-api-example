package com.sw.project.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
	
	//@ManyToOne(fetch = FetchType.LAZY, optional=false)
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "pro_idx", referencedColumnName = "idx", nullable = false)
	private Problem problem; //Problem <-> Project ManyToOne bidirectional

	@NotNull
	@Size(max=200)
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




















