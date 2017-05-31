
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
   
    
    //Returns index of the earthquake with smallest magnitude
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    
    //Sorts all earthquakes in increasing order of magnitudes using in-place Selection Sort
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    
    
    //Returns index of the earthquake occurring at the maximum depth
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from){
    	int maxIndex = from;
    	for(int i = from+1; i<quakeData.size(); i++){
    		if( quakeData.get(i).getDepth() > quakeData.get(maxIndex).getDepth()){
    			maxIndex = i;
    		}
    	}
    	return maxIndex;
    }

    
    //Sorts all earthquakes in decreasing order of the depths using in-place Selection Sort
    public void sortByLargestDepth(ArrayList<QuakeEntry> in){
    	for(int i = 0; i<50; i++){
    		int maxIndex = getLargestDepth(in, i);
    		QuakeEntry qMax = in.get(maxIndex);
    		QuakeEntry qI = in.get(i);
    		in.set(i, qMax);
    		in.set(maxIndex, qI);
    	}
    }
    
    
    //Carries out 1 pass of bubble sort on the list of earthquakes to sort them by magnitude
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted){
    	for(int i  = 1; i < quakeData.size()-numSorted; i++){
    		QuakeEntry curr = quakeData.get(i);
    		QuakeEntry prev = quakeData.get(i-1);
    		if( curr.getMagnitude() < prev.getMagnitude()){
    			quakeData.set(i, prev);
    			quakeData.set(i-1, curr);
    		}
    	}
    }
    
    //Sorts all earthquakes in increasing order of their magnitudes using in-place Bubble Sort
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in){
    	for(int i = 0; i<in.size()-1; i++){
    	//	System.out.println("Printing quakes befor pass "+i+" :\n "+in);
    		onePassBubbleSort(in, i);
    	//	System.out.println("Printing quakes befor pass "+i+" :\n "+in);
    	}
    }
    
    
    //Returns true if the list of earthquakes is already sorted in increasing order of magnitudes
    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes){
    	for(int i = 1; i<quakes.size(); i++){
    		QuakeEntry curr = quakes.get(i);
    		QuakeEntry prev = quakes.get(i-1);
    		if( curr.getMagnitude() < prev.getMagnitude())
    			return false;
    	}
    	return true;
    }
    
    //Sorts all earthquakes in increasing order of their magnitudes using in-place Bubble Sort
    //Also, stops early if it sees that the list is already sorted
    public void sortWithBubbleSortWithCheck(ArrayList<QuakeEntry> in){
    	int i =0;
    	for(; i<in.size()-1; i++){
    		onePassBubbleSort(in, i);
    		if(checkInSortedOrder(in))
    			break;
    	}
    	System.out.println("Number of passes required to sort the list :"+(i+1));
    }
    
    
    //Sorts all earthquakes in increasing order of their magnitudes using in-place Selection Sort
    //Also, stops early if it sees that the list is already sorted
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in){
    	  int i=0;
    	  for (; i< in.size(); i++) {
              int minIdx = getSmallestMagnitude(in,i);
              QuakeEntry qi = in.get(i);
              QuakeEntry qmin = in.get(minIdx);
              in.set(i,qmin);
              in.set(minIdx,qi);
              if(checkInSortedOrder(in))
            	  break;
          }
    	  System.out.println("Number of passes required to sort the list :"+(i+1));
    }
    
    
    //Tests all the functions
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);  
       
        System.out.println("read data for "+list.size()+" quakes");    
      //  sortByMagnitude(list);
      //  sortByLargestDepth(list);
      //  sortByMagnitudeWithBubbleSort(list);
        sortWithBubbleSortWithCheck(list);
     //   sortByMagnitudeWithCheck(list);
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        } 
        
    }
    
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    
    public void dumpCSV(ArrayList<QuakeEntry> list) {
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}
    
    
    //Test client
    public static void main(String[] args) {
		QuakeSortInPlace qsip = new QuakeSortInPlace();
		qsip.testSort();
	}
}
