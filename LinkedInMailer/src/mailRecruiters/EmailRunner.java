/**
 * Class to run code from. 
 * Email configuration and mail content set here
 * API code from : http://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp
 * 	   Outgoing Mail (SMTP) Server
 *	   requires TLS or SSL: smtp.gmail.com (use authentication)
 *	   Use Authentication: Yes
 *	   Port for SSL: 465
 *
 * 
 * @author Prasandeep Singh
 * @created - 07/26/2017
 * @updated - 08/01/2017
 */


package mailRecruiters;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class EmailRunner {
	
	/**
	 * Sets up Configuration settings required to send e-mails
	 * @param s : Sender object that contains details of the user sending the e-mails
	 */
	private void setUpMailConfiguration(Sender s){
		Properties props = setUpProperties();
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(s.getEmailID(), s.getPassword());
			}
		};
		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
		sendMails(session, s.getEmailID(), s.getMailSubject());
	}
	
	/**
	 * Sets up properties 
	 * @return Properties Object 
	 */
	private Properties setUpProperties(){
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		return props;
	}

	/**
	 * Sends email to all recruiters filtered out from the Contacts Database
	 * 
	 * @param session : Holds details of the current session created in the setUpMailConfiguration() function
	 * @param sender  : Sender object that contains details of the user sending the emails
	 * @param subject : Holds email subject as a string
	 */
	private void sendMails(Session session, String sender, String subject){
		ArrayList<Contact> recruiterList = ContactsDataBase.getRecruiters();
		int count = 0;
		for(Contact currRecruiter : recruiterList){
			String body = getMailBody(currRecruiter);
		//	EmailUtil.sendEmail(session, sender, currRecruiter.getEmailId(), subject, body);	
			EmailUtil.sendAttachmentEmail(session, sender, currRecruiter, subject, body, "Resume.pdf");
			count++;
		}
		System.out.println("Mail sent to "+count+" recruiters");
	}
	
	
	/**
	 * Customize your mail body here. Can also pull recruiter data from the Contact database to tailor your mail according to the specific recruiter
	 * @param currRec : Recruiter to whom the mail is to be sent
	 * @return message body as a string
	 */
	private String getMailBody(Contact currRec){
		String body = "Hello "+currRec.getFirstName()+",\n\nThanks for connecting with me on LinkedIn. I am"
				+ " a graduate student at Indiana University, Bloomington pursuing Master's in Computer Science.\n"
				+ "I am reaching out to explore potential opportunities you have for someone with my background and "
				+ "qualifications. \nI have expertise in Software Development and am interested "
				+ "in exploring positions where I can add value to "+currRec.getCompany()+" as a Software Developer.\n\n"
				+ "It would be a sincere pleasure to hear back from you. You can reach me through email or "
				+ "the phone number below.\n\n"
				+ "Best Regards,\n"
				+ "Prasandeep Singh\n"
				+ "812-272-8705\n"
				+ "https://www.linkedin.com/in/prasandeepsingh/";
		return body;
	}
	
	/**
	 * Main method where the code will run from
	 */
	public static void main(String[] args) {
		Sender s = new Sender();
		Scanner in = new Scanner(System.in);
		System.out.println("Sender's email :");
		s.setEmailID(in.nextLine());
		System.out.println("Sender's password :");
		s.setPassword(in.nextLine());
		for(int clear = 0; clear < 100; clear++)
		     System.out.println() ;
		  
		EmailRunner ss = new EmailRunner();
		ss.setUpMailConfiguration(s);
		in.close();
	}
}