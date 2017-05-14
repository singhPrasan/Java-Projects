
public class OperationsOnStrings {
	
	//Returns true if stringb has atleast two occurrences of stringa in it
	public boolean twoOccurences(String stringa, String stringb){
		int firstOcc = stringb.indexOf(stringa);
		if( firstOcc == -1)
			return false;
		int secondOcc = stringb.indexOf(stringa, firstOcc+stringa.length());
		if(secondOcc == -1)
			return false;
		else
			return true;
	}
	
	//Returns part of stringb that occurs after first occurrence of stringa in stringb
	public String lastPart(String stringa, String stringb){
		int firstOcc = stringb.indexOf(stringa);
		if(firstOcc == -1)
			return stringb;
		else	
			return stringb.substring(firstOcc+stringa.length(), stringb.length());
	}
	
	
	public static void main(String[] args) {
		OperationsOnStrings oos = new OperationsOnStrings();
		System.out.println(oos.twoOccurences("atg", "ctgtatgta"));
		System.out.println(oos.lastPart("an", "banana"));
	}
}
