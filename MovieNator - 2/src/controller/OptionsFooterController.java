/**
 * Controller for optionsFooter
 * @author Eystein Davoen
 */
package controller;

import java.awt.Color;
import view.OptionsFooterView;

public class OptionsFooterController implements Runnable {

	private OptionsFooterView optionsFooterView;	

	public OptionsFooterController(OptionsFooterView view, ApplicationController applicationController){

		this.optionsFooterView = view;

	}


	/**
	 * Changes the text of the onlineStatus label to online if true, or offline if false.
	 * @param onlineStatus
	 */
	public void setOnlineStatus(boolean onlineStatus){        

		if(onlineStatus == true) {
			optionsFooterView.getOnlineStatusLabel().setText("Online ");
			optionsFooterView.getOnlineStatusLabel().setForeground(Color.LIGHT_GRAY);
			optionsFooterView.repaint();
		} else {
			optionsFooterView.getOnlineStatusLabel().setText("Offline ");
			optionsFooterView.getOnlineStatusLabel().setForeground(Color.RED);
			optionsFooterView.repaint();
		}
	}


	/**
	 * Overrides the run method. Calls the utility method isInternetReachable and then setOnlineStatus. 
	 * When thread is done, tries to sleep for 20 seconds, before repeating.
	 * @author Eystein Davoen
	 */
	@Override
	public void run() {
		boolean running = true;
		while(running){
			setOnlineStatus(utils.ConnectionCheck.isInternetReachable());
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
