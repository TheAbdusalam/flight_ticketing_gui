package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoginRegistrationPage extends CustomFrame {
	private JTextField loginUsernameField;
	private JPasswordField loginPasswordField;
	private JButton loginButton;

	private JTextField registerUsernameField;
	private JPasswordField registerPasswordField;
	private JButton registerButton;

	public LoginRegistrationPage() {
		setTitle("Abdifatah Ticketing System - Login / Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 400);
		setLocationRelativeTo(null);

		ImageIcon logoIcon = new ImageIcon("logo.png");
		Image image = logoIcon.getImage();
		Image newimg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
		JLabel logoLabel = new JLabel(new ImageIcon(newimg));
		add(logoLabel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane();

		JPanel loginPanel = createLoginPanel();
		JPanel registerPanel = createRegisterPanel();

		tabbedPane.addTab("Login", loginPanel);
		tabbedPane.addTab("Register", registerPanel);

		add(tabbedPane);
		setVisible(true);
	}

	private JPanel createLoginPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));

		JLabel usernameLabel = new JLabel("Username:");
		loginUsernameField = new JTextField();
		panel.add(usernameLabel);
		panel.add(loginUsernameField);

		JLabel passwordLabel = new JLabel("Password:");
		loginPasswordField = new JPasswordField();
		panel.add(passwordLabel);
		panel.add(loginPasswordField);

		loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = loginUsernameField.getText();
				char[] password = loginPasswordField.getPassword();
				String passwordStr = new String(password);

				if (DatabaseManager.loginUser(username, passwordStr)) {
					openTicketingPage(username);
					dispose(); // Close the login/registration page
				} else {
					JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
				}
			}
		});
		panel.add(loginButton);

		return panel;
	}

	private JPanel createRegisterPanel() {
		JPanel panel = new JPanel(new GridLayout(4, 2));

		JLabel usernameLabel = new JLabel("Name:");
		registerUsernameField = new JTextField();
		panel.add(usernameLabel);
		panel.add(registerUsernameField);

		JLabel emailLabel = new JLabel("Email:");
		JTextField emailField = new JTextField();
		panel.add(emailLabel);
		panel.add(emailField);

		JLabel passwordLabel = new JLabel("Password:");
		registerPasswordField = new JPasswordField();
		panel.add(passwordLabel);
		panel.add(registerPasswordField);

		registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = registerUsernameField.getText();
				String email = emailField.getText();
				char[] password = registerPasswordField.getPassword();
				String passwordStr = new String(password);
				if (DatabaseManager.registerUser(username, email, passwordStr)) {
					openTicketingPage(username);
					dispose(); // Close the login/registration page
				} else {
					JOptionPane.showMessageDialog(null,
							"Registration failed. Please try again with a different username.");
				}
			}
		});
		panel.add(registerButton);

		return panel;
	}

	private void openTicketingPage(String username) {
		SwingUtilities.invokeLater(() -> new TicketBookingApp(username));
	}

	public static void main(String[] args) {
		// DatabaseManager.createTable(); // Create the user table if not exists
		SwingUtilities.invokeLater(LoginRegistrationPage::new);
	}
}
