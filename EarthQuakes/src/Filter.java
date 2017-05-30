
/**
 * An interface for filters
 * 
 * @author Prasandeep Singh
 * @version 05/29/2017
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe); 
    public String getName();  	
}
