/**
 * @author Eystein Davøes
 * @author Lars Johnsen
 * @author Christoffer Valland
 * @author Aysha Kahn
 * 
 */
package view;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;


public class SearchHeaderView extends JPanel {
	/**
	 * Fields
	 */
	private static final long serialVersionUID = 1L;
	private JButton searchButton;
	private JComboBox comboBox;


	/**
	 * Create the panel Search Header
	 */
	public SearchHeaderView() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		comboBox = new JComboBox();
		comboBox.setMaximumRowCount(5);
		comboBox.setEditable(true);
		comboBox.setPreferredSize(new Dimension(200, 20));
				
		searchButton = new JButton("Search");
		
		add(comboBox);
		add(searchButton);
		
		setPreferredSize(new Dimension(800, 60));
		setBackground(Color.GRAY);
	}
	
	/**
	 * @return the searchButton
	 */
	public JButton getSearchButton() {
		return searchButton;
	}

	/**
	 * @param searchButton the searchButton to set
	 */
	public void setSearchButton(JButton searchButton) {
		this.searchButton = searchButton;
	}
	
	/**
	 * 
	 * @return comboBox
	 */
	public JComboBox getComboBox() {
		return comboBox;
	}

	/**
	 * 
	 * @param comboBox
	 */
	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

}
