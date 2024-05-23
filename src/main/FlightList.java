package main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class FlightList extends JFrame {

	private ArrayList<Flight> selectedFlights = new ArrayList<Flight>();
	private String selectedClass;

	public FlightList(List<Flight> flights, Boolean isRoundTrip, String selectedClass, int passengers) {
		this.selectedClass = selectedClass;

		setTitle("Flight List");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null); // Center the frame on the screen

		JPanel panel = createPanel();

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		getContentPane().add(scrollPane);

		// Print the given flights
		for (Flight flight : flights) {
			JPanel flightPanel = createFlightPanel(flight);
			flight.setPassengers(passengers);
			panel.add(flightPanel);

		}

		JButton checkoutButton = createCheckoutButton();
		panel.add(checkoutButton, BorderLayout.SOUTH);

		setVisible(true);
	}

	public JPanel createPanel() {
		// Flight list panel
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(20, 5));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel pageLabel = new JLabel("Available Flights");
		pageLabel.setFont(new Font("Arial", Font.BOLD, 24));
		pageLabel.setForeground(new Color(30, 144, 255));
		pageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(pageLabel);

		return panel;
	}

	public JPanel createFlightPanel(Flight flight) {
		JPanel flightPanel = new JPanel();
		flightPanel.setLayout(new GridLayout(0, 2));
		// flightPanel.setBorder(BorderFactory.createTitledBorder(flight.getFlightNumber()));
		flightPanel.setBackground(Color.WHITE);

		// Using HTML for multiline JLabel
		String flightDetails = "<html><p><b>Departure:</b> " + flight.getDepartureDate() + " at "
				+ flight.getDepartureTime()
				+ "</p>"
				+ "<p><b>Arrival:</b> " + flight.getArrivalDate() + " at " + flight.getArrivalTime() + "</p>"
				+ "<p><b>Available Seats:</b> " + flight.getAvailableSeats() + "</p>"
				+ "<p><b>Economy Class Price:</b> $" + flight.getEconomyClassPrice() + "</p>"
				+ "<p><b>Business Class Price:</b> $" + flight.getBusinessClassPrice() + "</p></html>";
		JLabel flightLabel = new JLabel(flightDetails);

		String title = "<html><h3>" + flight.getFromLocation() + "→" + flight.getToLocation() + "</h3></html>";

		flightPanel.setBorder(BorderFactory.createTitledBorder(title));
		flightPanel.add(flightLabel);

		String buttonText = "Select Flight";
		JButton bookButton = new JButton(buttonText);
		bookButton.setBackground(new Color(30, 144, 255));
		bookButton.setForeground(Color.BLACK);
		bookButton.setFocusPainted(false);
		bookButton.setFont(new Font("Arial", Font.BOLD, 16));
		bookButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		// bookButton.setMaximumSize(new Dimension(10, 40));

		bookButton.addActionListener(e -> {
			// toggle the button text
			if (bookButton.getText().equals(buttonText)) {
				bookButton.setText("Selected");
				bookButton.setBackground(Color.GRAY);

				// add the flight to the cart
				selectedFlights.add(flight);

			} else {
				bookButton.setText(buttonText);
				bookButton.setBackground(new Color(30, 144, 255));

				// remove the flight from the cart
				selectedFlights.remove(flight);

			}
		});

		flightPanel.add(bookButton);

		return flightPanel;
	}

	public JButton createCheckoutButton() {
		JButton checkoutButton = new JButton("Proceed to Checkout");
		checkoutButton.setBackground(Color.blue);
		checkoutButton.setForeground(Color.BLACK);
		// checkoutButton.setFocusPainted(false);
		checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
		checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		// position absolute
		checkoutButton.setBounds(100, 100, 200, 40);

		checkoutButton.addActionListener(e -> {
			// Open the checkout page
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new CheckoutPage(selectedFlights, selectedClass);
				}
			});

			dispose();

		});

		return checkoutButton;
	}
}
