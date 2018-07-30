package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ApplicationSearch {
	
	private String shortKey;
	private String securityKey;
	private String _applicationID;
	private String applicationStatus;
	private String _jobID;
	private String jobTitle;
	private String _companyProfileID;
	private String companyEmail;
	private String companyName;
	
	public ApplicationSearch() {

	}

	/**
	 * @return the _applicationID
	 */
	public String get_applicationID() {
		return _applicationID;
	}

	/**
	 * @param _applicationID the _applicationID to set
	 */
	public void set_applicationID(String _applicationID) {
		this._applicationID = _applicationID;
	}

	/**
	 * @return the applicationStatus
	 */
	public String getApplicationStatus() {
		return applicationStatus;
	}

	/**
	 * @param applicationStatus the applicationStatus to set
	 */
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	/**
	 * @return the _jobID
	 */
	public String get_jobID() {
		return _jobID;
	}

	/**
	 * @param _jobID the _jobID to set
	 */
	public void set_jobID(String _jobID) {
		this._jobID = _jobID;
	}

	/**
	 * @return the jobTitle
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * @param jobTitle the jobTitle to set
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * @return the _companyProfileID
	 */
	public String get_companyProfileID() {
		return _companyProfileID;
	}

	/**
	 * @param _companyProfileID the _companyProfileID to set
	 */
	public void set_companyProfileID(String _companyProfileID) {
		this._companyProfileID = _companyProfileID;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}

	/**
	 * @param companyEmail the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	/**
	 * @return the shortKey
	 */
	public String getShortKey() {
		return shortKey;
	}

	/**
	 * @param shortKey the shortKey to set
	 */
	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}

	/**
	 * @return the securityKey
	 */
	public String getSecurityKey() {
		return securityKey;
	}

	/**
	 * @param securityKey the securityKey to set
	 */
	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
}
