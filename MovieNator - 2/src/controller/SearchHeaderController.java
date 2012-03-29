/**
 * @author Eystein Davoen
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import model.MovieLibrary;
import view.SearchHeaderView;

public class SearchHeaderController implements ActionListener {

	/**
	 * Fields
	 */
	private ApplicationController applicationController;
	private SearchHeaderView searchHeaderView;
	MovieLibrary movieLibrary;
	private ProgressBarController progressBar;
	private Task task;

	/**
	 * Constructor
	 * @param view
	 * @param applicationController
	 * @param movieLibrary
	 * @param progressBar
	 */
	public SearchHeaderController(SearchHeaderView view, ApplicationController 
			applicationController, MovieLibrary movieLibrary, ProgressBarController progressBar)
	{
		this.applicationController = applicationController;
		searchHeaderView = view;
		searchHeaderView.getSearchButton().addActionListener(this);
		this.movieLibrary = movieLibrary;
		searchHeaderView.getComboBox().addActionListener(this);
		this.progressBar = progressBar;

	}

	/**
	 * Inner-class Task that extends SwingWorker. Handles the lengthy task of downloading movieObjects
	 * @author Eystein Davoen
	 */
	class Task extends SwingWorker<Void, Void> {

		/**
		 * Runs performAction in a separate worker thread
		 * @return null
		 * @author Eystein Davoen
		 */
		@Override
		protected Void doInBackground() {
			performAction();
			return null;
		}

		/**
		 * When doInBackground is finished, enable searchbutton again, and stop the progressBar.
		 * @author Eystein Davoen
		 */
		@Override
		public void done() {
			searchHeaderView.getSearchButton().setEnabled(true);
			progressBar.getProgressBarView().getProgressBar().setIndeterminate(false);
		}
	}

	/**
	 * Updates content of the combobox
	 */
	public void searchBarUpdater() {

		searchHeaderView.getComboBox().removeAllItems();
		for (String a :movieLibrary.getSearchHistory()){
			searchHeaderView.getComboBox().addItem(a);
		}
	}

	/**
	 * Starts the search for a movie 
	 */
	public void performAction(){
		if(searchHeaderView.getComboBox() instanceof JComboBox){

			String itemText = (String) searchHeaderView.getComboBox().getSelectedItem();
			movieLibrary.getSearchHistory().add(itemText); 
			if(itemText.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please input search text", "Input error", JOptionPane.ERROR_MESSAGE);
			} else {
				applicationController.getMovies(itemText);
			}
		}

	}


	/**
	 * ActionEventListener for the comboBox. When action is performed disables searchButton, creates a new instance of Task,
	 * and executes it. Sets progressBar to indeterminate.
	 * @author Lars Johnsen & Eystein Davoen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		@SuppressWarnings("unused")
		String itemText = (String) searchHeaderView.getComboBox().getSelectedItem( );
		if(actionCommand.equals("comboBoxEdited") ) {

			searchHeaderView.getSearchButton().setEnabled(false);
			task = new Task();
			task.execute();
			progressBar.getProgressBarView().getProgressBar().setIndeterminate(true);
		}

	}

}



