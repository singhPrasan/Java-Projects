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
//
    private ArrayList<Rating> getMoviesSortedByWeightedAverages(ArrayList<String> moviesHavingMinRaters, ArrayList<Rating> similarRatings,
    		 int numSimilarRaters){
    	ArrayList<Rating> ratingList = new ArrayList<>();
    	for(String currMovieId : moviesHavingMinRaters){
    		ArrayList<Double> weightedRating = new ArrayList<>();
    		//for(Rating currRating : similarRatings){
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
    		
    		ratingList.add(new Rating(currMovieId,Math.round(sumOfWeightedAverages/weightedRating.size())));
    	}
    	Collections.sort(ratingList, Collections.reverseOrder());
    	return ratingList;
    }
//    
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterId, int numSimilarRaters, int minimalRaters, Filter criteriaFilter){
    	
    	ArrayList<String> movies = MovieDatabase.filterBy(criteriaFilter);
    	ArrayList<Rating> similarRatings = getSimilarities(raterId);
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinR(movies,minimalRaters, similarRatings, numSimilarRaters);
    	ArrayList<Rating> ratingList = getMoviesSortedByWeightedAverages(moviesHavingMinRaters, similarRatings, numSimilarRaters);
    	
    	return ratingList;
    }
//    
    public ArrayList<Rating> getSimilarRatings(String raterId,  int numSimilarRaters, int minimalRaters){
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	ArrayList<Rating> similarRatings = getSimilarities(raterId);
 //   	System.out.println(similarRatings.size());	
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinR(movies,minimalRaters, similarRatings, numSimilarRaters);
    	ArrayList<Rating> ratingList = getMoviesSortedByWeightedAverages(moviesHavingMinRaters, similarRatings, numSimilarRaters);
    	return ratingList;
    }
//    
//	   //Returns a list of all movies that have been rated by atleast minimalRaters
//    private ArrayList<String> getMoviesHavingMinRaters(ArrayList<String> movies, int minimalRaters, ArrayList<Rating> similarRatings){
//    	ArrayList<String> moviesWithMinTopRaters = new ArrayList<>();
//    	HashMap<String, Integer> movieRaterCount = new HashMap<>();
//    	for(Rating currRating : similarRatings){
//    	//for(int i = 0; i<numSimilarRaters; i++){
//    	//	Rating currRating = similarRaters.get(i);
//    		Rater currRater = RaterDatabase.getRater(currRating.getItem());
//    		ArrayList<String> moviesByCurrRater = currRater.getItemsRated();
//    		for(String currMovie : moviesByCurrRater){
//    			if(movies.contains(currMovie)){
//    				if(!movieRaterCount.containsKey(currMovie)){
//    					movieRaterCount.put(currMovie, 1);
//    				}else{
//    					movieRaterCount.put(currMovie, movieRaterCount.get(currMovie) + 1);
//    				}
//    			}
//    		}
//    	}
//    	
//    	Set<String> movieSet = movieRaterCount.keySet();
//    	for(String movieId : movieSet){
//    		if(movieRaterCount.get(movieId) >= minimalRaters)
//    			moviesWithMinTopRaters.add(movieId);
//    	}
//    	return moviesWithMinTopRaters;
//
//    }
//    
    private ArrayList<Rating> getSimilarities(String raterIdToBeMatchedWith){
    	ArrayList<Rating> ratingList = new ArrayList<>();
    	Rater me = RaterDatabase.getRater(raterIdToBeMatchedWith);
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
    

        private double dotProduct(Rater me, Rater r, HashSet<String> setMe){
        	int dotP = 0;
        
        	ArrayList<String> moviesRatedByR = r.getItemsRated();
        	ArrayList<Double> meRatingsVector = new ArrayList<>();
        	ArrayList<Double> rRatingsVector = new ArrayList<>();
        	
        	
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
        
 	   //Returns a list of all movies that have been rated by atleast minimalRaters
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
//      //Returns number of raters who rated a particular movie
    	public int numRatingsOfMovie(ArrayList<Rating> similarRatings, String movieId, int numSimilarRaters){
    		int ratingCount = 0;
    		//for(Rating currRating : similarRatings){
    		for(int i = 0; i<numSimilarRaters; i++){
    	         Rating currRating = similarRatings.get(i);
    			Rater currRater = RaterDatabase.getRater(currRating.getItem());
    			if(currRater.hasRating(movieId))
    				ratingCount++;
    		}
    		return ratingCount;
    	}
//        public ArrayList<Rating> getSimilarRatings(String raterId, int numSimilarRaters, int minimalRaters){
//            ArrayList<Rating> result = new ArrayList<Rating>();
//            ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
//            ArrayList<Rating> similarRatings = getSimilarities(raterId);
//            
//            for(String movieId : movies){
//              double weightedAverage = 0;
//                double sum = 0;
//                int countRaters = 0;
//                
//                for (int i = 0; i < numSimilarRaters; i++) {
//                  Rating r = similarRatings.get(i);
//                  String currRaterId = r.getItem();
//                  double weight = r.getValue();
//                  
//                  //get all the movie rated by the rater
//                  Rater myRater = RaterDatabase.getRater(currRaterId);
//                if(myRater.hasRating(movieId)) {
//                  countRaters++;
//                  sum += weight * myRater.getRating(movieId);
//                }
//                }
//                
//                if(countRaters >= minimalRaters){
//                  weightedAverage = sum / countRaters;
//                  result.add(new Rating(movieId,weightedAverage));
//                }
//            }
//            Collections.sort(result, Collections.reverseOrder());
//            return result;
//            
//        }

}
