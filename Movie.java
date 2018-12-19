
/**
 * This class is used to represent movies and store details about them. 
 * The movie's title, release year, director, writer, and up to 3 actors are stored.
 * This class also contains a list of SF locations in which the movie was produced (together with associated fun facts).
 * @author atara
 */
import java.util.ArrayList;

public class Movie implements Comparable<Movie> { //implements comparable interface, has a compareTo method
	
	 //instance variables
	 private String title; 
	 private int year; 
	 private String director; 
	 private String writer; 
	 private Actor  actor1; 
	 private Actor actor2; 
	 private Actor actor3; 
	 
	 //every constructor set the value of every instance 
	 ArrayList<Location> sfLocations;  //a list of SF locations in which the movie was produced AND fun fact
	 
	/**
	* Constructs a new Movie object with specified name value.
	* @param title title value to be used for this Movie
	* @param year release year for this movie
	* @throws IllegalArgumentException if parameters are invalid
	 */
	 public Movie(String title, int year) throws IllegalArgumentException {
		 setTitle(title);
		 setYear(year);
		 sfLocations = new ArrayList<>();
	}
	
	/**
	 * Constructs a new Movie object with specified name value.
	 * @param title title value to be used for this Movie
	 * @param year year value to be used for this Movie
	 * @param director director value to be used for this Movie
	 * @param writer writer value to be used for this Movie
	 * @param actor1 actor1 value to be used for this Movie
	 * @param actor2 actor2 value to be used for this Movie
	 * @param actor3 actor3 value to be used for this Movie
	 * @throws IllegalArgumentException if parameters are invalid
	 */
	public Movie(String title, int year, String director, String writer, Actor actor1, Actor actor2, Actor actor3) throws IllegalArgumentException {
		setTitle(title);
		setYear(year);
		setDirector(director);
		setWriter(writer);
		setActor1(actor1);
		setActor2(actor2);
		setActor3(actor3);
		 sfLocations = new ArrayList<>();
	}
	 
	 /**
	  * Returns the movie's title for this Movie object.
	  * @return the movie's title for this Movie object
	  */
	 public String getTitle() {
		return title;
	}
	 
	/**
	 * Validates and sets the movie's title. Title cannot be a non-empty string.
	 * @param title the movie's title to be examined and set
	 * @throws IllegalArgumentException if the title is invalid
	 */
	public void setTitle(String title) throws IllegalArgumentException {
		if (title == null || title.length() == 0) { //if the title passed in NOT a non-empty string 
			throw new IllegalArgumentException("Not a valid title.");
		}
		this.title = title;
	}
	
	/**
	 * Returns the release year for this Movie object.
	 * @return the release year for this Movie object
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Validates and sets the movie's release year. Must be between 1900 and 2020.
	 * @param year the release year for this Movie object, to be examined and set
	 * @throws IllegalArgumentException if year is invalid
	 */
	public void setYear(int year) throws IllegalArgumentException {
		if (year < 1900 || year > 2020) { //if the yr is NOT between 1900 and 2020
			throw new IllegalArgumentException("Not a valid year.");
		}
		this.year = year;
	}
	
	/**
	 * Returns the director for this Movie object.
	 * @return the director for this Movie object
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * Sets the director for this Movie object.
	 * @param director the director for this Movie object, to be set
	 */
	public void setDirector(String director) {
		this.director = director;
	}
	
	/**
	 * Returns the writer for this Movie object.
	 * @return the writer for this Movie object
	 */
	public String getWriter() {
		return writer;
	}
	
	/**
	 * Sets the writer for this Movie object.
	 * @param writer the writer for this Movie object, to be set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	/**
	 * Returns actor1 in this Movie object.
	 * @return actor1 in this Movie object
	 */
	public Actor getActor1() {
		return actor1;
	}
	
	/**
	 * Validates and sets actor1 for this Movie object. Must be an Actor object and cannot be equal to null.
	 * @param actor1 the actor1 for this Movie object, to be examined and set
	 * @throws IllegalArgumentException if actor1 is invalid
	 */
	public void setActor1(Actor actor1) throws IllegalArgumentException {
		//if NOT an Actor object or found equal to null
		if (actor1 == null) {
			throw new IllegalArgumentException("Not a valid actor.");
		}
		this.actor1 = actor1;
	}
	
	/**
	 * Returns actor2 in this Movie object.
	 * @return actor2 in this Movie object
	 */
	public Actor getActor2() {
		return actor2;
	}
	
	/**
	 * Validates and sets actor2 for this Movie object. Must be an Actor object or be equal to null.
	 * @param actor2 the actor2 for this Movie object, to be examined and set
	 * @throws IllegalArgumentException if actor2 is invalid
	 */
	//assume it is an actor 
	public void setActor2(Actor actor2) throws IllegalArgumentException {
		//if NOT an Actor object or NOT equal to null
		this.actor2 = actor2;
	}
	
	/**
	 * Returns actor3 in this Movie object.
	 * @return actor3 in this Movie object
	 */
	public Actor getActor3() {
		return actor3;
	}

	/**
	 * Validates and sets actor3 for this Movie object. Must be an Actor object or be equal to null.
	 * @param actor3 the actor3 for this Movie object, to be examined and set
	 * @throws IllegalArgumentException if actor3 is invalid
	 */
	public void setActor3(Actor actor3) {
		this.actor3 = actor3;
	}
	
	/**
	 * Return a list of Movie objects.
	 * @return a list of Movie objects
	 */
	public ArrayList<Location> getSfLocations() {
		return sfLocations;
	}
	
	/**
	 * Adds a given location to the list of locations for the current Movie object.
	 * @param loc location to be added
	 * @return void nothing returned
	 * @throws IllegalArgumentException it is called with null parameter. 
	 */
	public void addLocation(Location loc) throws IllegalArgumentException {
		if (loc == null) throw new IllegalArgumentException("Location can't be null.");
		sfLocations.add(loc);
	}
	
	/**
	 * Compares which movie was released first. If the two movies were released in the same year,
	 * then it compares the titles.
	*/
	@Override
	public int compareTo(Movie o) {
		if (year != o.year)
			return year - o.year;
		return title.toLowerCase().compareTo(o.title.toLowerCase());
	}
	
	/**
	 * @override
	 * This method checks if 2 movies are the same. 
	 * They are considered the same if they have the same year and same title (case insensitive).
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Movie))
			return false;
		Movie other = (Movie) obj; //cast
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equalsIgnoreCase(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	/**
	 * This class should override the toString method.
	 */
	public String toString() {
		String result = title+" ("+year+") \n"+
				"------------------------------\n"+
				"director:\t"+director+"\n"+
				"writer:\t"+writer+"\n"+
				"starring:\t"+actor1;
		if (actor2 != null) {
			result += ", "+actor2;
		}
		if (actor3 != null) {
			result += ", "+actor3;
		}
		result += "\nfilmed on location at:\n";
		for (int i = 0; i < sfLocations.size(); i++) {
			result += "\t"+sfLocations.get(i) +"\n"; 
		}
		return result;
	}
}
