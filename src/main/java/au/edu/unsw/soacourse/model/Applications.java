package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Applications {

	private String shortKey;
	private String securityKey;
	private String _appID;
	private String _candidateProfileID;
	private String _jobID;
	private String coverLetter;
	private String status;
	private String _reviewID;
	private String first_ReviewerID;
	private String first_ReviewerUserName;
	private String second_ReviewerID;
	private String second_ReviewerUserName;
	private String capacity;
	
	public Applications(){
		
	}
	
	public Applications(String _appID, String _candidateProfileID){
		this._appID = _appID;
		this._candidateProfileID = _candidateProfileID;
	}

	/**
	 * @return the _appID
	 */
	public String get_appID() {
		return _appID;
	}

	/**
	 * @param _appID the _appID to set
	 */
	public void set_appID(String _appID) {
		this._appID = _appID;
	}

	/**
	 * @return the _candidateProfileID
	 */
	public String get_candidateProfileID() {
		return _candidateProfileID;
	}

	/**
	 * @param _candidateProfileID the _candidateProfileID to set
	 */
	public void set_candidateProfileID(String _candidateProfileID) {
		this._candidateProfileID = _candidateProfileID;
	}

	/**
	 * @return the coverLetter
	 */
	public String getCoverLetter() {
		return coverLetter;
	}

	/**
	 * @param coverLetter the coverLetter to set
	 */
	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
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
	 * @return the _reviewID
	 */
	public String get_reviewID() {
		return _reviewID;
	}

	/**
	 * @param _reviewID the _reviewID to set
	 */
	public void set_reviewID(String _reviewID) {
		this._reviewID = _reviewID;
	}

	/**
	 * @return the first_ReviewerID
	 */
	public String getFirst_ReviewerID() {
		return first_ReviewerID;
	}

	/**
	 * @param first_ReviewerID the first_ReviewerID to set
	 */
	public void setFirst_ReviewerID(String first_ReviewerID) {
		this.first_ReviewerID = first_ReviewerID;
	}

	/**
	 * @return the first_ReviewerUserName
	 */
	public String getFirst_ReviewerUserName() {
		return first_ReviewerUserName;
	}

	/**
	 * @param first_ReviewerUserName the first_ReviewerUserName to set
	 */
	public void setFirst_ReviewerUserName(String first_ReviewerUserName) {
		this.first_ReviewerUserName = first_ReviewerUserName;
	}

	/**
	 * @return the second_ReviewerID
	 */
	public String getSecond_ReviewerID() {
		return second_ReviewerID;
	}

	/**
	 * @param second_ReviewerID the second_ReviewerID to set
	 */
	public void setSecond_ReviewerID(String second_ReviewerID) {
		this.second_ReviewerID = second_ReviewerID;
	}

	/**
	 * @return the second_ReviewerUserName
	 */
	public String getSecond_ReviewerUserName() {
		return second_ReviewerUserName;
	}

	/**
	 * @param second_ReviewerUserName the second_ReviewerUserName to set
	 */
	public void setSecond_ReviewerUserName(String second_ReviewerUserName) {
		this.second_ReviewerUserName = second_ReviewerUserName;
	}

	/**
	 * @return the capacity
	 */
	public String getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity the capacity to set
	 */
	public void setCapacity(String capacity) {
		this.capacity = capacity;
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
