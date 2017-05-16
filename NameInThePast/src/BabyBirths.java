/*
 * Analytics on baby birth names
 */
import java.io.File;

import org.apache.commons.csv.*;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class BabyBirths {
	
	//Prints the total number of births, number of males and females in a given excel sheet
	public void totalBirths(FileResource fr){
		int totalBirths = 0;
		int totalMales = 0;
		int totalFemales = 0;
		int uniqMales = 0;
		int uniqFemales = 0;
		for(CSVRecord record : fr.getCSVParser(false)){
			totalBirths += Integer.parseInt(record.get(2));
			String gender = record.get(1);
			if( gender.equals("M")){
				uniqMales++;
				totalMales += Integer.parseInt(record.get(2));
			}
			else{
				uniqFemales++;
				totalFemales += Integer.parseInt(record.get(2));
			}
		}
		System.out.println("Total number of births : "+totalBirths);
		System.out.println("Total number of male births : "+totalMales);
		System.out.println("Total number of female births : "+totalFemales);
		System.out.println("Total number of Unique female names : "+uniqFemales);
		System.out.println("Total number of Unique male name: "+uniqMales);
	}
	
	
	//Returns the rank of a particular name in the selected year
	public int getRank(String year, String name, String gender){
		int rank = 1;
		boolean nameFound = false;
		String fileName = "data/us_babynames/us_babynames_by_year/yob"+year+".csv";
		FileResource fr = new FileResource(fileName);
		for(CSVRecord record : fr.getCSVParser(false)){
			if(gender.equals("F")){
				if(record.get(1).equals("F") && name.equals(record.get(0))){
					nameFound = true;
					break;
				}else
					rank++;
			}else{
				if(record.get(1).equals("F"))
					continue;
				if(name.equals(record.get(0))){
					nameFound =true;
					break;
				}else
					rank++;
			}
		}
		if(nameFound)
			return rank;
		else
			return Integer.MAX_VALUE;
	}
	
	//Returns the name that corresponds to a given rank
	public String getName(int year, int rank, String gender){
		int rankSeen = 1;
		String fileName = "data/us_babynames/us_babynames_by_year/yob"+year+".csv";
		FileResource fr = new FileResource(fileName);
		for(CSVRecord record : fr.getCSVParser(false)){
			if(gender.equals("F")){
				if(rank == rankSeen)
					return record.get(0);
				else
					rankSeen++;
				
			}else{
				if(record.get(1).equals("F"))
					continue;
				if(rank == rankSeen)
					return record.get(0);
				else
					rankSeen++;
				
			}
		}
		return "NO NAME";
	}
	
	
	//Prints What your name would have been in a different year
	public void whatIsNameInYear(String name, int year, int newYear, String gender){
		//find rank acc to name, year and gender
		int currRank = getRank(String.valueOf(year), name, gender);
		//find name according to the currRank in the new year
		String newName = getName(newYear, currRank, gender);
		System.out.println(name+" born in "+year+" would be "+newName+" if she was born in "+newYear);
	}
	
	
	//Returns the year in which rank of a particular name was the highest amongst the selected years
	public int yearOfHighestRank(String name, String gender){
		int highestRankYear = 0;
		int highestRankSoFar = Integer.MAX_VALUE;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			String fName = f.getName();
			String currFileYear = fName.substring(3,7);
			int currRank = 	getRank(currFileYear, name, gender);
			if(currRank < highestRankSoFar){
				highestRankSoFar = currRank;
				highestRankYear = Integer.parseInt(currFileYear);
			}
		}
		return highestRankYear;
	}
	
	
	//Returns the average rank of a particular name from the selected years
	public double getAverageRank(String name, String gender){
		double sum = 0;
		double count = 0;
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			String fName = f.getName();
			String currFileYear = fName.substring(3,7);
			int currRank = 	getRank(currFileYear, name, gender);
			if( currRank == -1)
				continue;
			sum += currRank;
			count++;
		}
		if(count == 0)
			return -1;
		return sum/count;
	}
	
	
	//Returns the total number of births of the same gender greater than the requested birth name
	public int getTotalBirthsRankedHigher(String name, String gender){
		int totalBirths = 0;
		FileResource fr = new FileResource();
		for(CSVRecord record : fr.getCSVParser(false)){
			String currName = record.get(0);
			if(currName.equals(name) && gender.equals(record.get(1)))
				break;
			if(gender.equals(record.get(1)))
				totalBirths += Integer.parseInt(record.get(2));
		}
		return totalBirths;
	}
	
	
	
	//Test client
	public static void main(String[] args) {
		BabyBirths bb = new BabyBirths();
		FileResource fr = new FileResource();
		bb.totalBirths(fr);
		System.out.println(bb.getRank("1971", "Frank", "M"));
		System.out.println(bb.getName(1982, 450, "M"));
		bb.whatIsNameInYear("Owen", 1974, 2014, "M");
		System.out.println(bb.yearOfHighestRank("Genevieve", "F"));
		System.out.println(bb.getAverageRank("Robert", "M"));
		System.out.println(bb.getTotalBirthsRankedHigher("Emily", "F"));
	}
}
