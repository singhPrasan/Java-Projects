import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class WordsInFiles {
	
	private HashMap<String, ArrayList<String>> map;
	
	public WordsInFiles(){
		map = new HashMap<>();
	}
	
	
	//Add words from a file into a hashmap and maps it to the file names in which it occurs
	private void addWordsFromFile(File f){
		FileResource fr = new FileResource(f);
		for(String word : fr.words()){
			if(!map.containsKey(word)){
				ArrayList<String> fNamesList = new ArrayList<>();
				fNamesList.add(f.getName());
				map.put(word, fNamesList);
			}else{
				ArrayList<String> fNamesList = map.get(word);
				if(!fNamesList.contains(f.getName()))
					fNamesList.add(f.getName());
				map.put(word, fNamesList);
			}
		}
	}
	
	
	//Calls addWordsFromFile function for each of the selected files
	public void buildWordFileMap(){
		map.clear();
		DirectoryResource dr = new DirectoryResource();
		for(File f : dr.selectedFiles()){
			addWordsFromFile(f);
		}
	}
	
	//Returns a list of all words that occur in exactly given number of files
	public ArrayList<String> wordsInNumFiles(int number){
		ArrayList<String> wordList = new ArrayList<>();
		for(String word : map.keySet()){
			int fileCount = map.get(word).size();
			if( fileCount == number)
				wordList.add(word);
		}
		return wordList;
	}
	
	
	//Prints names of all files in which the given word occurs
	public void printFilesIn(String word){
		System.out.println("The word "+word+" occurs in the below files:");
		ArrayList<String> fileList = map.get(word);
		for(String fileName : fileList)
			System.out.println(fileName);
	}
	
	
	//Returns the maximum number of files any word occurs in
	public int maxNumber(){
		int maxOccurenceOfWord = 0;
		for(String word : map.keySet()){
			int fileCount = map.get(word).size();
			if( fileCount > maxOccurenceOfWord){
				maxOccurenceOfWord = fileCount;
			}
		}
		return maxOccurenceOfWord;
	}
				
		
	//Prints the entire mapping of words with their corresponding file names in which they occur
	public void printEntireMapping(){
		System.out.println("Complete Mapping : \n");
		for(String word: map.keySet()){
			System.out.print(word+"\t");
			ArrayList<String> fileList = map.get(word);
			for(String fileName : fileList)
				System.out.print(fileName+" ");
			System.out.println();
		}
	}
	
	
	//Test Client
 	public static void main(String[] args) {
		WordsInFiles wif = new WordsInFiles();
		wif.buildWordFileMap();
		System.out.println("Maximum number of files any word is in :"+wif.maxNumber());
		ArrayList<String> wordsInFile = wif.wordsInNumFiles(7);
		System.out.println("Total number of words occuring in exactly 7 files :"+wordsInFile.size());
		System.out.println("Words appearing in exactly 4 files :");
		for(String word : wordsInFile)
			System.out.println(word);
		System.out.println();
		wif.printFilesIn("tree");
		System.out.println();
//		wif.printEntireMapping();
	}
}
