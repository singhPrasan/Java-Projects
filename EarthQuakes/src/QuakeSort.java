import java.util.*;

public class QuakeSort {
	
	//REturns the earthquake with the smallest magnitude
    public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> quakes) {
        QuakeEntry min = quakes.get(0);
        for(QuakeEntry q: quakes) {
            if (q.getMagnitude() < min.getMagnitude()) {
                min = q;
            }
        }
        return min;
    }
    

    //Returns a new list of earthquakes in a sorted order by magnitude
    public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in) {
    	ArrayList<QuakeEntry> out = new ArrayList<>();
    	while(!in.isEmpty()){
    		QuakeEntry minElement = getSmallestMagnitude(in);
    		in.remove(minElement);
    		out.add(minElement);
    	}
    	return out;
    }

    
    
    //Returns index of the earthquake with smallest magnitude
    public int getIndexOfSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from){
    	int minIndex = from;
    	for(int i = from+1; i<quakes.size(); i++){
    		if(quakes.get(i).getMagnitude() < quakes.get(minIndex).getMagnitude()){
    			minIndex = i;
    		}
    	}
    	return minIndex;
    }
    
    
    //Sorts the earthquakes in incresing order of magnitude without using extra space
    public void sortByMagnitudeInPlace(ArrayList<QuakeEntry> quakes){
    	for(int i = 0; i<quakes.size(); i++){
    		int minIndex = getIndexOfSmallestMagnitude(quakes, i);
    		QuakeEntry qi = quakes.get(i);
    		QuakeEntry qMin = quakes.get(minIndex);
    		quakes.set(i, qMin);
    		quakes.set(minIndex, qi);
    	}
    }
    
    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
     //   list = sortByMagnitude(list);
        sortByMagnitudeInPlace(list);
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

    
    //Test client
    public static void main(String[] args) {
		QuakeSort qs = new QuakeSort();
		qs.testSort();
	}
}
