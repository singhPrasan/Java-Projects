package objectOrientedDesign;

public class DirectorsFilter implements Filter{
	String myDirectors;
	
	public DirectorsFilter(String directors){
		myDirectors = directors;
	}

	@Override
	public boolean satisfies(String id) {
		String[] allDirectors = myDirectors.split(",");
		for(String currDirector : allDirectors){
			if(MovieDatabase.getDirector(id).contains(currDirector))
				return true;
		}
		return false;
	}
}
