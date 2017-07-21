

import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender{
	
	@Override
	public ArrayList<String> getItemsToRate(){
		ArrayList<String> movieList = MovieDatabase.filterBy(new TrueFilter());
		ArrayList<String> moviesToDisplay = new ArrayList<>();
		Random ran = new Random();
		if(movieList.size()>20){
			for(int i = 0; i<20; i++){
				String movieId = movieList.get(ran.nextInt(3000));
				moviesToDisplay.add(movieId);
			}
		}else return movieList;
		return moviesToDisplay;
	}
	
	public void printRecommendationsFor(String webRaterID){
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> ratings = fr.getSimilarRatings(webRaterID, 20, 5);
		if(ratings==null || ratings.size()<1){
			System.out.println("<h2>Not enough movies rated to generate recommendations. Please rate more movies<h2>");
			return;
		}
		System.out.println("<h1>Movie Recommendations</h1>");
		System.out.println("<style> table, th, td {border: 1px solid black; border-collapse: collapse; text-align : center; margin-left : 20%;} img{ height : 100px; width : 100px} body{text-align : center;}</style>");
		System.out.println("<table>");
		System.out.println("<tr>");
		System.out.println("<th>Poster</th>");
		System.out.println("<th>Title</th>");
		System.out.println("<th>Director(s)</th>");
		System.out.println("<th>Year of Release</th>");
		System.out.println("<th>Runtime(mins)</th>");
		System.out.println("</tr>");
		int len = ratings.size() > 15 ? 15 : ratings.size();
		for(int i = 0; i < len; i++) {
			String item = ratings.get(i).getItem();
			System.out.println("<tr>");
			System.out.println("<td><img src='"+MovieDatabase.getPoster(item)+"'></td>");
			System.out.println("<td>"+MovieDatabase.getTitle(item)+"</td>");
			System.out.println("<td>"+MovieDatabase.getGenres(item)+"</td>");
			System.out.println("<td>"+MovieDatabase.getYear(item)+"</td>");
			System.out.println("<td>"+MovieDatabase.getMinutes(item)+"</td>");
			System.out.println("</tr>");
		}
		System.out.println("</table>");
	}
	
//	public static void main(String[] args) {
//		RecommendationRunner rr = new RecommendationRunner();
//		rr.printRecommendationsFor("65");
//	}
}
