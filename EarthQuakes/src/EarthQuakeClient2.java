import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    
    //Returns a list of all earthquakes that match the given criteria/filter
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 

    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");

//        Filter f = new MinMagFilter(4.0); 
//        ArrayList<QuakeEntry> m7  = filter(list, f); 
//        for (QuakeEntry qe: m7) { 
//            System.out.println(qe);
//        } 
        
        
         Filter magFilter = new MagnitudeFilter(3.5, 4.5, "Magnitude");
         Filter depFilter = new DepthFilter(-55000.0, -20000.0, "Depth");
         ArrayList<QuakeEntry> quakeList =  new ArrayList<>();
         for(QuakeEntry currQuake : list){
        	 if(magFilter.satisfies(currQuake) && depFilter.satisfies(currQuake))
        		 quakeList.add(currQuake);
         }
         System.out.println(quakeList.size());
//         for(QuakeEntry currQuake : quakeList)
//        	 System.out.println(currQuake);
//      
        
//        Location Colorado = new Location(39.7392, -104.9903);
//        Filter distFil = new DistanceFilter(Colorado, 1000000, "Distance");
//        Filter phraseFil = new PhraseFilter("end", "a", "Phrase");
//        ArrayList<QuakeEntry> quakeList =  new ArrayList<>();
//        for(QuakeEntry currQuake : list){
//        	if(distFil.satisfies(currQuake) && phraseFil.satisfies(currQuake))
//        		quakeList.add(currQuake);
//        }
//        System.out.println(quakeList.size());
//        for(QuakeEntry currQuake : quakeList)
//        	System.out.println(currQuake);
    }

    
    //Tests multiple filters 
    public void testMatchAllFilter(){
    	EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0, "Magnitude"));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0, "Depth"));
        maf.addFilter(new PhraseFilter("any", "o", "Phrase"));
        ArrayList<QuakeEntry> quakeList = filter(list, maf);
//        for(QuakeEntry currQuake : quakeList)
//        	System.out.println(currQuake);
        System.out.println(quakeList.size());
    	System.out.println("Filters used are :"+maf.getName());
    }
    
  //Tests multiple filters 
    public void testMatchAllFilter2(){
    	EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0, "Magnitude"));
        Location Denmark = new Location(55.7308, 9.1153);
        maf.addFilter(new DistanceFilter(Denmark, 3000000*3000000, "Distance"));
        maf.addFilter(new PhraseFilter("any", "e", "Phrase"));
        ArrayList<QuakeEntry> quakeList = filter(list, maf);
//        for(QuakeEntry currQuake : quakeList)
//        	System.out.println(currQuake);
        System.out.println(quakeList.size());
    	
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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

    
    //Test Client
    public static void main(String[] args) {
		EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
	//	eqc2.quakesWithFilter();
	//	eqc2.testMatchAllFilter();
		eqc2.testMatchAllFilter2();
	}
}
