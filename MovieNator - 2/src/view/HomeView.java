/**
 * @author Eystein Dav¿en
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder; 

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;

public class HomeView extends JPanel {
	
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel favouritesPanel;
	private JPanel lastVisitedPanel;
	
	/**
	 * Constructor
	 * @param logonView
	 */
	public HomeView(LogonView logonView) {

		setBackground(Color.WHITE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		favouritesPanel = new JPanel();
		favouritesPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		favouritesPanel.setPreferredSize(new Dimension(700, 220));
		add(favouritesPanel);
		
		favouritesPanel.add(logonView);
		
		lastVisitedPanel = new JPanel();
		lastVisitedPanel.setBorder(new TitledBorder(null, "Last Visited", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(lastVisitedPanel);
		lastVisitedPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		lastVisitedPanel.setPreferredSize(new Dimension(700, 280));
	}


	/**
	 * Getter for favouritePanel
	 * @return the favouritesPanel
	 */
	public JPanel getFavouritesPanel() {
		return favouritesPanel;
	}

	/**
	 * Setter for favouritePanel
	 * @param favouritesPanel the favouritesPanel to set
	 */
	public void setFavouritesPanel(JPanel favouritesPanel) {
		this.favouritesPanel = favouritesPanel;
	}

	/**
	 * Getter for lastVisitedPanel
	 * @return the lastVisitedPanel
	 */
	public JPanel getLastVisitedPanel() {
		return lastVisitedPanel;
	}

	/**
	 * Setter for lastVisitedPanel
	 * @param lastVisitedPanel the lastVisitedPanel to set
	 */
	public void setLastVisitedPanel(JPanel lastVisitedPanel) {
		this.lastVisitedPanel = lastVisitedPanel;
	}

}
