/**
 * Checks whether the given earthquake's distance is less than the provided maximum distance
 * 
 * @author Prasandeep Singh
 * @version 05/29/2017
 */
public class DistanceFilter implements Filter{
	private Location location;
	private double maxDistance;
	private String filterName;
	
	public DistanceFilter(Location loc, double maxDist, String name) {
		location = loc;
		maxDistance = maxDist;
		filterName = name;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		if(qe.getLocation().distanceTo(location) < maxDistance)
			return true;
		return false;
	}

	@Override
	public String getName() {
		return filterName;
	}
	
}
