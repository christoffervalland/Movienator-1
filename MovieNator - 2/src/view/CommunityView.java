package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author ChristofferValland
 *
 */
public class CommunityView extends JPanel {
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;

	private JPanel communityMoviePanel;
	private JPanel otherUsersPanel;
	
	/**
	 * @author ChristofferValland og Lars P
	 */
	public CommunityView(){

		setBackground(new Color(238,238,238));
		setLayout(new BorderLayout());
		

		this.otherUsersPanel = new JPanel();
		otherUsersPanel.setPreferredSize(new Dimension(200, 100));
		otherUsersPanel.setBackground(new Color(238, 238, 238));
		otherUsersPanel.setBorder(new TitledBorder(null, "Other users", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(otherUsersPanel, BorderLayout.WEST);

		this.communityMoviePanel = new JPanel();
		communityMoviePanel.setPreferredSize(new Dimension(100, 100));
		communityMoviePanel.setBackground(new Color(238, 238, 238));
		communityMoviePanel.setBorder(new TitledBorder(null, "Others movies", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		add(communityMoviePanel, BorderLayout.CENTER);
		
	}

	public JPanel getOtherUsersPanel() {
		return otherUsersPanel;
	}

	public void setOtherUsersPanel(JPanel otherUsersPanel) {
		this.otherUsersPanel = otherUsersPanel;
	}

	public JPanel getCommunityMoviePanel() {
		return communityMoviePanel;
	}

	public void setCommunityMoviePanel(JPanel communityMoviePanel) {
		this.communityMoviePanel = communityMoviePanel;
	}

}
