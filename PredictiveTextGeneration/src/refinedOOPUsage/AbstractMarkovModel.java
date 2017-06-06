/**
 * Base class for all Markov Model classes. All child class must implement the abstract method of this class
 * 
 * @author Prasandeep Singh
 * @version 06/01/2017
 */

package refinedOOPUsage;
import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int seed;
    
    //Constructor
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    //Removes any whitespaces at the end/start of the text
    public void setTraining(String s) {
        myText = s.trim();
    }
    
	
	//Generates random number based on the given seed input
	public void setRandom(int seed){
		myRandom = new Random(seed);
	}
	
	//Returns a list of strings that follow the provided key
	protected ArrayList<String> getFollows(String key){
		ArrayList<String> follows = new ArrayList<>();
		for(int i = 0; i<myText.length()-1; ){
			int start = myText.indexOf(key, i);
			if(start == -1 || start==myText.length()-key.length())
				break;
			int index = start+key.length();
			follows.add(myText.substring(index, index+1));
			i = index;
		}
		return follows;
	}
	
	//Abstract function that is implemented in the child classes
    abstract public String getRandomText(int numChars);

}
