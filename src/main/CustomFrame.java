package main;

import javax.swing.*;
import java.awt.event.*;

public class CustomFrame extends JFrame {

	public CustomFrame() {
		JMenuBar menuBar = new JMenuBar();

		JMenu optionsMenu = new JMenu("Options");

		JMenuItem helpMenuItem = new JMenuItem("Help");
		helpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(CustomFrame.this, "Help Content will be ready soon!");
			}
		});
		optionsMenu.add(helpMenuItem);

		JMenuItem contactMenuItem = new JMenuItem("Contact");
		contactMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(CustomFrame.this, "Contact me at: abdifatah@st.uskudar.edu.tr");
			}
		});
		optionsMenu.add(contactMenuItem);

		menuBar.add(optionsMenu);

		// Set the menu bar for the frame
		setJMenuBar(menuBar);
	}
}
