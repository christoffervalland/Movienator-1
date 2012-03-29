/**
 * Launches all the views and all controllers
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */

package controller;

import java.awt.image.BufferedImage;
import java.sql.*;
import java.util.LinkedHashMap;
import sql.ConnectionSql;
import utils.ErrorHandler;
import model.Movie;
import model.MovieLibrary;
import model.User;
import IO.MovieFactory;
import view.*;

public class ApplicationController {

	/**
	 * Fields
	 */
	private LogonView logonView;
	private LoginStatusController loginStatusController;
	private ApplicationView applicationView;
	private SearchHeaderView searchHeaderView;
	private ProgressBarView progressBarView;
	private OptionsFooterView optionsFooterView;
	private HomeView homeView;
	private FavouritesView favouritesView;
	private SearchResultView searchResultView;
	private MoviePanelView moviePanelView;
	private CommunityView communityView;

	private LogonController logonController;
	private SearchHeaderController searchHeaderController;
	private ProgressBarController progressBarController;
	private OptionsFooterController optionsFooterController;
	private HomeController homeController;
	private FavouritesController favouritesController;
	private CommunityController communityController;
	private SearchResultController searchResultController;
	private MoviePanelController moviePanelController;
	
	private MovieFactory movieFactory;
	private MovieLibrary movieLibrary;
	private String error;
	private ConnectionSql connectionSql;
	private User user;
	
	/**
	 * Constructor
	 */
	public ApplicationController() 
	{
		moviePanelView = new MoviePanelView(new BufferedImage(150,200,BufferedImage.TYPE_INT_ARGB), new BufferedImage(60,60,BufferedImage.TYPE_INT_ARGB));
		optionsFooterView = new OptionsFooterView();
		loginStatusController = new LoginStatusController(optionsFooterView, this);
		movieLibrary = new MovieLibrary();
		logonView = new LogonView();
		movieFactory = MovieFactory.instanceOf();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnfe) { 
			System.out.println("Feil i lasting av jdbc-drivar" + cnfe);
		} 
		
		try {
			connectionSql = new ConnectionSql(logonView, logonController, this, communityController, movieLibrary, movieFactory);
		} catch (SQLException e) {
			error = "Could not connect to database.";
			ErrorHandler.getErrorMessage(error);
		}
		
		homeView = new HomeView(logonView);
		searchHeaderView = new SearchHeaderView();
		favouritesView = new FavouritesView();
		searchResultView = new SearchResultView();
		progressBarView = new ProgressBarView();

		communityView = new CommunityView();
		applicationView = new ApplicationView("Movienator", homeView, searchHeaderView, favouritesView,
				searchResultView, moviePanelView, logonView, progressBarView, 
				optionsFooterView, communityView, connectionSql);
		progressBarController = new ProgressBarController(progressBarView);
		moviePanelController = new MoviePanelController(moviePanelView, movieLibrary, this, connectionSql, 
				movieFactory, progressBarController);
		favouritesController = new FavouritesController(favouritesView, moviePanelController, movieLibrary, 
				this, connectionSql);
		logonController = new LogonController(logonView, connectionSql, applicationView, loginStatusController, 
				favouritesController, progressBarController);
		optionsFooterController = new OptionsFooterController(optionsFooterView, this);
		searchHeaderController = new SearchHeaderController(searchHeaderView, this, movieLibrary, progressBarController);
		homeController = new HomeController(homeView, movieLibrary, this, moviePanelController, progressBarController);
		searchResultController = new SearchResultController(searchResultView, moviePanelController, this, movieLibrary, 
				homeController, progressBarController);
		communityController = new CommunityController(communityView, connectionSql, this, moviePanelController, 
				movieLibrary, progressBarController);
		
		new Thread(optionsFooterController).start(); 
	}

	/**
	 * changes to "search result" tab and fills it with information
	 * @author Eystein
	 */
	public void getMovies(String title)
	{
		LinkedHashMap<Long, Movie> movies = movieFactory.getMoviesByTitle(title);

		searchResultController.setSearchResults(movies);
		applicationView.getTabbedPane().setSelectedIndex(1);
		searchHeaderController.searchBarUpdater();
		applicationView.repaint();

	}

	/**
	 * Changes tab to "current"
	 * @author Eystein
	 */
	public void setMoviePanelTab()
	{
		applicationView.repaint();
		applicationView.getTabbedPane().setSelectedIndex(2);
	}

	/**
	 * Getter for searchHeaderController
	 * @return the searchHeaderController
	 */
	public SearchHeaderController getSearchHeaderController() {
		return searchHeaderController;
	}

	/**
	 * Getter for favouritesController
	 * @return the favouritesController
	 */
	public FavouritesController getFavouritesController() {
		return favouritesController;
	}

	/**
	 * Getter for searchResultController
	 * @return the searchResultController
	 */
	public SearchResultController getSearchResultController() {
		return searchResultController;
	}

	/**
	 * Get for moviePanelController
	 * @return the moviePanelController
	 */
	public MoviePanelController getMoviePanelController() {
		return moviePanelController;
	}


	/**
	 * Getter for logonView
	 * @return logonView
	 */
	public LogonView getLogonView() {
		return logonView;
	}
	
	/**
	 * Setter for logonView
	 * @param logonView
	 */
	public void setLogonView(LogonView logonView) {
		this.logonView = logonView;
	}
	
	/**
	 * Creates the current user
	 * @param username
	 * @param password
	 * @param screenName
	 */
	public void createUser(String username, String password, String screenName){
		user = new User(username, password, screenName);
	}
	
	/**
	 * Getter for user
	 * @return user
	 */
	public User getActiveUser(){
		return user;
	}



}
