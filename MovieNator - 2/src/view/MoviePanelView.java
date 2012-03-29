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
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Font;
public class MoviePanelView extends JPanel {
	
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JButton addToFavouriteButton;
	private JPanel titleAndYearPanel;
	private JPanel synopsisPanel;
	private JPanel genrePanel;
	private JPanel runtimePanel;
	private JPanel castPanel;
	private JPanel MPAAPanel;
	private JPanel runtimePanel_1;
	private JPanel studioPanel;
	private JPanel leftBottomPanel;
	private JPanel leftTopPanel;
	private JLabel criticScoreLabel;
	private JLabel userScoreLabel;
	private JTextArea titleAndYearTextArea;
	private JTextArea synopsisTextArea;
	private JTextArea mpaaTextArea;
	private JTextArea runTimeTextArea;
	private	JTextArea studioTextArea;
	private JTextArea castAndDirectorTextArea;
	private JTextArea genreTextArea;
	private JLabel moviePosterLabel;
	private JPanel panel;
	private JButton link;
	private JPanel similarPanel;

	private JLabel loaderLabel;
	

	/**
	 * Create the panel of moviePanel
	 */
	public MoviePanelView(BufferedImage posterBI, BufferedImage diceBI) {
		setBackground(new Color(238, 238, 238));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		leftPanel = new JPanel();
		add(leftPanel);
		leftPanel.setPreferredSize(new Dimension(200, 500));
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		leftTopPanel = new JPanel();
		leftPanel.add(leftTopPanel);
		leftTopPanel.setPreferredSize(new Dimension(220, 270));
		leftTopPanel.setMaximumSize(new Dimension(220, 270));
		
		moviePosterLabel = new JLabel(new ImageIcon(posterBI));
		leftTopPanel.add(moviePosterLabel);
		
		leftBottomPanel = new JPanel();
		leftPanel.add(leftBottomPanel);
		leftBottomPanel.setPreferredSize(new Dimension(300, 100));
		leftBottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel = new JPanel();
		leftBottomPanel.add(panel);
		
		criticScoreLabel = new JLabel(new ImageIcon(diceBI));
		panel.add(criticScoreLabel);
		
		addToFavouriteButton = new JButton("Add to favourite");
		leftBottomPanel.add(addToFavouriteButton);
		
		rightPanel = new JPanel();
		add(rightPanel);
		rightPanel.setPreferredSize(new Dimension(550, 500));
		rightPanel.setSize(500, 0);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		titleAndYearPanel = new JPanel();
		titleAndYearPanel.setBorder(new TitledBorder(null, "Title and Release Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rightPanel.add(titleAndYearPanel);
		titleAndYearPanel.setLayout(new BoxLayout(titleAndYearPanel, BoxLayout.X_AXIS));
		titleAndYearPanel.setPreferredSize(new Dimension(600, 55));
		
		titleAndYearTextArea = new JTextArea();
		titleAndYearTextArea.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		titleAndYearTextArea.setText("");
		titleAndYearTextArea.setBackground(new Color(238, 238, 238));
		titleAndYearPanel.add(titleAndYearTextArea);
		titleAndYearTextArea.setColumns(10);
		titleAndYearTextArea.setMaximumSize(new Dimension(600, 55));
		
		synopsisPanel = new JPanel();
		synopsisPanel.setBorder(new TitledBorder(null, "Synopsis", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rightPanel.add(synopsisPanel);
		synopsisPanel.setLayout(new BoxLayout(synopsisPanel, BoxLayout.X_AXIS));
		synopsisPanel.setPreferredSize(new Dimension(150, 100));
		
		synopsisTextArea = new JTextArea();
		synopsisTextArea.setBackground(new Color(238, 238, 238));
		synopsisTextArea.setText("");
		synopsisPanel.add(synopsisTextArea);
		synopsisTextArea.setLineWrap(true);
		synopsisTextArea.setWrapStyleWord(true);
		synopsisTextArea.setEditable(false);
		
		JScrollPane jscrollSynopsis = new JScrollPane(synopsisTextArea);
		synopsisPanel.add(jscrollSynopsis);		
		
		genrePanel = new JPanel();
		genrePanel.setBorder(new TitledBorder(null, "Genres", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rightPanel.add(genrePanel);
		genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.X_AXIS));
		genrePanel.setPreferredSize(new Dimension(600, 55));
		genrePanel.setMaximumSize(new Dimension(600, 55));
		
		genreTextArea = new JTextArea();
		genreTextArea.setBackground(new Color(238, 238, 238));
		genrePanel.add(genreTextArea);
		genreTextArea.setEditable(false);
		
		runtimePanel = new JPanel();
		rightPanel.add(runtimePanel);
		runtimePanel.setPreferredSize(new Dimension(100, 10));
		runtimePanel.setLayout(new BoxLayout(runtimePanel, BoxLayout.X_AXIS));
		runtimePanel.setMaximumSize(new Dimension(600, 55));
		runtimePanel.setPreferredSize(new Dimension(600, 55));
		
		MPAAPanel = new JPanel();
		MPAAPanel.setBorder(new TitledBorder(null, "MPAA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		runtimePanel.add(MPAAPanel);
		MPAAPanel.setLayout(new BoxLayout(MPAAPanel, BoxLayout.X_AXIS));
		MPAAPanel.setPreferredSize(new Dimension(100, 10));
		
		mpaaTextArea = new JTextArea();
		mpaaTextArea.setBackground(new Color(238, 238, 238));
		MPAAPanel.add(mpaaTextArea);
		mpaaTextArea.setEditable(false);
				
		runtimePanel_1 = new JPanel();
		runtimePanel_1.setBorder(new TitledBorder(null, "Runtime", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		runtimePanel.add(runtimePanel_1);
		runtimePanel_1.setLayout(new BoxLayout(runtimePanel_1, BoxLayout.X_AXIS));
		
		runTimeTextArea = new JTextArea();
		runTimeTextArea.setBackground(new Color(238, 238, 238));
		runtimePanel_1.add(runTimeTextArea);
		runTimeTextArea.setEditable(false);
		
		
		studioPanel = new JPanel();
		studioPanel.setBorder(new TitledBorder(null, "Studio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		runtimePanel.add(studioPanel);
		studioPanel.setLayout(new BoxLayout(studioPanel, BoxLayout.X_AXIS));
		
		studioTextArea = new JTextArea();
		studioTextArea.setBackground(new Color(238, 238, 238));
		studioPanel.add(studioTextArea);
		studioTextArea.setEditable(false);
		
		castPanel = new JPanel();
		castPanel.setBorder(new TitledBorder(null, "Director and Cast", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		rightPanel.add(castPanel);
		castPanel.setLayout(new BoxLayout(castPanel, BoxLayout.Y_AXIS));
		castPanel.setPreferredSize(new Dimension(150, 100));
		
		castAndDirectorTextArea = new JTextArea();
//		castAndDirectorTextArea.setPreferredSize(new Dimension(100, 100));
		castAndDirectorTextArea.setBackground(new Color(238, 238, 238));
		castAndDirectorTextArea.setEditable(false);
		JScrollPane jscroll = new JScrollPane(castAndDirectorTextArea);
		castPanel.add(jscroll);
		
		ImageIcon icon = new ImageIcon(
                    "./resources/loader.gif");
		loaderLabel = new JLabel(icon);
		
		similarPanel = new JPanel();
		similarPanel.setBorder(new TitledBorder(null, "Similar Movies", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		similarPanel.setPreferredSize(new Dimension(100,100));
//		addLoader();
		rightPanel.add(similarPanel);
		
		link = new JButton("Full Reviews Online");
		leftBottomPanel.add(link);		
	}
	
	public void addLoader() {
		similarPanel.add(loaderLabel);
	}
	

	/**
	 * @return the moviePosterLabel
	 */
	public JLabel getMoviePosterLabel() {
		return moviePosterLabel;
	}


	/**
	 * @param moviePosterLabel the moviePosterLabel to set
	 */
	public void setMoviePosterLabel(JLabel moviePosterLabel) {
		this.moviePosterLabel = moviePosterLabel;
	}


	/**
	 * @return the leftPanel
	 */
	public JPanel getLeftPanel() {
		return leftPanel;
	}


	/**
	 * @param leftPanel the leftPanel to set
	 */
	public void setLeftPanel(JPanel leftPanel) {
		this.leftPanel = leftPanel;
	}


	/**
	 * @return the rightPanel
	 */
	public JPanel getRightPanel() {
		return rightPanel;
	}


	/**
	 * @param rightPanel the rightPanel to set
	 */
	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}


	/**
	 * @return the addToFavouriteButton
	 */
	public JButton getAddToFavouriteButton() {
		return addToFavouriteButton;
	}


	/**
	 * @param addToFavouriteButton the addToFavouriteButton to set
	 */
	public void setAddToFavouriteButton(JButton addToFavouriteButton) {
		this.addToFavouriteButton = addToFavouriteButton;
	}


	/**
	 * @return the titleAndYearPanel
	 */
	public JPanel getTitleAndYearPanel() {
		return titleAndYearPanel;
	}


	/**
	 * @param titleAndYearPanel the titleAndYearPanel to set
	 */
	public void setTitleAndYearPanel(JPanel titleAndYearPanel) {
		this.titleAndYearPanel = titleAndYearPanel;
	}


	/**
	 * @return the synopsisPanel
	 */
	public JPanel getSynopsisPanel() {
		return synopsisPanel;
	}


	/**
	 * @param synopsisPanel the synopsisPanel to set
	 */
	public void setSynopsisPanel(JPanel synopsisPanel) {
		this.synopsisPanel = synopsisPanel;
	}


	/**
	 * @return the genrePanel
	 */
	public JPanel getGenrePanel() {
		return genrePanel;
	}


	/**
	 * @param genrePanel the genrePanel to set
	 */
	public void setGenrePanel(JPanel genrePanel) {
		this.genrePanel = genrePanel;
	}


	/**
	 * @return the runtimePanel
	 */
	public JPanel getRuntimePanel() {
		return runtimePanel;
	}


	/**
	 * @param runtimePanel the runtimePanel to set
	 */
	public void setRuntimePanel(JPanel runtimePanel) {
		this.runtimePanel = runtimePanel;
	}


	/**
	 * @return the castPanel
	 */
	public JPanel getCastPanel() {
		return castPanel;
	}


	/**
	 * @param castPanel the castPanel to set
	 */
	public void setCastPanel(JPanel castPanel) {
		this.castPanel = castPanel;
	}


	/**
	 * @return the mPAAPanel
	 */
	public JPanel getMPAAPanel() {
		return MPAAPanel;
	}


	/**
	 * @param mPAAPanel the mPAAPanel to set
	 */
	public void setMPAAPanel(JPanel mPAAPanel) {
		MPAAPanel = mPAAPanel;
	}


	/**
	 * @return the runtimePanel_1
	 */
	public JPanel getRuntimePanel_1() {
		return runtimePanel_1;
	}


	/**
	 * @param runtimePanel_1 the runtimePanel_1 to set
	 */
	public void setRuntimePanel_1(JPanel runtimePanel_1) {
		this.runtimePanel_1 = runtimePanel_1;
	}


	/**
	 * @return the studioPanel
	 */
	public JPanel getStudioPanel() {
		return studioPanel;
	}


	/**
	 * @param studioPanel the studioPanel to set
	 */
	public void setStudioPanel(JPanel studioPanel) {
		this.studioPanel = studioPanel;
	}


	/**
	 * @return the leftBottomPanel
	 */
	public JPanel getLeftBottomPanel() {
		return leftBottomPanel;
	}


	/**
	 * @param leftBottomPanel the leftBottomPanel to set
	 */
	public void setLeftBottomPanel(JPanel leftBottomPanel) {
		this.leftBottomPanel = leftBottomPanel;
	}


	/**
	 * @return the leftTopPanel
	 */
	public JPanel getLeftTopPanel() {
		return leftTopPanel;
	}


	/**
	 * @param leftTopPanel the leftTopPanel to set
	 */
	public void setLeftTopPanel(JPanel leftTopPanel) {
		this.leftTopPanel = leftTopPanel;
	}


	/**
	 * @return the criticScoreLabel
	 */
	public JLabel getCriticScoreLabel() {
		return criticScoreLabel;
	}


	/**
	 * @param criticScoreLabel the criticScoreLabel to set
	 */
	public void setCriticScoreLabel(JLabel criticScoreLabel) {
		this.criticScoreLabel = criticScoreLabel;
	}


	/**
	 * @return the userScoreLabel
	 */
	public JLabel getUserScoreLabel() {
		return userScoreLabel;
	}


	/**
	 * @param userScoreLabel the userScoreLabel to set
	 */
	public void setUserScoreLabel(JLabel userScoreLabel) {
		this.userScoreLabel = userScoreLabel;
	}


	/**
	 * @return the titleAndYearTextArea
	 */
	public JTextArea getTitleAndYearTextArea() {
		return titleAndYearTextArea;
	}


	/**
	 * @param titleAndYearTextArea the titleAndYearTextArea to set
	 */
	public void setTitleAndYearTextArea(JTextArea titleAndYearTextArea) {
		this.titleAndYearTextArea = titleAndYearTextArea;
	}


	/**
	 * @return the synopsisTextArea
	 */
	public JTextArea getSynopsisTextArea() {
		return synopsisTextArea;
	}


	/**
	 * @param synopsisTextArea the synopsisTextArea to set
	 */
	public void setSynopsisTextArea(JTextArea synopsisTextArea) {
		this.synopsisTextArea = synopsisTextArea;
	}


	/**
	 * @return the mpaaTextArea
	 */
	public JTextArea getMpaaTextArea() {
		return mpaaTextArea;
	}


	/**
	 * @param mpaaTextArea the mpaaTextArea to set
	 */
	public void setMpaaTextArea(JTextArea mpaaTextArea) {
		this.mpaaTextArea = mpaaTextArea;
	}


	/**
	 * @return the runTimeTextArea
	 */
	public JTextArea getRunTimeTextArea() {
		return runTimeTextArea;
	}


	/**
	 * @param runTimeTextArea the runTimeTextArea to set
	 */
	public void setRunTimeTextArea(JTextArea runTimeTextArea) {
		this.runTimeTextArea = runTimeTextArea;
	}


	/**
	 * @return the studioTextArea
	 */
	public JTextArea getStudioTextArea() {
		return studioTextArea;
	}


	/**
	 * @param studioTextArea the studioTextArea to set
	 */
	public void setStudioTextArea(JTextArea studioTextArea) {
		this.studioTextArea = studioTextArea;
	}


	/**
	 * @return the castAndDirectorTextArea
	 */
	public JTextArea getCastAndDirectorTextArea() {
		return castAndDirectorTextArea;
	}


	/**
	 * @param castAndDirectorTextArea the castAndDirectorTextArea to set
	 */
	public void setCastAndDirectorTextArea(JTextArea castAndDirectorTextArea) {
		this.castAndDirectorTextArea = castAndDirectorTextArea;
	}


	/**
	 * @return the genreTextArea
	 */
	public JTextArea getGenreTextArea() {
		return genreTextArea;
	}


	/**
	 * @param genreTextArea the genreTextArea to set
	 */
	public void setGenreTextArea(JTextArea genreTextArea) {
		this.genreTextArea = genreTextArea;
	}


	public JButton getLink() {
		return link;
	}


	public void setLink(JButton link) {
		this.link = link;
	}


	/**
	 * SIMILARSIMILARSIMILARSIMILARSIMILARSIMILAR
	 * @return
	 * @author ChristofferValland
	 */
	public JPanel getSimilarPanel() {
		return similarPanel;
	}

	/**
	 * SIMILARSIMILARSIMILARSIMILARSIMILARSIMILAR
	 * @return
	 * @author ChristofferValland
	 */
	public void setSimilarPanel(JPanel similarPanel) {
		this.similarPanel = similarPanel;
	}


	/**
	 * @return the loaderLabel
	 */
	public JLabel getLoaderLabel() {
		return loaderLabel;
	}


	/**
	 * @param loaderLabel the loaderLabel to set
	 */
	public void setLoaderLabel(JLabel loaderLabel) {
		this.loaderLabel = loaderLabel;
	}

	
}
