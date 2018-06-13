package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author Hemraj
 * */
@Entity
@Table(name = "printstore_video")
public class PrintStoreVideo extends VsignBaseEntity implements Serializable{
	/**
	 * @author Hemraj
	 */
	private static final long	serialVersionUID	= 3898181382483363986L;
	private String				videoTitle;
	private String				videoPath;
	private String				videoType;
	private PrintStore			printStore;

	@Column(name = "video_title")
	public String getVideoTitle() {
		return videoTitle;
	}

	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	@Column(name = "video_path")
	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	@Column(name = "video_type")
	public String getVideoType() {
		return videoType;
	}

	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "printstore_id")
	public PrintStore getPrintStore() {
		return printStore;
	}

	public void setPrintStore(PrintStore printStore) {
		this.printStore = printStore;
	}

}
