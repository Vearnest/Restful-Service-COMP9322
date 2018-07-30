package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CandidateProfile {
	
	//Variables
	private String shortKey;
	private String securityKey;
	private String _profileID;
	private String firstName;
	private String lastName;
	private String nickName;
	private String age;
	private String address;
	private String email;
	private String contactNumber;
	private String currentPosition;
	private String education;
	private String professionalSkill;
	private String experience;
	
	//constructors
	public CandidateProfile(){
		
	}
	public CandidateProfile(String _profileID){
		this._profileID = _profileID;
	}
	
	//_profileID
	public String get_profileID(){
		return _profileID;
	}
	public void set_profileID(String _profileID){
		this._profileID = _profileID;
	}
	//firstName
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	//lastName
	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	//nickName
	public String getNickName(){
		return nickName;
	}
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	//age
	public String getAge(){
		return age;
	}
	public void setAge(String age){
		this.age = age;
	}
	//address
	public String getAddress(){
		return address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	//email
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	//contactNumber
	public String getContactNumber(){
		return contactNumber;
	}
	public void setContactNumber(String contactNumber){
		this.contactNumber = contactNumber;
	}
	//professionalSkill
	public String getProfessionalSkill(){
		return professionalSkill;
	}
	public void setProfessionalSkill(String professionalSkill){
		this.professionalSkill = professionalSkill;
	}
	//experience
	public String getExperience(){
		return experience;
	}
	public void setExperience(String experience){
		this.experience = experience;
	}
	/**
	 * @return the currentPosition
	 */
	public String getCurrentPosition() {
		return currentPosition;
	}
	/**
	 * @param currentPosition the currentPosition to set
	 */
	public void setCurrentPosition(String currentPosition) {
		this.currentPosition = currentPosition;
	}
	/**
	 * @return the educationString
	 */
	public String getEducation() {
		return education;
	}
	/**
	 * @param educationString the educationString to set
	 */
	public void setEducation(String education) {
		this.education = education;
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
