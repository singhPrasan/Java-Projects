package refinedOOPUsage;

/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author Prasandeep Singh
 * @version 06/01/2017
 */

public interface IMarkovModel {
    public void setTraining(String text);
    public void setRandom(int seed);
    public String getRandomText(int numChars);
    
}
