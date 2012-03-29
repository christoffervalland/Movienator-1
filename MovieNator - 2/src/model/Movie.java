/**
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

import com.google.gson.JsonArray;

import utils.DiceThrow;



public class Movie {

	/**
	 * Fields
	 */
	private long ID;
	private String tittel;
	private String aarstall;
	private String MPAArating;
	private String runtime;
	private String utgivelsesdato;
	private int score;
	private int userScore;
	private String synopsis;
	private String cast;
	private BufferedImage image;
	private String posterUrl;
	private BufferedImage diceImage;
	private String genre;
	private boolean favourited;
	//LINKLINK
	private String link;

	//SIMILARSIMILARSIMILAR
	private JsonArray similar;


	DiceThrow dt = new DiceThrow();
	private BufferedImage diceUserImage;

	/**
	 * Creates a movie Object
	 * @param mID
	 * @param filmTittel
	 * @param image
	 * @param filmaarstall
	 * @param rating
	 * @param mruntime
	 * @param mutgivelsesdato
	 * @param mscore
	 * @param mUserScore
	 * @param msynopsis
	 * @param mGenre
	 * @param cast
	 * @param favourited
	 */
	public Movie(long mID, String filmTittel, String image, String filmaarstall,
			String rating, String mruntime, String mutgivelsesdato, int mscore,
			int mUserScore, String msynopsis, String mGenre, String cast, 
			boolean favourited, String mLink, JsonArray mSimilar) {

		this.ID = mID;
		this.tittel = filmTittel;
		this.aarstall = filmaarstall;
		this.MPAArating = rating;
		this.runtime = mruntime;
		this.utgivelsesdato = mutgivelsesdato;
		this.score = mscore;
		this.userScore = mUserScore;
		this.synopsis = msynopsis;
		this.cast = cast;
		this.diceImage = calculateDiceImage();
		this.genre = mGenre;
		this.favourited = favourited;
		this.link = mLink;
		this.posterUrl = image;

		//SIMILARSIMILARSIMILARSIMILARSIMILAR
		this.similar = mSimilar;

		try {
			this.image = ImageIO.read(new URL(image));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {

		}

		if(this.image == null) {
			try {
				this.image = ImageIO.read(new File("resources/poster_default.gif"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}



	/**
	 * Requirements for the dices
	 *@return Dice image
	 */
	public BufferedImage calculateDiceImage() 
	{
		BufferedImage tempDiceImg = new BufferedImage(60, 60, BufferedImage.TYPE_INT_ARGB);

		if(score < 0){
			tempDiceImg = dt.getIconNa();
		} else if((getScore()) >= 0 && (getScore() < 20)){
			tempDiceImg = dt.getIcon1();
		} else if((getScore() >= 20) && (getScore() < 35)) {
			tempDiceImg = dt.getIcon2();
		} else if((getScore() >= 35) && (getScore() < 50)) {
			tempDiceImg = dt.getIcon3();
		} else if((getScore() >= 50) && (getScore() < 70)) {
			tempDiceImg = dt.getIcon4();
		} else if((getScore() >= 70) && (getScore() < 85)) {
			tempDiceImg = dt.getIcon5();
		} else if ((getScore() >= 85) && (getScore() <= 100)) {
			tempDiceImg = dt.getIcon6();
		} else {
			System.out.println("Ooops, something wrong happened...");
		}
		return tempDiceImg;
	}

	/**
	 * 
	 * @return title
	 */
	public String getTitle()
	{
		return tittel;
	}
	/**
	 * 
	 * @return image
	 */
	public BufferedImage getImage()
	{
		return image;
	}

	/**
	 * Getter for toString of image
	 * @return toString
	 */
	public String getImageAsString(){
		return image.toString();
	}

	/**
	 * toString method for printing
	 */
	@Override
	public String toString() {
		return "Movie [ID=" + ID + ", tittel=" + tittel + ", aarstall="
				+ aarstall + ", MPAArating=" + MPAArating + ", runtime="
				+ runtime + ", utgivelsesdato=" + utgivelsesdato + ", score="
				+ score + ", synopsis=" + synopsis + ", cast=" + cast
				+ ", image=" + image + "]";
	}
	/**
	 * 
	 * @return ID
	 */
	public long getID() {
		return ID;
	}
	/**
	 * 
	 * @param iD
	 */
	public void setID(long iD) {
		ID = iD;
	}

	/**
	 * 
	 * @param tittel
	 */
	public void setTittel(String tittel) {
		this.tittel = tittel;
	}
	/**
	 * 
	 * @return aarstall
	 */
	public String getAarstall() {
		return aarstall;
	}
	/**
	 * 
	 * @param aarstall
	 */
	public void setAarstall(String aarstall) {
		this.aarstall = aarstall;
	}
	/**
	 * 
	 * @return mpaaRating
	 */
	public String getMPAArating() {
		return MPAArating;
	}
	/**
	 * 
	 * @param mPAArating
	 */
	public void setMPAArating(String mPAArating) {
		MPAArating = mPAArating;
	}
	/**
	 * 
	 * @return runtime
	 */
	public String getRuntime() {
		return runtime;
	}
	/**
	 * 
	 * @param runtime
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	/**
	 * 
	 * @return releasedate
	 */
	public String getUtgivelsesdato() {
		return utgivelsesdato;
	}
	/**
	 * 
	 * @param utgivelsesdato
	 */
	public void setUtgivelsesdato(String utgivelsesdato) {
		this.utgivelsesdato = utgivelsesdato;
	}
	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * 
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * 
	 * @return Synopsis
	 */
	public String getSynopsis() {
		return synopsis;
	}
	/**
	 * 
	 * @param synopsis
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	/**
	 * 
	 * @return Cast
	 */
	public String getCast() {
		return cast;
	}
	/**
	 * 
	 * @param cast
	 */
	public void setCast(String cast) {
		this.cast = cast;
	}
	/**
	 * 
	 * @param image
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	/**
	 * @return the diceImage
	 */
	public BufferedImage getDiceImage() {
		return diceImage;
	}

	//IMAGETOSTRING FOR DATABASE
	public String getDiceImageAsString(){
		return diceImage.toString();
	}

	/**
	 * @param diceImage the diceImage to set
	 */
	public void setDiceImage(BufferedImage diceImage) {
		this.diceImage = diceImage;
	}

	/**
	 * @return the userScore
	 */
	public int getUserScore() {
		return userScore;
	}

	/**
	 * @param userScore the userScore to set
	 */
	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}



	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}



	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @return the diceUserImage
	 */
	public BufferedImage getDiceUserImage() {
		return diceUserImage;
	}

	/**
	 * @param diceUserImage the diceUserImage to set
	 */
	public void setDiceUserImage(BufferedImage diceUserImage) {
		this.diceUserImage = diceUserImage;
	}



	/**
	 * @return the favourited
	 */
	public boolean isFavourited() {
		return favourited;
	}



	/**
	 * @param favourited the favourited to set
	 */
	public void setFavourited(boolean favourited) {
		this.favourited = favourited;
	}



	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	/**
	 * SIMILARSIMILARSIMILARSIMILAR
	 * @return
	 * @author ChristofferValland
	 */
	public JsonArray getSimilar() {
		return similar;
	}



	public String getPosterUrl() {
		return posterUrl;
	}



	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}

	public void setSimilar(JsonArray similar) {
		this.similar = similar;
	}

}





