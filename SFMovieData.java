
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SFMovieData {
	public static void main(String [] args) {
		
		//Program is run without any arguments
		if(args.length == 0) {
			System.err.println("Error: the program expects a file name as an argument.\n");
			System.exit(1);
		}
		
		//Set argument to file
		File myFile = new File(args[0]);
		
		//File doesn't exist
		if(!myFile.exists()) {
			System.err.println("Error: the file "+myFile.getAbsolutePath()+" does not exist.\n");
			System.exit(1);
		}
		
		//File can't be read
		if (!myFile.canRead()){
			System.err.println("Error: the file "+myFile.getAbsolutePath()+
					" cannot be opened for reading.\n");
			System.exit(1);
		}
		
		//open the file for reading 
				Scanner myScanner = null; //declare scanner
				
				try { //try to read the file
					myScanner = new Scanner (myFile); //new scanner to scan the file
				} catch (FileNotFoundException e) {
					System.err.println("Error: the file "+myFile.getAbsolutePath()+
							" cannot be opened for reading.\n");
					System.exit(1); //terminate with 1 to show that program did not run properly
				}
				
				MovieList list = new MovieList(); //declare an empty list
				
				String line = null; //declare line as null
				myScanner.nextLine(); //skip line to avoid the titles line from being read in
				
				while (myScanner.hasNextLine()) { //while there are more lines in the file
					try { 
							line = myScanner.nextLine(); //read in the next line
							ArrayList<String> data = splitCSVLine(line); //returns entries
							
							//check data to make sure that title, year, location and actor1 are not blank
							if (data.size() >= 9 && !(data.get(0).length()==0 || data.get(1).length()==0  || data.get(2).length()==0 || data.get(8).length()==0)) { 
								
								//set all the data to its string in the entries arraylist
								String title = data.get(0); 
								
								String temp = data.get(1); 
								int relYear = Integer.parseInt(temp); //parse the string representing year into an int
								
								Location location = new Location(data.get(2),data.get(3)); 
								
								String director = data.get(6);
								
								String writer = data.get(7);
								
								Actor a1 = new Actor((String) data.get(8));
								
								Actor a2 = null;
								if (9 < data.size() && data.get(9).length() > 0)
									a2 = new Actor((String) data.get(9));
								
								Actor a3 = null;
								if (10 < data.size() && data.get(10).length() > 0)
									a3 = new Actor((String) data.get(10));
								
								Movie current = new Movie(title, relYear, director, writer, a1, a2, a3); //construct a movie with the above data
								current.addLocation(location); //adds location to movie
								list.addMovie(current); //adds movie to list
							}
						}
						
					catch (NoSuchElementException ex ) {
						//caused by an incomplete or miss-formatted line in the input file
						System.err.println(line);
						continue; 	
					}
				}
				
				//interactive mode: 		
				Scanner userInput  = new Scanner (System.in); 
				String userValue = "";
				
					do {
						//Print these lines to prompt the user to enter a query
						System.out.println("Search the database by matching keywords to titles or actor names.");
						System.out.println("   To search for matching titles, enter\n\t title KEYWORD");
						System.out.println("   To search for matching actor names, enter\n\t actor KEYWORD");
						System.out.println("   To finish the program, enter\n\t quit");
						System.out.println("\n\n");
						System.out.println("Please enter your search query:");
							
						//get value of from the user 
							userValue = userInput.nextLine();
							if (!userValue.equalsIgnoreCase("quit")) { 
								int space = userValue.indexOf(" ");
								if (space == -1) {
									System.out.println("This is not a valid query. Try again.");
								}
								else {
									if (userValue.substring(0,space).equalsIgnoreCase("title")) {
										String title = userValue.substring(space + 1); //the title is the rest
											
										MovieList resultList = list.getMatchingTitles(title); //make a list of results of the matching titles
										if (resultList != null) {
											System.out.println(resultList.toCompleteString());
										}
										else {
											System.out.println("No matches found. Try again.");
										}
									}
									else if (userValue.substring(0,space).equalsIgnoreCase("actor")) {
											String actor = userValue.substring(space + 1);
												
											MovieList resultList = list.getMatchingActor(actor); //make a list of results of the matching actors
											if (resultList != null) {
												System.out.println(resultList.toCompleteString());
											}
											else {
												System.out.println("No matches found. Try again.");
											}
									} 
									else {
										System.out.println("Invalid query.");
									}
								}
							}
						} while (!userValue.equalsIgnoreCase("quit")); 
				userInput.close();	//close System.in
		}
	/**
	 * Method written by Professor Klukowska to parse the data
	 * @param textLine the line of the csv file which is being parsed
	 * @return an arraylist of strings containing the data
	 * @author Professor Klukowska
	 */
	public static ArrayList<String> splitCSVLine(String textLine){
		ArrayList<String> entries = new ArrayList<String>(); 
		int lineLength = textLine.length(); 
		StringBuffer nextWord = new StringBuffer(); 
		char nextChar; 
		boolean insideQuotes = false; 
		boolean insideEntry= false;

		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
	
				// change insideQuotes flag when nextChar is a quote

				if (insideQuotes) {
					insideQuotes = false; 
					insideEntry = false;

				}else {
					insideQuotes = true; 
					insideEntry = true;
				}

			} else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
				// add it to the current entry 
					nextWord.append( nextChar );
				}else { // skip all spaces between entries
					continue; 
				}
			} else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar); 
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			} 
		}
		// add the last word ( assuming not empty ) 
		// trim the white space before adding to the list 

		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());

		}
		return entries;
	}
}

