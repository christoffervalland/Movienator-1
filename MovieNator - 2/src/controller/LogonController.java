/**
 * @author Eystein, Aysha, Lars Petter
 */

package controller;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import sql.ConnectionSql;

import utils.ErrorHandler;

import view.ApplicationView;

import view.LogonView;

public class LogonController implements ActionListener {

	/**
	 * Fields
	 */
	private LogonView logonView;
	private ApplicationView applicationView;

	private ConnectionSql connectionSql;

	private LoginStatusController loginStatusController;
	private FavouritesController favouritesController;
	private ProgressBarController progressBarController;

	/**
	 * Constructor
	 * @param logonView
	 * @param connectionSql
	 * @param applicationView
	 * @param loginStatusController
	 * @param favouritesController
	 * @param progressBarController
	 */
	public LogonController(LogonView logonView, ConnectionSql connectionSql,
			ApplicationView applicationView,LoginStatusController loginStatusController, 
			FavouritesController favouritesController, ProgressBarController progressBarController) {

		this.applicationView = applicationView;
		this.logonView = logonView;
		this.connectionSql = connectionSql;
		this.progressBarController = progressBarController;
		this.loginStatusController = loginStatusController;
		this.favouritesController = favouritesController;
		logonView.getBtnSignIn().addActionListener(this);
		logonView.getRegister().addActionListener(this);
		logonView.getRegisterAccount().addActionListener(this);
		logonView.getLoginButton().addActionListener(this);

	}
	
	/**
	 * Inner-class that handles adding the users favourite movies to favourite panel when signing in.
	 * @author Eystein Davoen
	 *
	 */
	class GetFavouriteMovies extends SwingWorker<Void, Void> {

		/**
		 * Overrides the doinBackground method, and runs the setFovouritePosters method in worker thread
		 * @return null
		 * @author Eystein Davoen
		 */
		@Override
		protected Void doInBackground() throws Exception {
			favouritesController.setFavouritePosters();
			return null;
		}
		
		/**
		 * Overrides the done method. when doInBackground is finished, stops the progressbar
		 * @author Eystein Davoen
		 */
		@Override
		public void done() {
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(false);
		}
	}

	/**
	 * @author Aysha Khan & Lars Petter Johnsen
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equals("Create Account")){
			logonView.getLogonPanel().setVisible(false);
			logonView.getRegisterPanel().setVisible(true);
		} else if (actionCommand.equals("Login")){
			logonView.getRegisterPanel().setVisible(false);
			logonView.getLogonPanel().setVisible(true);

		} else if(actionCommand == "Register"){
			try {
				addUser();
			} catch (SQLException e1) {
				String error = "The username already exists or it«s something wrong with the database";
				ErrorHandler.getErrorMessage(error);
			}
		} else if (actionCommand.equals("Sign in")){
			try {
				logon();
				success();
			} catch (SQLException e1) {
				String error = "Please check your spelling";
				ErrorHandler.getErrorMessage(error);
			}

		}

	}
	
	/** Shows an errormessage if username, password or screenname is empty
	 * Adds a user to the database if all fields are correctly filled in
	 * @author Aysha Khan
	 * @throws SQLException
	 */
	public void addUser() throws SQLException {


		if(logonView.getRegisterAccount() instanceof JButton){
			
			String userName = logonView.getNewUserName().getText();
			String password = logonView.getNewPassword().getText();
			String screenName = logonView.getNewScreenName().getText();

			if(userName.isEmpty()) {
				String error = "Please type in a username";
				ErrorHandler.getErrorMessage(error);
			} else if (password.isEmpty()) {
				String error = "Please type in a password";
				ErrorHandler.getErrorMessage(error);
			} else if (screenName.isEmpty()){
				String error = "Please type in a screenname";
				ErrorHandler.getErrorMessage(error);
			} else {
				connectionSql.addUser();
				logonView.getNewUserName().setText("");
				logonView.getNewPassword().setText("");
				logonView.getNewScreenName().setText("");
				logonView.getRegisterPanel().setVisible(false);
				logonView.getLogonPanel().setVisible(true);
			}

		}

	}
	
	/** 
	 * Checks if user is logged in, and get user's favourite movies in separate worker thread
	 * @author Aysha Khan, Eystein
	 * @throws SQLException
	 */
	public void success() throws SQLException{

		if(connectionSql.logonUser() == true){
			@SuppressWarnings("unused")
			String screenName = logonView.getNewScreenName().getText();

			logonView.removeAll();
			logonView.getUserName().setText("");
			logonView.getPassword().setText("");
			progressBarController.getProgressBarView().getProgressBar().setIndeterminate(true);
			GetFavouriteMovies getFav = new GetFavouriteMovies();
			getFav.execute();
			logonView.repaint();
			applicationView.repaint();
			loginStatusController.setLoginStatus();
		}

	}
	
	/**
	 * @author Aysha Khan & Lars Petter Johnsen
	 * @throws SQLException
	 */
	public void logon() throws SQLException {

		connectionSql.logonUser();

		@SuppressWarnings("unused")
		String existingUser = logonView.getUserName().getText();

		@SuppressWarnings("unused")
		String existingPassword = logonView.getPassword().getText();
	}

	/**
	 * Getter for connectionSql
	 * @return connectionSql
	 */
	public ConnectionSql getConnectionSql() {

		return connectionSql;

	}
	
	/**
	 * Setter for connnectionSql
	 * @param connectionSql
	 */
	public void setConnectionSql(ConnectionSql connectionSql) {

		this.connectionSql = connectionSql;

	}

}

