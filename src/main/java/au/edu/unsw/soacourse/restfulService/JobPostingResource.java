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

import au.edu.unsw.soacourse.dao.CompanyProfileDAO;
import au.edu.unsw.soacourse.dao.JobPostingDAO;
import au.edu.unsw.soacourse.model.JobPosting;
import au.edu.unsw.soacourse.model.JobSearch;

@Path("/JobPosting")
public class JobPostingResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public JobPosting getJobPosting(@PathParam("id") String _jobID){
		JobPostingDAO jobPostingDAO = new JobPostingDAO();
		JobPosting jobPosting = new JobPosting();
		if (_jobID == null) {
			return jobPosting;
		}
		jobPosting = jobPostingDAO
				.searchOneJob(_jobID);
		return jobPosting;
	}
	
	//search jobs post by one company
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<JobSearch> getJobForOneCompany(@QueryParam("companyEmail") String companyEmail) {
		List<JobSearch> jobSearchList = new ArrayList<JobSearch>();
		JobPostingDAO jobPostingDAO = new JobPostingDAO();
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		String _companyProfileID = companyProfileDAO.emailToID(companyEmail);
		jobSearchList = jobPostingDAO.searchJobsForOneCompany(_companyProfileID);
		return jobSearchList;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public JobPosting createJobPosting(JobPosting jobPosting){
		if (!jobPosting.getSecurityKey().equals("i-am-foundit")) {
			jobPosting.setSecurityKey("Security ERROR");
			return jobPosting;
		}
		if (!jobPosting.getShortKey().equals("app-manager")) {
			jobPosting.setSecurityKey("ShortKey ERROR");
			return jobPosting;
		}
		jobPosting.setSecurityKey(null);
		JobPostingDAO jobPostingDAO = new JobPostingDAO();
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		jobPosting.set_companyProfileID(companyProfileDAO.emailToID(jobPosting.get_companyProfileID()));
		jobPosting.set_jobID(jobPostingDAO.createNew_JobID(jobPosting.get_companyProfileID()));
		jobPostingDAO.writeInNewJob(jobPosting);
		return jobPosting;
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateJobPosting(JobPosting jobPosting) {
		JobPostingDAO jobPostingDAO = new JobPostingDAO();
		if (!jobPosting.getSecurityKey().equals("i-am-foundit")) {
			return "Security ERROR";
		}
		if (!jobPosting.getShortKey().equals("app-manager")) {
			return "ShortKey ERROR";
		}
		CompanyProfileDAO companyProfileDAO = new CompanyProfileDAO();
		jobPosting.set_companyProfileID(companyProfileDAO.emailToID(jobPosting.get_companyProfileID()));
		Boolean check = jobPostingDAO.updateJob(jobPosting);
		if (check == true) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteJobPosting(@PathParam("id") String _jobID) {
		JobPostingDAO jobPostingDAO = new JobPostingDAO();
		Boolean check = jobPostingDAO.deleteJob(_jobID);
		if (check == false) {
			throw new RuntimeException("DELETE: Book with " + _jobID +  " not found");
		}
	}
}
