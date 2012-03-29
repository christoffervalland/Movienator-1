/**
 * @author Eystein Davoen
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package view;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class FavouritesView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel of favourites
	 */
	public FavouritesView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}

}
