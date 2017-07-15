package recommendations;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
	//Prints all movies along with their average ratings in increasing order of ratings
	private void printAverageRatings(){
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = tr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		ArrayList<Rating> list = tr.getAverageRatings(35);
		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
		
		System.out.println("Number of movies that have more than specified ratings : "+list.size());
	}
	
	private void printAverageRatingsByYear(){
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = tr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		YearAfterFilter yearFilter = new YearAfterFilter(2000);
		ArrayList<Rating> list = tr.getAverageRatingsByFilter(20, yearFilter);
		System.out.println("Number of movies after the specified year : "+list.size());
		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"YEAR"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getYear(currRating.getItem())+"\t"+MovieDatabase.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
				
	}
	
	private void printAverageRatingsByGenre(){
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = tr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		GenreFilter genreFilter = new GenreFilter("Comedy");
		ArrayList<Rating> list = tr.getAverageRatingsByFilter(20, genreFilter);
		System.out.println("Number of movies of the specified genre : "+list.size());
//		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
//		System.out.println("RATING"+"\t"+"MOVIE TITLE"+"\t"+"GENRE");
//		for(Rating currRating : list){
//			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem())+"\t"+MovieDatabase.getGenres(currRating.getItem()));
//		}
//		System.out.println("------------------------------------------------------------------");
//				
	}
	
	private void printAverageRatingsByMinutes(){
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = tr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		MinutesFilter minutesFilter = new MinutesFilter(105, 135);
		ArrayList<Rating> list = tr.getAverageRatingsByFilter(5, minutesFilter);
		System.out.println("Number of movies of the specified duration : "+list.size());
		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"RUNNING TIME"+"\t"+"MOVIE TITLE");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getMinutes(currRating.getItem())+"\t\t"+MovieDatabase.getTitle(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
				
	}
	
	private void printAverageRatingsByDirectors(){
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = tr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		DirectorsFilter directorsFilter = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
		ArrayList<Rating> list = tr.getAverageRatingsByFilter(4, directorsFilter);
		System.out.println("Number of movies of the specified directors : "+list.size());
		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"MOVIE TITLE"+"\t"+"DIRECTOR(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getTitle(currRating.getItem())+"\t\t"+MovieDatabase.getDirector(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
				
	}
	
	private void printAverageRatingsByYearAfterAndGenre(){
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = tr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		AllFilters af = new AllFilters();
		af.addFilter(new YearAfterFilter(1990));
		af.addFilter(new GenreFilter("Drama"));
		ArrayList<Rating> list = tr.getAverageRatingsByFilter(8, af);
		System.out.println("Number of movies after the specified year and of the specified genre : "+list.size());
		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"YEAR"+"\t"+"MOVIE TITLE"+"\t"+"GENRE(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getYear(currRating.getItem())+"\t"+
		MovieDatabase.getTitle(currRating.getItem())+"\t\t"+MovieDatabase.getGenres(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
				
	}
	
	private void printAverageRatingsByDirectorsAndMinutes(){
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		MovieDatabase.initialize("ratedmoviesfull.csv");
		int numberOfMovies = MovieDatabase.size();
		int numberOfRaters = tr.getRaterSize();
		System.out.println("Total number of movies loaded :"+numberOfMovies);
		System.out.println("Total number of raters loaded :"+numberOfRaters);
		System.out.println("------------------------------------------------------------------");
		
		AllFilters af = new AllFilters();
		af.addFilter(new MinutesFilter(90, 180));
		af.addFilter(new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
		ArrayList<Rating> list = tr.getAverageRatingsByFilter(3, af);
		System.out.println("Number of movies of specified duration and directed by specified director(s) : "+list.size());
		Collections.sort(list);	//implements compareTo() method and sorts by increasing order of average ratings
		System.out.println("RATING"+"\t"+"TIME"+"\t"+"MOVIE TITLE"+"\t"+"DIRECTOR(s)");
		for(Rating currRating : list){
			System.out.println(currRating.getValue()+"\t"+MovieDatabase.getMinutes(currRating.getItem())+"\t"+
		MovieDatabase.getTitle(currRating.getItem())+"\t\t"+MovieDatabase.getDirector(currRating.getItem()));
		}
		System.out.println("------------------------------------------------------------------");
	
	}
	public static void main(String[] args) {
		MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
		mrwf.printAverageRatings();
		mrwf.printAverageRatingsByYear();
		mrwf.printAverageRatingsByGenre();
		mrwf.printAverageRatingsByMinutes();
		mrwf.printAverageRatingsByDirectors();
		mrwf.printAverageRatingsByYearAfterAndGenre();
		mrwf.printAverageRatingsByDirectorsAndMinutes();
	}
}
