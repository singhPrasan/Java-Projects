package recommendations;

import java.util.ArrayList;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    private FirstRatings fr;

    // default constructor
    public ThirdRatings() {
        this("data/ratings.csv");
    }
    
    //Customized constructor
    public ThirdRatings(String ratingFile){
    	fr = new FirstRatings();
    	myRaters = fr.loadRaters("data/"+ratingFile);
    }
    
    //Returns total number of unique raters on the file
    public int getRaterSize(){
    	return myRaters.size();
    }
    
    //Returns average rating of the specified movie 
    //To be considered for an average rating, it must be rated by atleast minimalRaters
    public double getAverageById(String id, int minimalRaters){
    	int numRatersForGivenMovie = fr.numRatingsOfMovie(myRaters, id);
    	double sumOfAllRatings = 0.0;
    	if(numRatersForGivenMovie>=minimalRaters){
    		for(Rater currRater : myRaters){
    			double currRating = (currRater.hasRating(id))?currRater.getRating(id):0;
    			sumOfAllRatings += currRating;
    		}
    	}else
    		return 0.0;
    	return sumOfAllRatings/numRatersForGivenMovie;
    }
    
    //Returns a list of all movies along with their average ratings
    //To be considered for an average rating, it must be rated by atleast minimalRaters
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    	ArrayList<Rating> ratingList = new ArrayList<>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinRaters(movies, minimalRaters);
    	if(moviesHavingMinRaters.size()==0)	return null;
    	
    	for(String currMovieId : moviesHavingMinRaters){
    		double avgRatingForCurrMovie = getAverageById(currMovieId, minimalRaters);
    		ratingList.add(new Rating(currMovieId, avgRatingForCurrMovie));
    	}
    	return ratingList;
    }
    
	public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
		ArrayList<Rating> ratingList = new ArrayList<>();
		ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
		ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinRaters(movies, minimalRaters);
    	if(moviesHavingMinRaters.size()==0)	return null;
    	for(String currMovieId : moviesHavingMinRaters){
    		double avgRatingForCurrMovie = getAverageById(currMovieId, minimalRaters);
    		ratingList.add(new Rating(currMovieId, avgRatingForCurrMovie));
    	}
		return ratingList;
	}
	
    //Returns a list of all movies that have been rated by atleast minimalRaters
    private ArrayList<String> getMoviesHavingMinRaters(ArrayList<String> movies, int minimalRaters){
    	ArrayList<String> moviesHavingMinRaters = new ArrayList<>();
    	for(String currMovieId : movies){
    		int numRatersForCurrMovie = fr.numRatingsOfMovie(myRaters, currMovieId);
    		if(numRatersForCurrMovie>=minimalRaters)
    			moviesHavingMinRaters.add(currMovieId);
    	}
    	return moviesHavingMinRaters;
    }
}
