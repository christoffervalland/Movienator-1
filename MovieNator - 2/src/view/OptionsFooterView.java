/**
 * OptinsFooter view
 * @author Eystein Davoen
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;



public class OptionsFooterView extends JPanel {
	
	/**
	 * Field
	 */
	private static final long serialVersionUID = 1L;
	private JLabel onlineStatusLabel;
	private JLabel loginStatusLabel;
	
	/**
	 * Constructor
	 */
	public OptionsFooterView() {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(500, 35));
		this.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		
		loginStatusLabel = new JLabel();
		loginStatusLabel.setText("");
		this.add(loginStatusLabel);
		
		onlineStatusLabel = new JLabel();
        onlineStatusLabel.setText("");
		this.add(onlineStatusLabel);
		
	}

	/**
	 * Getter for onlineStatuslabel
	 * @return the onlineStatusLabel
	 */
	public JLabel getOnlineStatusLabel() {
		return onlineStatusLabel;
	}

	/**
	 * Setter for onlineStatusLabel
	 * @param onlineStatusLabel the onlineStatusLabel to set
	 */
	public void setOnlineStatusLabel(JLabel onlineStatusLabel) {
		this.onlineStatusLabel = onlineStatusLabel;
	}

	/**
	 * Getter for loginStatusLabel
	 * @return loginStatusLabel
	 */
	public JLabel getLoginStatusLabel() {
		return loginStatusLabel;
	}

	/**
	 * Setter for loginStatusLabel
	 * @param loginStatusLabel
	 */
	public void setLoginStatusLabel(JLabel loginStatusLabel) {
		this.loginStatusLabel = loginStatusLabel;
	}


	
}
