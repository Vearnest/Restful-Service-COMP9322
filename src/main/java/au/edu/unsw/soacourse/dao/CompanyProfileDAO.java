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

import au.edu.unsw.soacourse.model.CompanyProfile;

public class CompanyProfileDAO {

	public CompanyProfileDAO() {
		
	}
	
	//create a new _profileID
	public String createNew_ProfileID(String companyEmail) {
		String newProfileID = null;
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/CompanyProfileControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<CompanyProfiles></CompanyProfiles>");
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
				newProfileID = "0";
			}
			else {
				newProfileID = String.valueOf(
						Integer.parseInt(root.getLastChild().getFirstChild().getTextContent()) + 1);
			}
			Element entryElement = document.createElement("Company");
			root.appendChild(entryElement);
			Element idElement = document.createElement("Id");
			idElement.setTextContent(newProfileID);
			entryElement.appendChild(idElement);
			Element ABNElement = document.createElement("Email");
			ABNElement.setTextContent(companyEmail);
			entryElement.appendChild(ABNElement);
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
	
	//write in the new company profile
	public void writeInNewProfile(CompanyProfile companyProfile) {
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/CompanyProfileFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/CompanyProfileFolder/"
				+ companyProfile.get_profileID() + ".xml");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Company></Company>");
				printWriter.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			Element root = null;
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Element idElement = document.createElement("_profileID");
			idElement.setTextContent(companyProfile.get_profileID());
			root.appendChild(idElement);
			Element CNElement = document.createElement("companyName");
			CNElement.setTextContent(companyProfile.getCompanyName());
			root.appendChild(CNElement);
			Element ABNElement = document.createElement("ABN");
			ABNElement.setTextContent(companyProfile.getABN());
			root.appendChild(ABNElement);
			Element adrElement = document.createElement("companyAddress");
			adrElement.setTextContent(companyProfile.getCompanyAddress());
			root.appendChild(adrElement);
			Element mElement = document.createElement("companyEmail");
			mElement.setTextContent(companyProfile.getCompanyEmail());
			root.appendChild(mElement);
			Element contactElement = document.createElement("contactNumber");
			contactElement.setTextContent(companyProfile.getContactNumber());
			root.appendChild(contactElement);
			Element IElement = document.createElement("introduction");
			IElement.setTextContent(companyProfile.getIntroduction());
			root.appendChild(IElement);
			Element URLElement = document.createElement("mainPageURL");
			URLElement.setTextContent(companyProfile.getMainPageURL());
			root.appendChild(URLElement);
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
	public String emailToID(String companyEmail) {
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/CompanyProfileControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<CompanyProfiles></CompanyProfiles>");
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
			NodeList entries = root.getChildNodes();
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element emailElement = (Element) curElement.getLastChild();
				if (emailElement.getTextContent().equals(companyEmail)) {
					return curElement.getFirstChild().getTextContent();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//search for a company profile
	public CompanyProfile searchProfile(String _profileID) {
		CompanyProfile companyProfile = new CompanyProfile();
		File companyFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/CompanyProfileFolder/" + _profileID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(companyFile);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			companyProfile.set_profileID(tmpElement.getTextContent());
			companyProfile.setCompanyName((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			companyProfile.setABN((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			companyProfile.setCompanyAddress((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			companyProfile
					.setCompanyEmail((tmpElement = tmpElement.getNextSibling())
							.getTextContent());
			companyProfile.setContactNumber((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			companyProfile.setIntroduction((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			companyProfile.setMainPageURL((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return companyProfile;
	}
	
	//update a company profile
	public Boolean updateProfile(CompanyProfile companyProfile) {
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/CompanyProfileFolder/"
				+ companyProfile.get_profileID() + ".xml");
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
			if (!tmpElement.getTextContent().equals(companyProfile.get_profileID()) 
					&& companyProfile.get_profileID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(companyProfile.getCompanyName())
					&& companyProfile.getCompanyName() != null) {
				tmpElement.setTextContent(companyProfile.getCompanyName());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(companyProfile.getABN())
					&& companyProfile.getABN() != null) {
				tmpElement.setTextContent(companyProfile.getABN());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(companyProfile.getCompanyAddress())
					&& companyProfile.getCompanyAddress() != null) {
				tmpElement.setTextContent(companyProfile.getCompanyAddress());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(companyProfile.getCompanyEmail())
					&& companyProfile.getCompanyEmail() != null) {
				tmpElement.setTextContent(companyProfile.getCompanyEmail());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(companyProfile.getContactNumber())
					&& companyProfile.getContactNumber() != null) {
				tmpElement.setTextContent(companyProfile.getContactNumber());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(companyProfile.getIntroduction())
					&& companyProfile.getIntroduction() != null) {
				tmpElement.setTextContent(companyProfile
						.getIntroduction());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(companyProfile.getMainPageURL())
					&& companyProfile.getMainPageURL() != null) {
				tmpElement.setTextContent(companyProfile.getMainPageURL());
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
	
	//delete a company profile
	public Boolean deleteProfile(String _profileID) {
		File companyFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/CompanyProfileFolder/" + _profileID + ".xml");
		companyFile.delete();
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/CompanyProfileControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = document.getElementsByTagName("Company");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getFirstChild();
				if (idElement.getTextContent().equals(_profileID)) {
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
