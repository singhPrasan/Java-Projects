import edu.duke.*;
import java.util.*;

public class GladLibMap {
	
	private HashMap<String, ArrayList<String>> map;
	private ArrayList<String> seenWords;
	private ArrayList<String> categoriesConsidered;
	
	private Random myRandom;
	
	private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
	private static String dataSourceDirectory = "data";
	
	public GladLibMap(){
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	public GladLibMap(String source){
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	private void initializeFromSource(String source) {
		seenWords = new ArrayList<>();
		map = new HashMap<>();
		categoriesConsidered = new ArrayList<>();
		String[] fileName = {"adjective.txt","noun.txt", "color.txt"
				, "country.txt", "name.txt", "animal.txt", "timeframe.txt", 
				"verb.txt","fruit.txt" };
		for(String f : fileName){
			map.put(f.substring(0, f.indexOf('.')), readIt(source+"/"+f));
		}			
 	}
	
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	private String getSubstitute(String label) {
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if(!categoriesConsidered.contains(label))
			categoriesConsidered.add(label);
		return randomFrom(map.get(label));
	}
	
	private String processWord(String w){
		
		int first = w.indexOf("<");
		int last = w.indexOf(">",first);
		if (first == -1 || last == -1){
			return w;
		}
		String prefix = w.substring(0,first);
		String suffix = w.substring(last+1);
		String sub = getSubstitute(w.substring(first+1,last));
		while(seenWords.contains(sub))	
			sub = getSubstitute(w.substring(first+1,last));
		seenWords.add(sub);
		 
		return prefix+sub+suffix;
	}
	
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
				System.out.println();
				charsWritten = 0;
			}
			System.out.print(w+" ");
			charsWritten += w.length() + 1;
		}
	}
	
	private String fromTemplate(String source){
		String story = "";
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String word : resource.words()){
				story = story + processWord(word) + " ";
			}
		}
		return story;
	}
	
	private ArrayList<String> readIt(String source){
		ArrayList<String> list = new ArrayList<String>();
		if (source.startsWith("http")) {
			URLResource resource = new URLResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		else {
			FileResource resource = new FileResource(source);
			for(String line : resource.lines()){
				list.add(line);
			}
		}
		return list;
	}
	
	
	//Returns total number of words that are there in all the category files
	public int totalWordsInMap(){
		int totalWords = 0;
		for(String category : map.keySet()){
			ArrayList<String> words = map.get(category);
			totalWords+= words.size();
		}
		return totalWords;
	}
	
	//Returns the total number of words that were considered for a story
	public int totalWordsConsidered(){
		int totalWordsConsidered = 0;
		for(String categories : categoriesConsidered){
			totalWordsConsidered += map.get(categories).size();
		}
		return totalWordsConsidered;
	}
	
	
	//Test client
	public static void main(String args[]){
		GladLibMap glm = new GladLibMap();
		glm.seenWords.clear();
	    System.out.println("\n");
		String story = glm.fromTemplate("data/madtemplate2.txt");
		glm.printOut(story, 60);
		System.out.println("\nNumber of words replaced : "+glm.seenWords.size());
		System.out.println("Total number of words :"+glm.totalWordsInMap());
		System.out.println("Total number of words considered:"+glm.totalWordsConsidered());
	}
	


}
