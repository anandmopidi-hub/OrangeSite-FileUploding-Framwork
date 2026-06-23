package utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import listeners.ExecutionListener;

public class SendMail {
	 public static void sendReport(long executionTime) {

	        System.out.println("Execution Time : " + executionTime);

	    
			
			final String fromEmail = "anand.mopidi@magnetoitsolutions.com"; 
			final String appPassword = "vtxk uusy zecf rtmo"; 
			final String toEmail = "anand.mopidi@magnetoitsolutions.com"; 
			Properties props = new Properties(); 
			props.put("mail.smtp.host", "smtp.gmail.com"); 
			props.put("mail.smtp.port", "587"); 
			props.put("mail.smtp.auth", "true"); props.put("mail.smtp.starttls.enable", "true"); 
			Session session = Session.getInstance( props, new Authenticator() { @Override protected PasswordAuthentication getPasswordAuthentication() { return new PasswordAuthentication(fromEmail, appPassword); } });
			try {

			    Message message = new MimeMessage(session);

			    message.setFrom(new InternetAddress(fromEmail));

			    message.setRecipients(
			            Message.RecipientType.TO,
			            InternetAddress.parse(toEmail));

			    message.setSubject("OrangeHRM Recruitment Automation Framework using Playwright, TestNG, Google Sheets, and Email Reporting");

			    String status = ExecutionListener.failedTests > 0 ? "FAIL" : "PASS";

			    String htmlBody =
			    "<html>" +
			    "<body>" +
			    "<h2>Automation Report User details Test cases formate</h2>" +
			    "<table border='1' cellpadding='5'>" +
			    "<tr><td><b>Application</b></td><td>OrangeHRM</td></tr>" +
			    "<tr><td><b>Environment</b></td><td>QA</td></tr>" +
			    "<tr><td><b>Framework</b></td><td>Playwright + TestNG</td></tr>" +
			    "<tr><td><b>Total Tests</b></td><td>" + ExecutionListener.totalTests + "</td></tr>" +
			    "<tr><td><b>Passed</b></td><td>" + ExecutionListener.passedTests + "</td></tr>" +
			    "<tr><td><b>Failed</b></td><td>" + ExecutionListener.failedTests + "</td></tr>" +
			    "<tr><td><b>Execution Time</b></td><td>" + executionTime + " Seconds</td></tr>" +
			    "<tr><td><b>Status</b></td><td>" + status + "</td></tr>" +
			    "</table>" +
			    "<br><br>" +
			    "Regards,<br>" +
			    "Anand" +
			    "</body>" +
			    "</html>";

			    message.setContent(htmlBody, "text/html");
			    message.setContent(htmlBody, "text/html");

			    Transport.send(message);

			    System.out.println("Email Sent Successfully");

			} catch (Exception e) {
			    e.printStackTrace();
			}
		}

}
