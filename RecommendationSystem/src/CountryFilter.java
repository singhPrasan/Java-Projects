/**
 * A class that implements Filter interface and returns all movies that were produced in the given country
 *
 * @author   - Prasandeep Singh
 * @created  - 19/01/2017
 */


public class CountryFilter implements Filter{
	String myCountry;
	
	public CountryFilter(String country){
		myCountry = country;
	}

	@Override
	public boolean satisfies(String id) {
		String[] allCountries = myCountry.split(",");
		for(String currCountry : allCountries){
			if(MovieDatabase.getCountry(id).contains(currCountry))
				return true;
		}
		return false;
	}
}
 