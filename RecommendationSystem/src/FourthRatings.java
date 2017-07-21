/**
 * This file contains the business logic to provide appropriate recommendations to a given user
 *
 * @author  - Prasandeep Singh
 * @created - 07/01/2017
 * @updated - 07/17/2017 
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FourthRatings {
   
    //Returns a list of all recommendations for a specified user based on the selected filter
    //raterId : user for whom recommendations have to be provided
    //numSimilarRaters : This is the minimum number of raters that have to be considered who have the same taste in movies as the specified user
    //minimalRaters : only movies that have atleast minimalRaters number of raters should be considered for recommendations
    //criteriaFilter : Filters out movies based on the criteria specified. Reduces the sample space to be considered
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, int numSimilarRaters, int minimalRaters, Filter criteriaFilter){
    	ArrayList<String> movies = MovieDatabase.filterBy(criteriaFilter);
    	ArrayList<Rating> similarRatings = getSimilarities(raterId);
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinR(movies,minimalRaters, similarRatings, numSimilarRaters);
    	ArrayList<Rating> ratingList = getMoviesSortedByWeightedAverages(moviesHavingMinRaters, similarRatings, numSimilarRaters);
   	return ratingList;
    }
    
    //Returns a list of all recommendations for a specified user
    //raterId : user for whom recommendations have to be provided
    //numSimilarRaters : This is the minimum number of raters that have to be considered who have the same taste in movies as the specified user
    //minimalRaters : only movies that have atleast minimalRaters number of raters should be considered for recommendations
    public ArrayList<Rating> getSimilarRatings(String raterId,  int numSimilarRaters, int minimalRaters){
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	ArrayList<Rating> similarRatings = getSimilarities(raterId);
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinR(movies,minimalRaters, similarRatings, numSimilarRaters);
    	ArrayList<Rating> ratingList = getMoviesSortedByWeightedAverages(moviesHavingMinRaters, similarRatings, numSimilarRaters);
    	return ratingList;
    }

    //Returns a list consisting of all other raters and their degree of similarity in movie tastes with the specified user
    //List is returned in decreasing order of similarity. Users more similar to the concerned user will end up on top of the returned list
    //raterIdToBeMatchedWith : User/rater in consideration
    private ArrayList<Rating> getSimilarities(String raterIdToBeMatchedWith){
    	ArrayList<Rating> ratingList = new ArrayList<>();
    	Rater me = RaterDatabase.getRater(raterIdToBeMatchedWith);

	//Store all movies of the concerned user in a hashset
	//All other users would be compared against this set to get similarity factor
    	HashSet<String> setMe = new HashSet<>();
    	for(String s : me.getItemsRated()){
    		setMe.add(s);
    	}
    	for(Rater r : RaterDatabase.getRaters()){
    		double similarityFactor = 0;
    		String currRaterId = r.getID();
    		if(!raterIdToBeMatchedWith.equals(currRaterId)){
    			similarityFactor = dotProduct(me, r, setMe);
    			if(similarityFactor > 0 )
    				ratingList.add(new Rating(currRaterId, similarityFactor));
    		}
    	}
    	Collections.sort(ratingList, Collections.reverseOrder());
    	return ratingList;
    }
    
    //Returns a similarity factor between two specified users/raters
    //me : Rater for whom the recommendations have to be provided
    //r  : Rater with which the degree of similarity has to be calculated
    //setMe : A hashset that contains all the rated movies by the "me" user 
    private double dotProduct(Rater me, Rater r, HashSet<String> setMe){
      	int dotP = 0;
       	ArrayList<String> moviesRatedByR = r.getItemsRated();
       	ArrayList<Double> meRatingsVector = new ArrayList<>();
       	ArrayList<Double> rRatingsVector = new ArrayList<>();
       	for(int i = 0; i<moviesRatedByR.size();i++){
      		String movieId = moviesRatedByR.get(i);
       		if(setMe.contains(movieId)){

			//Crucial step : To center the ratings around 0
			//Doing this, raters that have dissimilar tastes, get a negative similarity factor and can be left out
       			meRatingsVector.add(me.getRating(movieId)-5);
       			rRatingsVector.add(r.getRating(movieId)-5);
       		}
       	}
      	for(int k = 0; k<meRatingsVector.size();k++)
       		dotP += meRatingsVector.get(k)*rRatingsVector.get(k);
       	return dotP;
    }
   
    //Returns a list of all movies that have been rated by atleast minimalRaters from a list of similarRatings
    //movies : a list of all movie ids that adhere to the specified filter.
    //numSimilarRaters : This is the minimum number of raters that have to be considered who have the same taste in movies as the specified user
    //minimalRaters : only movies that have atleast minimalRaters number of raters should be considered for recommendations
    //similarRatings: a list of all the raters that have similar taste of movies as that of the concerned user/rater 
    private ArrayList<String> getMoviesHavingMinR(ArrayList<String> movies, int minimalRaters, ArrayList<Rating> similarRatings,
     		 int numSimilarRaters){
      	ArrayList<String> moviesWithMinTopRaters = new ArrayList<>();
       	for(String currMovieId : movies){
       		int numRatersForCurrMovie = numRatingsOfMovie(similarRatings, currMovieId, numSimilarRaters);
       		if(numRatersForCurrMovie>=minimalRaters)
       			moviesWithMinTopRaters.add(currMovieId);	
       	}
       	return moviesWithMinTopRaters;
    }

    //Returns count of top similar raters who rated a particular movie
    //numSimilarRaters : This is the minimum number of raters that have to be considered who have the same taste in movies as the specified user
    //similarRatings: a list of all the raters that have similar taste of movies as that of the concerned user/rater 
    public int numRatingsOfMovie(ArrayList<Rating> similarRatings, String movieId, int numSimilarRaters){
    	int ratingCount = 0;
    	numSimilarRaters = numSimilarRaters>=similarRatings.size() ? similarRatings.size() : numSimilarRaters;
        for(int i = 0; i<numSimilarRaters; i++){
    	        Rating currRating = similarRatings.get(i);
    		Rater currRater = RaterDatabase.getRater(currRating.getItem());
    		if(currRater.hasRating(movieId))
    			ratingCount++;
    	}
    	return ratingCount;
    }

    //Returns a list of all movies along with their weighted averages that is calculated based on the similarity factor of similar raters
    //Movies with a higher weighted average are kept on the top
    //moviesHavingMinRaters : a list of all movies that have atleast minimalRaters number of topRaters from the similarRatings list
    private ArrayList<Rating> getMoviesSortedByWeightedAverages(ArrayList<String> moviesHavingMinRaters, ArrayList<Rating> similarRatings,
    		 int numSimilarRaters){
    	ArrayList<Rating> ratingList = new ArrayList<>();
    	for(String currMovieId : moviesHavingMinRaters){
    		ArrayList<Double> weightedRating = new ArrayList<>();
    		for(int k = 0; k<numSimilarRaters; k++){
    			Rating currRating = similarRatings.get(k);
    			Rater currRater = RaterDatabase.getRater(currRating.getItem());
    			if(currRater.hasRating(currMovieId))
    				weightedRating.add(currRater.getRating(currMovieId) * currRating.getValue());
    		}
    		if(weightedRating.isEmpty()) continue;
    		double sumOfWeightedAverages = 0;
    		for(double wr : weightedRating)
    			sumOfWeightedAverages+=wr;
    		
		//Math.round is applied to limit the space in output. Can be removed/altered as needed
    		ratingList.add(new Rating(currMovieId,Math.round(sumOfWeightedAverages/weightedRating.size())));
    	}
    	Collections.sort(ratingList, Collections.reverseOrder());
    	return ratingList;
    }
}
