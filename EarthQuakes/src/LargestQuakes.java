/*
 * Determines the N-biggest earthquakes with largest magnitudes
 */

import java.util.*;

public class LargestQuakes {
	
	
	//Prints a list of top howMany earthquake with maximum magnitude
	public void findLargestQuakes(){
		 EarthQuakeParser parser = new EarthQuakeParser();
	     String source = "data/nov20quakedata.atom";
//	     String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	     ArrayList<QuakeEntry> list  = parser.read(source);
//	     for(QuakeEntry currQuake : list)
//	    	 System.out.println(currQuake);
	     System.out.println("read data for "+list.size());
//	     System.out.println(indexOfLargest(list));
	     
	     ArrayList<QuakeEntry> quakeList = getLargest(list, 50);
	     for(QuakeEntry currQuake : quakeList)
	    	 System.out.println(currQuake);
	}
	
	
	//Returns index position of earthquake with maximum magnitude
	public int indexOfLargest(ArrayList<QuakeEntry> data){
		int maxIndex = 0;
		for(int i = 0; i<data.size(); i++){
			double mag = data.get(i).getMagnitude();
			double maxMag = data.get(maxIndex).getMagnitude();
			if( mag > maxMag)
				maxIndex = i;
		}
		return maxIndex;
	}
	
	
	//Returns a list of top howMany earthquakes with maximum magnitude
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
		ArrayList<QuakeEntry> copy = new ArrayList<>(quakeData);
		ArrayList<QuakeEntry> answer = new ArrayList<>();
		for(int i = 0; i<howMany; i++){
			int maxIndex = indexOfLargest(copy);
			answer.add(copy.get(maxIndex));
			copy.remove(maxIndex);
		}
		return answer;
		
	}
	
	
	//Test Client
	public static void main(String[] args) {
		LargestQuakes lq = new LargestQuakes();
		lq.findLargestQuakes();
	}
}
