package separateClasses;
/*
 * Tester class to test all the Markov Order models
 * 
 * @author - Prasandeep Singh
 */
import java.util.ArrayList;
import edu.duke.FileResource;

public class Tester {
	
	//Tests getFollows method of MarkovOne class using small input
	public void testGetFollows(){
		MarkovOne mOne = new MarkovOne();
		mOne.setTraining("this is a test yes this is a test.");
		ArrayList<String> follows = mOne.getFollows("t");
		System.out.println(follows);
		System.out.println(follows.size());
	}
	
	
	//Tests getFollows method of MarkovOne class using input from a files
	public void testGetFollowsWithFile(){
		MarkovOne mOne = new MarkovOne();
		FileResource fr = new FileResource();
		String trainingText = fr.asString();
		mOne.setTraining(trainingText);
		ArrayList<String> follows = mOne.getFollows("he");
		System.out.println(follows);
		System.out.println(follows.size());
	}
	
	
	public static void main(String[] args) {
		Tester test = new Tester();
	//	test.testGetFollows();
		test.testGetFollowsWithFile();
	}
}
