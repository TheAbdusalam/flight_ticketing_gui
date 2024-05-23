package main;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class ConfirmationPage extends JFrame {

	public ConfirmationPage(String confirmationNumber) {
		setTitle("Ticket Confirmation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null); // Center the frame on the screen

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		ImageIcon confettiIcon = new ImageIcon("confetti.png");
		// confettiIcon.setImage(confettiIcon.getImage().getScaledInstance(100, 100,
		// Image.SCALE_DEFAULT));
		confettiIcon.setDescription("Confetti");
		confettiIcon.setImage(confettiIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

		JLabel confettiLabel = new JLabel(confettiIcon);
		mainPanel.add(confettiLabel, BorderLayout.SOUTH);

		JLabel thankYouLabel = new JLabel("Thank you for booking your flight!");
		thankYouLabel.setFont(new Font("Arial", Font.BOLD, 20));
		thankYouLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(thankYouLabel, BorderLayout.NORTH);

		JPanel confirmationPanel = createConfirmationPanel(confirmationNumber);
		mainPanel.add(confirmationPanel, BorderLayout.CENTER);

		add(mainPanel);
		setVisible(true);
	}

	public JPanel createConfirmationPanel(String confirmationNumber) {
		JPanel confirmationPanel = new JPanel(new GridBagLayout());
		// confirmationPanel.setBackground(Color.WHITE);

		JLabel confirmationLabel = new JLabel("Your Confirmation Number:");
		confirmationPanel.add(confirmationLabel);

		JLabel numberLabel = new JLabel(confirmationNumber);
		numberLabel.setFont(new Font("Arial", Font.BOLD, 16));
		confirmationPanel.add(numberLabel);

		return confirmationPanel;
	}

	public static String generateConfirmationNumber() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}
}
