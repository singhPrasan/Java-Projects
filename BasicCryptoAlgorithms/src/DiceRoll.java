import java.util.Random;
import java.util.Scanner;

/*
 * To roll a dice N times and check how many times all the possible numbers occur
 */
public class DiceRoll {
	
	
	//Prints the number of times sum of two dices rolled shows up from 2 to 12
	public void simpleRoll(int rolls){
		Random ran = new Random();
		
		//counts all possible outputs from 2 to 12. 
		int []counts = new int[13];
		
		for(int i = 0; i<rolls; i++){
			//First die roll
			int d1 = ran.nextInt(6) + 1;
			//Second die roll
			int d2 = ran.nextInt(6) + 1;
			
			//Increment count for the sum in the appropriate counts array
			counts[d1+d2]++;
		}
		for(int i = 2; i<=12; i++)
			System.out.println("Number of "+i+"'s rolled :"+counts[i] + "\t" + 100.0*counts[i]/rolls);
	}
	
	
	public static void main(String[] args) {
		DiceRoll dr = new DiceRoll();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of times you want to roll the dice :");
		int rolls = in.nextInt();
		dr.simpleRoll(rolls);
		
		in.close();
	}
}
