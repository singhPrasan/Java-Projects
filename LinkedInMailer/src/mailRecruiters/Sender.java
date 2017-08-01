/**
 * A class to store information about the sender mainly his/her emailID, password and the subject of E-mail
 * 
 * @author Prasandeep Singh
 * @created - 07/31/2017
 */

package mailRecruiters;

public class Sender {
	private String emailID;
	private String password;
	private String mailSubject;
	
	public Sender(){
		this.mailSubject = "Seeking New Grad Oppurtunities | Software Developer | Resume Attached";
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMailSubject() {
		return mailSubject;
	}
	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}
}