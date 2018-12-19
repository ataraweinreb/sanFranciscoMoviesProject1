
/**
 * This class inherits from ArrayList and is used to store all the Movie objects.
 * @author atara
 */

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class MovieList extends ArrayList<Movie> {

	//constructor
	/**
	 * Constructs a new movielist by calling the constructor of it's superclass, ArrayList
	 */
	public MovieList() {
		super();
	}
	
	/**
	 * Adds new movie to the list. Void return.
	 * @param m the movie to be added
	 */
	public void addMovie(Movie m) {
		for (int i = 0; i < size(); i++) {
			if (get(i).equals(m)) { //the movie exists
				get(i).addLocation(m.getSfLocations().get(0)); //so just add the location
				return; //add location and stop
			}
		}
		add(m); //no match exists so add as a new movie
	}
	/**
	 * This method should return a list of Movie objects whose titles contain the specified keyword as a substring.
	 * @param keyword the specified keyword to search for
	 * @return MovieList the type of list to be returned
	 */
	public MovieList getMatchingTitles ( String keyword ) {
		MovieList matchingTitles = new MovieList();
		if (keyword == null || keyword.trim().length() == 0) return null;
		for (int i = 0; i < size(); i++) {
			if (get(i).getTitle().toLowerCase().contains(keyword.toLowerCase())) {
				matchingTitles.add(this.get(i));
			}
		}
		Collections.sort(matchingTitles); //sort them
		if (matchingTitles.size() == 0) {
			return null;
		}
		return matchingTitles;
	}
	
	/**
	 * This method should return a list of Movie object whose actors names contain the keyword as a substring.
	 * @param keyword the specified keyword to search for
	 * @return MovieList the type of list to be returned
	 */
	public MovieList getMatchingActor ( String keyword ) {
		MovieList matchingActors = new MovieList();
		if (keyword == null || keyword.trim().length() == 0) return null;
		for (int i = 0; i < size(); i++) {
			if (get(i).getActor1().getName().toLowerCase().contains(keyword.toLowerCase())) {
				matchingActors.add(this.get(i));
			}
			else {
				Actor actor2 = get(i).getActor2();
				if (actor2 != null && actor2.getName().toLowerCase().contains(keyword.toLowerCase())) {
					matchingActors.add(this.get(i));
				}
				
				else {
					Actor actor3 = get(i).getActor3();
					if (actor3 != null && actor3.getName().toLowerCase().contains(keyword.toLowerCase())) {
						matchingActors.add(this.get(i));
					}
				}
			}
		}
		Collections.sort(matchingActors); //sort them
		if (matchingActors.size() == 0) {
			return null;
		}
		return matchingActors;
	}
	
	/**
	 * This method overrides toString. 
	 * Instead, it returns a String containing a semi-colon and space separated list of the titles of the Movie objects stored in the list
	 * @return String representation of the MovieList
	 */
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < size(); i++) {
			result += get(i).getTitle() + "; ";
		}
		if (result.endsWith("; "))
			result = result.substring(0, result.length()-2);
		return result;
	}
	
	/**
	 * Additional method to override toString
	 * @return the string format of the MovieList
	 */
	public String toCompleteString() {
		String result = "";
		for (int i = 0; i < size(); i++) {
			result += get(i)+"\n";
		}
		return result;
	}
}


