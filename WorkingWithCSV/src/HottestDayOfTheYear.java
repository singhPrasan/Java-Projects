/*
 * Given a hourly temperature in a csv file for a day, find the maximum temperature and the day at which it occurred for a year
 */

import java.io.File;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class HottestDayOfTheYear {
	
	//Returns the hour row for the maximum temperature for 1 day
	public CSVRecord findHottestOneDay(CSVParser parser){
		CSVRecord hottestSoFar = null;
		for(CSVRecord currentRow : parser){
			hottestSoFar = getHottestOfTwo(hottestSoFar, currentRow);
		}
		return hottestSoFar;
	}
	
	//Returns a record with the maximum temperature for multiple selected days
	public CSVRecord findHottestMultipleDays(){
		DirectoryResource dr = new DirectoryResource();
		CSVRecord hottestSoFar = null;
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = findHottestOneDay(fr.getCSVParser());
			hottestSoFar = getHottestOfTwo(hottestSoFar, currentRow);
		}
		return hottestSoFar;
	}
	
	//Returns the record with the one with larger temperature value
	private CSVRecord getHottestOfTwo(CSVRecord hottestSoFar, CSVRecord currentRow){
		if(hottestSoFar == null){
			hottestSoFar = currentRow;
		}else{
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double hottestTemp = Double.parseDouble(hottestSoFar.get("TemperatureF"));
			if( currentTemp > hottestTemp)
				hottestSoFar = currentRow;
		}
		return hottestSoFar;
	}
	
	public static void main(String[] args) {
		HottestDayOfTheYear hh = new HottestDayOfTheYear();
		CSVRecord hottestRecord = hh.findHottestMultipleDays();
		System.out.println("The hottest temperature was "+hottestRecord.get("TemperatureF")+"F at : "+hottestRecord.get("DateUTC"));
	}
}
