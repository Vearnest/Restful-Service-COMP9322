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

import au.edu.unsw.soacourse.dao.HiringTeamDAO;
import au.edu.unsw.soacourse.dao.CompanyProfileDAO;
import au.edu.unsw.soacourse.model.HiringTeam;

@Path("/HiringTeam")
public class HiringTeamResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{userName}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public HiringTeam getHiringTeam(@PathParam("userName") String userName){
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		HiringTeam hiringTeam = new HiringTeam();
		String _reviewerID = hiringTeamDAO.userNameToID(userName);
		if (_reviewerID == null) {
			return hiringTeam;
		}
		hiringTeam = hiringTeamDAO
				.searchHiringTeam(_reviewerID);
		return hiringTeam;
	}
	
	//search reviewers in one company
	@GET
	@Path("/HiringTeamSearch")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<HiringTeam> getHiringTeamForOneCompany(@QueryParam("companyID") String _companyProfileID) {
		List<HiringTeam> hiringTeamList = new ArrayList<HiringTeam>();
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		_companyProfileID = companyProfileDAO.emailToID(_companyProfileID);
		hiringTeamList = hiringTeamDAO.searchReviewersForOneCompany(_companyProfileID);
		return hiringTeamList;
	}
	
	@GET
	@Path("/HiringTeam_Available")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<HiringTeam> getAvailableHiringTeamForOneCompany(
			@QueryParam("companyID") String _companyProfileID) {
		List<HiringTeam> hiringTeamList = new ArrayList<HiringTeam>();
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		_companyProfileID = companyProfileDAO.emailToID(_companyProfileID);
		hiringTeamList = hiringTeamDAO.searchReviewersAvailableForOneCompany(_companyProfileID);
		return hiringTeamList;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public HiringTeam createHiringTeam(HiringTeam hiringTeam){
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		if (!hiringTeam.getSecurityKey().equals("i-am-foundit")) {
			hiringTeam.setSecurityKey("Security ERROR");
			return hiringTeam;
		}
		if (!hiringTeam.getShortKey().equals("app-manager")) {
			hiringTeam.setSecurityKey("ShortKey ERROR");
			return hiringTeam;
		}
		hiringTeam.setSecurityKey(null);
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		hiringTeam.set_companyProfileID(companyProfileDAO.emailToID(hiringTeam.get_companyProfileID()));
		hiringTeam.set_reviewerID(hiringTeamDAO.createNew_ReviewerID
				(hiringTeam.getUserName(), hiringTeam.get_companyProfileID()));
		hiringTeamDAO.writeInNewReviewer(hiringTeam);
		return hiringTeam;
	}
	
	@PUT
	@Path("{userName}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateHiringTeam(HiringTeam hiringTeam) {
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		hiringTeam.set_companyProfileID(companyProfileDAO.emailToID(hiringTeam.get_companyProfileID()));
		hiringTeam.set_reviewerID(hiringTeamDAO.userNameToID(hiringTeam.getUserName()));
		Boolean check = hiringTeamDAO.updateHiringTeam(hiringTeam);
		if (check == true) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
	
	@PUT
	@Path("{userName}/statusUpdate")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateReviewerStatus(@PathParam("userName") String userName) {
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		hiringTeamDAO.reviewerStatusUpdate(hiringTeamDAO.userNameToID(userName));
		return "SUCCESS";
	}
	
	
	@DELETE
	@Path("{userName}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteHiringTeam(@PathParam("userName") String userName) {;
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		String _reviewerID = hiringTeamDAO.userNameToID(userName);
		Boolean check = hiringTeamDAO.deleteHiringTeam(_reviewerID);
		if (check == false) {
			throw new RuntimeException("DELETE: Book with " + _reviewerID +  " not found");
		}
	}
}
