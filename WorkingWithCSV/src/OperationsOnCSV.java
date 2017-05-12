
/*
 * Write a program that performs operations on data present in a CSV file
 */
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;

public class OperationsOnCSV {
	
	//List the items exported and the revenue earned by a given country
	public String countryInfo(CSVParser parser, String country){
		for(CSVRecord record : parser){
			String ctry = record.get("Country");
			if(ctry.equals(country)){
				return country+": "+ record.get("Exports")+": "+record.get("Value (dollars)");
			}
		}
		return "NOT FOUND";
	}
	
	//List all the countries which export both the mentioned items
	public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
		System.out.println("Countries which export both "+exportItem1+" and "+exportItem2+" are :");
		for(CSVRecord record : parser){
			String exportedItems = record.get("Exports");
			if(exportedItems.contains(exportItem1) && exportedItems.contains(exportItem2))
				System.out.print(record.get("Country")+" ");
		}
		System.out.println();
	}
	
	//To count the number of countries which export a particular item
	public int numberOfExporters(CSVParser parser, String exportItem){
		int count  = 0;
		for(CSVRecord record : parser){
			String item = record.get("Exports");
			if(item.contains(exportItem))
				count++;
		}
		return count;
	}
	
	//To list all countries whose Export values is more than $999,999,999
	public void bigExporters(CSVParser parser, String expValue){
		for(CSVRecord record : parser){
			String exportValue = record.get("Value (dollars)");
			if(exportValue.length() > expValue.length()){
				System.out.print(record.get("Country")+" : ");
				System.out.println(exportValue);
			}
		}
	}
	
	
	public static void main(String[] args) {
		OperationsOnCSV oocsv = new OperationsOnCSV();
		FileResource fr = new FileResource();
		CSVParser csvp = fr.getCSVParser();
		String countryInfo = oocsv.countryInfo(csvp, "Nauru");
		System.out.println(countryInfo);
		oocsv.listExportersTwoProducts(fr.getCSVParser(), "cotton", "flowers");
		System.out.println("Number of countries exporting this good :"+oocsv.numberOfExporters(fr.getCSVParser(), "cocoa"));
		oocsv.bigExporters(fr.getCSVParser(), "$999,999,999,999");
	}
}
