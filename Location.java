
/**
 * This class is used to represent the locations and fun facts that may be associated with them.
 * @author atara
 *
 */
public class Location {

	//instance variables
	private String locationName;
	private String funFact = null;
	
	/**
	 * Returns the location name representing this Location object.
	 * @return the location name representing this Location object
	 */
	public String getLocationName() {
		return locationName;
	}
	
	/**
	 * Validates and sets the location name representing this Location object.
	 * @param locationName the location name representing this Location object; can't be null or empty
	 * @throws IllegalArgumentException when the location name passed in is invalid
	 */
	public void setLocationName(String locationName) throws IllegalArgumentException {
		if(locationName == null || locationName.trim().length() == 0)
			throw new IllegalArgumentException("Error: location cannot be null or empty.\n");
		this.locationName = locationName;
	}
	
	/**
	 * Returns a fun fact about this Location object.
	 * @return a fun fact about this Location object
	 */
	public String getFunFact() {
		return funFact;
	}
	
	/**
	 * Sets a fun fact to this Location object.
	 * @param funFact  a fun fact about this Location object; it is allowed to be null or empty if no fun fact
	 */
	public void setFunFact(String funFact) {
		this.funFact = funFact;
	}
	
	/**
	 * Constructs a new Location object with specified location.  
	 * @param ln location component of this Location object; should not be null or empty  
	 * @throws IllegalArgumentException  if ln parameters are invalid 
	 */
	public Location(String ln) throws IllegalArgumentException {
		if(ln == null || ln.trim().length() == 0)
			throw new IllegalArgumentException("Error: location cannot be null or empty.\n");
		locationName = ln;
	}
	
	/**
	 * Constructs a new Location object with specified location and specified fun fact.
	 * @param ln location component of this Location object; should not be null or empty
	 * @param ff fun fact about this Location object; it is allowed to be null or empty if no fun fact
	 * @throws IllegalArgumentException
	 */
	public Location(String ln, String ff) throws IllegalArgumentException {
		if(ln == null || ln.trim().length() == 0) {
			System.out.println("location "+ln);
			throw new IllegalArgumentException("Error: location cannot be null or empty.\n");
		}
		locationName = ln;
		funFact = ff;
	}
	
	/**
	 * Returns the string representation of this location.
	 * @returns the string representation of this location object 
	 */
	public String toString () {
		String result = locationName;
		if (funFact != null && funFact.length() > 0) {
			result += " ("+funFact+") ";
		}
		return result;
	}
}
