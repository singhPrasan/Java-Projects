package refinedOOPUsage;
/*
 * Generates random text based on myText training text using Markov Order Zero Model
 * 
 * @author - Prasandeep Singh
 */

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel{
	
	public MarkovZero(){
		myRandom = new Random();
	}
	
//	/*
//	 * Generates random number based on the given seed input
//	 * 
//	 * The "seed" is a starting point for the sequence and the guarantee is that if you start 
//	 * from the same seed you will get the same sequence of numbers. This is very useful for example for debugging 
//	 * (when you are looking for an error in a program you need to be able to reproduce the problem and study it, 
//	 * a non-deterministic program would be much harder to debug because every run would be different).
//	 */
//	public void setRandom(int seed){
//		myRandom = new Random(seed);
//	}
	
	//Sets the training text to the provided string
//	public void setTraining(String s){
//		myText = s.trim();
//	}

	//Returns a string based on random characters selected from myText
	public String getRandomText(int numChars){
		if( myText == null )	return "";
		StringBuilder sb = new StringBuilder();
		for(int i =0; i<numChars; i++){
			int index = myRandom.nextInt(myText.length());
			sb.append(myText.charAt(index));
		}
		return sb.toString();
	}
	
	public String toString(){
		
		return "Markov Model of order : 0";
}

}
