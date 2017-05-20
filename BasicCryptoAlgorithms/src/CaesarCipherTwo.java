/*
 * To implement the Caesar Cipher using two key encryption
 */


public class CaesarCipherTwo {
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int mainKey1;
	private int mainKey2;
	
	public CaesarCipherTwo(int key1, int key2){
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
		shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
		mainKey1 = key1;
		mainKey2 = key2;
	}
	
	//Encryptes the message in accordance to two separate keys
	public String encrypt(String input){
		StringBuilder encrypted = new StringBuilder(input);
		CaesarCipher cc = new CaesarCipher(0);
		
		for(int i = 0; i<input.length(); i++){
			if(i % 2 == 0){
				encrypted = cc.encryptChar(encrypted, i, shiftedAlphabet1);
			}else{
				encrypted = cc.encryptChar(encrypted, i, shiftedAlphabet2);
			}
		}
		return encrypted.toString();
	}
	
	//Returns the decrypted message
	public String decrypt(String encrypted){
		//Create an object of CaesarCipher class
		CaesarCipherTwo cct = new CaesarCipherTwo(26-mainKey1, 26 - mainKey2);
		//Call the encrypt function in the CaesarCipher class with the decryption key to return the original message
		return cct.encrypt(encrypted);
	}
		
}
