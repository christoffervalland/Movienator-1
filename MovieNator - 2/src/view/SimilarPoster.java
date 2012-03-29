/**

 * @author Eystein Davoen
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package view;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

public class SimilarPoster extends JPanel {
	
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel posterThumbnailLabel;
	private long movieID;
	private String movieRelease;
	private String movieTitle;
	
	/**
	 * Constructor
	 * @param movieTitle
	 * @param posterThumbnailImage
	 * @param movieID
	 * @param movieRealease
	 */
	public SimilarPoster(String movieTitle, BufferedImage posterThumbnailImage, long movieID, String movieRealease) {
		
		this.movieRelease = movieRealease;
		this.movieTitle = movieTitle;
		this.movieID = movieID;
		
		setToolTipText(movieTitle + " (" + movieRealease + ")");
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(70, 100));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		posterThumbnailLabel = new JLabel(new ImageIcon(posterThumbnailImage));
		add(posterThumbnailLabel);
		

	}

	/**
	 * Getter for movieID
	 * @return the movieID
	 */
	public long getMovieID() {
		return movieID;
	}

	/**
	 * Getter for movieRelease
	 * @return the movieRelease
	 */
	public String getMovieRelease() {
		return movieRelease;
	}

	/**
	 * Setter for movieRelease
	 * @param movieRelease the movieRelease to set
	 */
	public void setMovieRelease(String movieRelease) {
		this.movieRelease = movieRelease;
	}

	/**
	 * Getter for movieTitle
	 * @return the movieTitle
	 */
	public String getMovieTitle() {
		return movieTitle;
	}

	/**
	 * Setter for movieTitle
	 * @param movieTitle the movieTitle to set
	 */
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

}
