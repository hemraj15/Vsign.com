package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * author : Hemraj Parmar
 */

@MappedSuperclass
public class VsignBaseEntity implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private Long				id;
	private Date				dateCreated;
	protected Date				dateUpdated;
	private String				createdBy;
	private String				lastModifiedBy;
	private String				status;
	private String              description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// TODO need to implement using @createdDate annotation using spring
	@Column(name = "date_created")
	@Temporal(TemporalType.DATE)
	@PrePersist
	public Date getDateCreated() {
		return dateCreated == null ? new Date() : dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	// TODO need to implement using @updatedDate annotation using spring
	@Column(name = "date_updated")
	@Temporal(TemporalType.DATE)
	@PrePersist
	@PostUpdate
	public Date getDateUpdated() {
		return dateUpdated ==null ? new Date():dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "last_modified_by")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Column(name = "status", nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'ACTIVE'")
	public String getStatus() {
		return status == null ? "ACTIVE" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "description", length = 200)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}