/**
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class DiceThrow {

	/**
	 * Fields
	 */
	private BufferedImage icon6;
	private BufferedImage icon5;
	private BufferedImage icon4;
	private BufferedImage icon3;
	private BufferedImage icon2;
	private BufferedImage icon1;
	private BufferedImage iconNA;
	
	/**
	 * Images for the dices including Not Applicable image.
	 */
	public DiceThrow() {
		
		this.icon6 = null;
		try {
		    icon6 = ImageIO.read(new File("./resources/terning_6.gif"));
		} catch (IOException e) {
		}
		
		this.icon5 = null;
		try {
		    icon5 = ImageIO.read(new File("./resources/terning_5.gif"));
		} catch (IOException e) {
		}
		
		this.icon4 = null;
		try {
		    icon4 = ImageIO.read(new File("./resources//terning_4.gif"));
		} catch (IOException e) {
		}
		
		this.icon3 = null;
		try {
		    icon3 = ImageIO.read(new File("./resources//terning_3.gif"));
		} catch (IOException e) {
		}
		
		this.icon2 = null;
		try {
		    icon2 = ImageIO.read(new File("./resources//terning_2.gif"));
		} catch (IOException e) {
		}
		
		this.icon1 = null;
		try {
		    icon1 = ImageIO.read(new File("./resources/terning_1.gif"));
		} catch (IOException e) {
		}
		
		this.iconNA = null;
		try {
		    iconNA = ImageIO.read(new File("./resources/terning_na.gif"));
		} catch (IOException e) {
		}
		
	}

	/**
	 * @return the icon6
	 */
	public BufferedImage getIcon6() {
		return icon6;
	}

	/**
	 * @param icon6 the icon6 to set
	 */
	public void setIcon6(BufferedImage icon6) {
		this.icon6 = icon6;
	}

	/**
	 * @return the icon5
	 */
	public BufferedImage getIcon5() {
		return icon5;
	}

	/**
	 * @param icon5 the icon5 to set
	 */
	public void setIcon5(BufferedImage icon5) {
		this.icon5 = icon5;
	}

	/**
	 * @return the icon4
	 */
	public BufferedImage getIcon4() {
		return icon4;
	}

	/**
	 * @param icon4 the icon4 to set
	 */
	public void setIcon4(BufferedImage icon4) {
		this.icon4 = icon4;
	}

	/**
	 * @return the icon3
	 */
	public BufferedImage getIcon3() {
		return icon3;
	}

	/**
	 * @param icon3 the icon3 to set
	 */
	public void setIcon3(BufferedImage icon3) {
		this.icon3 = icon3;
	}

	/**
	 * @return the icon2
	 */
	public BufferedImage getIcon2() {
		return icon2;
	}

	/**
	 * @param icon2 the icon2 to set
	 */
	public void setIcon2(BufferedImage icon2) {
		this.icon2 = icon2;
	}

	/**
	 * @return the icon1
	 */
	public BufferedImage getIcon1() {
		return icon1;
	}

	/**
	 * @param icon1 the icon1 to set
	 */
	public void setIcon1(BufferedImage icon1) {
		this.icon1 = icon1;
	}

	/**
	 * @return the iconNa
	 */
	public BufferedImage getIconNa() {
		return iconNA;
	}

	/**
	 * @param iconNa the iconNa to set
	 */
	public void setIconNa(BufferedImage iconNA) {
		this.iconNA = iconNA;
	}


}

