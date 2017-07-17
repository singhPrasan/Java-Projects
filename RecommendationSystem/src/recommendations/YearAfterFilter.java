/**
 * A class that implements Filter interface and returns all movies that released after a given year
 *
 * @author   - Prasandeep Singh
 * @created  - 07/01/2017
 * @updated  - 07/17/2017
 */

package recommendations;

public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id) {
		return MovieDatabase.getYear(id) >= myYear;
	}

}
