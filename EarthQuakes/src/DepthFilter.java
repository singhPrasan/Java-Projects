/**
 * Checks whether the given earthquake has depth within the specified limits of not
 * 
 * @author Prasandeep Singh
 * @version 05/29/2017
 */
public class DepthFilter implements Filter{
	private double minDepth;
	private double maxDepth;
    private String filterName;

	public DepthFilter(double min, double max, String name) {
		minDepth = min;
		maxDepth = max;
		filterName = name;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
		if(qe.getDepth()>= minDepth && qe.getDepth() <= maxDepth)
			return true;
		return false;
	}

	@Override
	public String getName() {
		return filterName;
	}
	
}
