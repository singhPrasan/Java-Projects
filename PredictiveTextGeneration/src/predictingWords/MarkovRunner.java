
/**
 * Class used to run all Markov Models
 * 
 * @author Prasandeep Singh
 * @version 06/03/2017
 */
package predictingWords;
import edu.duke.*;

public class MarkovRunner {
    //Executes Markov model specified by the object
    //Does not use a seed input provided by the user
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    //Executes Markov model specified by the object
    //Uses a particular seed input provided by the user
    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    
    //Takes input as the file from which random text will be generated
    //Calls runModel function for further execution
    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
    //  MarkovWordOne markovWord = new MarkovWordOne(); 
        MarkovWord mWord = new MarkovWord(5);
     // runModel(markovWord, st, 120, 139);
     // runModel(markovWord, st, 200); 
        runModel(mWord, st, 200, 844);
    } 

    //Runs Markov model of order 2
    public void runMarkovTwo() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        runModel(markovWord, st, 1000, 832);
     //   runModel(markovWord, st, 200); 
    } 
    
    
    //Prints out the random text generated
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    //Tests simple markov model class
    private void testSimple(){
    	String test = "this is just a test yes this is a simple test";
    	runModel(new MarkovWordTwo(), test, 200);
    }
    
    //Tests the hashmap created for Efficient markov model
    private void testHashMap(){
    	EfficientMarkovWord emw = new EfficientMarkovWord(2);
    	int seed = 42;
    	String inp = "this is a test yes this is really a test yes a test this is wow";
    	int size = 50;
    	runModel(emw, inp, size, seed);
    }
    
    
    //Compares execution time of normal MarkovModel class and Efficient Markov Model class
    private void compareMethods(){
    	FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
    	MarkovWord mw = new MarkovWord(2);
    	EfficientMarkovWord emm = new EfficientMarkovWord(2);
    	int seed = 65;
    	int size = 100;
    	long startTime = System.currentTimeMillis();
    //	runModel(mw, st, size, seed);
    	runModel(emm, st, size, seed);
    	long endTime = System.currentTimeMillis();
    	System.out.println("Time taken : "+(endTime-startTime));
    }
    
    
    //Test Client
    public static void main(String[] args) {
		MarkovRunner mr = new MarkovRunner();
	//	mr.runMarkov();
	//	mr.testSimple();
	//	mr.runMarkovTwo();
	//	mr.testHashMap();
		mr.compareMethods();
	}
}
