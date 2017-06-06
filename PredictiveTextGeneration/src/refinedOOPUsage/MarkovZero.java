/*
 * Generates random text based on myText training text using Markov Order Zero Model
 * 
 * @author - Prasandeep Singh
 */

package refinedOOPUsage;
import java.util.Random;

public class MarkovZero extends AbstractMarkovModel{
	
	public MarkovZero(){
		myRandom = new Random();
	}

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
	
	//To print contents of class object
	public String toString(){
		return "Markov Model of order : 0";
	}
s}
