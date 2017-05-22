import java.util.HashMap;

import edu.duke.FileResource;

public class WordFrequenciesMap {
	
	public void countWords(){
		FileResource fr = new FileResource();
		HashMap<String, Integer> map = new HashMap<>();
		int total = 0;
		for(String word : fr.words()){
			word = word.toLowerCase();
			if(map.containsKey(word))
				map.put(word, map.get(word)+1);
			else
				map.put(word, 1);
		}
		for(String word : map.keySet()){
			int occurrences = map.get(word);
			if(occurrences > 200)
				System.out.println(occurrences+"\t"+word);
		}
	}
	public static void main(String[] args) {
		WordFrequenciesMap wfm = new WordFrequenciesMap();
		wfm.countWords();
	}
}
