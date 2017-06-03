package refinedOOPUsage;
/*
 * Generates random text based on myText training text using Markov Order One Model
 * 
 * @author - Prasandeep Singh
 */

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel{
	
	public MarkovOne(){
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
//	public void setRandom(int seed){
//		myRandom = new Random(seed);
//	}
	
	//Sets the training text to the provided string
//	public void setTraining(String s){
//		myText = s.trim();
//	}
	
	//Returns a string based on random characters selected from myText
	public String getRandomText(int numChars){
		StringBuilder sb = new StringBuilder();
		//Generating index from 0 to length - 1 because the last index will not have any follow character
		//Hence not generating the last index to address this point
		int index = myRandom.nextInt(myText.length()-1);
		String key = myText.substring(index, index+1);
		sb.append(key);
		for(int i = 0; i < numChars-1; i++){
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
	public String toString(){
		
		return "Markov Model of order : 1";
}

}
