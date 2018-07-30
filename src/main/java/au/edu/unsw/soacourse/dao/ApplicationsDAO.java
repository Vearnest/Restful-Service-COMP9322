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

import au.edu.unsw.soacourse.model.ApplicationSearch;
import au.edu.unsw.soacourse.model.Applications;
import au.edu.unsw.soacourse.model.CandidateProfile;

public class ApplicationsDAO {

	public ApplicationsDAO() {
		
	}
	
	//search for a candidate profile
	public Applications searchProfile(String _appID) {
		Applications applications = new Applications();
		File candidateFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/" + _appID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(candidateFile);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			applications.set_appID(tmpElement.getTextContent());
			applications.set_candidateProfileID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			applications.set_jobID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			applications.setCoverLetter((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			applications.setStatus((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			applications.set_reviewID((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			applications.setFirst_ReviewerID((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			applications.setFirst_ReviewerUserName((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			applications.setSecond_ReviewerID((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			applications.setSecond_ReviewerUserName((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			applications.setCapacity((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applications;
	}
	
	//search candidateProfile for one application
	public CandidateProfile searchCandidateProfileForOneApplication(String _appID) {
		CandidateProfile candidateProfile = new CandidateProfile();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/"
				+ _appID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			String cid = root.getElementsByTagName("_candidateProfileID").item(0).getTextContent();
			File cFile = new File(System.getProperty("catalina.home")
					+ "/webapps/ROOT/CandidateProfileFolder/"
					+ cid + ".xml");
			if (cFile.exists()) {
				documentBuilderFactory = DocumentBuilderFactory
						.newInstance();
				root = null;
				documentBuilderFactory.setIgnoringElementContentWhitespace(true);
				documentBuilder = documentBuilderFactory
						.newDocumentBuilder();
				document = documentBuilder.parse(cFile);
				root = document.getDocumentElement();
				candidateProfile.set_profileID(root.getElementsByTagName("_profileID").item(0).getTextContent());
				candidateProfile.setFirstName(root.getElementsByTagName("firstName").item(0).getTextContent());
				candidateProfile.setLastName(root.getElementsByTagName("lastName").item(0).getTextContent());
				candidateProfile.setNickName(root.getElementsByTagName("nickName").item(0).getTextContent());
				candidateProfile.setAge(root.getElementsByTagName("age").item(0).getTextContent());
				candidateProfile.setAddress(root.getElementsByTagName("address").item(0).getTextContent());
				candidateProfile.setEmail(root.getElementsByTagName("email").item(0).getTextContent());
				candidateProfile.setContactNumber(root.getElementsByTagName("contactNumber")
						.item(0).getTextContent());
				candidateProfile.setCurrentPosition(root.getElementsByTagName("currentPosition")
						.item(0).getTextContent());
				candidateProfile.setEducation(root.getElementsByTagName("education").item(0).getTextContent());
				candidateProfile.setProfessionalSkill(root.getElementsByTagName("professionalSkill")
						.item(0).getTextContent());
				candidateProfile.setExperience(root.getElementsByTagName("experience").item(0).getTextContent());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateProfile;
	}
	
	//create a new _appID
	public String createNew_AppID(String _applicationsID, String _jobID) {
		String newJobID = null;
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ApplicationsControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Applications></Applications>");
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
				newJobID = "0";
			} else {
				newJobID = String.valueOf(Integer.parseInt(root
						.getLastChild().getFirstChild().getTextContent()) + 1);
			}
			Element entryElement = document.createElement("App");
			root.appendChild(entryElement);
			Element appidElement = document.createElement("Appid");
			appidElement.setTextContent(newJobID);
			entryElement.appendChild(appidElement);
			Element cidElement = document.createElement("Candidateid");
			cidElement.setTextContent(_applicationsID);
			entryElement.appendChild(cidElement);
			Element jidElement = document.createElement("Jobid");
			jidElement.setTextContent(_jobID);
			entryElement.appendChild(jidElement);
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
		return newJobID;
	}
	
	//write in the new application
	public void writeInNewApplication(Applications applications) {
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/"
				+ applications.get_appID() + ".xml");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Application></Application>");
				printWriter.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			Element root = null;
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Element appidElement = document.createElement("_appID");
			appidElement.setTextContent(applications.get_appID());
			root.appendChild(appidElement);
			Element cidElement = document.createElement("_candidateProfileID");
			cidElement.setTextContent(applications.get_candidateProfileID());
			root.appendChild(cidElement);
			Element jidElement = document.createElement("_jobID");
			jidElement.setTextContent(applications.get_jobID());
			root.appendChild(jidElement);
			Element CLElement = document.createElement("coverLetter");
			CLElement.setTextContent(applications.getCoverLetter());
			root.appendChild(CLElement);
			Element SElement = document.createElement("status");
			SElement.setTextContent(applications.getStatus());
			root.appendChild(SElement);
			
			Element RVElement = document.createElement("_reviewID");
			RVElement.setTextContent(applications.get_reviewID());
			root.appendChild(RVElement);
			Element FRVElement = document.createElement("first_ReviewerID");
			FRVElement.setTextContent(applications.getFirst_ReviewerID());
			root.appendChild(FRVElement);
			Element FRVNElement = document.createElement("first_ReviewerUserName");
			FRVNElement.setTextContent(applications.getFirst_ReviewerUserName());
			root.appendChild(FRVNElement);
			Element SRVElement = document.createElement("second_ReviewerID");
			SRVElement.setTextContent(applications.getSecond_ReviewerID());
			root.appendChild(SRVElement);
			Element SRVNElement = document.createElement("second_ReviewerUserName");
			SRVNElement.setTextContent(applications.getSecond_ReviewerUserName());
			root.appendChild(SRVNElement);
			Element CElement = document.createElement("capacity");
			CElement.setTextContent(applications.getCapacity());
			root.appendChild(CElement);
			
			
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
	
	//update a application
	public Boolean updateApplications(Applications applications) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/"
				+ applications.get_appID() + ".xml");
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
					applications.get_appID())
					&& applications.get_appID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(applications.get_candidateProfileID())
					&& applications.get_candidateProfileID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(applications.get_jobID())
					&& applications.get_jobID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(applications.getCoverLetter())
					&& applications.getCoverLetter() != null) {
				tmpElement.setTextContent(applications.getCoverLetter());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(applications.getStatus())
					&& applications.getStatus() != null) {
				tmpElement.setTextContent(applications.getStatus());
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
	
	//update the status of application
	public Boolean applicationsStatusUpdate(String _appID, String status) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/HiringTeamFolder/"
				+ _appID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			//*****check the structure of files*****
			document.getElementsByTagName("status").item(0).setTextContent("autoChecked");
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
	
	//delete a candidate profile
	public Boolean deleteApplications(String _appID) {
		File candidateFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/" + _appID + ".xml");
		candidateFile.delete();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ApplicationsControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = document.getElementsByTagName("App");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getFirstChild();
				if (idElement.getTextContent().equals(_appID)) {
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
	public List<ApplicationSearch> searchApplicationsForOneJob(String _jobID) {
		List<ApplicationSearch> applicationSearchList = new ArrayList<ApplicationSearch>();
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/";
		// check the number of jobs
		int numOfApps = 0;
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ApplicationsControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Applications></Applications>");
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
				numOfApps = Integer.parseInt(root.getLastChild()
						.getFirstChild().getTextContent()) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// search in each XML file
		for (int i = 0; i < numOfApps; i++) {
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
					String jobIDInFile = root
							.getElementsByTagName("_jobID").item(0)
							.getTextContent();
					Vector<String> resVector = new Vector<String>();
					resVector = correspondingJobAndCompany(jobIDInFile);
					if (jobIDInFile.equals(_jobID)) {
						ApplicationSearch applicationSearch = new ApplicationSearch();
						applicationSearch.set_applicationID(root.getElementsByTagName("_appID")
								.item(0).getTextContent());
						applicationSearch.setApplicationStatus(root.getElementsByTagName("status")
								.item(0).getTextContent());
						applicationSearch.set_jobID(resVector.elementAt(0));
						applicationSearch.setJobTitle(resVector.elementAt(1));
						applicationSearch.set_companyProfileID(resVector.elementAt(2));
						applicationSearch.setCompanyEmail(resVector.elementAt(3));
						applicationSearch.setCompanyName(resVector.elementAt(4));
						applicationSearchList.add(applicationSearch);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return applicationSearchList;
	}
	
	//search applications post by one person
	public List<ApplicationSearch> searchApplicationsForOnePerson(String _candidateID) {
		List<ApplicationSearch> applicationSearchList = new ArrayList<ApplicationSearch>();
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/";
		// check the number of jobs
		int numOfApps = 0;
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ApplicationsControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Applications></Applications>");
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
				numOfApps = Integer.parseInt(root.getLastChild()
						.getFirstChild().getTextContent()) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// search in each XML file
		for (int i = 0; i < numOfApps; i++) {
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
					String jobIDInFile = root.getElementsByTagName("_candidateProfileID")
							.item(0).getTextContent();
					if (jobIDInFile.equals(_candidateID)) {
						Vector<String> resVector = new Vector<String>();
						resVector = correspondingJobAndCompany(root.getElementsByTagName("_jobID")
								.item(0).getTextContent());
						ApplicationSearch applicationSearch = new ApplicationSearch();
						applicationSearch.set_applicationID(root.getElementsByTagName("_appID").item(0)
								.getTextContent());
						applicationSearch.setApplicationStatus(root.getElementsByTagName("status").item(0)
								.getTextContent());
						applicationSearch.set_jobID(resVector.elementAt(0));
						applicationSearch.setJobTitle(resVector.elementAt(1));
						applicationSearch.set_companyProfileID(resVector.elementAt(2));
						applicationSearch.setCompanyEmail(resVector.elementAt(3));
						applicationSearch.setCompanyName(resVector.elementAt(4));
						applicationSearchList.add(applicationSearch);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return applicationSearchList;
	}
	
	//find corresponding jobid and companyid([0]jobid, [1]jobTitle, [2]companyid, [3]companyEmail, [4]companyName)
	public Vector<String> correspondingJobAndCompany(String _jobID) {
		Vector<String> resVector = new Vector<String>();
		resVector.add(_jobID);  //jobid
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/";
		File file = new File(filePath + "JobPostingFolder/" + _jobID + ".xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Job></Job>");
				printWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			resVector.add(root.getElementsByTagName("title").item(0).getTextContent());   //jobTitle
			String companyIDInFile = root.getElementsByTagName("_companyProfileID").item(0).getTextContent();
			resVector.add(companyIDInFile);   //companyid
			File companyFile = new File(filePath + "CompanyProfileFolder/" + companyIDInFile + ".xml");
			if (!companyFile.exists()) {
				try {
					companyFile.createNewFile();
					PrintWriter printWriter = new PrintWriter(companyFile);
					printWriter.print("<Company></Company>");
					printWriter.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			DocumentBuilderFactory documentBuilderFactory2 = DocumentBuilderFactory.newInstance();
			Element root2 = null;
			documentBuilderFactory2.setIgnoringElementContentWhitespace(true);
			DocumentBuilder documentBuilder2 = documentBuilderFactory2.newDocumentBuilder();
			Document document2 = documentBuilder2.parse(companyFile);
			root2 = document2.getDocumentElement();
			resVector.add(root2.getElementsByTagName("companyEmail").item(0).getTextContent());   //companyEmail
			resVector.add(root2.getElementsByTagName("companyName").item(0).getTextContent());   //companyName
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resVector;
	}
	
	//update a application
	public Boolean updateReviewer(Applications applications) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/"
				+ applications.get_appID() + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			if (root.getElementsByTagName("capacity").item(0).getTextContent().equals("2")) {
				return false;
			}
			if (document.getElementsByTagName("first_ReviewerID").item(0).getTextContent().equals("null") 
					&& applications.getFirst_ReviewerID() != null) {
				root.getElementsByTagName("first_ReviewerID").item(0)
					.setTextContent(applications.getFirst_ReviewerID());
				root.getElementsByTagName("first_ReviewerUserName").item(0)
				.setTextContent(applications.getFirst_ReviewerUserName());
				String tmp = Integer.toString((Integer.parseInt(
						root.getElementsByTagName("capacity").item(0).getTextContent()) + 1));
				root.getElementsByTagName("capacity").item(0).setTextContent(tmp);
			}
			if (root.getElementsByTagName("second_ReviewerID").item(0).getTextContent().equals("null") 
					&& applications.getSecond_ReviewerID() != null) {
				root.getElementsByTagName("second_ReviewerID").item(0)
					.setTextContent(applications.getSecond_ReviewerID());
				root.getElementsByTagName("second_ReviewerUserName").item(0)
				.setTextContent(applications.getSecond_ReviewerUserName());
				String tmp = Integer.toString((Integer.parseInt(
						root.getElementsByTagName("capacity").item(0).getTextContent()) + 1));
				root.getElementsByTagName("capacity").item(0).setTextContent(tmp);				
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
	
	//search applications under one reviewer whose status is autoChecked
	public List<Applications> searchApplicationsForOneReviewer(String _reviewerID) {
		List<Applications> applicationsList = new ArrayList<Applications>();
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/";
		// check the number of applications
		int numOfApps = 0;
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/ApplicationsControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Applications></Applications>");
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
				numOfApps = Integer.parseInt(root.getLastChild()
						.getFirstChild().getTextContent()) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// search in each XML file
		for (int i = 0; i < numOfApps; i++) {
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
					String firstReviewerIDInFile = root.getElementsByTagName("first_ReviewerID")
							.item(0).getTextContent();
					String secondReviewerIDInFile = root.getElementsByTagName("second_ReviewerID")
							.item(0).getTextContent();
					String statusInFile = root.getElementsByTagName("status")
							.item(0).getTextContent();
					if ((firstReviewerIDInFile.equals(_reviewerID) 
							|| secondReviewerIDInFile.equals(_reviewerID)) 
							&& (statusInFile.equals("autoChecked") || statusInFile.equals("inProcess"))) {
						String _reviewID = root.getElementsByTagName("_reviewID").item(0).getTextContent();
						if (!_reviewID.equals("null")) {
							File reviewsfile = new File(System.getProperty("catalina.home")
									+ "/webapps/ROOT/ReviewsFolder/"
									+ _reviewID + ".xml");
							documentBuilderFactory = DocumentBuilderFactory.newInstance();
							Element root1 = null;
							documentBuilderFactory.setIgnoringElementContentWhitespace(true);
							documentBuilder = documentBuilderFactory
									.newDocumentBuilder();
							document = documentBuilder.parse(reviewsfile);
							root1 = document.getDocumentElement();
							String firstReviewerID = root1.getElementsByTagName("first_reviewerID")
									.item(0).getTextContent();
							String secondReviewerID = root1.getElementsByTagName("second_reviewerID")
									.item(0).getTextContent();
							if (!firstReviewerID.equals(_reviewerID) && !secondReviewerID.equals(_reviewID)) {
								Applications applications = new Applications();
								applications.set_appID(root.getElementsByTagName("_appID").item(0).getTextContent());
								applications.set_candidateProfileID(root.getElementsByTagName
										("_candidateProfileID").item(0).getTextContent());
								applications.set_jobID(root.getElementsByTagName("_jobID").item(0).getTextContent());
								applications.setCoverLetter(root.getElementsByTagName
										("coverLetter").item(0).getTextContent());
								applications.setStatus(root.getElementsByTagName("status").item(0).getTextContent());
								applications.set_reviewID(root.getElementsByTagName
										("_reviewID").item(0).getTextContent());
								applications.setFirst_ReviewerID(root.getElementsByTagName
										("first_ReviewerID").item(0).getTextContent());
								applications.setFirst_ReviewerUserName(root.getElementsByTagName
										("first_ReviewerUserName").item(0).getTextContent());
								applications.setSecond_ReviewerID(root.getElementsByTagName
										("second_ReviewerID").item(0).getTextContent());
								applications.setSecond_ReviewerUserName(root.getElementsByTagName
										("second_ReviewerUserName").item(0).getTextContent());
								applications.setCapacity(root.getElementsByTagName("capacity").item(0)
										.getTextContent());
								applicationsList.add(applications);
							}
						}
						else {
							Applications applications = new Applications();
							applications.set_appID(root.getElementsByTagName("_appID").item(0).getTextContent());
							applications.set_candidateProfileID(root.getElementsByTagName
									("_candidateProfileID").item(0).getTextContent());
							applications.set_jobID(root.getElementsByTagName("_jobID").item(0).getTextContent());
							applications.setCoverLetter(root.getElementsByTagName
									("coverLetter").item(0).getTextContent());
							applications.setStatus(root.getElementsByTagName("status").item(0).getTextContent());
							applications.set_reviewID(root.getElementsByTagName
									("_reviewID").item(0).getTextContent());
							applications.setFirst_ReviewerID(root.getElementsByTagName
									("first_ReviewerID").item(0).getTextContent());
							applications.setFirst_ReviewerUserName(root.getElementsByTagName
									("first_ReviewerUserName").item(0).getTextContent());
							applications.setSecond_ReviewerID(root.getElementsByTagName
									("second_ReviewerID").item(0).getTextContent());
							applications.setSecond_ReviewerUserName(root.getElementsByTagName
									("second_ReviewerUserName").item(0).getTextContent());
							applications.setCapacity(root.getElementsByTagName("capacity").item(0)
									.getTextContent());
							applicationsList.add(applications);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return applicationsList;
	}
	
	//search a map of one application(key) and one candidateProfile(value)
	public CandidateProfile searchCandidateProfile(String _appID) {
		CandidateProfile candidateProfile = new CandidateProfile();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ApplicationsFolder/"
				+ _appID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			File cFile = new File(System.getProperty("catalina.home")
					+ "/webapps/ROOT/CandidateProfileFolder/"
					+ root.getElementsByTagName("_jobID").item(0).getTextContent() + ".xml");
			documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			root = null;
			documentBuilderFactory.setIgnoringElementContentWhitespace(true);
			documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			document = documentBuilder.parse(cFile);
			root = document.getDocumentElement();
			candidateProfile.set_profileID(root.getElementsByTagName("_profileID").item(0).getTextContent());
			candidateProfile.setFirstName(root.getElementsByTagName("firstName").item(0).getTextContent());
			candidateProfile.setLastName(root.getElementsByTagName("lastName").item(0).getTextContent());
			candidateProfile.setNickName(root.getElementsByTagName("nickName").item(0).getTextContent());
			candidateProfile.setAge(root.getElementsByTagName("age").item(0).getTextContent());
			candidateProfile.setAddress(root.getElementsByTagName("address").item(0).getTextContent());
			candidateProfile.setEmail(root.getElementsByTagName("email").item(0).getTextContent());
			candidateProfile.setContactNumber(root.getElementsByTagName("contactNumber").item(0).getTextContent());
			candidateProfile.setCurrentPosition(root.getElementsByTagName("currentPosition").item(0).getTextContent());
			candidateProfile.setEducation(root.getElementsByTagName("education").item(0).getTextContent());
			candidateProfile.setProfessionalSkill(root.getElementsByTagName("professionalSkill").item(0).getTextContent());
			candidateProfile.setExperience(root.getElementsByTagName("experience").item(0).getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateProfile;
	}
	
}
