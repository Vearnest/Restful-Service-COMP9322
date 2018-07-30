package au.edu.unsw.soacourse.restfulService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import au.edu.unsw.soacourse.dao.JobPostingDAO;
import au.edu.unsw.soacourse.model.JobSearch;

@Path("/JobSearch")
public class JobSearchService {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("/Jobs")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<JobSearch> getTargetJobList(@QueryParam("keyword") String keyword
			, @QueryParam("skills") String skills, @QueryParam("status") String status) {
		List<JobSearch> jobSearchList = new ArrayList<JobSearch>();
		JobPostingDAO jobPostingDAO = new JobPostingDAO();
		jobSearchList = jobPostingDAO.searchJobs(keyword, skills, status);
		return jobSearchList;
	}
}
