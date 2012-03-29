/**
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import javax.swing.ImageIcon;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

public class MovieResultPoster extends JPanel {
	
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel posterThumbnailLabel;
	private JTextPane movieTitleArea;
	private long movieID;
	
	/**
	 * Create the panel of movieresults
	 */
	public MovieResultPoster(String movieTitle, BufferedImage posterThumbnailImage, String movieReleaseDate, long movieID) {
		setForeground(Color.WHITE);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(130, 230));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		
		posterThumbnailLabel = new JLabel(new ImageIcon(posterThumbnailImage));
		add(posterThumbnailLabel);
		
		movieTitleArea = new JTextPane();
		movieTitleArea.setText(movieTitle + " (" + movieReleaseDate + ")");
		movieTitleArea.setBackground(Color.BLACK);
		movieTitleArea.setForeground(Color.WHITE);

		movieTitleArea.setEditable(false);
		add(movieTitleArea);
		movieTitleArea.setSize(125, 25);
		movieTitleArea.setPreferredSize(new Dimension(125, 45));
		
		StyledDocument doc = movieTitleArea.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		this.movieID = movieID;

	}

	/**
	 * @return the movieID
	 */
	public long getMovieID() {
		return movieID;
	}
	
	/**
	 * Sets background color
	 * @param n
	 */
	public void setMovieTitleAreaBackgroud(Color n) {
		movieTitleArea.setBackground(n);
	}
	
	/**
	 * Area for movie title
	 * @return movieTitleArea
	 */
	public JTextPane getMovieTitleArea() {
		return movieTitleArea;
	}

}
