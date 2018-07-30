package au.edu.unsw.soacourse.util;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;




public class MailSender {
	static public boolean sendTextMail(String fromEmail, final String username, final String password, String toEmail, String subject, String message)
{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.host", "smtp.gmail.com");
		
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.socketFactory.class", "javax.net.ssl,SSLSocketFactory");
		//props.put("mail.smtp.socketFactory.port", "465");
		//props.put("mail.smtp.socketFactory.fallback", "false");
		
		Session mailSession = Session.getDefaultInstance(props,
				new javax.mail.Authenticator(){
					protected PasswordAuthentication getPasswordAuthentication(){
						return new PasswordAuthentication(username, password);
					}
		});
		mailSession.setDebug(true);
		Message mailMessage = new MimeMessage(mailSession);
		
		try {
			mailMessage.setFrom(new InternetAddress(fromEmail));
			mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			mailMessage.setContent(message, "text/html");
			mailMessage.setSubject(subject);
			
			Transport transport = mailSession.getTransport("smtp");
			transport.connect("smtp.gmail.com", username, password);
			
			transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
//		props.put("mail.smtp.password", "jackwang12");
//		Session session = Session.getDefaultInstance(props,
//				new Authenticator() {
//					@Override
//					protected PasswordAuthentication getPasswordAuthentication() {
//						String username = "vipkaka22@163.com";
//						String password = "jackwang12";
//						return new PasswordAuthentication(username, password);
//					}
//				});
//		
//			Message mailMessage = new MimeMessage(session);
//			Address from = new InternetAddress("vipkaka22@163.com");
//			mailMessage.setFrom(from);
//			Address to = new InternetAddress(toAddress);
//			mailMessage.setRecipient(Message.RecipientType.TO, to);
//			mailMessage.setSubject(subject);
//			mailMessage.setSentDate(new Date());
//			String mailContent = context;
//			mailMessage.setText(mailContent);
//			Transport.send(mailMessage);
//			return true;
		return false;
	}
}
