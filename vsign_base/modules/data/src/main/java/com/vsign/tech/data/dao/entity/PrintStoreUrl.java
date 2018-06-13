package com.vsign.tech.data.dao.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "printstore_url")
public class PrintStoreUrl extends VsignBaseEntity implements Serializable {

	/**
	 * @author Hemraj
	 */
	private static final long	serialVersionUID	= -5306032961843962514L;

	private String				url;
	private PrintStore			printStore;
	private UrlType				urlType;

	@Column(name = "url", length = 200)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@ManyToOne
	@JoinColumn(name = "printstore_id")
	public PrintStore getPrintstore() {
		return printStore;
	}

	public void setPrintstore(PrintStore printStore) {
		this.printStore = printStore;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "url_type_id")
	public UrlType getUrlType() {
		return urlType;
	}

	public void setUrlType(UrlType urlType) {
		this.urlType = urlType;
	}

}
