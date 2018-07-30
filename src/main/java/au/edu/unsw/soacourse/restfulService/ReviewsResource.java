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

import au.edu.unsw.soacourse.dao.HiringTeamDAO;
import au.edu.unsw.soacourse.dao.ReviewsDAO;
import au.edu.unsw.soacourse.model.Reviews;

@Path("/Reviews")
public class ReviewsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Path("{_reviewID}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Reviews getReviews(@PathParam("_reviewID") String _reviewID) {
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		Reviews reviews = new Reviews();
		if (_reviewID == null) {
			return reviews;
		}
		reviewsDAO.searchReview(_reviewID);
		return reviews;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Reviews createReviews(Reviews reviews){
		if (!reviews.getSecurityKey().equals("i-am-foundit")) {
			reviews.setSecurityKey("Security ERROR");
			return reviews;
		}
		if (!reviews.getShortKey().equals("app-reviewer")) {
			reviews.setSecurityKey("ShortKey ERROR");
			return reviews;
		}
		reviews.setSecurityKey(null);
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		reviews.set_reviewID(reviewsDAO.createNew_Review(reviews.get_appID()));
		reviews.setFirst_reviewerID(hiringTeamDAO.userNameToID(reviews.getFirst_reviewerID()));
		reviews.setSecond_reviewerID("null");
		reviews.setSecondComment("null");
		reviews.setSecondDecision("null");
		reviewsDAO.writeInNewReviews(reviews);
		return reviews;
	}
	
	@PUT
	@Path("{_reviewID}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public String updateReviews(Reviews reviews) {
		if (!reviews.getSecurityKey().equals("i-am-foundit")) {
			return "Security ERROR";
		}
		if (!reviews.getShortKey().equals("app-reviewer")) {
			return "ShortKey ERROR";
		}
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		HiringTeamDAO hiringTeamDAO = new HiringTeamDAO();
		reviews.setSecond_reviewerID(hiringTeamDAO.userNameToID(reviews.getSecond_reviewerID()));
		Boolean check = reviewsDAO.updateReviews(reviews);
		if (check == true) {
			return "SUCCESS";
		}
		else {
			return "FAIL";
		}
	}
	
	@DELETE
	@Path("{_reviewID}")
	@Produces(MediaType.APPLICATION_XML)
	public void deleteReivews(@PathParam("_reviewID") String _reviewID) {;
		ReviewsDAO reviewsDAO = new ReviewsDAO();
		Boolean check = reviewsDAO.deleteReviews(_reviewID);
		if (check == false) {
			throw new RuntimeException("DELETE: Book with " + _reviewID +  " not found");
		}
	}
}
