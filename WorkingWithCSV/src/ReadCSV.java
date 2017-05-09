/*
 * Given a CSV(Comma Separated) excel sheet, output its contents
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class ReadCSV {
	public void readFile(){
		//Since nothing is mentioned in the arguments, a window pop up will occur and ask you to select the file
		FileResource fr = new FileResource();
		//CSVParser is a class from open source apache
		CSVParser csvParser = fr.getCSVParser();
		//Iterate over all the records in the file using CSVRecord class
		for(CSVRecord record : csvParser){
			//record.get("Column name")
			System.out.print(record.get("Name")+" ");
			System.out.print(record.get("Favorite Color")+" ");
			System.out.println(record.get("Favorite Drink"));
		}
	}
	public static void main(String[] args) {
		ReadCSV rcsv = new ReadCSV();
		rcsv.readFile();
	}
}