/**
 * A class that implements Filter interface and returns all movies that lie within the specified duration
 *
 * @author   - Prasandeep Singh
 * @created  - 07/01/2017
 * @updated  - 07/18/2017
 */

package recommendations;

public class MinutesFilter implements Filter{
	private int minMinutes;
	private int maxMinutes;
	
	public MinutesFilter(int min, int max){
		minMinutes = min;
		maxMinutes = max;
	}
	
	@Override
	public boolean satisfies(String id) {
		return (MovieDatabase.getMinutes(id)>=minMinutes && MovieDatabase.getMinutes(id)<=maxMinutes);
	}

}
