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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import model.Movie;
import model.MovieLibrary;
import utils.ImageUtilities;
import view.HomeView;
import view.MovieResultPoster;

public class HomeController implements MouseListener {
	
	/**
	 * Fields
	 */
	private HomeView homeView;
	private MovieLibrary lib;
	private ApplicationController applicationController;
	private MoviePanelController moviePanelController;
	private GetMovie getMovie;
	private ProgressBarController progressBarController;

	/**
	 * 
	 * @param view
	 * @param movieLibrary
	 * @param applicationController
	 * @param moviePanelController
	 */
	public HomeController(HomeView view, MovieLibrary movieLibrary, ApplicationController applicationController, 
			MoviePanelController moviePanelController, ProgressBarController progressBarController)
	{
		homeView = view;
		this.moviePanelController = moviePanelController;
		lib = movieLibrary;
		this.applicationController = applicationController;
		this.progressBarController = progressBarController;
		
	}
	
	class GetMovie extends SwingWorker<Void, Void> {
		
		private Long movieID;
		
		public GetMovie(Long movieID) {
			this.movieID = movieID;
		}

		@Override
		protected Void doInBackground() throws Exception {
			// TODO Auto-generated method stub
			moviePanelController.setMoviePanelValues(getVisitedMovie(movieID));
			return null;
		}
		
		@Override
		public void done() {
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(false);
			applicationController.setMoviePanelTab();

		}
		
	}
	
	
	public int removeDuplicates()
	{
	  int duplicates = 0;

	  for ( int i = 0; i < lib.getLastVisitedMovies().size(); i++ )
	  {
	     for ( int j = 0; j < lib.getLastVisitedMovies().size(); j++ )
	     {
	        if ( i == j )
	        {
	          // Do nothing
	        }

	        else if ( lib.getLastVisitedMovies().get( j ).equals( lib.getLastVisitedMovies().get( i ) ) )
	        {
	        	lib.getLastVisitedMovies().remove( j );
	           duplicates++;
	        }
	     }
	 }
	   return duplicates;
	}
	
	/**
	 * adds the last visted movies to the front page.
	 */
		public void addHistory(){
			
			JPanel fiveSearchMoviePostersPanel = new JPanel();
			fiveSearchMoviePostersPanel.getComponentCount();
			
			removeDuplicates();
			
			for (Movie movie :lib.getLastVisitedMovies())	{			
				
				MovieResultPoster movieResultPoster = new MovieResultPoster(movie.getTitle(), ImageUtilities.resize(movie.getImage(), 100, 148),
				movie.getAarstall(), movie.getID());
				movieResultPoster.addMouseListener(this);
				
				fiveSearchMoviePostersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 17, 5));
				fiveSearchMoviePostersPanel.add(movieResultPoster);
				JPanel HistoryPanel = homeView.getLastVisitedPanel();
				
				if (fiveSearchMoviePostersPanel.getComponentCount() <= 4){
					HistoryPanel.removeAll();
					HistoryPanel.add(fiveSearchMoviePostersPanel);
					HistoryPanel.repaint();						
				}
				
				else{
				
		
				}
			}
		}
	
	/**
	 * abstract method implementation
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		long movieID = poster.getMovieID();
		progressBarController.getProgressBarView().getProgressBar().setIndeterminate(true);
		getMovie = new GetMovie(movieID);
		getMovie.execute();
		
		


		

		   
		   	
	}
	
	/**
	 * 
	 * @param movieID
	 * @return
	 */
	public Movie getVisitedMovie(long movieID) {
		
		Movie returnMovie = null;
		for (int i = 0; i < lib.getLastVisitedMovies().size(); i++) {
			Movie movie = lib.getLastVisitedMovies().get(i);
			if(movieID == movie.getID()) {
				returnMovie = movie;
		
		}
		}
		
		return returnMovie;
	}	
		
	/**
	 * abstract method implementation
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
	 * Sets the background color to grey
	 * @param mouseEvent mouse entered
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.DARK_GRAY);
		poster.setMovieTitleAreaBackgroud(Color.DARK_GRAY);
		poster.repaint();
		
	}
	/**
	 * Sets the background color to black
	 * @param mouseEvent mouse exited
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		MovieResultPoster poster = (MovieResultPoster) e.getComponent();
		poster.setBackground(Color.BLACK);
		poster.setMovieTitleAreaBackgroud(Color.BLACK);
		poster.repaint();
		
	
		
	}
}

	

	

