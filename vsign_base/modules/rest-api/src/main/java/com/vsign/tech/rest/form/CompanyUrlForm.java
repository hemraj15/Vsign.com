/**
 * 
 */
package com.vsign.tech.rest.form;

/**
 * @author Hemraj
 *
 */
public class CompanyUrlForm {

	private Long	urlTypeId;
	private String	url;

	public Long getUrlTypeId() {
		return urlTypeId;
	}

	public void setUrlTypeId(Long urlTypeId) {
		this.urlTypeId = urlTypeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyUrlForm [urlTypeId=");
		builder.append(urlTypeId);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}

}
