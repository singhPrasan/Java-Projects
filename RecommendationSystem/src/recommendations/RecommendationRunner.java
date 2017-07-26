package recommendations;

import java.util.ArrayList;

public class RecommendationRunner implements Recommender{
	
	@Override
	public ArrayList<String> getItemsToRate(){
		ArrayList<String> movieList = MovieDatabase.filterBy(new YearAfterFilter(2015));
		ArrayList<String> moviesToDisplay = new ArrayList<>();
		if(movieList.size()>20){
			for(int i = 0; i<20; i++){
				String movieId = movieList.get(i);
				moviesToDisplay.add(movieId);
			}
		}else return movieList;
		return moviesToDisplay;
	}
	
	public void printRecommendationsFor(String webRaterID){
		loadDataBase();
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, 20, 5);
		System.out.println("<html><head></head><body>");
		System.out.println("<h1>Movie Recommendations</h1>");
		System.out.println("<style> table, th, td {border: 1px solid black; border-collapse: collapse;}</style>");
		System.out.println("<table>");
		System.out.println("<tr>");
		System.out.println("<th>Poster</th>");
		System.out.println("<th>Title</th>");
		System.out.println("<th>Genre</th>");
		System.out.println("<th>Year of Release</th>");
		System.out.println("<th>Runtime(mins)</th>");
		System.out.println("</tr>");
		for(int i = 0; i < 15; i++) {
			String item = ratings.get(i).getItem();
			System.out.println("<tr>");
			System.out.println("<td><img src='"+MovieDatabase.getPoster(item)+"'></td>");
			System.out.println("<td>"+MovieDatabase.getTitle(item)+"</td>");
			System.out.println("<td>"+MovieDatabase.getGenres(item)+"</td>");
			System.out.println("<td>"+MovieDatabase.getYear(item)+"</td>");
			System.out.println("<td>"+MovieDatabase.getMinutes(item)+"</td>");
			System.out.println("</tr>");
		}
		System.out.println("</table></body></html>");
	}
	
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
	
	public static void main(String[] args) {
		RecommendationRunner rr = new RecommendationRunner();
		rr.printRecommendationsFor("65");
	}
}
