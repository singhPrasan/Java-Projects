/**
 * A class that implements Filter interface and returns all movies that were directed by any one of the directors specified in the list
 *
 * @author   - Prasandeep Singh
 * @created  - 07/01/2017
 * @updated  - 07/17/2017
 */


public class DirectorsFilter implements Filter{
	String myDirectors;
	
	public DirectorsFilter(String directors){
		myDirectors = directors;
	}

	@Override
	public boolean satisfies(String id) {
		String[] allDirectors = myDirectors.split(",");
		for(String currDirector : allDirectors){
			if(MovieDatabase.getDirector(id).contains(currDirector))
				return true;
		}
		return false;
	}
}
