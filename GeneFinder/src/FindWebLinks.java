/*
 * To search for all links that navigate to youtube from a given webpage
 */

import edu.duke.*;

public class FindWebLinks {
	
	//Prints all the youtube links that are present on a given webpage
	public void findLinks(){
		URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
		String youTube = "youtube.com";
		for(String word : ur.words()){
			int startIndex = word.toLowerCase().indexOf(youTube);
			if(startIndex!=-1){
				int leftQuoteIndex = word.indexOf("\"");
				int rightQuoteIndex = word.indexOf("\"",startIndex+youTube.length());
				System.out.println(word.substring(leftQuoteIndex, rightQuoteIndex+1));
			}
		}
	}
	public static void main(String[] args) {
		FindWebLinks fwl = new FindWebLinks();
		fwl.findLinks();
	}
}
