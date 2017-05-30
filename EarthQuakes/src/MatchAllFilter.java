/**
 * Matches all the provided filters and responds accordingly
 * 
 * @author Prasandeep Singh
 * @version 05/29/2017
 */

import java.util.ArrayList;

public class MatchAllFilter implements Filter{
	private ArrayList<Filter> filters;
	
	public MatchAllFilter() {
		filters = new ArrayList<>();
	}
	
	
	public void addFilter(Filter f){
		filters.add(f);
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		for(Filter currFilter : filters){
			if(!currFilter.satisfies(qe))
				return false;
		}
		return true;
	}


	@Override
	public String getName() {
		String filterNames = "";
		for(Filter currFilter : filters)
			filterNames = filterNames + " "+currFilter.getName();
		return filterNames;
	}

	
}
