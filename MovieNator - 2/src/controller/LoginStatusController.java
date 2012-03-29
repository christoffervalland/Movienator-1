/**
 * Controller for LoginStatus
 * @author Lars Petter Johnsen
 */
package controller;

import view.OptionsFooterView;

public class LoginStatusController {
	
	/**
	 * Fields
	 */
	private OptionsFooterView optionsFooterView;
	private ApplicationController applicationController;

	/**
	 * Constructor
	 * @param optionFooterView
	 * @param applicationController
	 */
	public LoginStatusController(OptionsFooterView optionFooterView, ApplicationController applicationController){
		this.optionsFooterView = optionFooterView;
		this.applicationController = applicationController;


	}
	
	/**
	 * Sets loginStatusLabel text,
	 */
	public void setLoginStatus(){
		optionsFooterView.getLoginStatusLabel().setText("Logged inn as: " + applicationController.getActiveUser().getScreenName());
	}

}

