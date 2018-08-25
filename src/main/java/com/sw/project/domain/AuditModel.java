package com.sw.project.domain;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) //생성시간, 수정시간, 생성자, 수정자 등이 구현된 class 
@JsonIgnoreProperties(
		value = {"createdAt", "updatedAt"}, //그중 createdAt, updatedAt은 우리가 따로 쓸것임.
		allowGetters = true
)
public abstract class AuditModel implements Serializable {

	
	private static final long serialVersionUID = 1L;

		@Column(name = "created_at", nullable = false, updatable = false)
		@CreatedDate
		private Date createdAt;
		
		@Column(name = "updated_at", nullable = false)
		@LastModifiedDate
		private Date updatedAt;

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}
		
		
}
