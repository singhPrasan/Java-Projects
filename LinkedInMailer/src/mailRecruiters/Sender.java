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