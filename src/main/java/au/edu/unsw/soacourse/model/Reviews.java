package au.edu.unsw.soacourse.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reviews {

	private String shortKey;
	private String securityKey;
	private String _reviewID;
	private String _appID;
	private String first_reviewerID;
	private String firstComment;
	private String firstDecision;
	private String second_reviewerID;
	private String secondComment;
	private String secondDecision;
	private String status;
	
	public Reviews(){
		
	}
	
	public Reviews(String _reviewID, String _appID){
		this._reviewID = _reviewID;
		this._appID = _appID;
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
	 * @return the first_reviewerID
	 */
	public String getFirst_reviewerID() {
		return first_reviewerID;
	}

	/**
	 * @param first_reviewerID the first_reviewerID to set
	 */
	public void setFirst_reviewerID(String first_reviewerID) {
		this.first_reviewerID = first_reviewerID;
	}

	/**
	 * @return the firstComment
	 */
	public String getFirstComment() {
		return firstComment;
	}

	/**
	 * @param firstComment the firstComment to set
	 */
	public void setFirstComment(String firstComment) {
		this.firstComment = firstComment;
	}

	/**
	 * @return the firstDecision
	 */
	public String getFirstDecision() {
		return firstDecision;
	}

	/**
	 * @param firstDecision the firstDecision to set
	 */
	public void setFirstDecision(String firstDecision) {
		this.firstDecision = firstDecision;
	}

	/**
	 * @return the second_reviewerID
	 */
	public String getSecond_reviewerID() {
		return second_reviewerID;
	}

	/**
	 * @param second_reviewerID the second_reviewerID to set
	 */
	public void setSecond_reviewerID(String second_reviewerID) {
		this.second_reviewerID = second_reviewerID;
	}

	/**
	 * @return the secondComment
	 */
	public String getSecondComment() {
		return secondComment;
	}

	/**
	 * @param secondComment the secondComment to set
	 */
	public void setSecondComment(String secondComment) {
		this.secondComment = secondComment;
	}

	/**
	 * @return the secondDecision
	 */
	public String getSecondDecision() {
		return secondDecision;
	}

	/**
	 * @param secondDecision the secondDecision to set
	 */
	public void setSecondDecision(String secondDecision) {
		this.secondDecision = secondDecision;
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
