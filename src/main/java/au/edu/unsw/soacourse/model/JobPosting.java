package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JobPosting {

	private String shortKey;
	private String securityKey;
	private String _jobID;
	private String _companyProfileID;
	private String title;
	private String salaryRate;
	private String positionType;
	private String location;
	private String jobDescription;
	private String requirement;
	private String status;
	
	//constructor
	public JobPosting(){
		
	}
	
	public JobPosting(String _jobID, String _companyProfileID){
		this._jobID = _jobID;
		this._companyProfileID = _companyProfileID;
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
	 * @return the salaryRate
	 */
	public String getSalaryRate() {
		return salaryRate;
	}
	/**
	 * @param salaryRate the salaryRate to set
	 */
	public void setSalaryRate(String salaryRate) {
		this.salaryRate = salaryRate;
	}
	/**
	 * @return the positionType
	 */
	public String getPositionType() {
		return positionType;
	}
	/**
	 * @param positionType the positionType to set
	 */
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * @return the jobDescription
	 */
	public String getJobDescription() {
		return jobDescription;
	}
	/**
	 * @param jobDescription the jobDescription to set
	 */
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	/**
	 * @return the requirement
	 */
	public String getRequirement() {
		return requirement;
	}
	/**
	 * @param requirement the requirement to set
	 */
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
