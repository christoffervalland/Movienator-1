/**
 * Code snippet found on Stack Overflow: http://stackoverflow.com/questions/1139547/detect-internet-connection-using-java
 */

package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;


public class ConnectionCheck {



	/**
	 * checks for connection to the internet through dummy request
	 */
	public static boolean isInternetReachable()
	{
		try {
			URL url = new URL("http://www.google.com");

			HttpURLConnection urlConnect = (HttpURLConnection)url.openConnection();


			@SuppressWarnings("unused")
			Object objData = urlConnect.getContent();

		} catch (UnknownHostException e) {
			return false;

		}
		catch (IOException e) {
			return false;

		}
		return true;
	}



}
