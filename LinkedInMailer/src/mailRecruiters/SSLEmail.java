package mailRecruiters;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for SSL: 465
	 */
	public static void main(String[] args) {
		Sender s = new Sender();
		Scanner in = new Scanner(System.in);
		System.out.println("Sender's email :");
		s.setEmailID(in.nextLine());
		System.out.println("Sender's password :");
		s.setPassword(in.nextLine());
		SSLEmail ss = new SSLEmail();
		ss.setUpMailConfiguration(s);
		in.close();
	}
	
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

	private void sendMails(Session session, String sender, String subject){
		ArrayList<Contact> recruiterList = ContactsDataBase.getRecruiters();
		int count = 0;
		for(Contact currRecruiter : recruiterList){
			String body = getMailBody(currRecruiter);
			EmailUtil.sendEmail(session, sender, currRecruiter.getEmailId(), subject, body);	
			count++;
		}
		System.out.println("Mail sent to "+count+" recruiters");
	}
	
	private String getMailBody(Contact currRec){
		String body = "Hello "+currRec.getFirstName()+",\n\nThanks for connecting with me on LinkedIn. I am"
				+ " a graduate student at Indiana University, Bloomington pursuing Master's in Computer Science.\n"
				+ "I’m reaching out to explore potential opportunities you have for someone with my background and "
				+ "qualifications. \nI have expertise in Software Development and am interested "
				+ "in exploring positions where I can add value to "+currRec.getCompany()+".\n\n"
				+ "It would be a sincere pleasure to hear back from you. You can reach me through email or "
				+ "the phone number below.\n\n"
				+ "Best Regards,\n"
				+ "Prasandeep Singh\n"
				+ "812-272-8705\n"
				+ "<html><head></head><body><a href=\"https://www.linkedin.com/in/prasandeepsingh/\">LinkedIn</a>"
				+ "</body></html>";
		return body;
	}
	
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
//	
//	private ArrayList<String> getRecievers(){
//		ArrayList<String> emailList = new ArrayList<>();
//		ArrayList<Contact> recruiterList = ContactsDataBase.getRecruiters();
//		System.out.println("Total number of recruiters : "+recruiterList.size());
//		for(Contact r : recruiterList){
//			emailList.add(r.getEmailId());
//		//	System.out.println(r);
//		}
//		return emailList;
//	}
}