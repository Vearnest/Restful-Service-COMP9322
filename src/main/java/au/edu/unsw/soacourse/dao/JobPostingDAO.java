package au.edu.unsw.soacourse.dao;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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

import au.edu.unsw.soacourse.model.JobPosting;
import au.edu.unsw.soacourse.model.JobSearch;

public class JobPostingDAO {

	public JobPostingDAO(){
		
	}
	
	//create a new _jobID
	public String createNew_JobID(String _companyProfileID) {
		String newJobID = null;
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/JobPostingControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<JobPosting></JobPosting>");
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
			Element entryElement = document.createElement("Job");
			root.appendChild(entryElement);
			Element jobIdElement = document.createElement("Jobid");
			jobIdElement.setTextContent(newJobID);
			entryElement.appendChild(jobIdElement);
			Element companyIdElement = document.createElement("Companyid");
			companyIdElement.setTextContent(_companyProfileID);
			entryElement.appendChild(companyIdElement);
			Element statusElement = document.createElement("Status");
			entryElement.appendChild(statusElement);
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
	
	//write in the new job
	public void writeInNewJob(JobPosting jobPosting) {
		//write in jobpostingfolder
		File folder = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/JobPostingFolder");
		if (!folder.exists()) {
			folder.mkdir();
		}
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/JobPostingFolder/"
				+ jobPosting.get_jobID() + ".xml");
		try {
			if (!file.exists()) {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<Job></Job>");
				printWriter.close();
			}
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			Element root = null;
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Element jobidElement = document.createElement("_jobID");
			jobidElement.setTextContent(jobPosting.get_jobID());
			root.appendChild(jobidElement);
			Element cidElement = document.createElement("_companyProfileID");
			cidElement.setTextContent(jobPosting.get_companyProfileID());
			root.appendChild(cidElement);
			Element TElement = document.createElement("title");
			TElement.setTextContent(jobPosting.getTitle());
			root.appendChild(TElement);
			Element SRElement = document.createElement("salaryRate");
			SRElement.setTextContent(jobPosting.getSalaryRate());
			root.appendChild(SRElement);
			Element PTElement = document.createElement("positionType");
			PTElement.setTextContent(jobPosting.getPositionType());
			root.appendChild(PTElement);
			Element LElement = document.createElement("location");
			LElement.setTextContent(jobPosting.getLocation());
			root.appendChild(LElement);
			Element JDElement = document.createElement("jobDescription");
			JDElement.setTextContent(jobPosting.getJobDescription());
			root.appendChild(JDElement);
			Element RElement = document.createElement("requirement");
			RElement.setTextContent(jobPosting.getRequirement());
			root.appendChild(RElement);
			Element SElement = document.createElement("status");
			SElement.setTextContent(jobPosting.getStatus());
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
		//write in control file
		File controlFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/JobPostingControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		DocumentBuilder documentBuilder;
		try {
			documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(controlFile);
			root = document.getDocumentElement();
			root.getLastChild().getLastChild().setTextContent(jobPosting.getStatus());
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "no");
			DOMSource source = new DOMSource(document);
			PrintWriter printWriter = new PrintWriter(controlFile);
			StreamResult streamResult = new StreamResult(printWriter);
			transformer.transform(source, streamResult);
			printWriter.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//search for one job
	public JobPosting searchOneJob(String _jobID) {
		JobPosting jobPosting = new JobPosting();
		File jobFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/JobPostingFolder/" + _jobID + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			Document document = documentBuilder.parse(jobFile);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			jobPosting.set_jobID(tmpElement.getTextContent());
			jobPosting.set_companyProfileID((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			jobPosting.setTitle((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			jobPosting.setSalaryRate((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			jobPosting.setPositionType((tmpElement = tmpElement.getNextSibling())
					.getTextContent());
			jobPosting.setLocation((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			jobPosting
					.setJobDescription((tmpElement = tmpElement.getNextSibling())
							.getTextContent());
			jobPosting.setRequirement((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
			jobPosting.setStatus((tmpElement = tmpElement
					.getNextSibling()).getTextContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jobPosting;
	}
	
	//search in the limitation
	public List<JobSearch> searchJobs(String keyword, String skills, String status) {
		List<JobSearch> jobSearchList = new ArrayList<JobSearch>();
		String[] skillsVector = null;
		String[] keywordVector = null;
		if (keyword != null) {
			keywordVector = keyword.split(",");
		}
		if (skills != null) {
			skillsVector = skills.split(",");
		}
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/JobPostingFolder/";
		//check the number of jobs
		int numOfJobs = 0;
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/JobPostingControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<JobPosting></JobPosting>");
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
				numOfJobs = 0;
			} else {
				numOfJobs = Integer.parseInt(root
						.getLastChild().getFirstChild().getTextContent()) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//search in each XML file
		for (int i = 0; i < numOfJobs; i++) {
			File jobFile = new File(filePath + i + ".xml");
			if (jobFile.exists()) {
				Boolean checkExists = true;
				documentBuilderFactory = DocumentBuilderFactory
						.newInstance();
				root = null;
				documentBuilderFactory.setIgnoringElementContentWhitespace(true);
				try {
					DocumentBuilder documentBuilder = documentBuilderFactory
							.newDocumentBuilder();
					Document document = documentBuilder.parse(jobFile);
					root = document.getDocumentElement();
					//search status
					if (!root.getLastChild().getTextContent().equals(status) 
							&& status != null && !status.equals("")) {
						checkExists = false;
					}
					//search skills
					if (checkExists == true && skills != null && !skills.equals("")) {
						String skillList = root.getElementsByTagName("requirement").item(0).getTextContent();
						for (int j = 0; j < skillsVector.length; j++) {
							if (!skillList.contains(skillsVector[j])) {
								checkExists = false;
							}
							else {
								checkExists = true;
								break;
							}
						}
					}
					//search keyword
					if (checkExists == true && keyword != null && !keyword.equals("")) {
						Node tmpNode = root.getFirstChild();
						String tmpString = "";
						while(tmpNode != null) {
							tmpString += (tmpNode.getTextContent() + " ");
							tmpNode = tmpNode.getNextSibling();
						}
						for (int j = 0; j < skillsVector.length; j++) {
							if (!tmpString.contains(keywordVector[j])) {
								checkExists = false;	
							}
							else {
								checkExists = true;
								break;
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (checkExists == true) {
					try {
						DocumentBuilder documentBuilder = documentBuilderFactory
								.newDocumentBuilder();
						Document document = documentBuilder.parse(jobFile);
						root = document.getDocumentElement();
						JobSearch jobSearch = new JobSearch();
						jobSearch.set_jobID(root.getElementsByTagName("_jobID").item(0).getTextContent());
						jobSearch.setTitle(root.getElementsByTagName("title").item(0).getTextContent());
						jobSearch.setRequirement(root.getElementsByTagName("requirement").item(0).getTextContent());
						jobSearch.setStatus(root.getElementsByTagName("status").item(0).getTextContent());
						jobSearchList.add(jobSearch);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return jobSearchList;
	}
	
	//update a job
	public Boolean updateJob(JobPosting jobPosting) {
		File file = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/JobPostingFolder/" + jobPosting.get_jobID() + ".xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			Node tmpElement = root.getFirstChild();
			if (!tmpElement.getTextContent().equals(jobPosting.get_jobID()) 
					&& jobPosting.get_jobID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.get_companyProfileID()) 
					&& jobPosting.get_companyProfileID() != null) {
				return false;
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.getTitle())
					&& jobPosting.getTitle() != null) {
				tmpElement.setTextContent(jobPosting.getTitle());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.getSalaryRate())
					&& jobPosting.getSalaryRate() != null) {
				tmpElement.setTextContent(jobPosting.getSalaryRate());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.getPositionType())
					&& jobPosting.getPositionType() != null) {
				tmpElement.setTextContent(jobPosting.getPositionType());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.getLocation())
					&& jobPosting.getLocation() != null) {
				tmpElement.setTextContent(jobPosting.getLocation());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.getJobDescription())
					&& jobPosting.getJobDescription() != null) {
				tmpElement.setTextContent(jobPosting.getJobDescription());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.getRequirement())
					&& jobPosting.getRequirement() != null) {
				tmpElement.setTextContent(jobPosting.getRequirement());
			}
			if (!(tmpElement = tmpElement.getNextSibling()).getTextContent()
					.equals(jobPosting.getStatus())
					&& jobPosting.getStatus() != null) {
				tmpElement.setTextContent(jobPosting.getStatus());
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
	
	//delete a job
	public Boolean deleteJob(String _jobID) {
		File jobFile = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/JobPostingFolder/" + _jobID + ".xml");
		jobFile.delete();
		File file = new File(System.getProperty("catalina.home") 
				+ "/webapps/ROOT/ControlFolder/JobPostingControl.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		Element root = null;
		documentBuilderFactory.setIgnoringElementContentWhitespace(true);
		try {
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			root = document.getDocumentElement();
			NodeList entries = document.getElementsByTagName("Job");
			for (int i = 0; i < entries.getLength(); ++i) {
				Element curElement = (Element) entries.item(i);
				Element idElement = (Element) curElement.getFirstChild();
				if (idElement.getTextContent().equals(_jobID)){
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
	
	//search jobs post by one company
	public List<JobSearch> searchJobsForOneCompany(String _companyProfileID) {
		List<JobSearch> jobSearchList = new ArrayList<JobSearch>();
		String filePath = System.getProperty("catalina.home")
				+ "/webapps/ROOT/JobPostingFolder/";
		//check the number of jobs
		int numOfJobs = 0;
		File file = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/ControlFolder/JobPostingControl.xml");
		if (!file.exists()) {
			try {
				file.createNewFile();
				PrintWriter printWriter = new PrintWriter(file);
				printWriter.print("<JobPosting></JobPosting>");
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
				numOfJobs = 0;
			} else {
				numOfJobs = Integer.parseInt(root
						.getLastChild().getFirstChild().getTextContent()) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//search in each XML file
		for (int i = 0; i < numOfJobs; i++) {
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
					String companyIDInFile = root.getElementsByTagName("_companyProfileID")
							.item(0).getTextContent();
					if (companyIDInFile.equals(_companyProfileID)) {
						JobSearch jobSearch = new JobSearch();
						jobSearch.set_jobID(root.getElementsByTagName("_jobID")
								.item(0).getTextContent());
						jobSearch.setTitle(root.getElementsByTagName("title")
								.item(0).getTextContent());
						jobSearch.setRequirement(root
								.getElementsByTagName("requirement").item(0)
								.getTextContent());
						jobSearch.setStatus(root.getElementsByTagName("status")
								.item(0).getTextContent());
						jobSearchList.add(jobSearch);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return jobSearchList;
	}
}
