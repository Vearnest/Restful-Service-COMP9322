package au.edu.unsw.soacourse.restfulService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import au.edu.unsw.soacourse.dao.JobAlertsDAO;

@Path("/JobAlerts")
public class JobAlertsService {

	@GET
	@Path("/jobs")
	@Produces(MediaType.APPLICATION_XML)
	public String showJobAlerts(@QueryParam("keyword") String keyword
			, @QueryParam("sort_by") String sort_by, @QueryParam("email") String email) {
		JobAlertsDAO jobAlertsDAO = new JobAlertsDAO();
		String tmp = "";
		try {
			tmp = jobAlertsDAO.jobAlerts(keyword, sort_by, email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}
}
