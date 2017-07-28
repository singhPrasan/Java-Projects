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
		
		Scanner in = new Scanner(System.in);
		System.out.println("Sender's email :");
		String sender = in.nextLine();
		System.out.println("Sender's password :");
		String password = in.nextLine();
		
		
		SSLEmail ss = new SSLEmail();
		ss.setUpMailConfiguration(sender, password);
//	       
//	        EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");

	     //   EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");

	}
	private void setUpMailConfiguration(String sender, String password){
		
		ArrayList<String> recieversList = getRecievers(); // can be any email id 
		
		Properties props = setUpProperties();
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Session created");
	//	sendMails(session, recieversList, "SSLEmail Testing Subject", "SSLEmail Testing Body");
		 
	}
	
	private void sendMails(Session session, ArrayList<String> recieverList, String subject, String body){
		for(String r : recieverList)
			EmailUtil.sendEmail(session, r, subject, body);	
	}
	
	
	private Properties setUpProperties(){
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		return props;
	}
	private ArrayList<String> getRecievers(){
		ArrayList<String> emailList = new ArrayList<>();
		ArrayList<Contact> recruiterList = ContactsDataBase.getRecruiters();
		System.out.println("Total number of recruiters : "+recruiterList.size());
		for(Contact r : recruiterList){
			emailList.add(r.getEmailId());
		//	System.out.println(r);
		}
		return emailList;
	}
}