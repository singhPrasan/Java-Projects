/*
 * Generates random text based on myText training text using Markov Order Two Model
 * 
 * @author - Prasandeep Singh
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovTwo {
	private Random myRandom;
	private String myText;
	
	public MarkovTwo(){
		myRandom = new Random();
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
	}
	
	//Returns a string based on random characters selected from myText
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		//Generating index from 0 to length - 1 because the last index will not have any follow character
		//Hence not generating the last index to address this point
		int index = myRandom.nextInt(myText.length()-2);
		String key = myText.substring(index, index+2);
		sb.append(key);
		for(int i = 0; i < numChars-2; i++){
			ArrayList<String> follows = getFollows(key);
			if(follows.size()==0)
				break;
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			key = next;
		}
		return sb.toString();
	}
	
	
	//Returns a list of strings that follow the provided key
	private ArrayList<String> getFollows(String key){
		ArrayList<String> follows = new ArrayList<>();
		int pos = 0;
		while(pos < myText.length()){
			int start = myText.indexOf(key, pos);
			if(start == -1)
				break;
			if(start+key.length() >= myText.length()-1)
				break;
			String next = myText.substring(start+key.length(), start+key.length()+1);
			follows.add(next);
			pos = start+key.length();
		}
		return follows;
	}
}
