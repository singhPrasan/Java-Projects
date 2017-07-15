/**
 * 
 * @author - Prasandeep Singh
 * @created -07/01/2017
 */

package recommendations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FourthRatings {

    //Returns average rating of the specified movie 
    //To be considered for an average rating, it must be rated by atleast minimalRaters
    public double getAverageById(String id, int minimalRaters){
    	FirstRatings fr = new FirstRatings();
    	int numRatersForGivenMovie = fr.numRatingsOfMovie(RaterDatabase.getRaters(), id);
    	double sumOfAllRatings = 0.0;
    	if(numRatersForGivenMovie>=minimalRaters){
    		for(Rater currRater : RaterDatabase.getRaters()){
    			double currRating = (currRater.hasRating(id))?currRater.getRating(id):0;
    			sumOfAllRatings += currRating;
    		}
    	}else
    		return 0.0;
    	return sumOfAllRatings/numRatersForGivenMovie;
    }
    
//    //Returns a list of all movies along with their average ratings
//    //To be considered for an average rating, it must be rated by atleast minimalRaters
//    public ArrayList<Rating> getAverageRatings(int minimalRaters){
//    	ArrayList<Rating> ratingList = new ArrayList<>();
//    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
//    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinRaters(movies, minimalRaters);
//    	if(moviesHavingMinRaters.size()==0)	return null;
//    	for(String currMovieId : moviesHavingMinRaters){
//    		double avgRatingForCurrMovie = getAverageById(currMovieId, minimalRaters);
//    		ratingList.add(new Rating(currMovieId, avgRatingForCurrMovie));
//    	}
//    	return ratingList;
//    }
//    
//	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
//		ArrayList<Rating> ratingList = new ArrayList<>();
//		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
//		ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinRaters(movies, minimalRaters);
//    	if(moviesHavingMinRaters.size()==0)	return null;
//    	for(String currMovieId : moviesHavingMinRaters){
//    		double avgRatingForCurrMovie = getAverageById(currMovieId, minimalRaters);
//    		ratingList.add(new Rating(currMovieId, avgRatingForCurrMovie));
//    	}
//		return ratingList;
//	}
//	

    private ArrayList<Rating> getMoviesSortedByWeightedAverages(ArrayList<String> moviesHavingMinRaters, ArrayList<Rating> similarRatings){
    	ArrayList<Rating> ratingList = new ArrayList<>();
    	for(String currMovieId : moviesHavingMinRaters){
    		ArrayList<Double> weightedRating = new ArrayList<>();
    		for(Rating currRating : similarRatings){
    			Rater currRater = RaterDatabase.getRater(currRating.getItem());
    			weightedRating.add(currRater.getRating(currMovieId) * currRating.getValue());
    		}
    		double sumOfWeightedAverages = 0;
    		for(double wr : weightedRating)
    			sumOfWeightedAverages+=wr;
    		ratingList.add(new Rating(currMovieId, sumOfWeightedAverages/weightedRating.size()));
    	}
    	Collections.sort(ratingList, Collections.reverseOrder());
    	return ratingList;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, int numSimilarRaters, int minimalRaters, Filter criteriaFilter){
    	
    	ArrayList<String> movies = MovieDatabase.filterBy(criteriaFilter);
    	ArrayList<Rating> similarRatings = getSimilarities(raterId);
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinRaters(movies,minimalRaters, similarRatings, numSimilarRaters);
    	ArrayList<Rating> ratingList = getMoviesSortedByWeightedAverages(moviesHavingMinRaters, similarRatings);
    	
    	return ratingList;
    }
    
    public ArrayList<Rating> getSimilarRatings(String raterId, int minimalRaters, int numSimilarRaters){
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	ArrayList<Rating> similarRatings = getSimilarities(raterId);
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinRaters(movies,minimalRaters, similarRatings, numSimilarRaters);
    	ArrayList<Rating> ratingList = getMoviesSortedByWeightedAverages(moviesHavingMinRaters, similarRatings);
    	return ratingList;
    }
    
	   //Returns a list of all movies that have been rated by atleast minimalRaters
    private ArrayList<String> getMoviesHavingMinRaters(ArrayList<String> movies, int minimalRaters, ArrayList<Rating> similarRaters, int numSimilarRaters){
    	ArrayList<String> moviesWithMinTopRaters = new ArrayList<>();
    	HashMap<String, Integer> movieRaterCount = new HashMap<>();
    	//for(Rating currRating : similarRaters){
    	for(int i = 0; i<numSimilarRaters; i++){
    		Rating currRating = similarRaters.get(i);
    		Rater currRater = RaterDatabase.getRater(currRating.getItem());
    		ArrayList<String> moviesByCurrRater = currRater.getItemsRated();
    		for(String currMovie : moviesByCurrRater){
    			if(movies.contains(currMovie)){
    				if(!movieRaterCount.containsKey(currMovie)){
    					movieRaterCount.put(currMovie, 1);
    				}else{
    					movieRaterCount.put(currMovie, movieRaterCount.get(currMovie) + 1);
    				}
    			}
    		}
    	}
    	
    	Set<String> movieSet = movieRaterCount.keySet();
    	for(String movieId : movieSet){
    		if(movieRaterCount.get(movieId) >= minimalRaters)
    			moviesWithMinTopRaters.add(movieId);
    	}
    	return moviesWithMinTopRaters;

    }
    
    private ArrayList<Rating> getSimilarities(String raterIdToBeMatchedWith){
    	ArrayList<Rating> ratingList = new ArrayList<>();
    	Rater me = RaterDatabase.getRater(raterIdToBeMatchedWith);
    	for(Rater r : RaterDatabase.getRaters()){
    		double similarityFactor = 0;
    		String currRaterId = r.getID();
    		if(!raterIdToBeMatchedWith.equals(currRaterId)){
    			similarityFactor = dotProduct(me, r);
    			if(similarityFactor >= 0 )
    				ratingList.add(new Rating(currRaterId, similarityFactor));
    		}
    	}
    	Collections.sort(ratingList, Collections.reverseOrder());
    	return ratingList;
    }
    
//    private double dotProduct(Rater me, Rater r){
//    	int dotP = 0;
//    
//    	ArrayList<String> moviesRatedByMe=  me.getItemsRated();
//    	ArrayList<String> moviesRatedByR = r.getItemsRated();
//    	ArrayList<Double> meRatingsVector = new ArrayList<>();
//    	ArrayList<Double> rRatingsVector = new ArrayList<>();
//
//    	int i = 0;
//    	int j = 0;
//    	while(i<moviesRatedByMe.size() && j<moviesRatedByR.size()){
//    		String meMovie = moviesRatedByMe.get(i);
//    		String rMovie = moviesRatedByR.get(j);
//    		if(meMovie.equals(rMovie)){
//    			meRatingsVector.add(me.getRating(meMovie)-5);
//    			rRatingsVector.add(r.getRating(rMovie)-5);
//    		}else{
//    			meRatingsVector.add(0.0);
//    			rRatingsVector.add(0.0);
//    		}
//    		i++;
//    		j++;
//    	}
//    	
//    	for(int k = 0; k<meRatingsVector.size();k++){
//    		dotP += meRatingsVector.get(k)*rRatingsVector.get(k);
//    	}
//    	return dotP;
////    }
        private double dotProduct(Rater me, Rater r){
        	int dotP = 0;
        
        	ArrayList<String> moviesRatedByMe=  me.getItemsRated();
        	ArrayList<String> moviesRatedByR = r.getItemsRated();
        	HashSet<String> setMe = new HashSet<>();
        	ArrayList<Double> meRatingsVector = new ArrayList<>();
        	ArrayList<Double> rRatingsVector = new ArrayList<>();
        	
        	for(String s : moviesRatedByMe){
        		setMe.add(s);
        	}
        	for(int i = 0; i<moviesRatedByR.size();i++){
        		String movieId = moviesRatedByR.get(i);
        		if(setMe.contains(movieId)){
        			meRatingsVector.add(me.getRating(movieId)-5);
        			rRatingsVector.add(r.getRating(movieId)-5);
        		}
        	}
        	
        	for(int k = 0; k<meRatingsVector.size();k++){
        		dotP += meRatingsVector.get(k)*rRatingsVector.get(k);
        	}
        	return dotP;
        }

}
