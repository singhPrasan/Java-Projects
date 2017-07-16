package recommendations;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
	//Prints all movies along with their average ratings in increasing order of ratings
//	private void printAverageRatings(){
//		RaterDatabase.initialize("ratings.csv");
//		MovieDatabase.initialize("ratedmoviesfull.csv");
//		int numberOfMovies = MovieDatabase.size();
//		int numberOfRaters = RaterDatabase.size();
//		System.out.println("Total number of movies loaded :"+numberOfMovies);
//		System.out.println("Total number of raters loaded :"+numberOfRaters);
//		System.out.println("------------------------------------------------------------------");
//		
//		FourthRatings fr = new FourthRatings();
//		ArrayList<Rating> list = fr.getAverageRatings(35);
//		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
//		System.out.println("RATING"+"\t"+"MOVIE TITLE");
//		for(Rating currRating : list){
//			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem()));
//		}
//		System.out.println("------------------------------------------------------------------");
//		
//		System.out.println("Number of movies that have more than specified ratings : "+list.size());
//	}
//
//	private void printAverageRatingsByYearAfterAndGenre(){
//		RaterDatabase.initialize("ratings.csv");
//		MovieDatabase.initialize("ratedmoviesfull.csv");
//		int numberOfMovies = MovieDatabase.size();
//		int numberOfRaters = RaterDatabase.size();
//		System.out.println("Total number of movies loaded :"+numberOfMovies);
//		System.out.println("Total number of raters loaded :"+numberOfRaters);
//		System.out.println("------------------------------------------------------------------");
//		
//		FourthRatings fr = new FourthRatings();
//		AllFilters af = new AllFilters();
//		af.addFilter(new YearAfterFilter(1990));
//		af.addFilter(new GenreFilter("Drama"));
//		ArrayList<Rating> list = fr.getAverageRatingsByFilter(8, af);
//		System.out.println("Number of movies after the specified year and of the specified genre : "+list.size());
//		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
//		System.out.println("RATING"+"\t"+"YEAR"+"\t"+"MOVIE TITLE"+"\t"+"GENRE(s)");
//		for(Rating currRating : list){
//			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getYear(currRating.getItem())+"\t"+
//		MovieDatabase.getTitle(currRating.getItem())+"\t\t"+MovieDatabase.getGenres(currRating.getItem()));
//		}
//		System.out.println("------------------------------------------------------------------");
//				
//	}
	
	private void printSimilarRatings(){
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = RaterDatabase.size();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatings("71", 20, 5);
	//	Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
		
	//System.out.println("Number of movies that have more than specified ratings : "+list.size());
	}
	
	private void printSimilarRatingsByGenre(){
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = RaterDatabase.size();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		
		GenreFilter genreFilter = new GenreFilter("Mystery");
		
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("964", 20, 5, genreFilter);
		System.out.println("Number of movies of the specified genre : "+list.size());
		
	//	Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"MOVIE TITLE"+"\t\t"+"GENRE(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem())+"\t\t"+
		MovieDatabase.getGenres(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
		
	//System.out.println("Number of movies that have more than specified ratings : "+list.size());
	}
	
	private void printSimilarRatingsByDirector(){
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = RaterDatabase.size();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		
		DirectorsFilter DirectorFilter = new DirectorsFilter(
				"Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("120", 10, 2, DirectorFilter);
		System.out.println("Number of movies of the specified genre : "+list.size());
		
	//	Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"MOVIE TITLE"+"\t\t"+"DIRECTOR(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem())
					+"\t\t"+MovieDatabase.getDirector(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
		
	//System.out.println("Number of movies that have more than specified ratings : "+list.size());
	}
	
	
	private void printSimilarRatingsByGenreAndMinutes(){
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = RaterDatabase.size();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		
		AllFilters af = new AllFilters();
		af.addFilter(new MinutesFilter(80, 160));
		af.addFilter(new GenreFilter("Drama"));
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("168", 10, 3, af);
		System.out.println("Number of movies of the specified genre : "+list.size());
		
	//	Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"MINUTES"+"\t"+"MOVIE TITLE"+"\t\t"+"GENRE(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getMinutes(currRating.getItem())
					+"\t"+MovieDatabase.getTitle(currRating.getItem())
							+"\t\t"+MovieDatabase.getGenres(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
		
	//System.out.println("Number of movies that have more than specified ratings : "+list.size());
	}
	
	
	private void printSimilarRatingsByYearAfterAndMinutes(){
		RaterDatabase.initialize("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = RaterDatabase.size();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		
		AllFilters af = new AllFilters();
		af.addFilter(new YearAfterFilter(1975));
		af.addFilter(new MinutesFilter(70, 200));
		
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatingsByFilter("314", 10, 5, af);
		System.out.println("Number of movies of the specified genre : "+list.size());
		
	//	Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"YEAR"+"\t"+"MINUTES"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getYear(currRating.getItem())+"\t"+
		MovieDatabase.getMinutes(currRating.getItem())+"\t"+MovieDatabase.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
		
	//System.out.println("Number of movies that have more than specified ratings : "+list.size());
	}
	
	
	
	
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
