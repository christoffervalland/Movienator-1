/**
 * @author Eystein Davoen
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package IO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import utils.ErrorHandler;
import org.rottentomatoes.RottenTomatoes;
import model.Movie;
import com.google.gson.*;

/**
 * 
 * Class for getting the rottentomatoes result and interpret the result from JSON Array.
 *
 */


public class MovieFactory {

	private RottenTomatoes rt;
	private HashSet<Long> favourites;
	private static MovieFactory movieFactory;

	private String error;

	private MovieFactory()
	{
		System.out.println("Create new ROtten tomatoes object");
		favourites = new HashSet<Long>();
		rt = new RottenTomatoes();
	}

	public static MovieFactory instanceOf() {

		if(movieFactory == null) {
			movieFactory = new MovieFactory();
		}

		return movieFactory;
	}
	/**
	 * performs a search for a movie.
	 * @param title
	 * @return HashMap movies
	 */
	public LinkedHashMap<Long, Movie> getMoviesByTitle(String title)
	{
		JsonArray jsonMovies = rt.moviesSearch(title);
		LinkedHashMap<Long, Movie> movieList = new LinkedHashMap<Long, Movie>();



		for(JsonElement jsonMovie : jsonMovies) {
			Movie movie = jsonMovieToMovie(jsonMovie);
			movieList.put(movie.getID(), movie);

		}

		return movieList;
	}

	/**
	 * Performes a search for movies based on an movie ID
	 * @param id
	 * @return Movie
	 */
	public Movie getMovieById(long id)
	{
		JsonObject movieObject = rt.movieInfo(id);
		Movie movie = jsonMovieToMovie(movieObject);

		return movie;
	}
	/**
	 * Takes the JSONElement and transform it to a movie Object
	 * @param jsonMovie
	 * @return Movie 
	 */
	public Movie jsonMovieToMovie(JsonElement jsonMovie)
	{
		JsonObject movieObject = jsonMovie.getAsJsonObject();

		JsonElement jsonId = movieObject.get("id");
		JsonElement jsonTitle = movieObject.get("title");
		JsonElement jsonaarstall = movieObject.get("year");
		JsonElement jsonRating = movieObject.get("mpaa_rating");
		JsonElement jsonRuntime = movieObject.get("runtime");
		JsonElement jsonReleases = movieObject.get("release_dates");
		JsonElement jsonRelease = jsonReleases.getAsJsonObject().get("dvd" ); 
		JsonElement jsonScores = movieObject.get("ratings");
		JsonElement jsonScore = jsonScores.getAsJsonObject().get("critics_score");
		JsonElement jsonUserScore = jsonScores.getAsJsonObject().get("audience_score");
		JsonElement jsonSynopsis = movieObject.get("synopsis");
		JsonElement jsonPosters = movieObject.get("posters");
		JsonElement jsonDetailedPoster = jsonPosters.getAsJsonObject().get("detailed");

		JsonElement jsonLinks = movieObject.get("links");
		JsonElement linksDetailed = jsonLinks.getAsJsonObject().get("alternate");
		String detailedLink = linksDetailed.getAsString();

		long id = jsonId.getAsLong();
		String movieTitle = jsonTitle.getAsString();
		String posterUrl = jsonDetailedPoster.getAsString();
		String aarstall = jsonaarstall.getAsString();
		String rating = jsonRating.getAsString();
		String runtime = jsonRuntime.getAsString();
		String release = "NA";
		if(jsonRelease != null) {
			release = jsonRelease.getAsString();
		}
		int score = jsonScore.getAsInt();
		int userScore = jsonUserScore.getAsInt();
		String synopsis = jsonSynopsis.getAsString();

		JsonObject jGenre = rt.movieInfo(jsonId.getAsLong());
		String genreString = "";
		JsonArray jsonGenre = jGenre.getAsJsonArray("genres");
		if(jsonGenre == null) {
			System.out.println("OOPS, something wroooooooong");
		} else {
			for(int i = 0; i < jsonGenre.size(); i++) {
				genreString += jsonGenre.get(i).getAsString() + ", ";
			}
		}


		/**
		 * Lager jsonobject av SIMILAR
		 */
		JsonObject jSimilar = rt.similarMovie(jsonId.getAsLong());
		JsonArray jsonSimilar = jSimilar.getAsJsonArray("movies");


		/**
		 * Lager jsonobject av CAST
		 */
		JsonObject jCast = rt.movieCast(jsonId.getAsLong());
		String castString = "";
		JsonArray jsonCast = jCast.getAsJsonArray("cast");

		if(jCast != null){

			for(int v = 0; v < jsonCast.size(); v++){
				JsonObject jo = jsonCast.get(v).getAsJsonObject();
				JsonArray joChar = jo.get("characters").getAsJsonArray();

				String characters = "";

				for(int j = 0; j < joChar.size(); j++){
					characters += joChar.get(j).getAsString();
					if(j+1 != joChar.size()){
						characters += " \\ ";
					}
				}

				castString += (jo.get("name").getAsString() + " - " + characters + "\n"); 

			}
		}

		boolean favourited = false;
		if(favourites.contains(id)) {
			favourited = true;
		}
		Movie movie = new Movie(id, movieTitle, posterUrl, aarstall, rating,  runtime, release, 
				score, userScore, synopsis, genreString, castString, favourited, detailedLink, jsonSimilar);
		return movie;
	}

	/**
	 * @param favourites the favourites to add
	 */
	public void addFavourites(Collection<Long> favouritesSet) {

		this.favourites.addAll(favouritesSet);
	}

	/**
	 * @param favourites the favourites to remove
	 */
	public void removeFavourites(Collection<Long> favouritesSet) {

		this.favourites.retainAll(favouritesSet);
	}


	/**
	 * Add a movie id to the favourites list
	 * @param id
	 */
	public void addFavouriteToFavourites(long id) {
		favourites.add(id);
	}

	/**
	 * Saves favourites as Arraylist
	 * @author Eystein Dav¿en
	 */
	public void saveFavourites() {
		// TODO Auto-generated method stub
		ArrayList<Long> favouriteList = new ArrayList<Long>();
		favouriteList.addAll(favourites);
		try {
			MovieNatorIO.writeMovies(favouriteList);
		} catch (IOException e) {
			//sender feilmelding til feilhåndteringsklassen ErrorHandler
			//som oppretter et feilmeldingsvindu ut til brukeren
			error = "We could not save your favourites";
			ErrorHandler.getErrorMessage(error);		}
	}

	public RottenTomatoes getRt() {
		return rt;
	}

	public void setRt(RottenTomatoes rt) {
		this.rt = rt;
	}
	public JsonArray getSimilar(Long movieID){
		JsonObject jSimilar = rt.similarMovie(movieID);
		JsonArray jasonSimilar = jSimilar.getAsJsonArray();
		return jasonSimilar;
	}


}


