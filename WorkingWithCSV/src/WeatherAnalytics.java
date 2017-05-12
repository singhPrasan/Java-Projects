/*
 * To perform various weather analytics over a large data set (year). Can take input greater than 365 days also
 */

import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

public class WeatherAnalytics {
	
	//Returns record with the coldest hour in a day
	public CSVRecord coldestHourInFile(CSVParser parser){
		CSVRecord coldestSoFar = null;
		for(CSVRecord currentRow : parser){
			coldestSoFar = getLowestOfTwo(coldestSoFar, currentRow);
		}
		return coldestSoFar;
	}
	
	//Returns record with lower of the two temperatures
	private CSVRecord getLowestOfTwo(CSVRecord coldestSoFar, CSVRecord currentRow){
		if(coldestSoFar == null){
			coldestSoFar = currentRow;
		}else{
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			if(currentTemp == -9999)	currentTemp = Integer.MAX_VALUE;
			double lowestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
			if( currentTemp < lowestTemp )
				coldestSoFar = currentRow;
		}
		return coldestSoFar;
	}
	
	
	//Returns name of the file with the coldest temperature
	public String fileWithColdestTemperature() {
		String fileColdest = "";
		CSVRecord coldestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			coldestSoFar = getLowestOfTwo(coldestSoFar, currentRow);
			if(coldestSoFar == currentRow){
				fileColdest = f.getAbsolutePath();
			}
		}
		return fileColdest;
	}
	
	
	//Displays name of the excel file with the coldest day
	//Displays coldest temperature of the coldest day
	//Displays all the temperatures on that particular day
	public void displayColdestAnalytics(){
		//To display the name of the file
		String coldestFile = fileWithColdestTemperature();
		System.out.println("Coldest day was in "+coldestFile.substring(coldestFile.lastIndexOf("/")+1,coldestFile.length()));
	
		//To display coldest temperature on that day
		FileResource fr = new FileResource(coldestFile);
		CSVParser cParser = fr.getCSVParser();
		CSVRecord record = coldestHourInFile(cParser);
		System.out.println("Coldest temperature on that day was "+record.get("TemperatureF"));
		
		//To display all temperatures on that day
		System.out.println("All the temperatures on the coldest day were :");
		CSVParser cPar = fr.getCSVParser();
		for(CSVRecord rec : cPar){
			System.out.println(rec.get("TemperatureF"));
		}
	}
	
	
	//Test Client
	public static void main(String[] args){
		WeatherAnalytics wa = new WeatherAnalytics();
		wa.displayColdestAnalytics();
	}
}
