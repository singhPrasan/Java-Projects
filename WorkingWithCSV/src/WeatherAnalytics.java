/*
 * To perform various weather analytics over a large data set (year). Can take input greater than 365 days also
 */

import edu.duke.*;
import java.io.*;

import javax.annotation.processing.Filer;

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
	public String fileWithColdestTemperature(){
		String fileColdest = "";
		CSVRecord coldestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			String currFileName = f.getName();
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			coldestSoFar = getLowestOfTwo(coldestSoFar, currentRow);
			if(coldestSoFar == currentRow)
				fileColdest = currFileName;
		}
		return fileColdest;
	}
	
	
	//Test Client
	public static void main(String[] args) {
		WeatherAnalytics wa = new WeatherAnalytics();
		String coldestFile = wa.fileWithColdestTemperature();
		System.out.println("Coldest day was in "+coldestFile);
		FileResource fr = new FileResource(new File("/coldestFile"));
		System.out.println("Coldest temperature on that day was "+wa.coldestHourInFile(fr.getCSVParser()));
	}
}
