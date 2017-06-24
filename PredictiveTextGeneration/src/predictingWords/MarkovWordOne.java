
/**
 * Generates random text based on Markov Models of order 1.
 * This class can be ignored as MarkovModel class is implemented 
 * which can work for any given order
 * 
 * @author Prasandeep Singh
 * @version 06/03/2017
 */
package predictingWords;
import java.util.*;


public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordOne() {
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
		int index = myRandom.nextInt(myText.length-1);  // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for(int k=0; k < numWords-1; k++){
		    ArrayList<String> follows = getFollows(key);
		//    System.out.println(key+"\t"+follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		
		return sb.toString().trim();
	}
	
	private ArrayList<String> getFollows(String key) {
	    ArrayList<String> follows = new ArrayList<String>();
	    for(int i = 0; i<myText.length-1;){
    		int start = indexOf(myText, key, i);
	    	if(start == -1 || start == myText.length-1)
	    		break;
	    	String next = myText[start+1];
	 	    follows.add(next);
	    	i = start+1;
	    }
	    return follows;
    }
	
	private int indexOf(String[] words, String target, int start){
		int index = -1;
		for(int i = start; i<words.length; i++){
			if(words[i].equals(target)){
				index = i;
				break;
			}
		}
		return index;
	}

	private void testIndexOf(){
		String test = "this just a test yes this is a simple test";
		String[] inp = test.split("\\s+");
		System.out.println(indexOf(inp, "test",5));
	}
	
	
	
	public static void main(String[] args) {
		MarkovWordOne mwo = new MarkovWordOne();
		mwo.testIndexOf();
	}
}
