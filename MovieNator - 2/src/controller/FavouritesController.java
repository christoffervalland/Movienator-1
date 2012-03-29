/**
 * @author Eystein Davoen
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JPanel;
import model.Movie;
import model.MovieLibrary;
import sql.ConnectionSql;
import utils.ErrorHandler;
import utils.ImageUtilities;
import view.FavouritesView;
import view.MovieResultPoster;

public class FavouritesController implements MouseListener {

	/**
	 * Fields
	 */
	private FavouritesView favouritesView;
	private MoviePanelController moviePanelController;
	private ApplicationController applicationController;
	private MovieLibrary movieLibrary;
	private ConnectionSql connectionSql;


	/**
	 * Constructor
	 * @param view
	 * @param moviePanelController
	 * @param movieLibrary
	 * @param applicationController
	 */
	public FavouritesController(FavouritesView view, MoviePanelController moviePanelController, MovieLibrary movieLibrary, ApplicationController applicationController, 
			ConnectionSql connectionSql)
	{
		setFavouritesView(view);
		favouritesView = view;
		this.moviePanelController = moviePanelController;
		this.applicationController = applicationController;
		this.movieLibrary = movieLibrary;
		this.connectionSql = connectionSql;		
	}

	/**
	 * Sets favourite posters in favourites
	 * @author Lars Petter, Christoffer
	 */
	public void setFavouritePosters()
	{
		try {
			connectionSql.getUserMovies();
		} catch (SQLException e) {
			String error = "Oops, something went wrong\n No movies found in favourites";
			System.out.println(error);
			ErrorHandler.getErrorMessage(error);
		}
		
		if(applicationController.getActiveUser() != null){
		
		favouritesView.removeAll();
		
		for(int i = 0; i < movieLibrary.getMyFavourites().size(); i+=5) {

			JPanel fiveSearchMoviePostersPanel = new JPanel();
			fiveSearchMoviePostersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
			
			for(int c = 0; c < 5 && i+c < movieLibrary.getMyFavourites().size(); c++) {
				Movie movie = movieLibrary.getMyFavourites().get(i+c);

				MovieResultPoster movieResultPoster = new MovieResultPoster(movie.getTitle(), ImageUtilities.resize(movie.getImage(), 100, 148),
						movie.getAarstall(), movie.getID());

				movieResultPoster.addMouseListener(this);

				fiveSearchMoviePostersPanel.add(movieResultPoster);
			}
			favouritesView.add(fiveSearchMoviePostersPanel);
			favouritesView.repaint();
			applicationController.paintFavouritesPanel();
			}
		
		}
	}

	/**
	 * Navigates the user to the current movietab, and reads the clicked film to set the correct information
	 * @param mouseEvent mouse clicked
	 * @author Christoffer, Lars Petter
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		Movie favMovie = null;
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		for(Movie movie : movieLibrary.getMyFavourites()){
			if (movie.getID() ==  poster.getMovieID()){
				favMovie = movie;	
				moviePanelController.getMoviePanelView().addLoader();
				moviePanelController.setMoviePanelValues(favMovie);
				applicationController.setMoviePanelTab();
			}		
		}

	}
	/**
	 * Changes the color of the background to grey, so the user can see where the cursor is.
	 * @param mouseEvent mouse entered
	 * @author Eystein
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.DARK_GRAY);
		poster.setMovieTitleAreaBackgroud(Color.DARK_GRAY);
		poster.repaint();

	}
	/** Changes the color of the background back to black
	 * @param mouseEvent mouse exited
	 * @author Eystein
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.BLACK);
		poster.setMovieTitleAreaBackgroud(Color.BLACK);
		poster.repaint();

	}
	/** abstract method implementation
	 * 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		//Nothing to do
	}
	/** abstract method implementation
	 * 
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	
	/** 
	 * Gets the favouriteView
	 * @return FavouritesView
	 */
	public FavouritesView getFavouritesView() {
		return favouritesView;
	}
	
	/** 
	 * sets the favouritesView
	 * @param favouritesView
	 */
	public void setFavouritesView(FavouritesView favouritesView) {
		this.favouritesView = favouritesView;
	}



}