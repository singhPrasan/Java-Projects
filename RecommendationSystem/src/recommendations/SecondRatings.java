package recommendations;
/**
 * Computes average ratings and corrosponding operations required by MovieRunnerAverage class.
 * A minimum number of ratings are necessary to consider movie for average ratings
 * 
 * @author Prasandeep Singh
 * @created 06/24/2017
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    private FirstRatings fr;

    // default constructor
    public SecondRatings() {
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    //Customized constructor
    public SecondRatings(String movieFile, String ratingFile){
    	fr = new FirstRatings();
    	myMovies = fr.loadMovies(movieFile);
    	myRaters = fr.loadRaters(ratingFile);
    }
    
    //Returns total number of movies present on the file
    public int getMovieSize(){
    	return myMovies.size();
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
    	ArrayList<String> moviesHavingMinRaters = getMoviesHavingMinRaters(minimalRaters);
    	if(moviesHavingMinRaters.size()==0)	return null;
    	for(String currMovieId : moviesHavingMinRaters){
    		double avgRatingForCurrMovie = getAverageById(currMovieId, minimalRaters);
    		ratingList.add(new Rating(currMovieId, avgRatingForCurrMovie));
    	}
    	return ratingList;
    }
    
    //Returns Movie Title for the specified movie id
    public String getTitle(String id){
    	for(Movie currMovie : myMovies){
    		String currMovieId = currMovie.getID();
    		if(currMovieId.equals(id))
    			return currMovie.getTitle();
    	}
    	return "Movie with the specified id does not exists!";
    }
    
    //Returns movie id for the specified movie title
    public String getId(String title){
    	for(Movie currMovie : myMovies){
    		String currMovieTitle = currMovie.getTitle();
    		if(currMovieTitle.equals(title))
    			return currMovie.getID();
    	}
    	return "NO SUCH TITLE!";
    }
    
    //Returns a list of all movies that have been rated by atleast minimalRaters
    private ArrayList<String> getMoviesHavingMinRaters(int minimalRaters){
    	ArrayList<String> moviesHavingMinRaters = new ArrayList<>();
    	for(Movie currMovie : myMovies){
    		String currMovieId = currMovie.getID();
    		int numRatersForCurrMovie = fr.numRatingsOfMovie(myRaters, currMovieId);
    		if(numRatersForCurrMovie>=minimalRaters)
    			moviesHavingMinRaters.add(currMovieId);
    	}
    	return moviesHavingMinRaters;
    }
}