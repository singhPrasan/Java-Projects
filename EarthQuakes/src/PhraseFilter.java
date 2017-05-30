/**
 * Checks whether the given earthquake's title contains the given phrase at the given location or not
 * 
 * @author Prasandeep Singh
 * @version 05/29/2017
 */
public class PhraseFilter implements Filter{
	private String requestType;
	private String phrase;
	private String filterName;
	
	public PhraseFilter(String where, String word, String name) {
		requestType = where;
		phrase = word;
		filterName = name;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		String title = qe.getInfo();
		switch(requestType){
		case "start":
			if(title.startsWith(phrase))	return true;
		case "end":
			if(title.endsWith(phrase))		return true;
		case "any":	
			if(title.contains(phrase))		return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return filterName;
	}
	
}
