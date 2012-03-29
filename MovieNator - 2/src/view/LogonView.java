/**
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package view;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;


public class LogonView extends JPanel {


	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;

	private JTextField userName;
	private JPasswordField password;
	private JTextField newUserName;
	private JTextField newPassword;
	private JTextField screenName;

	private JLabel screenNameLabel;
	private JLabel newUsernameLabel;
	private JLabel newPasswordLabel;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel registerLabel;

	private JButton btnSignIn;
	private JButton register;
	private JButton registerAccount;
	private JButton login;

	private JPanel registerPanel;
	private JPanel logonPanel;
	private JPanel rightPanel;
	


	/**
	 * Create the logonpanel or registerpanel with getPanel
	 * @author Aysha
	 */
	public LogonView() {


		//--logonPanel--
		logonPanel = new JPanel();
		logonPanel.setPreferredSize(new Dimension(185,250));
		logonPanel.setSize(185,250);
		logonPanel.setBackground(Color.LIGHT_GRAY);
		logonPanel.setLayout(new BoxLayout(logonPanel, BoxLayout.Y_AXIS));
		logonPanel.setBorder(BorderFactory.createMatteBorder(5,10,5,10, Color.LIGHT_GRAY));
		logonPanel.setVisible(true);

		//Userame (label and button)
		logonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		usernameLabel = new JLabel("Username:");
		logonPanel.add(usernameLabel);
		userName = new JTextField();
		userName.setColumns(10);
		logonPanel.add(userName);

		//Password (label and button)
		logonPanel.add(Box.createRigidArea(new Dimension(0,5)));
		passwordLabel =new JLabel("Password:");
		logonPanel.add(passwordLabel);
		password = new JPasswordField();
		logonPanel.add(password);
		logonPanel.add(Box.createRigidArea(new Dimension(0,3)));

		//sign in button
		btnSignIn = new JButton("Sign in");
		logonPanel.add(btnSignIn);
		logonPanel.add(Box.createRigidArea(new Dimension(0,70)));

		//register new account (button and label)
		registerLabel = new JLabel("Not registered yet?");
		logonPanel.add(registerLabel);
		logonPanel.add(Box.createRigidArea(new Dimension(0,0)));
		register = new JButton("Create Account");
		logonPanel.add(register);
		
		add(logonPanel);
		
		//--registerPanel--	
				registerPanel = new JPanel();
				registerPanel.setPreferredSize(new Dimension(185,250));
				registerPanel.setSize(185,250);
				registerPanel.setBackground(Color.LIGHT_GRAY);
				registerPanel.setLayout(new GridLayout(8,0));
				registerPanel.setBorder(BorderFactory.createMatteBorder(5,10,5,10,Color.lightGray));
				registerPanel.setVisible(false);

				//Create Username (label and button)
				newUsernameLabel = new JLabel("Create Username:");
				registerPanel.add(newUsernameLabel);
				newUserName = new JTextField();
				newUserName.setColumns(10);
				registerPanel.add(newUserName);

				//Create nickname
				screenNameLabel = new JLabel("Create Screen Name");
				registerPanel.add(screenNameLabel);
				screenName = new JTextField();
				registerPanel.add(screenName);

				//Create Password (label and button)
				newPasswordLabel =new JLabel("Create Password:");
				registerPanel.add(newPasswordLabel);
				newPassword = new JTextField();
				registerPanel.add(newPassword);


				//register button
				registerPanel.add(Box.createRigidArea(new Dimension(0,1)));
				JPanel buttonPanel = new JPanel();
				buttonPanel.setBackground(Color.lightGray);
				registerAccount = new JButton("Register");
				buttonPanel.add(registerAccount);
			
				login = new JButton("Login");
				buttonPanel.add(login);
				registerPanel.add(buttonPanel);
				buttonPanel.add(Box.createRigidArea(new Dimension(0,5)));
				add(registerPanel);

		//-- right panel-- 
		rightPanel= new JPanel();
	    add(rightPanel);
		rightPanel.setPreferredSize(new Dimension(600, 250));
		rightPanel.setSize(300, 0);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

	}

	public JButton getLoginButton(){
	return login;	
	}
	
	public JPanel getRegisterPanel() {
		return registerPanel;
	}

	public void setRegisterPanel(JPanel registerPanel) {
		this.registerPanel = registerPanel;
	}

	public JPanel getLogonPanel() {
		return logonPanel;
	}

	public void setLogonPanel(JPanel logonPanel) {
		this.logonPanel = logonPanel;
	}

	public JPanel getRightPanel() {
		return rightPanel;
	}

	public void setRightPanel(JPanel rightPanel) {
		this.rightPanel = rightPanel;
	}

	public JButton getRegisterAccount() {
		return registerAccount;
	}

	public void setRegisterAccount(JButton registerAccount) {
		this.registerAccount = registerAccount;
	}

	public JTextField getUserName() {
		return userName;
	}

	public void setUserName(JTextField userName) {
		this.userName = userName;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}
	public JButton getBtnSignIn() {
		return btnSignIn;
	}

	public void setBtnSignIn(JButton btnSignIn) {
		this.btnSignIn = btnSignIn;
	}

	public JTextField getNewUserName() {
		return newUserName;
	}

	public void setNewUserName(JTextField newUserName) {
		this.newUserName = newUserName;
	}

	public JTextField getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(JTextField newPassword) {
		this.newPassword = newPassword;
	}


	public JTextField getNewScreenName() {
		return screenName;
	}

	public void setNewScreenName(JTextField screenName) {
		this.screenName = screenName;
	}

	public JButton getRegister() {
		return register;
	}

	public void setRegister(JButton register) {
		this.register = register;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
