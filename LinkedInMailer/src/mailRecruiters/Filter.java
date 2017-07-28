/**
 * An interface that has a method signature which is implemented by all classes that implement filter
 * 
 * @author  - Prasandeep Singh
 * @created - 07/01/2017
 * @updated - 07/18/2017
 */
package mailRecruiters;

public interface Filter {
	public boolean satisfies(String id);
}
