/**
 * A class that implements Filter interface and returns all movies in the database
 * 
 * @author   - Prasandeep Singh
 * @created  - 07/01/2017
 * @updated  - 07/18/2017
 */


public class TrueFilter implements Filter {
	@Override
	public boolean satisfies(String id) {
		return true;
	}

}
