/**
 * @author Eystein Davoen
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import IO.MovieFactory;

public class MovieLibrary {
 
	/**
	 * Fields
	 */
	private ArrayList<Movie> myFavourites;
	private LinkedHashMap<Long, Movie> favouriteMovies;
	private MovieFactory movieFactory;
	public ArrayList<String> SearchHistory = new ArrayList<String>();
	public ArrayList<Movie> lastVisitedMovies = new ArrayList<Movie>();
	public ArrayList<Movie> similarMovies;
	public HashMap<Long, Movie> othersFavourites;
	
	/**
	 * Constructor
	 */
	public MovieLibrary() 
	{
		this.favouriteMovies = new LinkedHashMap<Long, Movie>();
		movieFactory = MovieFactory.instanceOf();
		this.similarMovies = new ArrayList<Movie>();
		this.myFavourites = new ArrayList<Movie>();
		this.othersFavourites = new HashMap<Long, Movie>();
	}

	/**
	 * @return the favouriteMovies
	 */
	public HashMap<Long, Movie> getFavouriteMovies() {
		return favouriteMovies;
	}

	/**
	 * @param favouriteMovies the favouriteMovies to set
	 */
	public void setFavouriteMovies(LinkedHashMap<Long, Movie> favouriteMovies) {
		this.favouriteMovies = favouriteMovies;
		movieFactory.addFavourites(favouriteMovies.keySet());
	}
	
	/**
	 * Add movie to favourite map
	 */
	public void setFavouriteMovie(Movie movie) {
		favouriteMovies.put(movie.getID(), movie);
		movieFactory.addFavourites(favouriteMovies.keySet());
	}
	
	/**
	 * Remove movie from favourite map
	 * @param movie
	 */
	public void removeFavouriteMovie(Movie movie) 
	{
		favouriteMovies.remove(movie.getID());
		movieFactory.removeFavourites(favouriteMovies.keySet());
	}

	/**
	 * 
	 * @return searchHistory
	 */
	public ArrayList<String> getSearchHistory() {
		return SearchHistory;
	}

	/**
	 * 
	 * @param searchHistory
	 */
	public void setSearchHistory(ArrayList<String> searchHistory) {
		SearchHistory = searchHistory;
	}

	/**
	 * 
	 * @return lastVisitedMovies
	 */
	public ArrayList<Movie> getLastVisitedMovies() {
		return lastVisitedMovies;
	}

	/**
	 * 
	 * @param lastVisitedMovies
	 */
	public void setLastVisitedMovies(ArrayList<Movie> lastVisitedMovies) {
		this.lastVisitedMovies = lastVisitedMovies;
	}
	
	
	/**
	 * @return similarMovies;
	 * @author ChristofferValland
	 */
	public ArrayList<Movie> getSimilarMovies() {
		return similarMovies;
	}
	
	/**
	 * @param similarMovies
	 * @author Christoffer Valland
	 */
	public void setSimilarMovies(ArrayList<Movie> similarMovies) {
		this.similarMovies = similarMovies;
	}
	
	public ArrayList<Movie> getMyFavourites() {
		return myFavourites;
	}

	public void setMyFavourites(ArrayList<Movie> myFavourites) {
		this.myFavourites = myFavourites;
	}

	public HashMap<Long, Movie> getOthersFavourites() {
		return othersFavourites;
	}

	public void setOthersFavourites(HashMap<Long, Movie> othersFavourites) {
		this.othersFavourites = othersFavourites;
	}
	
}

