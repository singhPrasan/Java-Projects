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
			System.out.print(rec.get("TemperatureF")+" ");
		}
		System.out.println();
	}
	
	
	//Returns record with lowest humidity on a particular day
	public CSVRecord lowestHumidityInFile(CSVParser parser){
		CSVRecord lowestSoFar = null;
		for(CSVRecord currentRow : parser){
			lowestSoFar = getLowestOfTwoHum(lowestSoFar, currentRow);
		}
		return lowestSoFar;
	}
	//Returns Record with lowest humidity amongst all the selected days
	public CSVRecord lowestHumidityInManyFiles(){
		DirectoryResource dr = new DirectoryResource();
		CSVRecord lowestHumidSoFar = null;
		for(File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			lowestHumidSoFar = getLowestOfTwoHum(lowestHumidSoFar, currentRow);
		}
		return lowestHumidSoFar;
	}
	//Returns lowest of the two humidities
	public CSVRecord getLowestOfTwoHum(CSVRecord lowestSoFar, CSVRecord currentRow){
		if(lowestSoFar == null){
			lowestSoFar = currentRow;
		}
		double currentHum = (currentRow.get("Humidity").equals("N/A")? Integer.MAX_VALUE : Double.parseDouble(currentRow.get("Humidity")));
		double lowestHum = (lowestSoFar.get("Humidity").equals("N/A")? Integer.MAX_VALUE : Double.parseDouble(lowestSoFar.get("Humidity")));
		if(currentHum < lowestHum)
			lowestSoFar = currentRow;
		return lowestSoFar;
	}
	
	
	//Returns the average temperature of a particular day
	public double averageTemperatureInFile(CSVParser parser){
		double sum = 0;
		int count = 0;
		for(CSVRecord current : parser){
			sum += Double.parseDouble(current.get("TemperatureF"));
			count++;
		}
		return sum/count;
	}
	
	
	//Returns the average temperature only when humidity is higher than a given value on the selected day
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
		double sum = 0;
		int count = 0;
		for(CSVRecord current : parser){
			int currentHum = (current.get("Humidity").equals("N/A")? Integer.MAX_VALUE : Integer.parseInt(current.get("Humidity")));	
			if(currentHum >= value){
				sum += Double.parseDouble(current.get("TemperatureF"));
				count++;
			}
		}
		//If no temperature is found with humidity greater than the given value, return -1
		if(count == 0)	return -1;
		return sum/count;
	}
	
	
	
	//Test Client
	public static void main(String[] args){
		WeatherAnalytics wa = new WeatherAnalytics();
		
		//Test for coldest days
		wa.displayColdestAnalytics();
		
	
		//Test for lowest humidity
		CSVRecord lowestHumidityRec = wa.lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was "+lowestHumidityRec.get("Humidity")+" at "+lowestHumidityRec.get("DateUTC"));
	
		//Test for average temperature on a day with a given humidity
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		System.out.println("Average temperature in file :"+wa.averageTemperatureInFile(parser));
		
		int humidity = 80;
		double avgTemp = wa.averageTemperatureWithHighHumidityInFile(parser, humidity);
		if( avgTemp == -1)
			System.out.println("No temperatures with that humidity");
		else
			System.out.println("Average temperature in File with Humidity greater than : "+humidity+" was "+avgTemp);
	
	}
}
