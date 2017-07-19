/**
 * A class that implements Filter interface and returns all movies of the genres mentioned specified in the list
 *
 * @author   - Prasandeep Singh
 * @created  - 07/01/2017
 * @updated  - 07/18/2017
 */

package recommendations;

public class GenreFilter implements Filter{
	private String myGenre;
	
	public GenreFilter(String genre) {
		myGenre = genre;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getGenres(id).contains(myGenre);
	}
}
