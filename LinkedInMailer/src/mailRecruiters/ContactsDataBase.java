/**
 *  To access information about all contacts about your Linkedin profile
 *  Simulates a database. All fields are static, no need to create objects
 *  Can be accessed directly using class name itself  
 *  
 *  @author 	Prasandeep Singh
 *  @created	07/26/2017
 */

package mailRecruiters;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class ContactsDataBase {
	private static HashMap<Integer, Contact> allContacts;

    public static void initialize(String contactsFile) {
        if (allContacts == null) {
        	allContacts = new HashMap<Integer,Contact>();
            loadAllContacts("data/" + contactsFile);
        }
    }

    private static void initialize() {
        if (allContacts == null) {
        	allContacts = new HashMap<Integer,Contact>();
            loadAllContacts("data/ratedmoviesfull.csv");
        }
    }	

	
    private static void loadAllContacts(String filename) {
        ArrayList<Contact> list = loadContacts(filename);
        for (Contact c : list) {
            allContacts.put(c.getUniqId(), c);
        }
    }	
    
    //MOve this method to The runner class later
	//Loads all the movies from the given CSV file into an arraylist of movie objects
	protected static ArrayList<Contact> loadContacts(String fileName){
		ArrayList<Contact> contactsList = new ArrayList<>();
		FileResource fr = new FileResource(fileName);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord row : parser){
				Contact currContact = new Contact(row.get("FirstName"), row.get("LastName"),  row.get("Companies"),
						row.get("Title"), row.get("EmailAddress"));
				contactsList.add(currContact);
		}
		return contactsList;
	}
}
