package org.rottentomatoes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * General utilities that may be used from other classes, commonly used for string convertion etc
 * @author Sindre Benonisen
 * @author Kjetil Bruland
 */

public class Util{
	
	/**
	* Converts a string and converts it to a valid UTF-8 URL.
	* @param String text The text that should be converted.
	* @return The result of the conversion.
	*/
	public static String urlEncode(String text){
		try{
			text = URLEncoder.encode(text, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return text;
	}
	
	/**
	* Converts a buffering character-input stream  to a string.
	* @param BufferedReader rd The buffering character-input stream 
	* @return String The converted string.
	* @throws IOException if the Stringbuilder fails to create a string from the BufferedReader.
	*/
	public static String readAll(BufferedReader rd) throws IOException{
		StringBuilder sb = new StringBuilder();
		int cp;
		while((cp = rd.read()) != -1){
			sb.append((char) cp);
		}
		return sb.toString();
	}
	
}