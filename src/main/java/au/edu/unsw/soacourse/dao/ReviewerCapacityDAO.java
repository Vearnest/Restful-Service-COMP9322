package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

import au.edu.unsw.soacourse.model.ReviewerCapacity;
import au.edu.unsw.soacourse.model.ReviewerCapacity;

public class ReviewerCapacityDAO {

	public ReviewerCapacityDAO() {
		
	}
	
	//search for a reviewer capacity
	public ReviewerCapacity searchReviewerCapacity(String _reviewID) {
		ReviewerCapacity reviewerCapacity = new ReviewerCapacity();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewerCapacityFolder/" + _reviewID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			reviewerCapacity.set_applicationID(tmpElement.getTextContent());
			reviewerCapacity.set_reviewID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviewerCapacity.setFirst_ReviewerID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviewerCapacity.setSecond_ReviewerID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			reviewerCapacity.setCapacity((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reviewerCapacity;
	}
	
	//write in the new candidate profile
	public void writeInNewReviewerCapacity(ReviewerCapacity reviewerCapacity) {
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewerCapacityFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewerCapacityFolder/"
				+ reviewerCapacity.get_reviewID() + ".xml");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<ReviewerCapacity></ReviewerCapacity>");
				printWriter.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			Element root = null;
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Element idElement = document.createElement("_applicationID");
			idElement.setTextContent(reviewerCapacity.get_applicationID());
			root.appendChild(idElement);
			Element FNElement = document.createElement("_reviewID");
			FNElement.setTextContent(reviewerCapacity.get_reviewID());
			root.appendChild(FNElement);
			Element LNElement = document.createElement("first_ReviewerID");
			LNElement.setTextContent(reviewerCapacity.getFirst_ReviewerID());
			root.appendChild(LNElement);
			Element NNElement = document.createElement("second_ReviewerID");
			NNElement.setTextContent(reviewerCapacity.getSecond_ReviewerID());
			root.appendChild(NNElement);
			Element ageElement = document.createElement("capacity");
			ageElement.setTextContent(reviewerCapacity.getCapacity());
			root.appendChild(ageElement);
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
	}
	
	//update a candidate profile
	public Boolean updateReviewerCapacity(ReviewerCapacity reviewerCapacity) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewerCapacityFolder/"
				+ reviewerCapacity.get_reviewID() + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			if (!tmpElement.getTextContent().equals(
					reviewerCapacity.get_reviewID())
					&& reviewerCapacity.get_reviewID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(reviewerCapacity.get_applicationID())
					&& reviewerCapacity.get_applicationID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(reviewerCapacity.getFirst_ReviewerID())
					&& reviewerCapacity.getFirst_ReviewerID() != null) {
				tmpElement.setTextContent(reviewerCapacity.getFirst_ReviewerID());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(reviewerCapacity.getSecond_ReviewerID())
					&& reviewerCapacity.getSecond_ReviewerID() != null) {
				tmpElement.setTextContent(reviewerCapacity.getSecond_ReviewerID());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(reviewerCapacity.getCapacity())
					&& reviewerCapacity.getCapacity() != null) {
				tmpElement.setTextContent(reviewerCapacity.getCapacity());
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	//delete a reviewer capacity
	public Boolean deleteReviewerCapacity(String _reviewID) {
		File capacityFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewerCapcityFolder/" + _reviewID + ".xml");
		capacityFile.delete();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ReviewerCapacityControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = document.getElementsByTagName("rc");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getLastChild();
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

	//search applications post in one job
	public List<ReviewerCapacity> searchReviewerForOneApplication(String _applicationID) {
		List<ReviewerCapacity> capacitySearchList = new ArrayList<ReviewerCapacity>();
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/ReviewerCapacityFolder/";
		// check the number of jobs
		int numOfApps = 0;
		Vector<String> appIDVector = new Vector<String>();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ReviewerCapacityControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<ReviewerCapacity></ReviewerCapacity>");
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
				numOfApps = 0;
			} else {
				NodeList tmpNodeList = root.getChildNodes();
				numOfApps = tmpNodeList.getLength();
				for (int i = 0; i < numOfApps; i++) {
					appIDVector.add(tmpNodeList.item(i).getFirstChild().getTextContent());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// search in each XML file
		for (int i = 0; i < numOfApps; i++) {
			File jobFile = new File(filePath + appIDVector.elementAt(i) + ".xml");
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			root = null;
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			try {
				DocumentBuilder documentBuilder = documentBuilderFactory
						.newDocumentBuilder();
				Document document = documentBuilder.parse(jobFile);
				root = document.getDocumentElement();
				String appIDInFile = root.getElementsByTagName("_applicationID")
						.item(0).getTextContent();
				if (appIDInFile.equals(_applicationID)) {
					ReviewerCapacity capacitySearch = new ReviewerCapacity();
					capacitySearch.set_applicationID(root.getElementsByTagName("_applicationID").item(0)
							.getTextContent());
					capacitySearch.set_reviewID(root.getElementsByTagName("_reviewID").item(0)
							.getTextContent());
					capacitySearch.setFirst_ReviewerID(root.getElementsByTagName("first_ReviewerID").item(0)
							.getTextContent());
					capacitySearch.setSecond_ReviewerID(root.getElementsByTagName("second_ReviewerID").item(0)
							.getTextContent());
					capacitySearch.setCapacity(root.getElementsByTagName("capacity").item(0)
							.getTextContent());
					capacitySearchList.add(capacitySearch);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return capacitySearchList;
	}
}
