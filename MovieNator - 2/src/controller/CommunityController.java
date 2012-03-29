/**
 * @author Eystein Davoen
 * @author Christoffer Valland
 * @author Lars Petter Johnsen
 * @version 1.0
 */
package controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Movie;
import model.MovieLibrary;
import sql.ConnectionSql;
import utils.ImageUtilities;
import view.CommunityView;
import view.MovieResultPoster;


/**
 * @author ChristofferValland
 *
 */
public class CommunityController implements MouseListener, ListSelectionListener{
	/**
	 * Fields
	 */
	private CommunityView communityView;
	private ConnectionSql connectionSql;
	private ArrayList<String> users;
	private JList list;
	private ApplicationController applicationController;
	private MoviePanelController moviePanelController;
	private MovieLibrary movieLibrary;
	private LinkedHashMap<Long, Movie> othersFavourites;
	private ProgressBarController progressBarController;


	/**
	 * @param communityView
	 * @param connectionSql
	 */
	public CommunityController(CommunityView communityView, ConnectionSql connectionSql, ApplicationController applicationController,
			MoviePanelController moviePanelController, MovieLibrary movieLibrary, ProgressBarController progressBarController){

		this.applicationController = applicationController;
		this.moviePanelController = moviePanelController;
		this.progressBarController = progressBarController;
		this.communityView = communityView;
		this.connectionSql = connectionSql;
		this.users = new ArrayList<String>();
		this.list = new JList();
		this.movieLibrary = movieLibrary;


		try {
			connectionSql.otherUsers();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(String user:connectionSql.getUsers()){
			users.add(user);
		}

		list.setListData(users.toArray());
		//		list.addMouseListener(this);
		list.addListSelectionListener(this);
		communityView.getOtherUsersPanel().add(list);
	}

	/**
	 * Inner-class extending SwingWorker. Handles the input operation when user clicks another's username in the Other User list.
	 * @author Eystein Davoen
	 *
	 */
	class GetUserMovies extends SwingWorker<Void, Void> {

		/**
		 * Overrides the doInBackground method. Runs the setOthersMovies in worker thread
		 * @throws SQLException
		 * @return null
		 * @author Eystein Davoen
		 */
		@Override
		protected Void doInBackground() throws Exception {
			try {
				setOthersMovies(connectionSql.othersFavouriteMovies(list.getModel().getElementAt(list.getSelectedIndex()).toString()));

			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			return null;
		}

		/**
		 * Overrides the done method. When worker thread is finished, stops the progressbar and repaints the communityView panel.
		 * @author Eystein Davoen
		 */
		@Override
		public void done() {
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(false);
			applicationController.getApplicationView().repaint();
			communityView.repaint();
		}
	}

	/**
	 * Inner-class handling getting movie object
	 * @author Eystein Davoen
	 */
	class GetMovie extends SwingWorker<Void, Void> {
		private  Long movieID;
		
		public GetMovie(Long movieID) {
			this.movieID = movieID;
		}

		/**
		 * Overrides doInBackground. Handles downloading movie from database and adding to moviePanel tab
		 * @return null
		 * @author Eystein Davoen
		 */
		@Override
		protected Void doInBackground() throws Exception {
			Movie currentMovie = othersFavourites.get(movieID);			
			moviePanelController.setMoviePanelValues(currentMovie);

			return null;
		}

		/**
		 * When worker thread is finished, stops progressbar and repaint moviePanelView
		 * @author Eystein Davoen
		 */
		@Override
		public void done() {
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(false);
			moviePanelController.getMoviePanelView().repaint();
		}
	}

	/** When username clicked, paints a panel with the users favourite movies
	 * @author Lars P og Christoffer V
	 */
	public void setOthersMovies(LinkedHashMap<Long, Movie> otherFavourites){
		communityView.getCommunityMoviePanel().removeAll();
		this.othersFavourites = otherFavourites;
		
		for(Movie movie : otherFavourites.values()){
			for(int c = 0; c < otherFavourites.size(); c+=5){
				JPanel fiveFavouriteMovies = new JPanel();
				fiveFavouriteMovies.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
				MovieResultPoster movieResultPoster = new MovieResultPoster(movie.getTitle(), ImageUtilities.resize(movie.getImage(), 100, 148),
						movie.getAarstall(), movie.getID());
				movieResultPoster.addMouseListener(this);
				fiveFavouriteMovies.add(movieResultPoster);
				communityView.getCommunityMoviePanel().add(fiveFavouriteMovies);
				communityView.getCommunityMoviePanel().repaint();
			}
		}
	}

	/**
	 * When element clicked, remove all from community panel. Create new instance of GetUserMovies worker thread
	 * and repaint.
	 * @author Eystein
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (! e.getValueIsAdjusting()) {
			communityView.getCommunityMoviePanel().removeAll();
			communityView.getCommunityMoviePanel().validate();
			movieLibrary.getOthersFavourites().clear();
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(true);
			GetUserMovies getUserMovies = new GetUserMovies();
			getUserMovies.execute();
		}
		communityView.getCommunityMoviePanel().repaint();
	}

	/**
	 * When mouse i clicked, creates new instance of GetMovie, and executes it.
	 * @author Eystein Davoen
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		applicationController.setMoviePanelTab();
		MovieResultPoster poster = (MovieResultPoster) arg0.getComponent();
		long movieID = poster.getMovieID();
		moviePanelController.getMoviePanelView().getSimilarPanel().removeAll();
		moviePanelController.getMoviePanelView().addLoader();
		GetMovie getMovie = new GetMovie(movieID);
		getMovie.execute();
	}

	/**
	 * Change color of panel when mouse enters panel
	 * @author Eystein
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.DARK_GRAY);
		poster.setMovieTitleAreaBackgroud(Color.DARK_GRAY);
		poster.repaint();
	}

	/**
	 * Return panel to original color when mouse exits
	 * @author Eystein
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.BLACK);
		poster.setMovieTitleAreaBackgroud(Color.BLACK);
		poster.repaint();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	/**
	 * @return communityView
	 */
	public CommunityView getCommunityView() {
		return communityView;
	}

	/**
	 * @param communityView
	 */
	public void setCommunityView(CommunityView communityView) {
		this.communityView = communityView;
	}

	/**
	 * @return users
	 */
	public ArrayList<String> getUsers() {
		return users;
	}

	/**
	 * @param users
	 */
	public void setUsers(ArrayList<String> users) {
		this.users = users;
	}


}
