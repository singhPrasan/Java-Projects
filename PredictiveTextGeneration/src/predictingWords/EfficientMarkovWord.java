package predictingWords;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel{

	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private HashMap<WordGram, ArrayList<String>> map;
	
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        map = new HashMap<>();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
	public String toString(){
		return "Markov Model of order : "+myOrder;
	}
	
    public void setTraining(String text){
		myText = text.split("\\s+");
		buildMap();
	}
	
	//Builds the hashmap of every possible key mapped to a list of characters followed 
	//Traverses through the input text once
	private void buildMap(){
		ArrayList<String> follows;
		for(int i=0; i<myText.length; i++){
			String[] keyGram = new String[myOrder];
			for(int j = i; j-i<keyGram.length; j++)
				keyGram[j-i] = myText[j];
			WordGram currWordGram = new WordGram(keyGram, 0, myOrder);
			if(!map.containsKey(currWordGram)){
				follows = new ArrayList<>();
				map.put(currWordGram, follows);
			}
			else
				follows = map.get(currWordGram);
			int start = indexOf(myText, currWordGram, i);
			if(start == -1 || start == (myText.length - currWordGram.length()))
				break;
			int index = start + currWordGram.length();
			follows.add(myText[index]);
			map.put(currWordGram, follows);
		}
		printHashMapInfo();
	}
	
	
//	To test the hashmap and print its contents
	private void printHashMapInfo(){
		System.out.println("Number of keys is map :"+map.keySet().size());
		int maxFollows = 0;
		for(WordGram key : map.keySet()){
			int listSize = map.get(key).size();
			if(listSize > maxFollows)
				maxFollows = listSize;
		}
		System.out.println("Maximum number of keys following a key : "+maxFollows);
//		System.out.println("Keys that have the largest arrayList : " );
//		for(WordGram key : map.keySet()){
//			int listSize = map.get(key).size();
//			if(listSize == maxFollows){
//				System.out.println(key+"\t"+map.get(key));
//			}
//		}
//		System.out.println("Entire MAp : \n"+map);
	}
	  
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
		WordGram keyGram = new WordGram(myText, index, myOrder);
		for(int i = 0; i<keyGram.length(); i++){
			sb.append(keyGram.wordAt(i));
			sb.append(" ");
		}
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(keyGram);
		//    System.out.println(key+"\t"+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			keyGram = keyGram.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
	    
	    return map.get(kGram);
    }
	
	private int indexOf(String[] words, WordGram target, int start){
		int index = -1;
		for(int i = start; i<words.length-target.length(); i++){
			WordGram currWordGram  = new WordGram(words, i, target.length());
			if(target.equals(currWordGram)){
				index = i;
				break;
			}
		}
		return index;
	}

	private void testIndexOf(){
		String test = "this just a test yes this is a simple test";
		String[] inp = test.split("\\s+");
		WordGram targetTest = new WordGram(inp, 4, 2);
		System.out.println(indexOf(inp, targetTest,0));
	}
	
	private void testGetFollows(){
		WordGram targetTest = new WordGram(myText, 0, 1);
		ArrayList<String> follows = getFollows(targetTest);
		System.out.println(follows);
	}
	
	
	public static void main(String[] args) {
		MarkovWord mw = new MarkovWord(1);
		String test = "this just a test yes this is a simple test";
		mw.setTraining(test);
	//	mw.testIndexOf();
	//	mw.testGetFollows();
		mw.getRandomText(10);
	}
}
