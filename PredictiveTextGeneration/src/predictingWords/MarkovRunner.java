
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Prasandeep Singh
 * @version 06/03/2017
 */
package predictingWords;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
    //    MarkovWordOne markovWord = new MarkovWordOne(); 
        MarkovWord mWord = new MarkovWord(5);
     //   runModel(markovWord, st, 120, 139);
     //   runModel(markovWord, st, 200); 
        runModel(mWord, st, 200, 844);
    } 

    public void runMarkovTwo() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWordTwo markovWord = new MarkovWordTwo(); 
        runModel(markovWord, st, 1000, 832);
     //   runModel(markovWord, st, 200); 
    } 
    
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

    private void testSimple(){
    	String test = "this is just a test yes this is a simple test";
    	runModel(new MarkovWordTwo(), test, 200);
    }
    
    private void testHashMap(){
    	EfficientMarkovWord emw = new EfficientMarkovWord(2);
    	int seed = 42;
    	String inp = "this is a test yes this is really a test yes a test this is wow";
    	int size = 50;
    	runModel(emw, inp, size, seed);
    }
    
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
    
    public static void main(String[] args) {
		MarkovRunner mr = new MarkovRunner();
	//	mr.runMarkov();
	//	mr.testSimple();
	//	mr.runMarkovTwo();
	//	mr.testHashMap();
		mr.compareMethods();
	}
}
