/**
 * A class that implements Filter interface and overrides satisfies method to work with multiple filters
 *
 * @author   - Prasandeep Singh
 * @created  - 01/07/2017
 * @updated  - 17/07/2017
 */


import java.util.ArrayList;

public class AllFilters implements Filter {
    ArrayList<Filter> filters;
    
    public AllFilters() {
        filters = new ArrayList<Filter>();
    }

    //Added the given filter to a list of filters 
    public void addFilter(Filter f) {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if (! f.satisfies(id)) {
                return false;
            }
        }
        
        return true;
    }

}
