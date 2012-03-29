/**
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package view;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import sql.ConnectionSql;

import IO.MovieFactory;

public class ApplicationView extends JFrame {
	
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	private SearchHeaderView searchHeaderView;
	private HomeView homeView;
	private FavouritesView favouritesView;
	private MoviePanelView moviePanelView;
	private SearchResultView searchResultView;
	private JTabbedPane tabbedPane;
	ProgressBarView progressbarView;
	OptionsFooterView optionsFooterView;
	private ConnectionSql connectionSql;
	
	
	private LogonView logonView; // logon view
	private CommunityView communityView;//community view
	
	/**
	 * 
	 * @param title
	 * @param homeView
	 * @param searchHeaderView
	 * @param favouritesView
	 * @param searchHistoryView
	 * @param searchResultView
	 * @param moviePanelView
	 */
	public ApplicationView(String title, HomeView homeView, SearchHeaderView searchHeaderView, 
		FavouritesView favouritesView, SearchResultView searchResultView, MoviePanelView moviePanelView,
		LogonView logonView, ProgressBarView progressBarView, OptionsFooterView optionsFooterView,
		CommunityView communityView, ConnectionSql connectionSql)
		
		
		{
		this.connectionSql = connectionSql;
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		getContentPane().setPreferredSize(new Dimension(800, 760));
		
		getContentPane().add(searchHeaderView);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setPreferredSize(new Dimension(800, 700));
		
		this.homeView = homeView;
		tabbedPane.addTab("Home", null, homeView, null);
		
		this.searchResultView = searchResultView;
		this.favouritesView = favouritesView;
		this.moviePanelView = moviePanelView;
		//LogonLogonLogonLogonLogonLogonLogonLogon
		this.logonView = logonView;
		//Community
		this.communityView = communityView;
		
		JScrollPane searchResultViewScrollPane = new JScrollPane(searchResultView);
		tabbedPane.addTab("Search Results", null, searchResultViewScrollPane, null);
		searchResultViewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		searchResultViewScrollPane.getVerticalScrollBar().setUnitIncrement(19);
		
		JScrollPane moviePanelViewScrollPane = new JScrollPane(moviePanelView);
		tabbedPane.addTab("Current", null, moviePanelViewScrollPane, null);
		
		JScrollPane favouritesViewScrollPane = new JScrollPane(favouritesView);
		tabbedPane.addTab("Favourites", null, favouritesViewScrollPane, null);
		
		//Community tab
		JScrollPane communityViewScrollPane = new JScrollPane(communityView);
		tabbedPane.addTab("Community", null, communityViewScrollPane, null);
		
		JPanel footerPanel = new JPanel();
		footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
		footerPanel.add(progressBarView);
		footerPanel.add(optionsFooterView);	
		
		getContentPane().add(tabbedPane);
		getContentPane().add(footerPanel);
		setPreferredSize(new Dimension(800, 760));
		setSize(800, 760);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	@Override
	public void dispose() {
		MovieFactory.instanceOf().saveFavourites();
		connectionSql.closeConnection();
		System.exit(EXIT_ON_CLOSE);
	}
	
	/**
	 * @return the searchHeaderView
	 */
	public SearchHeaderView getSearchHeaderView() {
		return searchHeaderView;
	}

	/**
	 * @param searchHeaderView the searchHeaderView to set
	 */
	public void setSearchHeaderView(SearchHeaderView searchHeaderView) {
		this.searchHeaderView = searchHeaderView;
	}

	/**
	 * @return the homeView
	 */
	public HomeView getHomeView() {
		return homeView;
	}

	/**
	 * @param homeView the homeView to set
	 */
	public void setHomeView(HomeView homeView) {
		this.homeView = homeView;
	}

	/**
	 * @return the favouritesView
	 */
	public FavouritesView getFavouritesView() {
		return favouritesView;
	}

	/**
	 * @param favouritesView the favouritesView to set
	 */
	public void setFavouritesView(FavouritesView favouritesView) {
		this.favouritesView = favouritesView;
	}

	/**
	 * @return the tabbedPane
	 */
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * @param tabbedPane the tabbedPane to set
	 */
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	/**
	 * LogonLogonLogonLogonLogon
	 * @return
	 */
	public LogonView getLogonView() {
		return logonView;
	}

	/**
	 * LogonLogonLogonLogonLogon
	 * @param logonView
	 */
	public void setLogonView(LogonView logonView) {
		this.logonView = logonView;
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


	/**
	 * @return the searchResultView
	 */
	public SearchResultView getSearchResultView() {
		return searchResultView;
	}


	/**
	 * @param searchResultView the searchResultView to set
	 */
	public void setSearchResultView(SearchResultView searchResultView) {
		this.searchResultView = searchResultView;
	}


	/**
	 * @return the progressbarView
	 */
	public ProgressBarView getProgressbarView() {
		return progressbarView;
	}


	/**
	 * @param progressbarView the progressbarView to set
	 */
	public void setProgressbarView(ProgressBarView progressbarView) {
		this.progressbarView = progressbarView;
	}


	/**
	 * @return the optionsFooterView
	 */
	public OptionsFooterView getOptionsFooterView() {
		return optionsFooterView;
	}


	/**
	 * @param optionsFooterView the optionsFooterView to set
	 */
	public void setOptionsFooterView(OptionsFooterView optionsFooterView) {
		this.optionsFooterView = optionsFooterView;
	}


	public CommunityView getCommunityView() {
		return communityView;
	}


	public void setCommunityView(CommunityView communityView) {
		this.communityView = communityView;
	}	
}
