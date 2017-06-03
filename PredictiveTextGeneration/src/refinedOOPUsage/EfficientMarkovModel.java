package refinedOOPUsage;
/*
 * Optimization of MarkovModel
 * Generates random text based on myText training text using Markov Order N Model
 * 
 * @author - Prasandeep Singh
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{
	private int order;
	private HashMap<String, ArrayList<String>> map ;
	
	public EfficientMarkovModel(int orderNum){
		myRandom = new Random();
		order = orderNum;
		map = new HashMap<>();
	}
	
	/*
	 * Generates random number based on the given seed input
	 * 
	 * The "seed" is a starting point for the sequence and the guarantee is that if you start 
	 * from the same seed you will get the same sequence of numbers. This is very useful for example for debugging 
	 * (when you are looking for an error in a program you need to be able to reproduce the problem and study it, 
	 * a non-deterministic program would be much harder to debug because every run would be different).
	 */
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	//Sets the training text to the provided string
	public void setTraining(String s){
		myText = s.trim();
		buildMap(order);
	}
	
	//Returns a string based on random characters selected from myText
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		//Generating index from 0 to length - 1 because the last index will not have any follow character
		//Hence not generating the last index to address this point
		int index = myRandom.nextInt(myText.length()-order);
		String key = myText.substring(index, index+order);
		sb.append(key);
		for(int i = 0; i < numChars-order; i++){
			ArrayList<String> follows = getFollows(key);
			if(follows.size()==0)
				break;
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = key.substring(1)+next;
		}
	//	System.out.println("myText.length() : "+myText.length());
	//	System.out.println("random char index : "+index);
	//	System.out.println("random char: "+key);
		return sb.toString();
	}
	
	
	//Builds the hashmap of every possible key mapped to a list of characters followed 
	//Traverses through the input text once
	private void buildMap(int order){
		ArrayList<String> follows;
		for(int i=0; i<myText.length(); i++){
			String key = myText.substring(i, i+order);
			if(!map.containsKey(key)){
				follows = new ArrayList<>();
				map.put(key, follows);
			}
			else
				follows = map.get(key);
			int start = myText.indexOf(key, i);
			if(start == -1 || start == (myText.length() - key.length()))
				break;
			int index = start + key.length();
			follows.add(myText.substring(index, index+1));
			map.put(key, follows);
		}
		printHashMapInfo();
	}
	
	
//	To test the hashmap and print its contents
	private void printHashMapInfo(){
		System.out.println("Number of keys is map :"+map.keySet().size());
		int maxFollows = 0;
		for(String key : map.keySet()){
			int listSize = map.get(key).size();
			if(listSize > maxFollows)
				maxFollows = listSize;
		}
		System.out.println("Maximum number of keys following a key : "+maxFollows);
//		System.out.println("Keys that have the largest arrayList : " );
//		for(String key : map.keySet()){
//			int listSize = map.get(key).size();
//			if(listSize == maxFollows)
//				System.out.print(key+", ");
//			System.out.println(key+"\t"+map.get(key));
//		}
		
	}
	
	protected ArrayList<String> getFollows(String key){
		return map.get(key);
	}
	
	public String toString(){
		
		return "EfficientMarkovModel class of order : "+order;
	}

}	
