/**
 * Checks whether the given earthquake has magnitude within the specified limits of not
 * 
 * @author Prasandeep Singh
 * @version 05/29/2017
 */
public class MagnitudeFilter implements Filter{
	private double minMag;
	private double maxMag;
	private String filterName;
	
	public MagnitudeFilter(double min, double max, String name) {
		minMag = min;
		maxMag = max;
		filterName = name;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		if(qe.getMagnitude() >= minMag && qe.getMagnitude() <= maxMag)
			return true;
		return false;
	}

	@Override
	public String getName() {
		return filterName;
	}
	
}
