/**
 * An immutable passive data object (PDO) to represent information about each person
 * 
 *  @author		 Prasandeep Singh
 *  @created 	 07/26/2017
 *  
 */

package mailRecruiters;

public class Contact {
	private static int id = 1;		//static id field to assign each person a unique id
	private int uniqId;
	private String firstName;
	private String lastName;
	private String jobTitle;
	private String company;
	private String emailId;
	
	//Constructor to initialize all the instance variables
	public Contact(String fName, String lName, String jobTitle, String company, String emailId){
		this.uniqId = id++;
		this.firstName = fName;
		this.lastName = lName;
		this.jobTitle = jobTitle;
		this.company = company;
		this.emailId = emailId;
	}

	//Getter functions
	public int getUniqId() {
		return uniqId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public String getCompany() {
		return company;
	}

	public String getEmailId() {
		return emailId;
	}

	//Returns a concatenated string of all instances of the person object when it is printed
	public String toString(){
		String result = "Person [id="+uniqId+", Name="+firstName+" "+lastName+", Job Title="+jobTitle+", Email="+emailId+"]";
		return result;
	}
	
	//Tests the Contact Class
	private static void testContact(){
		Contact p[] = {new Contact("A", "B", "c", "d", "s"),  new Contact("B", "Bf", "casdf", "d", "s"),new Contact("C", "Bas", "cs", "d", "s")
				, new Contact("D", "Bs", "ca", "d", "s"), new Contact("E", "Bd", "ce", "d", "s")};
		
		for(Contact currP : p)
			System.out.println(currP);
		
	}
	
	//client function to run Person class independently
	public static void main(String[] args) {
		testContact();
	}
}
