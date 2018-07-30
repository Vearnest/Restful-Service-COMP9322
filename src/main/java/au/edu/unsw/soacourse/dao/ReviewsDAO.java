package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import au.edu.unsw.soacourse.model.Reviews;

public class ReviewsDAO {

	public ReviewsDAO() {
		
	}
	
	//search for a review
	public Reviews searchReview(String _reviewID) {
		Reviews reviews = new Reviews();
		File reviewsFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewsFolder/" + _reviewID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(reviewsFile);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			reviews.set_reviewID(tmpElement.getTextContent());
			reviews.set_appID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviews.setFirst_reviewerID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviews.setFirstComment((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			reviews.setFirstDecision((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviews.setSecond_reviewerID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviews.setSecondComment((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			reviews.setSecondDecision((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviews.setStatus((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviews;
	}
	
	//create a new reviewID
	public String createNew_Review(String _appID) {
		String newReviewID = null;
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ReviewsControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Reviews></Reviews>");
				printWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			if (root.getLastChild() == null) {
				newReviewID = "0";
			} else {
				newReviewID = String.valueOf(Integer.parseInt(root
						.getLastChild().getFirstChild().getTextContent()) + 1);
			}
			Element entryElement = document.createElement("rw");
			root.appendChild(entryElement);
			Element reviewRElement = document.createElement("Reviewid");
			reviewRElement.setTextContent(newReviewID);
			entryElement.appendChild(reviewRElement);
			Element appRElement = document.createElement("Appid");
			appRElement.setTextContent(_appID);
			entryElement.appendChild(appRElement);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			DOMSource source = new DOMSource(document);
			PrintWriter printWriter = new PrintWriter(file);
			StreamResult streamResult = new StreamResult(printWriter);
			transformer.transform(source, streamResult);
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File appFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewsFolder/"
				+ _appID + ".xml");
		DocumentBuilderFactory documentBuilderFactory1 = DocumentBuilderFactory
				.newInstance();
		Element root1 = null;
		documentBuilderFactory1.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder1 = documentBuilderFactory1
					.newDocumentBuilder();
			Document document1 = documentBuilder1.parse(file);
			root1 = document1.getDocumentElement();
			document1.getElementsByTagName("_reviewID").item(0).setTextContent(newReviewID);
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			DOMSource source = new DOMSource(document1);
			PrintWriter printWriter = new PrintWriter(appFile);
			StreamResult streamResult = new StreamResult(printWriter);
			transformer.transform(source, streamResult);
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newReviewID;
	}
	
	//write in the new review
	public void writeInNewReviews(Reviews reviews) {
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewsFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewsFolder/"
				+ reviews.get_reviewID() + ".xml");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Review></Review>");
				printWriter.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			Element root = null;
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Element RElement = document.createElement("_reviewID");
			RElement.setTextContent(reviews.get_reviewID());
			root.appendChild(RElement);
			Element appElement = document.createElement("_appID");
			appElement.setTextContent(reviews.get_appID());
			root.appendChild(appElement);
			Element CElement = document.createElement("first_reviewerID");
			CElement.setTextContent(reviews.getFirst_reviewerID());
			root.appendChild(CElement);
			Element DElement = document.createElement("firstComment");
			DElement.setTextContent(reviews.getFirstComment());
			root.appendChild(DElement);
			Element MElement = document.createElement("firstDecision");
			MElement.setTextContent(reviews.getFirstDecision());
			root.appendChild(MElement);
			Element SRElement = document.createElement("second_reviewerID");
			SRElement.setTextContent(reviews.getSecond_reviewerID());
			root.appendChild(SRElement);
			Element SCElement = document.createElement("secondComment");
			SCElement.setTextContent(reviews.getSecondComment());
			root.appendChild(SCElement);
			Element SDElement = document.createElement("secondDecision");
			SDElement.setTextContent(reviews.getSecondDecision());
			root.appendChild(SDElement);
			if (reviews.getFirstDecision().equals("reject")) {
				Element statusElement = document.createElement("status");
				statusElement.setTextContent("reject");
				root.appendChild(statusElement);
			}
			else {
				Element statusElement = document.createElement("status");
				statusElement.setTextContent("halfPass");
				root.appendChild(statusElement);
			}
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			DOMSource source = new DOMSource(document);
			PrintWriter printWriter = new PrintWriter(file);
			StreamResult streamResult = new StreamResult(printWriter);
			transformer.transform(source, streamResult);
			printWriter.close();
			//change the status of application
			File appFile = new File(System.getProperty("catalina.home")
					+ "/webapps/ROOT/ApplicationsFolder/"
					+ reviews.get_appID() + ".xml");
			documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			root = null;
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			document = documentBuilder.parse(appFile);
			root = document.getDocumentElement();
			root.getElementsByTagName("_reviewID").item(0).setTextContent(reviews.get_reviewID());
			if (reviews.getFirstDecision().equals("reject")) {
				root.getElementsByTagName("status").item(0).setTextContent("reject");
			}
			else {
				root.getElementsByTagName("status").item(0).setTextContent("inProcess");
			}
			tFactory = TransformerFactory.newInstance();
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			source = new DOMSource(document);
			printWriter = new PrintWriter(appFile);
			streamResult = new StreamResult(printWriter);
			transformer.transform(source, streamResult);
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//update a review
	public Boolean updateReviews(Reviews reviews) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewsFolder/"
				+ reviews.get_reviewID() + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			root.getElementsByTagName("second_reviewerID").item(0).setTextContent(reviews.getSecond_reviewerID());
			root.getElementsByTagName("secondComment").item(0).setTextContent(reviews.getSecondComment());
			root.getElementsByTagName("secondDecision").item(0).setTextContent(reviews.getSecondDecision());
			if (reviews.getSecondDecision().equals("reject")) {
				root.getElementsByTagName("status").item(0).setTextContent("reject");
			}
			else {
				root.getElementsByTagName("status").item(0).setTextContent("pass");
			}
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			DOMSource source = new DOMSource(document);
			PrintWriter printWriter = new PrintWriter(file);
			StreamResult streamResult = new StreamResult(printWriter);
			transformer.transform(source, streamResult);
			printWriter.close();
			File appFile = new File(System.getProperty("catalina.home")
					+ "/webapps/ROOT/ApplicationsFolder/"
					+ reviews.get_appID() + ".xml");
			documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			root = null;
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			document = documentBuilder.parse(appFile);
			root = document.getDocumentElement();
			if (reviews.getSecondDecision().equals("reject")) {
				root.getElementsByTagName("status").item(0).setTextContent("reject");
			}
			else {
				root.getElementsByTagName("status").item(0).setTextContent("shortListed");
			}
			tFactory = TransformerFactory.newInstance();
			transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			source = new DOMSource(document);
			printWriter = new PrintWriter(appFile);
			streamResult = new StreamResult(printWriter);
			transformer.transform(source, streamResult);
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//delete a review
	public Boolean deleteReviews(String _reviewID) {
		File reviewsFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewsFolder/" + _reviewID + ".xml");
		reviewsFile.delete();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ReviewsControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = document.getElementsByTagName("rw");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getFirstChild();
				if (idElement.getTextContent().equals(_reviewID)) {
					root.removeChild((Node) curElement);
					TransformerFactory tFactory = TransformerFactory
							.newInstance();
					Transformer transformer = tFactory.newTransformer();
					transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
					transformer.setOutputProperty(OutputKeys.INDENT, "no");
					DOMSource source = new DOMSource(document);
					PrintWriter printWriter = new PrintWriter(file);
					StreamResult streamResult = new StreamResult(printWriter);
					transformer.transform(source, streamResult);
					printWriter.close();
					return true;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
