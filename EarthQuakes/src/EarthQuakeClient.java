import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    
    //Returns a list of all earthquakes whose magnitude is greater than the given magnitude
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry curr : quakeData){
        	if( curr.getMagnitude() > magMin)
        		answer.add(curr);
        }
        return answer;
    }

    
    //Returns a list of all earthquakes whose distance is less than the given distance
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry currQuake : quakeData){
        	Location currLoc = currQuake.getLocation();
        	if( currLoc.distanceTo(from) < distMax ){
        		answer.add(currQuake);
        	}
        }
        return answer;
    }

    
    //Returns a list of all earthquakes that happened within a specified range of depth
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
    	ArrayList<QuakeEntry> answer = new ArrayList<>();
    	for(QuakeEntry currQuake : quakeData){
    		if( currQuake.getDepth() > minDepth && currQuake.getDepth()<maxDepth )
    			answer.add(currQuake);
    	}
    	return answer;
    }
    
    
    //Returns a list of all earthquakes that have the given phrase at specified locations in its title
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
    	ArrayList<QuakeEntry> answer = new ArrayList<>();
    	for(QuakeEntry currQuake : quakeData){
    		String title = currQuake.getInfo();
    		if( where.equals("start") && title.startsWith(phrase) )
    			answer.add(currQuake);
    		else if ( where.equals("end") && title.endsWith(phrase) )
    			answer.add(currQuake);
    		else if ( where.equals("any") && title.contains(phrase))
    			answer.add(currQuake);
    	}
    	return answer;
    }
    
    
 
    //Reads data of earthquakes and prints earthquakes which have magnitude greater than a particular value
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        ArrayList<QuakeEntry> qe = filterByMagnitude(list, 5.0);
        for(QuakeEntry quake : qe){
        	System.out.println(quake);
        }
        System.out.println("Found "+qe.size()+" that match the criteria");

    }

    
    
    //Prints details about the earthquakes that are close to the location provided
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
     //   String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
      //  Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
         Location city =  new Location(38.17, -118.82);
        
        ArrayList<QuakeEntry> quakeList = filterByDistanceFrom(list, 1000*1000, city);
        for(QuakeEntry currQuake : quakeList){
        	System.out.print(currQuake.getLocation().distanceTo(city)+" ");
        	System.out.print(currQuake.getInfo());
        	System.out.println();
        }
        System.out.println("Found "+quakeList.size()+" that match the criteria");
    }
    
    
    //Prints all earthwuakes that occurred within a specified depth
    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
     //   String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> quakeList = filterByDepth(list, -4000.0, -2000.0);
        for(QuakeEntry currQuake : quakeList)
        	System.out.println(currQuake);
        System.out.println("Found "+quakeList.size()+" that match the criteria");
    }
    
    
    //Prints a list of all earthquakes that contain a given phrase at a specified position in their title
    public void quakesByPhrase(){
    	EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
      //  String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
         
        String where = "any";
        String phrase = "Can";
        ArrayList<QuakeEntry> quakeList = filterByPhrase(list, where, phrase);
        for(QuakeEntry currQuake : quakeList)
        	System.out.println(currQuake);
        System.out.println("Found "+quakeList.size()+" that match the criteria");
    }
    
    
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    
    //Test client
    public static void main(String[] args) {
		EarthQuakeClient eqc = new EarthQuakeClient();
	//	eqc.bigQuakes();
	//	eqc.closeToMe();
	//	eqc.quakesOfDepth();
		eqc.quakesByPhrase();
	}
}
