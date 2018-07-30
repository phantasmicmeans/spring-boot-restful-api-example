package com.sw.project.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Problem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idx;
	private String title;
	
	@ManyToOne
	@JoinColumn(name = "code")
	private Project project; //Problem은 Project에 Many to one이고, 
	//project.code를 외래키로 갖는다.
	
	public int getIdx() {
		return idx;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "idx : "+ idx + " title : "+ title;
	}
}
