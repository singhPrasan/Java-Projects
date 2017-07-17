/**
 * This files provides an entry point for the recommendation system
 * Contains the main function and other functions to provide movie recommendations to a specified user 
 * depending on his/her movie ratings 
 * 
 * @author    -  Prasandeep Singh
 * @created   -  07/01/2017
 * @updated   -  07/17/2017
 */


package recommendations;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	
	//Loads static RaterDatabase and MovieDatabase classes with data from the excel files	
	private void loadDataBase(){
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = RaterDatabase.size();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
	}
	
	//Prints a list of all recommended movies for the specified user(raterId) 
	private void printSimilarRatings(){
		loadDatabase();
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatings("71", 20, 5);
 		System.out.println("RATING"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
	}

	//Prints a list of all recommended movies of a given list of for the specified user(raterId) 
	private void printSimilarRatingsByGenre(){
		loadDatabase();	
		GenreFilter genreFilter = new GenreFilter("Mystery");
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("964", 20, 5, genreFilter);
		System.out.println("Number of movies of the specified genre : "+list.size());
		System.out.println("RATING"+"\t"+"MOVIE TITLE"+"\t\t"+"GENRE(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem())+"\t\t"+
		MovieDatabase.getGenres(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
	}
	
	//Prints a list of all recommended movies directed by a given list of directors for the specified user(raterId) 
	private void printSimilarRatingsByDirector(){
			loadDatabase();
		DirectorsFilter DirectorFilter = new DirectorsFilter(
				"Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("120", 10, 2, DirectorFilter);
		System.out.println("Number of movies of the specified genre : "+list.size());
		System.out.println("RATING"+"\t"+"MOVIE TITLE"+"\t\t"+"DIRECTOR(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem())
					+"\t\t"+MovieDatabase.getDirector(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
	}
	
	//Prints a list of all recommended movies that are of a given genre and duration for the specified user(raterId) 
	private void printSimilarRatingsByGenreAndMinutes(){
	
			loadDatabase();
		AllFilters af = new AllFilters();
		af.addFilter(new MinutesFilter(80, 160));
		af.addFilter(new GenreFilter("Drama"));
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("168", 10, 3, af);
		System.out.println("Number of movies of the specified genre : "+list.size());
		System.out.println("RATING"+"\t"+"MINUTES"+"\t"+"MOVIE TITLE"+"\t\t"+"GENRE(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getMinutes(currRating.getItem())
					+"\t"+MovieDatabase.getTitle(currRating.getItem())
							+"\t\t"+MovieDatabase.getGenres(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
	}
	
	//Prints a list of all recommended movies that released after a given year and are of specified duration for the specified user(raterId)  
	private void printSimilarRatingsByYearAfterAndMinutes(){
			loadDatabase();
		AllFilters af = new AllFilters();
		af.addFilter(new YearAfterFilter(1975));
		af.addFilter(new MinutesFilter(70, 200));
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("314", 10, 5, af);
		System.out.println("Number of movies of the specified genre : "+list.size());
		System.out.println("RATING"+"\t"+"YEAR"+"\t"+"MINUTES"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getYear(currRating.getItem())+"\t"+
		MovieDatabase.getMinutes(currRating.getItem())+"\t"+MovieDatabase.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
	}
	
	
	
	//Entry point for the recommendation engine
	public static void main(String[] args) {
		MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
	//	mrsr.printAverageRatings();
	//	mrsr.printAverageRatingsByYearAfterAndGenre();
	//	mrsr.printSimilarRatings();
	//	mrsr.printSimilarRatingsByGenre();
	//	mrsr.printSimilarRatingsByDirector();
	//	mrsr.printSimilarRatingsByGenreAndMinutes();
		mrsr.printSimilarRatingsByYearAfterAndMinutes();
		
	}
}
