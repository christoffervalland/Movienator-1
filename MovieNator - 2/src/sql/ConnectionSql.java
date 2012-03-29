package sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Properties;

import IO.MovieFactory;

import com.google.gson.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import controller.ApplicationController;
import controller.CommunityController;
import controller.LogonController;

import model.Movie;
import model.MovieLibrary;
import model.User;

import utils.ErrorHandler;
import view.LogonView;

/**
 * All oppkobling og endring i databasen skjer via denne klassen
 * @author Christoffer Valland og Lars Petter
 */

public class ConnectionSql{
	String error;
	String host = "hoguslg.uib.no";
	String dbName = "ljo013";
	int port = 3306;
	String mySqlUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
	Properties userInfo = new Properties();
	private User user;
	private LogonView logonView;
	private ApplicationController applicationController;
	private ArrayList<String> users;
	private HashSet<Movie> favouriteMovies;
	private MovieLibrary movieLibrary;
	private Connection conn;
	private MovieFactory movieFactory;
	
	/**
	 * Legger inn rette brukernavn og passord til å kunne koble seg opp til databasen vår
	 * @throws SQLException
	 * @author ChristofferValland og Lars Petter
	 */
	public ConnectionSql(LogonView logonView, LogonController logonController, 
			ApplicationController applicationController, CommunityController communityController, 
			MovieLibrary movieLibrary, MovieFactory movieFactory) throws SQLException {	
		userInfo.put("user","ljo013"); 
		userInfo.put("password","Info231v12");
		this.logonView = logonView;
		this.applicationController = applicationController;
		this.users = new ArrayList<String>();
		this.favouriteMovies = new HashSet<Movie>();
		this.movieLibrary = movieLibrary;
		conn = DriverManager.getConnection(mySqlUrl, userInfo);
		this.movieFactory = movieFactory;
	}
	/**
	 * Legger til et filmobjekt med de rette verdiene dersom filmen ikke eksisterer i databasen allerede
	 * Skriver ut feilmelding i en dialog dersom filmen ikke eksisterer!
	 * @param movie
	 * @throws SQLException
	 * @author ChristofferValland og Lars P
	 */
	public void addFavourite(Movie movie) throws SQLException{
		String innsett = "INSERT INTO Movie VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		
		PreparedStatement innsettSetn = conn.prepareStatement(innsett);
		
		//Sjekker om filmen allerede ligger i databasen
		//Skriver ut feilmelding dersom den eksisterer, og legger inn verdiene dersom den ikke finnes
		if(innsettSetn.equals(movie)){
			error = "This movie already exists in the database.";
			ErrorHandler.getErrorMessage(error);
			
		
		} else if(applicationController.getActiveUser() == null) {
			error = "You have to sign in to do this";
			ErrorHandler.getErrorMessage(error);
		} else {
			
			innsettSetn.setLong(1, movie.getID());
			innsettSetn.setString(2, movie.getTitle());
			innsettSetn.setString(3, movie.getAarstall());
			innsettSetn.setString(4, movie.getMPAArating());
			innsettSetn.setString(5, movie.getRuntime());
			innsettSetn.setString(6, movie.getUtgivelsesdato());
			innsettSetn.setInt(7, movie.getScore());
			innsettSetn.setInt(8, movie.getUserScore());
			innsettSetn.setString(9, movie.getSynopsis());
			innsettSetn.setString(10, movie.getCast());
			innsettSetn.setString(11, movie.getPosterUrl());
			innsettSetn.setString(12, movie.getDiceImageAsString());
			innsettSetn.setString(13, movie.getGenre());
			innsettSetn.setBoolean(14, movie.isFavourited());
			innsettSetn.setString(15, movie.getLink());
			innsettSetn.setString(16, applicationController.getActiveUser().getUserName());
			innsettSetn.executeUpdate();
		}
	}
	
	/**
	 * Sjekker om id pŒ en film finnes i databasen. 
	 * Skriver ut feilmelding dersom filmen ikke finnes, og fjerner filmen dersom den finnes!
	 * @param movie
	 * @throws SQLException
	 * @author ChristofferValland
	 */
	public void removeFavourite(Movie movie) throws SQLException{
		long id = movie.getID();
		String fjern = "DELETE FROM Movie WHERE ID="+id;
		PreparedStatement fjernSetn = conn.prepareStatement(fjern);
		if(!fjernSetn.equals(movie)){
			fjernSetn.executeUpdate();
		} else {
			error = "Can«t remove this movie";
			ErrorHandler.getErrorMessage(error);
		}
	}
	
	/**
	 * Legger til brukere dersom brukeren ikke finnes. Skriver ut feilmelding om brukeren ligger der
	 * @throws SQLException
	 * @author ChristofferValland
	 */
	public void addUser() throws SQLException{
		String user = "INSERT INTO User VALUES(?,?,?)"; 
		PreparedStatement userSetn = conn.prepareStatement(user);
		
		userSetn.setString(1, logonView.getNewUserName().getText());
		userSetn.setString(2, logonView.getNewPassword().getText());
		userSetn.setString(3, logonView.getNewScreenName().getText());
		System.out.println(userSetn);
		
		try{
			//Legger til ønsket informasjon i tabellen "User"
			userSetn.executeUpdate();
		} catch (MySQLIntegrityConstraintViolationException m){
			//Fanger opp duplikater og gir brukeren en feilmelding
			error = "User already exists";
			ErrorHandler.getErrorMessage(error);
		}
	}
	
	/**
	 * Metode for å logge inn med en eksisterende bruker
	 * @return returnerer "true" kun dersom både brukernavn og passord stemmer overens
	 * ellers returnerer den "false".
	 * @throws SQLException
	 * @author Christoffer Valland
	 */
	public boolean logonUser() throws SQLException{
		String existingUser = logonView.getUserName().getText().toLowerCase();
		String existingPassword = logonView.getPassword().getText().toLowerCase();
		Statement st = conn.createStatement();
		System.out.println(existingUser + " " + existingPassword);
		ResultSet rs = st.executeQuery("SELECT EMAIL, PASSWORD, SCREENNAME FROM User");
		while(rs.next()) {
			String username = rs.getString(1);
			String password = rs.getString(2);
			String screenName = rs.getString(3);
			if(username.equals(existingUser) && password.equals(existingPassword)){
				applicationController.createUser(username, password, screenName);
				return true;
			}
			else{
				
			}
		}
		error = "Username or password is not correct";
		ErrorHandler.getErrorMessage(error);
		return false;
	}
	/**
	 * Printer ut alle brukerene fra databasen
	 * @author Lars Petter Johnsen
	 * @throws SQLException
	 */
	public void otherUsers() throws SQLException{
		Statement st = conn.createStatement();
		ResultSet userResult = st.executeQuery("SELECT SCREENNAME FROM User");
		while (userResult.next()){
			String screenName = userResult.getString(1);
			users.add(screenName);
			}
	}
	
	/**
	 * Henter ut filmene til den personen som blir trykket pŒ i lista over brukere
	 * @param string
	 * @throws SQLException
	 * @author ChristofferValland and Lars Petter Johnsen
	 */
	public LinkedHashMap<Long, Movie> othersFavouriteMovies(String string) throws SQLException{
		LinkedHashMap<Long, Movie> favouriteMovies = new LinkedHashMap<Long, Movie>();
		String selectedUser = string;
		String user = "SELECT m.*, u.SCREENNAME FROM User u, Movie m " +
				"WHERE u.EMAIL = m.EMAIL AND u.SCREENNAME=(?)"; 
		PreparedStatement thisUser = conn.prepareStatement(user);
		thisUser.setString(1, selectedUser);
		
		
		
		ResultSet rs = thisUser.executeQuery();
		while(rs.next()){
			long ID = rs.getLong(1);
			String title = rs.getString(2);
			String aarstall = rs.getString(3);
			String mpaaRating = rs.getString(4);
			String runtime = rs.getString(5);
			String release = rs.getString(6);
			int score = rs.getInt(7);
			int userScore = rs.getInt(8);
			String synopsis = rs.getString(9);
			String cast = rs.getString(10);
			String image = rs.getString(11);
//			String Dice = rs.getString(12);
			String genre = rs.getString(13);
			Boolean favourited = rs.getBoolean(14);
			String link = rs.getString(15);
			JsonObject similar = movieFactory.getRt().similarMovie(ID);
			
			JsonArray sim = similar.getAsJsonArray("movies");
			
 			Movie v = new Movie(ID, title, image, aarstall, mpaaRating, runtime, release, score, 
 					userScore, synopsis, genre, cast, favourited, link, sim);
			
 			favouriteMovies.put(v.getID(), v);
			
					
		}
		return favouriteMovies;
	}
	
	/**
	 * Henter alle filmene til den brukeren som er logget inn
	 * @throws SQLException
	 * @author Lars Petter Johnsen
	 */
	public void getUserMovies() throws SQLException{
		
		String user = "SELECT * FROM Movie WHERE EMAIL = +(?)"; 
		PreparedStatement userSetn = conn.prepareStatement(user);
		userSetn.setString(1, applicationController.getActiveUser().getUserName());
		
		ResultSet rs = userSetn.executeQuery();
		System.out.println(rs);
		while(rs.next()){
			long ID = rs.getLong(1);
			String Title = rs.getString(2);
			String Aarstall = rs.getString(3);
			String MPAArating = rs.getString(4);
			String Runtime = rs.getString(5);
			String Release = rs.getString(6);
			int Score = rs.getInt(7);
			int userScore = rs.getInt(8);
			String Synopsis = rs.getString(9);
			String Cast = rs.getString(10);
			String Image = rs.getString(11);
			String Genre = rs.getString(13);
			Boolean Favourited = rs.getBoolean(14);
			String Link = rs.getString(15);
			JsonArray similar = null;
			
 			Movie v = new Movie(ID, Title, Image, Aarstall, MPAArating, Runtime, Release, Score, 
 					userScore, Synopsis, Genre, Cast, Favourited, Link, similar);
 			
 			movieLibrary.getMyFavourites().add(v);
 			
		}
	}
	
	/**
	 * Lukker tilkoblingen til databasen
	 * @author ChristofferValland and Lars Petter Johnsen
	 */
	public  void closeConnection(){
		try {
			conn.close();
			} catch (SQLException e) {
			error = "Could not close the connection to the database";
			ErrorHandler.getErrorMessage(error);
		}
	}
	
	
	
	public ArrayList<String> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}
	public HashSet<Movie> getFavouriteMovies() {
		return favouriteMovies;
	}
	public void setFavouriteMovies(HashSet<Movie> favouriteMovies) {
		this.favouriteMovies = favouriteMovies;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	

	
}
