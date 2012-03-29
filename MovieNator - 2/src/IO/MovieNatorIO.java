/**
 * @author Eystein Davoen
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package IO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class MovieNatorIO {
	/**
	 * Reads and writes to file
	 */
	public MovieNatorIO()
	{
		
	}
	/**
	 * Write movieID to file
	 * @param movieId
	 * @throws IOException
	 */
	public static void writeMovies(ArrayList<Long> movieId) throws IOException
	{
		File file = new File("favourites.txt");
		if(file.exists()) {
			file.delete();
			
		}
		file.createNewFile();
		
		PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
		for(long i : movieId) {
			writer.println(i);
		}
		writer.close();
	}
	/**
	 * reads movieID from file
	 * @return ArrayList 
	 * @throws IOException
	 */
	public static ArrayList<Long> readMovies() throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader(new File("favourites.txt")));
		ArrayList<Long> movies = new ArrayList<Long>();
		String line = reader.readLine();
		while(line != null) {
			movies.add(Long.parseLong(line));
			line = reader.readLine();
		}
		
		return movies;
		
		
	}

}
