/**
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Arrays;


import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import IO.MovieFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import model.Movie;
import model.MovieLibrary;
import sql.ConnectionSql;
import utils.ErrorHandler;
import utils.ImageUtilities;
import view.MoviePanelView;
import view.SimilarPoster;

public class MoviePanelController extends Thread implements ActionListener, MouseListener {

	/**
	 * Fields
	 */
	private ApplicationController applicationController;
	private MoviePanelView moviePanelView;
	private MovieLibrary movieLibrary;
	private Movie movie;
	private ConnectionSql connectionSql;
	private String error;
	private MovieFactory movieFac;
	private ProgressBarController progressBarController;


	/**
	 * 
	 * @param view
	 * @param movieLibrary
	 * @param applicationController
	 */
	public MoviePanelController(MoviePanelView view, MovieLibrary movieLibrary,
			ApplicationController applicationController, ConnectionSql connectionSql,
			MovieFactory movieFac, ProgressBarController progressBarController)
	{

		this.applicationController = applicationController;
		moviePanelView = view;
		moviePanelView.getAddToFavouriteButton().addActionListener(this);
		moviePanelView.getLink().addActionListener(this);
		this.movieLibrary = movieLibrary;
		this.connectionSql = connectionSql;
		this.movieFac = movieFac;
		this.progressBarController = progressBarController;


	}


	/**
	 * Inner-class extending SwingWorker handling getting similar movies
	 * @author Eystein Davoen
	 */
	class GetSimilarMovie extends SwingWorker<Void, Void> {

		/**
		 * Fields
		 */
		private Long movieID;

		/**
		 * Constructor
		 * @param movieID
		 */
		public GetSimilarMovie(Long movieID) {

			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(true);
			this.movieID = movieID;
		}

		/**
		 * Overrides the doInBackground methods. Handles the setSimilar in separate worker thread
		 * @author Eystein Davoen
		 * @return null
		 */
		@Override
		protected Void doInBackground() throws Exception {

			setSimilar(movieID);				

			return null;
		}

		/**
		 * When thread is done, stops progressbar and changes tab to "current"
		 */
		@Override
		public void done() {
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(false);
			applicationController.setMoviePanelTab();
		}

	}

	/**
	 * Inner-Class extending SwingWorker handling getting movie
	 * @author Eystein Davoen
	 */
	class GetMovie extends SwingWorker<Void, Void> {

		/**
		 * Fields
		 */
		private Long movieID;

		/**
		 * Constructor
		 * @param movieID
		 */
		public GetMovie(Long movieID) {

			this.movieID = movieID;
		}

		/**
		 * Overrides doInBackground method, handles getting movie and setting moviePanelValues in separate worker thread
		 * @author Eystein Davoen
		 * @return null
		 */
		@Override
		protected Void doInBackground() throws Exception {

			setMoviePanelValues(movieFac.getMovieById(movieID));

			return null;
		}
	}


	/**
	 * sets the vaules for the moviePanel
	 * @param movie
	 */
	public void setMoviePanelValues(Movie movie) {

		progressBarController.getProgressBarView().getProgressBar().setIndeterminate(true);
		this.movie = movie;
		moviePanelView.getTitleAndYearTextArea().setText(movie.getTitle() + " (" + movie.getAarstall() + ")");
		moviePanelView.getSynopsisTextArea().setText(movie.getSynopsis());
		moviePanelView.getMpaaTextArea().setText("Rated: " + movie.getMPAArating());
		moviePanelView.getRunTimeTextArea().setText(movie.getRuntime() + " minutes");
		moviePanelView.getMoviePosterLabel().setIcon(new ImageIcon(movie.getImage()));
		moviePanelView.getCastAndDirectorTextArea().setText(movie.getCast());
		moviePanelView.getCriticScoreLabel().setIcon(new ImageIcon(movie.getDiceImage()));
		moviePanelView.getGenreTextArea().setText(String.valueOf(movie.getGenre()));

		if(movie.isFavourited() == true){
			moviePanelView.getAddToFavouriteButton().setText("Remove favourited");
		} else {
			moviePanelView.getAddToFavouriteButton().setText("Add to favourite");
		}

		moviePanelView.getLink();
		GetSimilarMovie getSimilarMovie = new GetSimilarMovie(movie.getID());
		getSimilarMovie.execute();
		moviePanelView.repaint();
	}	


	/**
	 * @author Lars Petter Johnsen & Aysha Khan
	 * adds a movie to the favouriteList, if user is not logged in get error dialog
	 */
	public void addToFavourites(){
		
		if(applicationController.getActiveUser() == null) {
			String error = "You have to be logged in to do this";
			ErrorHandler.getErrorMessage(error);
		} else {
			movie.setFavourited(true);
			moviePanelView.getAddToFavouriteButton().setText("Remove favourited");
			movieLibrary.setFavouriteMovie(movie);
			applicationController.getFavouritesController().setFavouritePosters();
			if(applicationController.getActiveUser() == null) {
				moviePanelView.getAddToFavouriteButton().setEnabled(false);
			}

			try {
				connectionSql.addFavourite(movie);
			} catch (SQLException e1) {

				error = "You need to log in to your account to add a favourite";
				ErrorHandler.getErrorMessage(error);

			}
		}
	}

	/**
	 * Removes a movie from the favoritelist
	 * @throws SQLException
	 */
	public void removeFromFavourites() {
		movie.setFavourited(false);
		movieLibrary.removeFavouriteMovie(movie);
		moviePanelView.getAddToFavouriteButton().setText("Add to favourite");
		applicationController.getFavouritesController().setFavouritePosters();

		try {
			connectionSql.removeFavourite(movie);
		} catch (SQLException e) {
			error = "Can«t remove a movie which is not favourited.";
			ErrorHandler.getErrorMessage(error);
		}
	}

	/**
	 * Opens standard browser on a computer
	 * Internett
	 */
	public void launchBrowser(String target){
		final String[] browsers = { "google-chrome", "firefox", "opera",
				"epiphany", "konqueror", "conkeror", "midori", "kazehakase", "mozilla" };
		final String errMsg = "Error attempting to launch web browser";

		try {  //attempt to use Desktop library from JDK 1.6+
			Class<?> d = Class.forName("java.awt.Desktop");
			d.getDeclaredMethod("browse", new Class[] {java.net.URI.class}).invoke(
					d.getDeclaredMethod("getDesktop").invoke(null),
					new Object[] {java.net.URI.create(target)});
			//above code mimicks:  java.awt.Desktop.getDesktop().browse()
		}
		catch (Exception ignore) {  //library not available or failed
			String osName = System.getProperty("os.name");
			try {
				if (osName.startsWith("Mac OS")) {
					Class.forName("com.apple.eio.FileManager").getDeclaredMethod(
							"openURL", new Class[] {String.class}).invoke(null,
									new Object[] {target});
				}
				else if (osName.startsWith("Windows"))
					Runtime.getRuntime().exec(
							"rundll32 url.dll,FileProtocolHandler " + target);
				else { //assume Unix or Linux
					String browser = null;
					for (String b : browsers)
						if (browser == null && Runtime.getRuntime().exec(new String[]
								{"which", b}).getInputStream().read() != -1)
							Runtime.getRuntime().exec(new String[] {browser = b, target});
					if (browser == null)
						throw new Exception(Arrays.toString(browsers));
				}
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, errMsg + "\n" + e.toString());
			}
		}
	}

	/**
	 * Creates clickable posters from similar movies. 
	 * Takes a JSON array stored in the movie object, and converts it to a new movie object
	 * @author Eystein, Lars Petter, Christoffer
	 */
	public void setSimilar(Long movieID) {
		movieLibrary.getSimilarMovies().clear();
		JsonArray jsonSimilar = movie.getSimilar();
		JPanel fiveSimilarMoviesPanel = new JPanel();
		fiveSimilarMoviesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));

		if(jsonSimilar != null){
			for(JsonElement e : jsonSimilar){
				movieLibrary.getSimilarMovies().add(movieFac.jsonMovieToMovie(e));
			}
			for (Movie movie : movieLibrary.getSimilarMovies())	{			
				SimilarPoster similarPoster = new SimilarPoster(movie.getTitle(), ImageUtilities.resize(movie.getImage(), 60, 89),
						movie.getID(), movie.getAarstall() );
				similarPoster.addMouseListener(this);
				fiveSimilarMoviesPanel.add(similarPoster);
			}
			moviePanelView.getSimilarPanel().removeAll();
			moviePanelView.getSimilarPanel().validate();
			moviePanelView.getSimilarPanel().add(fiveSimilarMoviesPanel);
			moviePanelView.repaint();								
		}
	}


	/**
	 * EventListener the checks the button for changes.
	 * @author Lars Petter, Christoffer
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand == "Add to favourite"){
			addToFavourites();
			moviePanelView.repaint();
		} else if(actionCommand == "Remove favourited") {
			removeFromFavourites();
			moviePanelView.repaint();
		} else if(actionCommand == "Full Reviews Online"){
			launchBrowser(movie.getLink());
		}
	}

	/**
	 * When mouse clicked creates new worker thread and executes it, starts progressbar and adds a loading image in similarPanel.
	 * @author Eystein Davoen
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		SimilarPoster poster = (SimilarPoster) e.getComponent();
		long movieID = poster.getMovieID();
		moviePanelView.getSimilarPanel().removeAll();
		moviePanelView.addLoader();
		moviePanelView.getSimilarPanel().validate();
		moviePanelView.repaint();
		progressBarController.getProgressBarView().getProgressBar().setIndeterminate(true);
		GetMovie getMovie = new GetMovie(movieID);
		getMovie.execute();

	}

	/**
	 * When mouse enters changes the color of the posterPanel
	 * @author Eystein
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		SimilarPoster poster = (SimilarPoster) e.getComponent();
		poster.setBackground(Color.DARK_GRAY);
		poster.repaint();

	}
	/**
	 * when mouse exits returns panel to original color
	 * @author Eystein Davoen
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		SimilarPoster poster = (SimilarPoster) e.getComponent();
		poster.setBackground(Color.BLACK);
		poster.repaint();

	}

	/**
	 * Abstract method implementation
	 */
	@Override
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * abstract method implementation
	 */
	@Override
	public void mouseReleased(MouseEvent e) {

	}
	/**
	 * @return the moviePanelView
	 */
	public MoviePanelView getMoviePanelView() {
		return moviePanelView;
	}
	/**
	 * @param moviePanelView the moviePanelView to set
	 */
	public void setMoviePanelView(MoviePanelView moviePanelView) {
		this.moviePanelView = moviePanelView;
	}


}
