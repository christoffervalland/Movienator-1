package model;


/** 
 * @author Christoffer Valland
 *
 */
public class User {
	private String userName;
	private String password;
	private String screenName;
	
	public User(String userName, String password, String screenName) {
		
		this.userName = userName;
		this.password = password;
		this.screenName = screenName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public User getUser(){
		return this;
	}
	
	
	
}
