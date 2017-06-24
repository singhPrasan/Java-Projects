/*
 * Generates random text based on myText training text using Markov Order N Model
 * 
 * @author - Prasandeep Singh
 * @date created - 06/01/2017
 */

package refinedOOPUsage;
import java.util.ArrayList;
import java.util.Random;

public class MarkovModel extends AbstractMarkovModel{
	private int order;
	
	//Constructor
	public MarkovModel(int numChars){
		myRandom = new Random();
		order = numChars;
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
	
	//To print contents of class object
	public String toString(){
		return "Markov Model of order : "+order;
	}
}	
