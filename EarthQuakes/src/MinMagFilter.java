
/**
 * Checks whether the given earthquake has magnitude greater than the provided magnitude
 * 
 * @author Prasandeep Singh
 * @version 05/29/2017
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    private String filterName;
    public MinMagFilter(double min, String name) { 
        magMin = min;
        filterName = name;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    }

	@Override
	public String getName() {
		return filterName;
	} 

}
