/**
 * Interface for classes to interact
 * 
 * @author Prasandeep Singh
 * @date created - 06/01/2017
 */
package refinedOOPUsage;

public interface IMarkovModel {
    public void setTraining(String text);
    public void setRandom(int seed);
    public String getRandomText(int numChars);
    
}
