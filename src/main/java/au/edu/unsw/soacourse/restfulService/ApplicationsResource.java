package au.edu.unsw.soacourse.restfulService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import au.edu.unsw.soacourse.dao.ApplicationsDAO;
import au.edu.unsw.soacourse.dao.CandidateProfileDAO;
import au.edu.unsw.soacourse.dao.HiringTeamDAO;
import au.edu.unsw.soacourse.model.ApplicationSearch;
import au.edu.unsw.soacourse.model.Applications;
import au.edu.unsw.soacourse.model.CandidateProfile;

@Path("/Applications")
public class ApplicationsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{_appID}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Applications getApplications(@PathParam("_appID") String _appID) {
		Applications applications = new Applications();
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		if (_appID == null) {
			return applications;
		}
		applications = applicationsDAO.searchProfile(_appID);
		return applications;
	}
	
	//search applications for one job
	@GET
	@Path("/job_applications")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<ApplicationSearch> getApplicationsForOneJob(@QueryParam("jobID") String _jobID) {
		List<ApplicationSearch> applicationSearchList = new ArrayList<ApplicationSearch>();
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		applicationSearchList = applicationsDAO.searchApplicationsForOneJob(_jobID);
		return applicationSearchList;
	}
	
	//search applications for one person
	@GET
	@Path("/candidate_applications")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<ApplicationSearch> getApplicationsForOnePerson(@QueryParam("candidateID") String _candidateID) {
		List<ApplicationSearch> applicationSearchList = new ArrayList<ApplicationSearch>();
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		CandidateProfileDAO candidateProfileDAO = new CandidateProfileDAO();
		_candidateID = candidateProfileDAO.emailToID(_candidateID);
		applicationSearchList = applicationsDAO.searchApplicationsForOnePerson(_candidateID);
		return applicationSearchList;
	}
	
	//search candidateProfile for one application
	@GET
	@Path("/application_candidate/{_appID}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public CandidateProfile getCandidateProfileForOneApplication(@PathParam("_appID") String _appID) {
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		CandidateProfile candidateProfile = new CandidateProfile();
		candidateProfile = applicationsDAO.searchCandidateProfileForOneApplication(_appID);
		return candidateProfile;
	}
	
	//search applications under one reviewer whose status is autoChecked
	@GET
	@Path("/reviewer_applications/{userName}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Applications> getApplicationsForOneReviewer(@PathParam("userName") String userName) {
		List<Applications> applicationsList = new ArrayList<Applications>();
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		applicationsList = applicationsDAO.searchApplicationsForOneReviewer(
				hiringTeamDAO.userNameToID(userName));
		return applicationsList;
	}
	
	//search one candidateProfile by appID
	@GET
	@Path("/candidateProfile/{_appID}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public CandidateProfile getMap(@PathParam("_appID") String _appID) {
		CandidateProfile candidateProfile = new CandidateProfile();
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		candidateProfile = applicationsDAO.searchCandidateProfile(_appID);
		return candidateProfile;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Applications createApplications(Applications applications) {
		if (!applications.getSecurityKey().equals("i-am-foundit")) {
			applications.setSecurityKey("Security ERROR");
			return applications;
		}
		if (!applications.getShortKey().equals("app-candidate")) {
			applications.setSecurityKey("ShortKey ERROR");
			return applications;
		}
		applications.setSecurityKey(null);
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		CandidateProfileDAO candidateProfileDAO = new CandidateProfileDAO();
		applications.set_candidateProfileID(candidateProfileDAO
				.emailToID(applications.get_candidateProfileID()));
		applications.set_appID(applicationsDAO.createNew_AppID(
				applications.get_candidateProfileID(), applications.get_jobID()));
		applications.setCapacity("0");
		applications.setFirst_ReviewerID("null");
		applications.setFirst_ReviewerUserName("null");
		applications.setSecond_ReviewerID("null");
		applications.setSecond_ReviewerUserName("null");
		applications.set_reviewID("null");
		applicationsDAO.writeInNewApplication(applications);
		return applications;
	}
	
	@PUT
	@Path("{_appID}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateApplications(Applications applications) {
		if (!applications.getSecurityKey().equals("i-am-foundit")) {
			return "Security ERROR";
		}
		if (!applications.getShortKey().equals("app-manager") 
				&& !applications.getShortKey().equals("app-candidate")) {
			return "ShortKey ERROR";
		}
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		CandidateProfileDAO candidateProfileDAO = new CandidateProfileDAO();
		applications.set_candidateProfileID(candidateProfileDAO
				.emailToID(applications.get_candidateProfileID()));
		Boolean check = applicationsDAO.updateApplications(applications);
		if (check == true) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
	
	@PUT
	@Path("/statusUpdate")
	@Produces(MediaType.APPLICATION_XML)
	public String updateReviewerStatus(@QueryParam("appID") String _appID
			, @QueryParam("status") String status) {
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		Boolean check = applicationsDAO.applicationsStatusUpdate(_appID, status);
		if (check == true) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
	
	@PUT
	@Path("/updateReviewer")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateReviewer(Applications applications) {
		if (!applications.getSecurityKey().equals("i-am-foundit")) {
			return "Security ERROR";
		}
		if (!applications.getShortKey().equals("app-manager") 
				&& !applications.getShortKey().equals("app-candidate") 
				&& !applications.getShortKey().equals("app-reviewer")) {
			return "ShortKey ERROR";
		}
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		if (applications.getFirst_ReviewerUserName() != null 
				&& !applications.getFirst_ReviewerUserName().equals("")) {
			applications.setFirst_ReviewerID(
					hiringTeamDAO.userNameToID(applications.getFirst_ReviewerUserName()));
		}
		if (applications.getSecond_ReviewerUserName() != null 
				&& !applications.getSecond_ReviewerUserName().equals("")) {
			applications.setSecond_ReviewerID(
					hiringTeamDAO.userNameToID(applications.getSecond_ReviewerUserName()));
		}
		Boolean check = applicationsDAO.updateReviewer(applications);
		if (check == false) {
			return "NO MORE THAN TWO REVIEWERS!";
		}
		return "SUCCESS";
	}
	
	@DELETE
	@Path("{_appID}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteApplications(@PathParam("_appID") String _appID) {
		ApplicationsDAO applicationsDAO = new ApplicationsDAO();
		Boolean check = applicationsDAO.deleteApplications(_appID);
		if (check == false) {
			throw new RuntimeException("DELETE: Book with " + _appID +  " not found");
		}
	}
}
