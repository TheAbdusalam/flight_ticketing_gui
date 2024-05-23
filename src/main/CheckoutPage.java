package main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CheckoutPage extends JFrame {
	private String selectedClass;

	public CheckoutPage(List<Flight> selectedFlights, String selectedClass) {
		this.selectedClass = selectedClass;

		setTitle("Checkout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 500);
		setLocationRelativeTo(null); // Center the frame on the screen

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel flightPanel = createFlightPanel(selectedFlights);
		mainPanel.add(flightPanel, BorderLayout.NORTH);

		JPanel paymentPanel = createPaymentPanel();
		mainPanel.add(paymentPanel, BorderLayout.CENTER);

		add(mainPanel);
		setVisible(true);
	}

	public JPanel createFlightPanel(List<Flight> selectedFlights) {
		JPanel flightPanel = new JPanel();
		flightPanel.setLayout(new BoxLayout(flightPanel, BoxLayout.Y_AXIS));
		flightPanel.setBackground(Color.WHITE);
		flightPanel.setBorder(BorderFactory.createTitledBorder("Selected Flights"));

		double total = 0;
		int totalPassengers = 0;
		for (Flight flight : selectedFlights) {
			double price = this.selectedClass.equals("economy") ? flight.getEconomyClassPrice()
					: flight.getBusinessClassPrice();

			total += price * flight.getPassengers();
			totalPassengers = flight.getPassengers();

			JLabel flightLabel = new JLabel("<html><h2>$" + price + "</h2>" +
					"<b>Flight Number:</b> " + flight.getFlightNumber() + "<br>"
					+ "<b>Route:</b> " + flight.getFromLocation() + " → " + flight.getToLocation() + "<br>"
					+ "<b>Departure:</b> " + flight.getDepartureDate() + " at " + flight.getDepartureTime() + "<br>"
					+ "<b>Arrival:</b> " + flight.getArrivalDate() + " at " + flight.getArrivalTime() + "</html>");
			flightPanel.add(flightLabel);
			flightPanel.add(Box.createVerticalStrut(10)); // Add spacing between flights
		}

		JLabel totalLabel = new JLabel(
				"<html><h2>Total: $" + total + "</h2><p>(for " + totalPassengers + " passengers)</p></html>");
		flightPanel.add(totalLabel);

		return flightPanel;
	}

	public JPanel createPaymentPanel() {
		JPanel paymentPanel = new JPanel(new BorderLayout());
		paymentPanel.setBackground(Color.WHITE);
		paymentPanel.setBorder(BorderFactory.createTitledBorder("Payment Details"));

		JPanel inputPanel = new JPanel(new GridBagLayout());
		inputPanel.setBackground(Color.WHITE);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;

		JLabel cardLabel = new JLabel("Card Number:");
		inputPanel.add(cardLabel, gbc);

		gbc.gridy++;
		JLabel expiryLabel = new JLabel("Expiry Date (MM/YY):");
		inputPanel.add(expiryLabel, gbc);

		gbc.gridy++;
		JLabel cvvLabel = new JLabel("CVV:");
		inputPanel.add(cvvLabel, gbc);

		gbc.gridy = 0;
		gbc.gridx++;
		gbc.anchor = GridBagConstraints.EAST;

		JTextField cardNumberField = new JTextField(15);
		inputPanel.add(cardNumberField, gbc);

		gbc.gridy++;
		JTextField expiryField = new JTextField(6);
		inputPanel.add(expiryField, gbc);

		gbc.gridy++;
		JTextField cvvField = new JTextField(3);
		inputPanel.add(cvvField, gbc);

		paymentPanel.add(inputPanel, BorderLayout.CENTER);

		JButton payButton = new JButton("Pay Now");
		payButton.setBackground(new Color(30, 144, 255));
		payButton.setForeground(Color.BLACK); // Change text color to black
		payButton.setFocusPainted(false);
		payButton.setFont(new Font("Arial", Font.BOLD, 14));

		// Add action listener to the pay button
		payButton.addActionListener(e -> {
			if (cardNumberField.getText().isEmpty() || expiryField.getText().isEmpty()
					|| cvvField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			JOptionPane.showMessageDialog(this, "Payment Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

			// Generate a random confirmation number
			String confirmationNumber = ConfirmationPage.generateConfirmationNumber();

			// Open the confirmation page
			SwingUtilities.invokeLater(() -> new ConfirmationPage(confirmationNumber));
			dispose(); // Close the checkout page
		});

		paymentPanel.add(payButton, BorderLayout.SOUTH);

		return paymentPanel;
	}
}
