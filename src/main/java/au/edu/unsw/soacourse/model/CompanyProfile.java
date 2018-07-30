package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompanyProfile {

	private String shortKey;
	private String securityKey;
	private String _profileID;
	private String companyName;
	private String ABN;
	private String companyAddress;
	private String companyEmail;
	private String contactNumber;
	private String introduction;
	private String mainPageURL;
	
	//constructor
	public CompanyProfile(){
		
	}
	
	public CompanyProfile(String _profileID){
		this._profileID = _profileID;
	}
	
	/**
	 * @return the _profileID
	 */
	public String get_profileID() {
		return _profileID;
	}
	/**
	 * @param _profileID the _profileID to set
	 */
	public void set_profileID(String _profileID) {
		this._profileID = _profileID;
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
	 * @return the aBN
	 */
	public String getABN() {
		return ABN;
	}
	/**
	 * @param aBN the aBN to set
	 */
	public void setABN(String aBN) {
		ABN = aBN;
	}
	/**
	 * @return the companyAddress
	 */
	public String getCompanyAddress() {
		return companyAddress;
	}
	/**
	 * @param companyAddress the companyAddress to set
	 */
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	/**
	 * @return the contactNumber
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * @param contactNumber the contactNumber to set
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * @return the introduction
	 */
	public String getIntroduction() {
		return introduction;
	}
	/**
	 * @param introduction the introduction to set
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**
	 * @return the mainPageURL
	 */
	public String getMainPageURL() {
		return mainPageURL;
	}
	/**
	 * @param mainPageURL the mainPageURL to set
	 */
	public void setMainPageURL(String mainPageURL) {
		this.mainPageURL = mainPageURL;
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
