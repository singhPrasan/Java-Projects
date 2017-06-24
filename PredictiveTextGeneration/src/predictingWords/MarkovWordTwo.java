
/**
 * Generates random text based on Markov Models of order 2.
 * This class can be ignored as MarkovModel class is implemented 
 * which can work for any given order
 * 
 * 
 * @author Prasandeep Singh
 * @version 06/03/2017
 */
package predictingWords;
import java.util.*;


public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
		String key2 = myText[index+1];
		sb.append(key1);		sb.append(" ");
		sb.append(key2);		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
		//    System.out.println(key+"\t"+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key1 = key2;
			key2 = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();
	    for(int i = 0; i<myText.length-1;){
    		int start = indexOf(myText, key1, key2, i);
	    	if(start == -1 || start == myText.length-2)
	    		break;
	    	String next = myText[start+2];
	 	    follows.add(next);
	    	i = start+2;
	    }
	    return follows;
    }
	
	private int indexOf(String[] words, String target1, String target2, int start){
		int index = -1;
		for(int i = start+1; i<words.length; i++){
			if(words[i-1].equals(target1) && words[i].equals(target2)){
				index = i-1;
				break;
			}
		}
		return index;
	}

	private void testIndexOf(){
		String test = "this just a test yes this is a simple test";
		String[] inp = test.split("\\s+");
		System.out.println(indexOf(inp, "simple", "test",0)); 
	}
	
	
	
	public static void main(String[] args) {
		MarkovWordTwo mTwo = new MarkovWordTwo();
		mTwo.testIndexOf();
	}
}
