/**
 * ProgressBar view
 * @author Eystein Davoen
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class ProgressBarView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JProgressBar progressBar;
	
	
	public ProgressBarView() {
		
		this.setPreferredSize(new Dimension(200, 40));
		setBackground(Color.GRAY);
		this.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		
		progressBar = new JProgressBar();
		this.add(progressBar);	
	}


	/**
	 * Getter for progresbar
	 * @return the progressBar
	 */
	public JProgressBar getProgressBar() {
		return progressBar;
	}


	/**
	 * Setter for progressbar
	 * @param progressBar the progressBar to set
	 */
	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	
}