package com.vsign.tech.data.dao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Hemraj
 * */
@Entity
@Table(name = "user_calendar_event")
public class UserCalendarEvent extends VsignBaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;
	private User				user;
	private Date				toDate;
	private Date				fromDate;
	private String				event;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "to_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name = "from_date")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name = "event")
	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

}
