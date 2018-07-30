package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HiringTeam {

	private String shortKey;
	private String securityKey;
	private String _reviewerID;
	private String _companyProfileID;
	private String userName;
	private String password;
	private String skills;
	private String status;
	
	public HiringTeam(){
		
	}
	
	public HiringTeam(String _reviewerID, String password){
		this._reviewerID = _reviewerID;
		this.password = password;
	}

	/**
	 * @return the _reviewerID
	 */
	public String get_reviewerID() {
		return _reviewerID;
	}

	/**
	 * @param _reviewerID the _reviewerID to set
	 */
	public void set_reviewerID(String _reviewerID) {
		this._reviewerID = _reviewerID;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the skills
	 */
	public String getSkills() {
		return skills;
	}

	/**
	 * @param skills the skills to set
	 */
	public void setSkills(String skills) {
		this.skills = skills;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
