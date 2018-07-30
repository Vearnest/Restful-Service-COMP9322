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

import au.edu.unsw.soacourse.dao.ReviewerCapacityDAO;
import au.edu.unsw.soacourse.model.ReviewerCapacity;

@Path("/ReviewerCapacity")
public class ReviewerCapacityResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{reviewID}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public ReviewerCapacity getReviewerCapacity(@PathParam("reviewID") String _reviewID){
		ReviewerCapacityDAO reviewerCapacityDAO = new ReviewerCapacityDAO();
		ReviewerCapacity reviewerCapacity = new ReviewerCapacity();
		reviewerCapacity = reviewerCapacityDAO.searchReviewerCapacity(_reviewID);
		return reviewerCapacity;
	}
	
	@GET
	@Path("/CapacitySearch")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<ReviewerCapacity> getCapacityForOneApplication(@QueryParam("applicationID") String _applicationID) {
		ReviewerCapacityDAO reviewerCapacityDAO = new ReviewerCapacityDAO();
		List<ReviewerCapacity> reviewerCapacitieList = new ArrayList<ReviewerCapacity>();
		reviewerCapacitieList = reviewerCapacityDAO.searchReviewerForOneApplication(_applicationID);
		return reviewerCapacitieList;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public ReviewerCapacity createReviewerCapacity(ReviewerCapacity reviewerCapacity){
		ReviewerCapacityDAO reviewerCapacityDAO = new ReviewerCapacityDAO();
		reviewerCapacityDAO.writeInNewReviewerCapacity(reviewerCapacity);
		return reviewerCapacity;
	}
	
	@PUT
	@Path("{reviewID}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateReviewerCapacity(ReviewerCapacity reviewerCapacity) {
		ReviewerCapacityDAO reviewerCapacityDAO = new ReviewerCapacityDAO();
		Boolean check = reviewerCapacityDAO.updateReviewerCapacity(reviewerCapacity);
		if (check == true) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
	
	@DELETE
	@Path("{reviewID}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteReviewerCapacity(@PathParam("reviewID") String _reviewID) {;
		ReviewerCapacityDAO reviewerCapacityDAO = new ReviewerCapacityDAO();
		Boolean check = reviewerCapacityDAO.deleteReviewerCapacity(_reviewID);
		if (check == false) {
			throw new RuntimeException("DELETE: Book with " + _reviewID +  " not found");
		}
	}
}
