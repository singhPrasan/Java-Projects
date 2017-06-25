/**
 * Perfroms analytics on movies in accordance to their average ratings 
 * A minimum number of ratings are necessary to consider movie for average ratings
 * 
 * @author Prasandeep Singh
 * @created 06/24/2017
 */

package stepOne;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {
	
	//Prints all movies along with their average ratings in increasing order of ratings
	private void printAverageRatings(){
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
		int numberOfMovies = sr.getMovieSize();
		int numberOfRaters = sr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		ArrayList<Rating> list = sr.getAverageRatings(12);
		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+sr.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
		
		System.out.println("Number of movies that have more than 50 ratings : "+list.size());
	}
	
	//Prints average rating for the specified movie
	private void getAverageRatingOneMovie(){
		SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv","data/ratings.csv");
		String inputMovieTitle = "Vacation";
		String movieId = sr.getId(inputMovieTitle);
		double averageRating = sr.getAverageById(movieId, 0);
		System.out.println("Average Rating for the movie ["+inputMovieTitle+"] is :"+averageRating);
	}
	
	//Test client
	public static void main(String[] args) {
		MovieRunnerAverage mra = new MovieRunnerAverage();
		mra.printAverageRatings();
		mra.getAverageRatingOneMovie();
	}
}
