package au.edu.unsw.soacourse.restfulService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import au.edu.unsw.soacourse.dao.CandidateProfileDAO;
import au.edu.unsw.soacourse.model.CandidateProfile;

@Path("/CandidateProfile")
public class CandidateProfileResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{email}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public CandidateProfile getCandidateProfile(@PathParam("email") String email){
		CandidateProfileDAO candidateProfileDAO = new CandidateProfileDAO();
		CandidateProfile candidateProfile = new CandidateProfile();
		String _profileID = candidateProfileDAO.emailToID(email);
		if (_profileID == null) {
			return candidateProfile;
		}
		candidateProfile = candidateProfileDAO
				.searchProfile(_profileID);
		return candidateProfile;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public CandidateProfile createCandidateProfile(CandidateProfile candidateProfile){
		if (!candidateProfile.getSecurityKey().equals("i-am-foundit")) {
			candidateProfile.setSecurityKey("Security ERROR");
			return candidateProfile;
		}
		if (!candidateProfile.getShortKey().equals("app-candidate")) {
			candidateProfile.setSecurityKey("ShortKey ERROR");
			return candidateProfile;
		}
		candidateProfile.setSecurityKey(null);
		CandidateProfileDAO candidateProfileDAO = new CandidateProfileDAO();
		candidateProfile.set_profileID(candidateProfileDAO.createNew_ProfileID(candidateProfile.getEmail()));
		candidateProfileDAO.writeInNewProfile(candidateProfile);
		return candidateProfile;
	}
	
	@PUT
	@Path("{email}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateCandidateProfile(CandidateProfile candidateProfile) {
		if (!candidateProfile.getSecurityKey().equals("i-am-foundit")) {
			return "Security ERROR";
		}
		if (!candidateProfile.getShortKey().equals("app-candidate")) {
			return "ShortKey ERROR";
		}
		CandidateProfileDAO candidateProfileDAO = new CandidateProfileDAO();
		candidateProfile.set_profileID(candidateProfileDAO.emailToID(candidateProfile.getEmail()));
		Boolean check = candidateProfileDAO.updateProfile(candidateProfile);
		if (check == true) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
	
	@DELETE
	@Path("{email}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteCandidateProfile(@PathParam("email") String email) {;
		CandidateProfileDAO candidateProfileDAO = new CandidateProfileDAO();
		String _profileID = candidateProfileDAO.emailToID(email);
		Boolean check = candidateProfileDAO.deleteProfile(_profileID);
		if (check == false) {
			throw new RuntimeException("DELETE: Book with " + email +  " not found");
		}
	}
}
