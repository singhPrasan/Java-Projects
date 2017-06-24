/*
 * Generates random text based on Markov Models of any given order
 * Not an efficient method
 * 
 * @author - Prasandeep Singh
 * @version - 06/04/2017
 */
package predictingWords;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel{

	private String[] myText;
	private Random myRandom;
	private int myOrder;
	
	//Contructor to initialize instance variables
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }
    
    //Generates same sequence of randomness based on the input seed
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    //Takes the input text and converts it into a string array 
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
    //To display contents of an object
	public String toString(){
		return "MarkovModel of order : "+myOrder;
	}
	
	
	//Generates random text of given length based on the order of MarkovModel
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
	
	
	//Returns a list of all the words that come right after a given WordGram object in the input text	
	private ArrayList<String> getFollows(WordGram kGram) {
	    ArrayList<String> follows = new ArrayList<String>();
	    for(int i = 0; i<myText.length-1;){
    		int start = indexOf(myText, kGram, i);
	    	if(start == -1 || start == myText.length-1)
	    		break;
	    	String next = myText[start+kGram.length()];
	 	    follows.add(next);
	    	i = start+kGram.length();
	    }
	    return follows;
    }
	
	//Returns index of target wordGram in the given array of words beginning from a start position
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

	//To test indexOf function
	private void testIndexOf(){
		String test = "this just a test yes this is a simple test";
		String[] inp = test.split("\\s+");
		WordGram targetTest = new WordGram(inp, 4, 2);
		System.out.println(indexOf(inp, targetTest,0));
	}

	//To test getFollows function
	private void testGetFollows(){
		WordGram targetTest = new WordGram(myText, 0, 1);
		ArrayList<String> follows = getFollows(targetTest);
		System.out.println(follows);
	}
	
	//Test Client
	public static void main(String[] args) {
		MarkovWord mw = new MarkovWord(1);
		String test = "this just a test yes this is a simple test";
		mw.setTraining(test);
	//	mw.testIndexOf();
	//	mw.testGetFollows();
		mw.getRandomText(10);
	}
}
