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
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import model.Movie;
import model.MovieLibrary;
import utils.ImageUtilities;
import view.MovieResultPoster;
import view.SearchResultView;

public class SearchResultController implements MouseListener {

	/**
	 * Fields
	 */
	private SearchResultView searchResultView;
	private HashMap<Long, Movie> movies;
	private MoviePanelController moviePanelController;
	private ApplicationController applicationController;
	private MovieLibrary lib;
	private HomeController hCon;
	private ProgressBarController progressBarController;
	private Long currentMovieID;
	private GetMovies getMovies;

	/**
	 * 
	 * @param view
	 * @param moviePanelController
	 * @param applicationController
	 * @param movieLibrary
	 * @param hController
	 */
	public SearchResultController(SearchResultView view, MoviePanelController moviePanelController,
			ApplicationController applicationController, MovieLibrary movieLibrary, HomeController hController, ProgressBarController progressbarController)
	{
		searchResultView = view;
		this.moviePanelController = moviePanelController;
		this.applicationController = applicationController;
		this.lib = movieLibrary;
		this.hCon = hController;
		this.progressBarController = progressbarController;
	}

	/**
	 * Inner-class that extends SwingWorker
	 * @author Eystein Davoen
	 */
	class GetMovies extends SwingWorker<Void, Void> {

		private Long movieID;

		/**
		 * Constructor for GetMovies
		 * @param movieID
		 */
		public GetMovies(Long movieID) {

			this.movieID = movieID;

		}

		/**
		 * Overrides the doInBackground method. Runs the setMoviePanelValues in a worker thread.
		 * @return null
		 * @author Eystein Davoen
		 */
		@Override
		protected Void doInBackground() throws Exception {
			boolean running = true;
			while(running) {
				moviePanelController.setMoviePanelValues(movies.get(movieID));
				running = false;
			}

			return null;
		}

		/**
		 * Overrides the done method. When doInBackground is finished, change tab in applicationView to "current", 
		 * add movieID to the last visited list and sets the progressbar to indeterminate.
		 * @author Eystein Davoen
		 */
		@Override
		public void done() {
			applicationController.setMoviePanelTab();
			checkLastVisited(movieID);	
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(false);

		}

	}

	/**
	 * Method that handles the painting of the resultposters of every movie object in the map.
	 * If map is empty, i.e., no movies found, show a label informing user of this.
	 * @param movies Map of movies
	 */
	public void setSearchResults(HashMap<Long, Movie> movies)
	{
		searchResultView.removeAll();
		this.movies = movies;

		if(!movies.isEmpty()) {
			ArrayList<Movie> movieList = new ArrayList<Movie>(movies.values());


			for(int i = 0; i < movieList.size(); i+=5) {


				JPanel fiveSearchMoviePostersPanel = new JPanel();
				fiveSearchMoviePostersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));



				for(int c = 0; c < 5 && i+c < movieList.size(); c++) {
					Movie movie = movieList.get(i+c);

					MovieResultPoster movieResultPoster = new MovieResultPoster(movie.getTitle(), ImageUtilities.resize(movie.getImage(), 100, 148),
							movie.getAarstall(), movie.getID());

					movieResultPoster.addMouseListener(this);

					fiveSearchMoviePostersPanel.add(movieResultPoster);


				}
				searchResultView.add(fiveSearchMoviePostersPanel);

			}
		} else {
			searchResultView.add(new JLabel("Found no movies with that title"));
		}
	}

	/**
	 * EventListener for 
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		long movieID = poster.getMovieID();
		moviePanelController.getMoviePanelView().getSimilarPanel().removeAll();
		moviePanelController.getMoviePanelView().addLoader();
		applicationController.setMoviePanelTab();
		progressBarController.getProgressBarView().getProgressBar().setIndeterminate(true);
		getMovies = new GetMovies(movieID);
		getMovies.execute();
	}

	/**
	 * When mouse enters change colour of panel
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.DARK_GRAY);
		poster.setMovieTitleAreaBackgroud(Color.DARK_GRAY);
		poster.repaint();

	}
	/**
	 * When mouse exits return panel to original colour
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.BLACK);
		poster.setMovieTitleAreaBackgroud(Color.BLACK);
		poster.repaint();

	}

	/**
	 * Abstract method implementation
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * abstract method implementation
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param movieID
	 */
	public void checkLastVisited(long movieID) {
		if(!lib.lastVisitedMovies.isEmpty()) {
			setLastVisited(movieID);	
		} 
		else {
			setLastVisited(movieID);
		}
	}

	/**
	 *  
	 * @param movieID
	 */
	public void setLastVisited(long movieID) {
		if(lib.lastVisitedMovies.size() < 5){
			lib.lastVisitedMovies.add(movies.get(movieID));
			hCon.addHistory();
		} else{
			lib.getLastVisitedMovies().remove(0);
			lib.lastVisitedMovies.add(movies.get(movieID));
			hCon.addHistory();
		}
	}

	/**
	 * @return the currentMovieID
	 */
	public Long getCurrentMovieID() {
		return currentMovieID;
	}

	/**
	 * @param currentMovieID the currentMovieID to set
	 */
	public void setCurrentMovieID(Long currentMovieID) {
		this.currentMovieID = currentMovieID;
	}
}


