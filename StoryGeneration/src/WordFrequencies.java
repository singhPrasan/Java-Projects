/*
 * Counts the total number of unique words present in a text file and also the number of times each word occurs
 */

import java.util.ArrayList;

import edu.duke.FileResource;

public class WordFrequencies {
	private ArrayList<String> myWords;
	private ArrayList<Integer> myFreqs;
	
	public WordFrequencies(){
		myWords = new ArrayList<String>();
		myFreqs = new ArrayList<Integer>();
	}
	
	
	//Counts the total number of unique words along with their frequency of occurrence
	public void findUnique(){
		myWords.clear();
		myFreqs.clear();
		FileResource fr = new FileResource();
		for(String word : fr.words()){
			word = word.toLowerCase();
			int index = myWords.indexOf(word);
			if(index == -1){
				myWords.add(word);
				myFreqs.add(1);
			}else{
				int value = myFreqs.get(index);
				myFreqs.set(index, value+1);
			}
		}
	}
	
	
	//Returns index of the most frequently occurring word
	public int findIndexOfMax(){
		int maxIndex = 0;
		for(int i = 0; i<myFreqs.size(); i++){
			if( myFreqs.get(i) > myFreqs.get(maxIndex))
				maxIndex = i;
		}
		return maxIndex;
	}
	
	
	public static void main(String[] args) {
		WordFrequencies wf = new WordFrequencies();
		wf.findUnique();
		System.out.println("# unique words :"+wf.myWords.size());
		System.out.println("Frequency of all the unique words : ");
		for(int i=0; i<wf.myFreqs.size(); i++)
			System.out.println(wf.myFreqs.get(i)+ "\t"+wf.myWords.get(i));
		
		int mostCommonWordIndex = wf.findIndexOfMax();
		System.out.println("The word that occurs most often and its count are: "+wf.myWords.get(mostCommonWordIndex) +"\t"+wf.myFreqs.get(mostCommonWordIndex));
	}
}
