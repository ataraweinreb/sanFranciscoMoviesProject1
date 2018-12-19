
/** 
 * This class represents the actors.
 * @author atara
 */

public class Actor {
	
	private String name; //instance variable to store the actor's name
	
	/**
	 *  Returns the name of this Actor object. 
	 * @return the name of this Actor object
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Constructs a new Actor object with specified name value.
	 * @param n name value to be used for this Actor; can't be null or an empty string
	 * @throws IllegalArgumentException if n parameter is invalid
	 */
	public Actor(String n) {
		if (n == null || n.trim().length() == 0) //tests if it was passed a null or an empty string
			throw new IllegalArgumentException("Error: the actor's name cannot be null or empty.\n");
		name = n; // sets actors name to the specified value for n
	}
	
	/**
	 * Returns the string representation of this Actor.
	 * @returns the string representation of this Actor object 
	 */
	public String toString() {
		return name;
	}
}
