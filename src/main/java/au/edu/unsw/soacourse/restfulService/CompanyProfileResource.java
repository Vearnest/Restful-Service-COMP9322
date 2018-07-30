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

import au.edu.unsw.soacourse.dao.CompanyProfileDAO;
import au.edu.unsw.soacourse.model.CompanyProfile;

@Path("/CompanyProfile")
public class CompanyProfileResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{companyEmail}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public CompanyProfile getCompanyProfile(@PathParam("companyEmail") String companyEmail){
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		CompanyProfile companyProfile = new CompanyProfile();
		String _profileID = companyProfileDAO.emailToID(companyEmail);
		if (_profileID == null) {
			return companyProfile;
		}
		companyProfile = companyProfileDAO
				.searchProfile(_profileID);
		return companyProfile;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public CompanyProfile createCompanyProfile(CompanyProfile companyProfile){
		if (!companyProfile.getSecurityKey().equals("i-am-foundit")) {
			companyProfile.setSecurityKey("Security ERROR");
			return companyProfile;
		}
		if (!companyProfile.getShortKey().equals("app-manager")) {
			companyProfile.setSecurityKey("ShortKey ERROR");
			return companyProfile;
		}
		companyProfile.setSecurityKey(null);
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		companyProfile.set_profileID(companyProfileDAO.createNew_ProfileID(companyProfile.getCompanyEmail()));
		companyProfileDAO.writeInNewProfile(companyProfile);
		return companyProfile;
	}
	
	@PUT
	@Path("{companyEmail}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateCompanyProfile(CompanyProfile companyProfile) {
		if (!companyProfile.getSecurityKey().equals("i-am-foundit")) {
			return "Security ERROR";
		}
		if (!companyProfile.getShortKey().equals("app-manager")) {
			return "ShortKey ERROR";
		}
		String res = null;
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		companyProfile.set_profileID(companyProfileDAO.emailToID(companyProfile.getCompanyEmail()));
		System.out.println(companyProfile.get_profileID());
		Boolean check = companyProfileDAO.updateProfile(companyProfile);
		if (check == true) {
			res = "SUCCESS";
		}
		else {
			res = "FAIL";
		}
		return res;
	}
	
	@DELETE
	@Path("{companyEmail}")
	@Produces(MediaType.TEXT_PLAIN)
	public void deleteCompanyProfile(@PathParam("companyEmail") String companyEmail) {;
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		String _profileID = companyProfileDAO.emailToID(companyEmail);
		Boolean check = companyProfileDAO.deleteProfile(_profileID);
		if (check == false) {
			throw new RuntimeException("DELETE: Book with " + companyEmail +  " not found");
		}
	}
}
