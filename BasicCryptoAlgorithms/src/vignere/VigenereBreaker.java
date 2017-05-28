package vignere;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class VigenereBreaker {
	
	//To extract all the characters from the message that have been encrypted with a particular key
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i = whichSlice; i<message.length(); i+=totalSlices){
        	sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    //To return the keys that were used to encypt the encrypted message
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        CaesarCracker cc = new CaesarCracker();
        for(int i = 0; i<klength; i++){
        	String sliced = sliceString(encrypted, i, klength);
        	key[i] = cc.getKey(sliced);
        }
        
        return key;
    }

    
    //Returns the most commonly occurring character in a particular language
    public char mostCommonCharIn(HashSet<String> dictionary){
    	int countOcc[] = new int[26];
    	int maxIndex = 0;
    	CaesarCracker cc =new CaesarCracker();
    	for(String word : dictionary){
    		countOcc = cc.countLetters(word);
    	}
    	maxIndex = cc.maxIndex(countOcc);
    	return (char) (maxIndex+'a');
    }
    
    
    
    //Creates and returns a HashSet of all the words read from a particular dictionary file
    public HashSet<String> readDictionary(FileResource fr){
    	HashSet<String> dictSet = new HashSet<>();
    	for(String line : fr.lines()){
    		line = line.toLowerCase();
    		dictSet.add(line);
    	}
    	return dictSet;
    }
    
    
    //Returns the number of real words that occur in the message string
    public int countWords(String message, HashSet<String> dictionary){
    	String[] words = message.split("\\W+");
    	int countRealWords = 0;
    	for(String word : words){
    		word = word.toLowerCase();
    		if( dictionary.contains(word))
    			countRealWords++;
    	}
    	
    	return countRealWords;
    }
    
    
    //Returns the best decryption with maximum real words with key lengths from 1 to 100
    //Instead of 1 to 100, we can also go from 1 to encrpted.length(). This would make the code slower
    //Also, we have been provided that the keyLength will be between 1 to 100
    public String breakForLanguage(String encrypted, HashSet<String> dictionary){
    	int maxRealWords = 0;
 //   	int keyLengthUsed = 0;
    	String bestDecryption = "";
    	for(int i = 1; i<=100; i++){
    		char mostCommonChar = mostCommonCharIn(dictionary);
    		int[] obtainedKey = tryKeyLength(encrypted, i, mostCommonChar);
    		VigenereCipher vc =new VigenereCipher(obtainedKey);
    		String currDecrpt = vc.decrypt(encrypted);
    		int currCount = countWords(currDecrpt, dictionary);
    		if(currCount>maxRealWords){
    			maxRealWords = currCount;
    			bestDecryption = currDecrpt;
    	//		keyLengthUsed = obtainedKey.length;
    			System.out.print(".");
    		}
    	}
    //	System.out.println("Key length used "+keyLengthUsed);
    //	System.out.println("Number of valid words -------------"+maxRealWords);
    	return bestDecryption;
    }
    
    
    //Finds the encrypted language and original message from various languages
    public void breakForAllLangs(String encrypted, HashMap<String, HashSet<String>> languages){
    	int maxRealWords = 0;
    	String bestDecryption = "";
    	String decryptedLanguage = "";
    	System.out.print("Decrypting language and message ");
    	for(String langName : languages.keySet()){
    		HashSet<String> dict = languages.get(langName);
    		String bestDecryptionForLangName = breakForLanguage(encrypted, dict);
    		int currCount = countWords(bestDecryptionForLangName, dict);
    		if(currCount>maxRealWords){
    			maxRealWords = currCount;
    			bestDecryption = bestDecryptionForLangName;
    			decryptedLanguage = langName;
    		}
    	}
    	System.out.println();
    	System.out.println("Language Identified : "+decryptedLanguage);
    	System.out.println("Decrypted message : \n"+bestDecryption);
    }
    
    
    //Maps and returns a language to its dictionary of words
    public HashMap<String, HashSet<String>> langDictionary(DirectoryResource dr){
    	HashMap<String, HashSet<String>> map = new HashMap<>();
    	System.out.print("Scanning dictionaries");
    	for(File f : dr.selectedFiles()){
    		FileResource fr = new FileResource(f);
			String fName = f.getName();
			HashSet<String> dict = readDictionary(fr);
			System.out.print(".");
			map.put(fName, dict);
		}
    	System.out.println("All Dictionaries scanned!");
    	return map;
    }
    
    
    
    //Test Client
    public static void main(String[] args) {
		VigenereBreaker vb = new VigenereBreaker();
		FileResource fr = new FileResource();
		String encryptedText = fr.asString();
	//	int[] key = vb.tryKeyLength(encryptedText, 5, 'e');
	//	System.out.println(Arrays.toString(key));
	//	VigenereCipher vc = new VigenereCipher(key);
	
		DirectoryResource dr = new DirectoryResource();
		HashMap<String, HashSet<String>> langDict = vb.langDictionary(dr);
	//	System.out.println("Most Common character : "+vb.mostCommonCharIn(dictionary));
	//	String decrypted = vb.breakForLanguage(encryptedText, dictionary);
	//	System.out.println("Decrypted message : "+decrypted);
		vb.breakForAllLangs(encryptedText, langDict);
	}
}
