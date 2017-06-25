/**
 * Does analytics on movies based on number of ratings and raters who provided ratings
 * 
 * @author Prasandeep Singh
 * @created 06/23/2017
 * @updated 06/24/2017
 */
package stepOne;
import java.util.*;

import org.apache.commons.csv.*;

import edu.duke.*;

public class FirstRatings {
	
	//Loads all the movies from the given CSV file into an arraylist of movie objects
	protected ArrayList<Movie> loadMovies(String fileName){
		ArrayList<Movie> movieList = new ArrayList<>();
		FileResource fr = new FileResource(fileName);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord row : parser){
				Movie currMovie = new Movie(row.get("id"), row.get("title"), row.get("year"),  row.get("genre"),
						row.get("director"), row.get("country"), row.get("poster"), Integer.parseInt(row.get("minutes")));
				movieList.add(currMovie);
		}
		return movieList;
	}
	
	//Loads all the unique raters in an arraylist of Rater objects
	//A particular rater can rate multiple movies, returns a list of all unique raters only
	protected ArrayList<Rater> loadRaters(String fileName){
		ArrayList<Rater> raterList = new ArrayList<>();
		HashMap<String, Rater> map = new HashMap<>();
		FileResource fr = new FileResource(fileName);
		CSVParser parser = fr.getCSVParser();
		for(CSVRecord row : parser){
				String currRaterId = row.get("rater_id");
				if(!map.containsKey(currRaterId))
					map.put(currRaterId, new Rater(currRaterId));
				Rater currRater = map.get(currRaterId);
				currRater.addRating(row.get("movie_id"), Double.parseDouble(row.get("rating")));
				if(!raterList.contains(currRater))
					raterList.add(currRater);
		}
		return raterList;
	}
	
	//Counts number of movies of the specified genre
	private int specificGenreCount(ArrayList<Movie> movieList, String genre){
		int genreCount = 0;
		for(Movie currMovie : movieList){
			if(currMovie.getGenres().contains(genre)){
				genreCount++;
			}
		}
		return genreCount;
	}
	
	//Counts number of movies greater than a specified length in minutes
	private int specificLengthCount(ArrayList<Movie> movieList, int minutes){
		int greaterThan150mins = 0;
		for(Movie currMovie : movieList){
			if(currMovie.getMinutes()>150)
				greaterThan150mins++;
		}
		return greaterThan150mins;
	}

	//Returns a mapping of directors with the number of movies directed by each director
	private HashMap<String, Integer> getUniqueDirectorsAndMovieCount(ArrayList<Movie> movieList){
		HashMap<String, Integer> map = new HashMap<>();
		for(Movie currMovie : movieList){
			String[] currMovieDirectors = currMovie.getDirector().split(",");
			for(String d : currMovieDirectors)
				if(!map.containsKey(d))
					map.put(d, 1);
				else
					map.put(d, map.get(d)+1);
		}
		return map;
	}
	
	//Returns maximum number of movies directed by any director
	private int maxMoviesDirected(ArrayList<Movie> movieList){
		int maxMoviesDirected = 0;
		HashMap<String, Integer> map = getUniqueDirectorsAndMovieCount(movieList);
		Set<String> directorSet = map.keySet();
		for(String currDirector : directorSet){
			int moviesDirected = map.get(currDirector);
			maxMoviesDirected = Math.max(maxMoviesDirected, moviesDirected);
		}
		return maxMoviesDirected;
	}
	
	//Returns a list of all directors than have directed maximum number of movies
	private ArrayList<String> directorsWithMaxMovieDirections(ArrayList<Movie> movieList, int max){
		HashMap<String, Integer> map = getUniqueDirectorsAndMovieCount(movieList);
		ArrayList<String> directorList = new ArrayList<>();
		Set<String> directorSet = map.keySet();
		for(String currDirector : directorSet){
			if(map.get(currDirector)==max)
				directorList.add(currDirector);
		}
		return directorList;
	}
	
	//To test and perform analytics on movies 
	private void testLoadMovies(){
		String inputFile = "data/ratedmoviesfull.csv";
		ArrayList<Movie> movieList = loadMovies(inputFile);
		
		//Total number of movies in the file
		System.out.println("Total number of movies :"+movieList.size());
		
		//Display all the movies
//		for(Movie currMovie : movieList){
//			System.out.println(currMovie);
//		}
		
		int genreCount = specificGenreCount(movieList, "Comedy");
		System.out.println("Number of comedy movies on the file :"+genreCount);
		
		int greaterMinCount = specificLengthCount(movieList, 150);
		System.out.println("Number of movies of length greater than 150 minutes :"+greaterMinCount);
		
		int maxMoviesDirectedByAnyDirector = maxMoviesDirected(movieList);
		System.out.println("Maximum number of movies directed by any director :"+maxMoviesDirectedByAnyDirector);
		
		ArrayList<String> directorList = directorsWithMaxMovieDirections(movieList, maxMoviesDirectedByAnyDirector);
		System.out.println("List of directors who directed maximum number of movies :");
		for(String  d : directorList)
			System.out.println(d);
	}
	
	//Returns number of unique movies rated by a given rater (identified by his/her unique rater id)
	private int moviesRated(ArrayList<Rater> raterList, String rater_ID){
		int moviesRateCount = 0;
		for(Rater currRater : raterList){
			if(currRater.getID().equals(rater_ID))
				moviesRateCount = currRater.numRatings();
		}
		return moviesRateCount;
	}
	
	//Returns a mapping of all raters with a count of movies rated by them
	private HashMap<String, Integer> getRaterIdWithRatings(ArrayList<Rater> raterList){
		HashMap<String, Integer> map = new HashMap<>();
		for(Rater currRater : raterList){
			String currRaterId = currRater.getID();
			if(!map.containsKey(currRaterId))
				map.put(currRaterId, currRater.numRatings());
		}
		return map;
	}
	
	//Returns maximum number of ratings given by any rater
	private int maxRatingsGiven(ArrayList<Rater> raterList){
		int maxRatingsGiven = 0;
		HashMap<String, Integer> map = getRaterIdWithRatings(raterList);
		Set<String> raterSet = map.keySet();
		for(String currRaterId : raterSet){
			int ratingsGiven = map.get(currRaterId);
			maxRatingsGiven = Math.max(maxRatingsGiven, ratingsGiven);
		}
		return maxRatingsGiven;
	}
	
	//Returns a list of all raters which provided maximum number of ratings
	private ArrayList<String> ratersWhoGaveMaxNumberIfRatings(ArrayList<Rater> raterList, int max){
		HashMap<String, Integer> map = getRaterIdWithRatings(raterList);
		ArrayList<String> raterIdList = new ArrayList<>();
		Set<String> raterSet = map.keySet();
		for(String currRaterId : raterSet){
			if(map.get(currRaterId)==max)
				raterIdList.add(currRaterId);
		}
		return raterIdList;
	}
	
	//Returns number of raters who rated a particular movie
	public int numRatingsOfMovie(ArrayList<Rater> raterList, String movieId){
		int ratingCount = 0;
		for(Rater currRater : raterList){
			if(currRater.hasRating(movieId))
				ratingCount ++;
		}
		return ratingCount;
	}
	
	//Returns total number of unique movies rated by all the raters
	private int uniqueMoviesRated(ArrayList<Rater> raterList){
		ArrayList<String> uniqueMovieList = new ArrayList<>();
		for(Rater currRater : raterList){
			ArrayList<String> moviesRatedByCurrRater = currRater.getItemsRated();
			for(String movie : moviesRatedByCurrRater)
				if(!uniqueMovieList.contains(movie))
					uniqueMovieList.add(movie);
		}
		return uniqueMovieList.size();
	}
	
	//To test and perform analytics on raters and ratings
	private void testLoadRaters(){
		String inputFile = "data/ratings.csv";
		ArrayList<Rater> raterList = loadRaters(inputFile);
		
		//Total number of movies in the file
		System.out.println("Total number of raters :"+raterList.size());
		
		//Display all the raters
//		for(Rater currRater : raterList){
//			System.out.println("raterID :"+currRater.getID()+"\t\t"+"Total ratings :"+currRater.numRatings());
//			ArrayList<String> moviesRated = currRater.getItemsRated(); 
//			for(String movie : moviesRated){
//				System.out.println("movieID :"+movie+"\t"+"rating :"+currRater.getRating(movie));
//			}
//			System.out.println("-------------------------------------------------------");
//		}
		
		int moviesRateCount = moviesRated(raterList, "193");
		System.out.println("Number of movies rated by the specified user :"+moviesRateCount);
		
		int maxRatingsGiven = maxRatingsGiven(raterList);
		System.out.println("Maximum number of ratings given by any rater :"+maxRatingsGiven);
		
		ArrayList<String> raterIdList = ratersWhoGaveMaxNumberIfRatings(raterList, maxRatingsGiven);
		System.out.println("Number of raters who provided most number of ratings :"+raterIdList.size());
		System.out.println("List of rater id's who provided maximum number of ratings :");
		for(String  r : raterIdList)
			System.out.println("rater_id : "+r);	
		
		int ratingsForAGivenMovie = numRatingsOfMovie(raterList, "1798709");
		System.out.println("Number of raters who provided rating for the specified movie :"+ratingsForAGivenMovie);
		
		int uniqueMoviesRated = uniqueMoviesRated(raterList);
		System.out.println("Number of unique movies rated by all the raters :"+uniqueMoviesRated);
	}
	
	
	
	//Test Client
	public static void main(String[] args) {
		FirstRatings fr = new FirstRatings();
		fr.testLoadMovies();
		fr.testLoadRaters();
	}
}
