/**
 * Class which runs all markov models using the interface
 * 
 * @author Duke Software
 * @version 1.0
 */
package refinedOOPUsage;
import edu.duke.*; 

public class MarkovRunnerWithInterface {
	
    //Executes Markov model specified by the object
    //Uses a particular seed input provided by the user	
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println(markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    //Takes input as the file from which random text will be generated
    //Calls runModel function for further execution
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 42;
//        MarkovZero mz = new MarkovZero();
//        runModel(mz, st, size, seed);
//    
//        MarkovOne mOne = new MarkovOne();
//        runModel(mOne, st, size, seed);
//        
//        MarkovModel mThree = new MarkovModel(3);
//        runModel(mThree, st, size,seed);
//        
//        MarkovFour mFour = new MarkovFour();
//       runModel(mFour, st, size, seed);
        
        EfficientMarkovModel emm = new EfficientMarkovModel(5);
        runModel(emm, st, size, 531);

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
	
	
	//To test out whether the hashmap built is correct or not
	public void testHashMap(){
		EfficientMarkovModel mTwo = new EfficientMarkovModel(2);
		mTwo.seed = 42;
		mTwo.setTraining("yes-this-is-a-thin-pretty-pink-thistle");
	}
	
    //Compares execution time of normal MarkovModel class and Efficient Markov Model class	
	public void compareMethods(){
		MarkovModel mm = new MarkovModel(2);
		EfficientMarkovModel emm = new EfficientMarkovModel(2);
		mm.seed = 42; emm.seed = 42;
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		st = "yes-this-is-a-thin-pretty-pink-thistle";
		int size = 1000;
		long  start= System.nanoTime();
		runModel(mm, st, size, mm.seed);
        runModel(emm, st, size, emm.seed);
        long end = System.nanoTime();
        System.out.println("Execution time in nano seconds :"+(end-start));
		
	}
	
	//Test Client
	public static void main(String[] args) {
		MarkovRunnerWithInterface mrwi = new MarkovRunnerWithInterface();
		mrwi.runMarkov();
	//	mrwi.testHashMap();
	//	mrwi.compareMethods();
	}
	
}
