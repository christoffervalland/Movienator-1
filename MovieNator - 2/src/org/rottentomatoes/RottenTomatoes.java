package org.rottentomatoes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Properties;

import com.google.gson.JsonArray;

import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * This is class is a starting point for retrieving information from rottentomatoes.com.
 * The class has methods to support movie searching and finding detailed movie information.
 * 
 * Get a API-key from developer.rottentomatoes.com and paste it in /config/rottentomatoes.properties
 * @author Sindre Benonisen
 * @author Kjetil Bruland
 */
public class RottenTomatoes {
	public String apiKey = null;
	
	/**
	 * Creates a new RottenTomoatoe object.
	 */
	public RottenTomatoes(){
		inititalizeAPI();
	}

	
	/**
	 * Search for movies with pagenumber set as 1 and pagelimit set as 10
	 * @param String movie The plain text search query to search for a movie. 
	 * @return A JsonArray containing the result of the search.
	 */
	
	public JsonArray moviesSearch(String movie){
		return moviesSearch(movie, 25, 1);
	}
	
	/**
	 * The movies search endpoint for plain text queries. Let's you search for movies! 
	 * @param String movie The plain text search query to search for a movie. 
	 * @param int pageLimit The amount of movie search results to show per page
	 * @param int page The selected page of movie search results
	 * @return A JsonArray containing the result of the search.
	 */
	public JsonArray moviesSearch(String movie, int pageLimit, int page){
		if(apiKey.equals("")){
			System.err.println("API-key not initilized. Check config/rottentomatoes.properties");
			return null;
		}

		movie = Util.urlEncode(movie);		
		if(movie.equals("")){
			return null;
		}

		String url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?q="+
				movie +"&page_limit="+pageLimit+"&page="+page+"&apikey="+apiKey;

		JsonArray movieArray = null;	
		try {
			JsonObject jsonResponse = getJSONresponse(url);
			if(jsonResponse != null){
				movieArray = jsonResponse.getAsJsonArray("movies");
			}
		} catch (JsonIOException e) {
			System.err.println("Error: Could not find any movies. Check that your API-key is correct");
		} 
		return movieArray;
	}
	/**
	* Detailed information on a specific movie specified by Id
	* @param long movieID The Movie ID from used at rottentomatoes.com
	* @return If successful a JsonObject containing detailed information. Returns null if not successful.
	*/
	public JsonObject movieInfo(long movieID) {
		if(apiKey.equals("")){
			System.err.println("API-key not initilized. Check config/rottentomatoes.properties");
			return null;
		}
		String url = "http://api.rottentomatoes.com/api/public/v1.0/movies/"+movieID+".json?apikey="+apiKey;
		JsonObject jsonResponse = null;
		try {
			jsonResponse = getJSONresponse(url);
		}  catch (JsonIOException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}

	/**
	 * This method initializes the class configurations. 
	 * It loads the API-key from the config folder.
	 * @return false if API-key is not set or a empty String, if not true.
	 */
	private boolean inititalizeAPI() {
		Properties config = new Properties();
		FileInputStream fileInputStream;
		
		try {
			fileInputStream = new FileInputStream("config/rottentomatoes.properties");
			config.load(fileInputStream);
		} catch (FileNotFoundException fe) {
			System.err.println("Can't find the configfile");
		} catch (IOException ie) {
			System.err.print("Something went wrong when loading the configfile");
		}
		
		apiKey = config.getProperty("API_KEY");
		
		if(apiKey.equals(null) || apiKey.trim().equals("")){
			return false;
		}else{
			return true;
		}
		
	}
	/**
	* Gets a Json response from a given URL. Must be of correct format. 
	* Checks if the API-key is set and if the host responds.
	* @param String url the URL.
	* @return A JsonObject from given URL. 
	* @throws JsonIOException If the URL is not a valid JSON format. 
	*/
	
	private JsonObject getJSONresponse(String url) throws JsonIOException{
		InputStream is = null;
		try{
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader( new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = Util.readAll(rd);
			JsonParser jp = new JsonParser();
			JsonObject json = (JsonObject) jp.parse(jsonText);
			return json;
		}
		catch (UnknownHostException uhe){
			System.err.println("Unknown host: Check your internet connection");
		}
	    catch (IOException ie) {
			System.err.println("Connection issues: Could not connect to the API. Check your API-key");
			
		}finally{
			try {
				if(is != null)
					is.close();
			} catch (IOException e) {
				
			}
		}
		return null;
	}
	
	/**
	 * New URL for the cast. 
	 * @param movieID
	 * @return jsonResponse (Cast)
	 * @author ChristofferValland
	 */
	public JsonObject movieCast(long movieID) {
		if(apiKey.equals("")){
			System.err.println("API-key not initilized. Check config/rottentomatoes.properties");
			return null;
		}
		String url = "http://api.rottentomatoes.com/api/public/v1.0/movies/"+movieID+"/cast.json?apikey="+apiKey;
		JsonObject jsonResponse = null;
		try {
			jsonResponse = getJSONresponse(url);
		}  catch (JsonIOException e) {
			e.printStackTrace();
		}
		return jsonResponse;
	}
	
	/**
	 * New URL for similar movies
	 * @param movieID
	 * @return jsonResponse (similar movies)
	 * @author ChristofferValland
	 */
	public JsonObject similarMovie(long movieID){
		if(apiKey.equals("")){
			System.err.println("API-key not initilized. Check config/rottentomatoes.properties");
			return null;
		}
		String url = "http://api.rottentomatoes.com/api/public/v1.0/movies/"+movieID+"/similar.json?apikey="+apiKey;
		JsonObject jsonResponse = null;
		try {
			jsonResponse = getJSONresponse(url);
		}  catch (JsonIOException e) {
			e.printStackTrace();
		}
		return jsonResponse;

	}
	
}
