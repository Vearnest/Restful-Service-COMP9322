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

import au.edu.unsw.soacourse.model.CandidateProfile;

public class CandidateProfileDAO {
	
	public CandidateProfileDAO(){
		
	}
	
	//create a new _profileID
	public String createNew_ProfileID(String email) {
		String newProfileID = null;
		File folder = new File(System.getProperty("catalina.home") + "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/ControlFolder/CandidateProfileControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<CandidateProfiles></CandidateProfiles>");
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
			if (root.getLastChild() == null) {
				newProfileID = "0";
			}
			else {
				newProfileID = String.valueOf(
						Integer.parseInt(root.getLastChild().getFirstChild().getTextContent()) + 1);
			}
			Element entryElement = document.createElement("Candidate");
			root.appendChild(entryElement);
			Element idElement = document.createElement("Id");
			idElement.setTextContent(newProfileID);
			entryElement.appendChild(idElement);
			Element emailElement = document.createElement("Email");
			emailElement.setTextContent(email);
			entryElement.appendChild(emailElement);
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
		return newProfileID;
	}
	
	//write in the new candidate profile
	public void writeInNewProfile(CandidateProfile candidateProfile) {
		File folder = new File(System.getProperty("catalina.home") + "/webapps/ROOT/CandidateProfileFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/CandidateProfileFolder/" + candidateProfile.get_profileID() + ".xml");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Candidate></Candidate>");
				printWriter.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			Element root = null;
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Element idElement = document.createElement("_profileID");
			idElement.setTextContent(candidateProfile.get_profileID());
			root.appendChild(idElement);
			Element FNElement = document.createElement("firstName");
			FNElement.setTextContent(candidateProfile.getFirstName());
			root.appendChild(FNElement);
			Element LNElement = document.createElement("lastName");
			LNElement.setTextContent(candidateProfile.getLastName());
			root.appendChild(LNElement);
			Element NNElement = document.createElement("nickName");
			NNElement.setTextContent(candidateProfile.getNickName());
			root.appendChild(NNElement);
			Element ageElement = document.createElement("age");
			ageElement.setTextContent(candidateProfile.getAge());
			root.appendChild(ageElement);
			Element adrElement = document.createElement("address");
			adrElement.setTextContent(candidateProfile.getAddress());
			root.appendChild(adrElement);
			Element mElement = document.createElement("email");
			mElement.setTextContent(candidateProfile.getEmail());
			root.appendChild(mElement);
			Element CNElement = document.createElement("contactNumber");
			CNElement.setTextContent(candidateProfile.getContactNumber());
			root.appendChild(CNElement);
			Element CPElement = document.createElement("currentPosition");
			CPElement.setTextContent(candidateProfile.getCurrentPosition());
			root.appendChild(CPElement);
			Element EDElement = document.createElement("education");
			EDElement.setTextContent(candidateProfile.getEducation());
			root.appendChild(EDElement);
			Element PSElement = document.createElement("professionalSkill");
			PSElement.setTextContent(candidateProfile.getProfessionalSkill());
			root.appendChild(PSElement);
			Element EElement = document.createElement("experience");
			EElement.setTextContent(candidateProfile.getExperience());
			root.appendChild(EElement);
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
	
	//email->id
	public String emailToID(String email) {
		File folder = new File(System.getProperty("catalina.home") + "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/ControlFolder/CandidateProfileControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<CandidateProfiles></CandidateProfiles>");
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
			NodeList entries = root.getChildNodes();
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element emailElement = (Element) curElement.getLastChild();
				if (emailElement.getTextContent().equals(email))
					return curElement.getFirstChild().getTextContent();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//search for a candidate profile
	public CandidateProfile searchProfile(String _profileID) {
		CandidateProfile candidateProfile = new CandidateProfile();
		File candidateFile = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/CandidateProfileFolder/" + _profileID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(candidateFile);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			candidateProfile.set_profileID(tmpElement.getTextContent());
			candidateProfile.setFirstName((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setLastName((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setNickName((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setAge((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setAddress((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setEmail((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setContactNumber((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setCurrentPosition((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setEducation((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setProfessionalSkill((tmpElement = tmpElement.getNextSibling()).getTextContent());
			candidateProfile.setExperience((tmpElement = tmpElement.getNextSibling()).getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return candidateProfile;
	}
	
	//update a candidate profile
	public Boolean updateProfile(CandidateProfile candidateProfile) {
		File file = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/CandidateProfileFolder/" + candidateProfile.get_profileID() + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			if (!tmpElement.getTextContent().equals(candidateProfile.get_profileID()) 
					&& candidateProfile.get_profileID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getFirstName())
					&& candidateProfile.getFirstName() != null) {
				tmpElement.setTextContent(candidateProfile.getFirstName());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getLastName())
					&& candidateProfile.getLastName() != null) {
				tmpElement.setTextContent(candidateProfile.getLastName());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getNickName())
					&& candidateProfile.getNickName() != null) {
				tmpElement.setTextContent(candidateProfile.getNickName());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getAge())
					&& candidateProfile.getAge() != null) {
				tmpElement.setTextContent(candidateProfile.getAge());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getAddress())
					&& candidateProfile.getAddress() != null) {
				tmpElement.setTextContent(candidateProfile.getAddress());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getEmail())
					&& candidateProfile.getEmail() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getContactNumber())
					&& candidateProfile.getContactNumber() != null) {
				tmpElement.setTextContent(candidateProfile.getContactNumber());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getCurrentPosition())
					&& candidateProfile.getCurrentPosition() != null) {
				tmpElement.setTextContent(candidateProfile.getCurrentPosition());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getEducation())
					&& candidateProfile.getEducation() != null) {
				tmpElement.setTextContent(candidateProfile.getEducation());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getProfessionalSkill())
					&& candidateProfile.getProfessionalSkill() != null) {
				tmpElement.setTextContent(candidateProfile.getProfessionalSkill());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(candidateProfile.getExperience())
					&& candidateProfile.getExperience() != null) {
				tmpElement.setTextContent(candidateProfile.getExperience());
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
	
	//delete a candidate profile
	public Boolean deleteProfile(String _profileID) {
		File candidateFile = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/CandidateProfileFolder/" + _profileID + ".xml");
		candidateFile.delete();
		File file = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/ControlFolder/CandidateProfileControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = document.getElementsByTagName("Candidate");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getFirstChild();
				if (idElement.getTextContent().equals(_profileID)){
					root.removeChild((Node)curElement);
					TransformerFactory tFactory = TransformerFactory.newInstance();
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
