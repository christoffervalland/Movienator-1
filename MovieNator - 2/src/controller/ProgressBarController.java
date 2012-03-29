/**
 * Controller for the progressBarPanel.
 * @author Eystein F. Davoen
 */

package controller;

import view.ProgressBarView;

public class ProgressBarController 
{
	/**
	 * Fields
	 */
	private ProgressBarView progressBarView;
	
	/**
	 * Constructor
	 * @param view
	 */
	public ProgressBarController(ProgressBarView view){
		
		this.progressBarView = view;
	}

	/**
	 * Get method for the progressBarView
	 * @return the progressBarView
	 */
	public ProgressBarView getProgressBarView() {
		return progressBarView;
	}
}



