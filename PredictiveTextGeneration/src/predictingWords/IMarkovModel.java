
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author Prasandeep Singh
 * @version 06/03/2017
 */
package predictingWords;

public interface IMarkovModel {
    public void setTraining(String text);
    public void setRandom(int seed);
    public String getRandomText(int numChars);

}
