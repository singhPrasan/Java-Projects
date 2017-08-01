/**
 *  To access information about all contacts about your Linkedin profile
 *  Simulates a database. All fields are static, no need to create objects
 *  Can be accessed directly using class name itself  
 *  
 *  @author 	Prasandeep Singh
 *  @created	07/26/2017
 *  @updated	08/01/2017
 */

package mailRecruiters;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;

public class ContactsDataBase {
	private static HashMap<Integer, Contact> allContacts;
	private static ArrayList<Contact> recruiters;

	//Initializes allContacts hashmap from the provided input file
    public static void initialize(String contactsFile) {
        if (allContacts == null) {
        	allContacts = new HashMap<Integer,Contact>();
        	recruiters = new ArrayList<Contact>();
            loadAllContacts("data/" + contactsFile);
        }
    }

    //Initializes allContacts hashmap from the default file if no input file is provided
    private static void initialize() {
        if (allContacts == null) {
        	allContacts = new HashMap<Integer,Contact>();
        	recruiters = new ArrayList<Contact>();
            loadAllContacts("data/Contacts-small.csv");
        }
    }	

	//Loads all contacts in the hashmap with key as Contact ID and value as the Contact Object
    private static void loadAllContacts(String filename) {
        ArrayList<Contact> list = loadContacts(filename);
        for (Contact c : list) {
            allContacts.put(c.getUniqId(), c);
        }
    }	
    
	//Loads all the movies from the given CSV file into an arraylist of movie objects
	protected static ArrayList<Contact> loadContacts(String fileName){
		ArrayList<Contact> contactsList = new ArrayList<>();
		FileResource fr = new FileResource(fileName);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord row : parser){
			
			if(row.get("EmailAddress").equals("") || row.get("FirstName").equals("") || row.get("Title").equals("")) continue;
			Contact currContact = new Contact(row.get("FirstName"), row.get("LastName"),  row.get("Title"), row.get("Companies"),
						 row.get("EmailAddress"));
			contactsList.add(currContact);
			if(row.get("Title").toLowerCase().contains("recruiter") || row.get("Title").toLowerCase().contains("sourcer")
					|| row.get("Title").toLowerCase().contains("talent"))
				recruiters.add(currContact);
		}
		return contactsList;
	}
	
    public static boolean containsID(String id) {
        initialize();
        return allContacts.containsKey(id);
    }

    public static String getFirstName(String id) {
        initialize();
        return allContacts.get(id).getFirstName();
    }

    public static String getLastName(String id) {
        initialize();
        return allContacts.get(id).getLastName();
    }

    public static String getJobTitle(String id) {
        initialize();
        return allContacts.get(id).getJobTitle();
    }

    public static Contact getContactById(String id) {
        initialize();
        return allContacts.get(id);
    }

    public static String getCompany(String id) {
        initialize();
        return allContacts.get(id).getCompany();
    }

    public static String getEmail(String id) {
        initialize();
        return allContacts.get(id).getEmailId();
    }

    public static ArrayList<Contact> getRecruiters(){
    	 initialize();
        return recruiters;
    }
    public static int size() {
        return allContacts.size();
    }
	
	//To test whether the hashmap gets loaded with correct data or not
	private static void testContactsMap(){
		initialize("Contacts.csv");
		allContacts.forEach((k,v) -> System.out.println(k+"\t"+v));
	}
	
	//Client program to run/test ContactsDataBase class
	public static void main(String[] args) {
		testContactsMap();
	}
}
