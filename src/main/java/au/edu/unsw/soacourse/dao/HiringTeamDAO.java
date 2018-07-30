package au.edu.unsw.soacourse.dao;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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

import au.edu.unsw.soacourse.model.HiringTeam;

public class HiringTeamDAO {

	public HiringTeamDAO() {
		
	}
	
	//username->id
	public String userNameToID(String userName) {
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/HiringTeamControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = root.getChildNodes();
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element userNameElement = (Element) curElement.getLastChild();
				if (userNameElement.getTextContent().equals(userName))
					return curElement.getFirstChild().getTextContent();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//search for a candidate profile
	public HiringTeam searchHiringTeam(String _reviewerID) {
		HiringTeam hiringTeam = new HiringTeam();
		File hiringTeamFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/" + _reviewerID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(hiringTeamFile);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			hiringTeam.set_reviewerID(tmpElement.getTextContent());
			hiringTeam.set_companyProfileID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			hiringTeam.setUserName((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			hiringTeam.setPassword((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			hiringTeam.setSkills((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			hiringTeam
					.setStatus((tmpElement = tmpElement.getNextSibling())
							.getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hiringTeam;
	}
	
	//create a new _reviewerID
	public String createNew_ReviewerID(String userName, String _companyProfileID) {
		String newReviewerID = null;
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/HiringTeamControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<HiringTeam></HiringTeam>");
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
				newReviewerID = "0";
			} else {
				newReviewerID = String.valueOf(Integer.parseInt(root
						.getLastChild().getFirstChild().getTextContent()) + 1);
			}
			Element entryElement = document.createElement("ht");
			root.appendChild(entryElement);
			Element RidElement = document.createElement("Reviewerid");
			RidElement.setTextContent(newReviewerID);
			entryElement.appendChild(RidElement);
			Element CidElement = document.createElement("CompanyProfileid");
			CidElement.setTextContent(_companyProfileID);
			entryElement.appendChild(CidElement);
			Element JidElement = document.createElement("Jobid");
			entryElement.appendChild(JidElement);
			Element SElement = document.createElement("Username");
			SElement.setTextContent(userName);
			entryElement.appendChild(SElement);
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
		return newReviewerID;
	}

	// write in the new reviewer
	public void writeInNewReviewer(HiringTeam hiringTeam) {
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/"
				+ hiringTeam.get_reviewerID() + ".xml");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Reviewer></Reviewer>");
				printWriter.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			Element root = null;
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Element RidElement = document.createElement("_reviewerID");
			RidElement.setTextContent(hiringTeam.get_reviewerID());
			root.appendChild(RidElement);
			Element CidElement = document.createElement("_companyProfileID");
			CidElement.setTextContent(hiringTeam.get_companyProfileID());
			root.appendChild(CidElement);
			Element UNElement = document.createElement("userName");
			UNElement.setTextContent(hiringTeam.getUserName());
			root.appendChild(UNElement);
			Element PWElement = document.createElement("password");
			PWElement.setTextContent(hiringTeam.getPassword());
			root.appendChild(PWElement);
			Element SKElement = document.createElement("skills");
			SKElement.setTextContent(hiringTeam.getSkills());
			root.appendChild(SKElement);
			Element SElement = document.createElement("status");
			SElement.setTextContent(hiringTeam.getStatus());
			root.appendChild(SElement);
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
	public Boolean updateHiringTeam(HiringTeam hiringTeam) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/"
				+ hiringTeam.get_reviewerID() + ".xml");
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
					hiringTeam.get_reviewerID())
					&& hiringTeam.get_reviewerID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(hiringTeam.get_companyProfileID())
					&& hiringTeam.get_companyProfileID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(hiringTeam.getUserName())
					&& hiringTeam.getUserName() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(hiringTeam.getPassword())
					&& hiringTeam.getPassword() != null) {
				tmpElement.setTextContent(hiringTeam.getPassword());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(hiringTeam.getSkills())
					&& hiringTeam.getSkills() != null) {
				tmpElement.setTextContent(hiringTeam.getSkills());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(hiringTeam.getStatus())
					&& hiringTeam.getStatus() != null) {
				tmpElement.setTextContent(hiringTeam.getStatus());
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
	
	//delete a hiring team
	public Boolean deleteHiringTeam(String _reviewerID) {
		File hiringTeamFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/" + _reviewerID + ".xml");
		hiringTeamFile.delete();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/HiringTeamControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = document.getElementsByTagName("ht");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getFirstChild();
				if (idElement.getTextContent().equals(_reviewerID)) {
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
	
	//search reviewers in one company
	public List<HiringTeam> searchReviewersForOneCompany(String _companyProfileID) {
		List<HiringTeam> hiringTeamList = new ArrayList<HiringTeam>();
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/";
		// check the number of jobs
		int numOfReviewers = 0;
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/HiringTeamControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<HiringTeam></HiringTeam>");
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
				numOfReviewers = 0;
			} else {
				numOfReviewers = Integer.parseInt(root.getLastChild()
						.getFirstChild().getTextContent()) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// search in each XML file
		for (int i = 0; i < numOfReviewers; i++) {
			File jobFile = new File(filePath + i + ".xml");
			if (jobFile.exists()) {
				documentBuilderFactory = DocumentBuilderFactory.newInstance();
				root = null;
				documentBuilderFactory.setIgnoringElementContentWhitespace(true);
				try {
					DocumentBuilder documentBuilder = documentBuilderFactory
							.newDocumentBuilder();
					Document document = documentBuilder.parse(jobFile);
					root = document.getDocumentElement();
					String companyIDInFile = root
							.getElementsByTagName("_companyProfileID").item(0)
							.getTextContent();
					if (companyIDInFile.equals(_companyProfileID)) {
						HiringTeam hiringTeam = new HiringTeam();
						hiringTeam.set_reviewerID(root.getElementsByTagName("_reviewerID")
								.item(0).getTextContent());
						hiringTeam.setUserName(root.getElementsByTagName("userName").item(0).getTextContent());
						hiringTeam.setSkills(root.getElementsByTagName("skills").item(0).getTextContent());
						hiringTeam.setStatus(root.getElementsByTagName("status")
								.item(0).getTextContent());
						hiringTeamList.add(hiringTeam);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return hiringTeamList;
	}
	
	//search reviewers in one company
	public List<HiringTeam> searchReviewersAvailableForOneCompany(
			String _companyProfileID) {
		List<HiringTeam> hiringTeamList = new ArrayList<HiringTeam>();
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/";
		// check the number of jobs
		int numOfReviewers = 0;
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/HiringTeamControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<HiringTeam></HiringTeam>");
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
				numOfReviewers = 0;
			} else {
				numOfReviewers = Integer.parseInt(root.getLastChild()
						.getFirstChild().getTextContent()) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// search in each XML file
		for (int i = 0; i < numOfReviewers; i++) {
			File jobFile = new File(filePath + i + ".xml");
			if (jobFile.exists()) {
				documentBuilderFactory = DocumentBuilderFactory.newInstance();
				root = null;
				documentBuilderFactory.setIgnoringElementContentWhitespace(true);
				try {
					DocumentBuilder documentBuilder = documentBuilderFactory
							.newDocumentBuilder();
					Document document = documentBuilder.parse(jobFile);
					root = document.getDocumentElement();
					String companyIDInFile = root
							.getElementsByTagName("_companyProfileID").item(0)
							.getTextContent();
					String statusInFile = root.getElementsByTagName("status").item(0).getTextContent();
					if (companyIDInFile.equals(_companyProfileID) && statusInFile.equals("available")) {
						HiringTeam hiringTeam = new HiringTeam();
						hiringTeam.set_reviewerID(root
								.getElementsByTagName("_reviewerID").item(0)
								.getTextContent());
						hiringTeam.setUserName(root
								.getElementsByTagName("userName").item(0)
								.getTextContent());
						hiringTeam.setSkills(root.getElementsByTagName("skills")
								.item(0).getTextContent());
						hiringTeam.setStatus(root.getElementsByTagName("status")
								.item(0).getTextContent());
						hiringTeamList.add(hiringTeam);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return hiringTeamList;
	}
	
	public void reviewerStatusUpdate(String _reviewerID) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/"
				+ _reviewerID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			if (document.getElementsByTagName("status").item(0).getTextContent().equals("avaiable")) {
				document.getElementsByTagName("status").item(0).setTextContent("unavailable");
			}
			else {
				document.getElementsByTagName("status").item(0).setTextContent("available");
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
	}
}
