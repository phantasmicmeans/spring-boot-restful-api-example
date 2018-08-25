package com.sw.project.domain;


public class subProblemBody {

	private Long pro_idx;
	private String content;
	
	public subProblemBody() {
		// TODO Auto-generated constructor stub
	}
	public subProblemBody(Long pro_idx, String content) {
		this.pro_idx = pro_idx;
		this.content = content;
	}
	public Long getPro_idx() {
		return pro_idx;
	}
	public void setPro_idx(Long pro_idx) {
		this.pro_idx = pro_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
