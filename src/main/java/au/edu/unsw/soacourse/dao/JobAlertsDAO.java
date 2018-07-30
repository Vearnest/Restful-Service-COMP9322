package au.edu.unsw.soacourse.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import au.edu.unsw.soacourse.util.MailSender;

public class JobAlertsDAO {
	
	public JobAlertsDAO() {
		
	}

	public String jobAlerts(String keyword, String sortby, String email) throws IOException, URISyntaxException, TransformerException{
		String folderPath = Thread.currentThread().getContextClassLoader().getResource("").toString();   
        folderPath = folderPath.replace("file:", "/");
        folderPath = folderPath.replace("classes/", "");  
        folderPath = folderPath.substring(1); 
        folderPath += "JobAlertsResource/";
       
        File outFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/JobAlertsResult");
        if (!outFile.exists()) {
			outFile.mkdir();
		}
        outFile = new File(System.getProperty("catalina.home")
				+ "/webapps/ROOT/JobAlertsResult/out.xml");
        if (!outFile.exists()) {
			outFile.createNewFile();
		}
        
        boolean sort = true;
        if (sortby == null) {
			sort = false;
		}
        String res = "";
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
	    	Source xslt;
	    	if (sort)
	    		xslt = new StreamSource(new File(folderPath + "sortSearch.xsl"));
	        else
	        	xslt = new StreamSource(new File(folderPath + "noSortSearch.xsl"));
			Transformer transformer = factory.newTransformer(xslt);
			transformer.setParameter("keyword", keyword);
	        if (sort)
	            transformer.setParameter("sortby", sortby);
	        Source text = new StreamSource(new File(folderPath + "jobAlerts.xml"));
	        //File file = new File("/Users/daixuancheng/Desktop/Semester4/COMP9322/cs9322-Prac/workspace/testDB/src/test/out.xml");
	        PrintWriter pw = new PrintWriter(outFile);
	        transformer.transform(text, new StreamResult(pw));
			BufferedReader bf = new BufferedReader(new FileReader(outFile));
			String content = "";
			StringBuilder sb = new StringBuilder();
			while (content != null) {
				content = bf.readLine();
				if (content == null) {
					break;
				}
				res += content;
				sb.append(content.trim());
			}
			bf.close();
			
			try {
				String subject = "Job Alerts";
				String url = "http://localhost:8080/FoundITRestfulService/JobAlerts/jobs?keyword=" + keyword + "&sort_by=" + sortby + "&email=" + email;
				String message = "Click the URL.\n"
						+ url + "\n";

				System.out.println(email);
				MailSender.sendTextMail("webapp9321@gmail.com", "webapp9321", "kaka9321", email, subject, message);
				
			} catch (Exception e) {}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
}