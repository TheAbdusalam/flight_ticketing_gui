package main;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TicketBookingApp extends CustomFrame {
    private String Name;

    public TicketBookingApp(String Name) {
        this.Name = Name;
        setTitle("Ticket Booking App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null); // Center the frame on the screen

        JPanel panel = createPanel();

        add(panel);

        setVisible(true);
    }

    private String[] getNext30Days() {
        String[] dates = new String[5];
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 5; i++) {
            dates[i] = dateFormat.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return dates;
    }

    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240)); // Set background color

        // Creating and scaling the image
        ImageIcon imageIcon = new ImageIcon("logo.png");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);

        // Creating and configuring the JLabel
        JLabel pageLabel = new JLabel(String.format("Welcome, Where do you want to go? %s", this.Name), imageIcon,
                SwingConstants.CENTER);
        pageLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Larger font size
        pageLabel.setForeground(new Color(30, 144, 255)); // Adjusted font color
        pageLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        pageLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        pageLabel.setBackground(Color.WHITE);
        pageLabel.setOpaque(true);

        // Adding the label to the panel
        panel.add(pageLabel, BorderLayout.NORTH);

        // Mock data for the airport dropdown
        String[] mockAirports = { "JFK - New York", "LHR - London", "LAX - Los Angeles", "ORD - Chicago",
                "DFW - Dallas",
                "DEN - Denver",
                "ATL - Atlanta", "SFO - San Francisco", "SEA - Seattle", "LAS - Las Vegas", "MIA - Miami",
                "IST - Istanbul" };

        // Creating the form components
        JPanel formPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for better alignment
        formPanel.setBackground(Color.WHITE); // Set background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Add insets for spacing

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(fromLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> fromDropBox = new JComboBox<>(mockAirports);
        formPanel.add(fromDropBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(destinationLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> destinationDropdown = new JComboBox<>(mockAirports);
        formPanel.add(destinationDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel dateLabel = new JLabel("Date of Travel:");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(dateLabel, gbc);

        gbc.gridx = 1;
        JComboBox<String> dateDropdown = new JComboBox<>(getNext30Days()); // Populate with next 10 days
        formPanel.add(dateDropdown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        JLabel tripTypeLabel = new JLabel("Trip Type:");
        tripTypeLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(tripTypeLabel, gbc);

        gbc.gridx = 1;
        JRadioButton oneWayRadio = new JRadioButton("One Way");
        oneWayRadio.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(oneWayRadio, gbc);

        gbc.gridx = 2;
        JRadioButton roundTripRadio = new JRadioButton("Round Trip");
        roundTripRadio.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(roundTripRadio, gbc);

        gbc.gridx = 5;
        gbc.gridy = 0;
        JLabel returnDateLabel = new JLabel("Return Date:");
        JComboBox returnDateDropdown = new JComboBox<>(getNext30Days()); // Populate with next 10 days
                                                                         // returnDateLabel.setVisible(false);
        returnDateLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        returnDateLabel.setVisible(false);
        returnDateDropdown.setVisible(false);

        // Action listener for trip type radio buttons
        ActionListener tripTypeListener = e -> {
            if (oneWayRadio.isSelected()) {
                returnDateLabel.setVisible(false);
                returnDateDropdown.setVisible(false);
            } else {
                returnDateLabel.setVisible(true);
                returnDateDropdown.setVisible(true);
            }
        };

        oneWayRadio.addActionListener(tripTypeListener);
        roundTripRadio.addActionListener(tripTypeListener);

        ButtonGroup tripTypeGroup = new ButtonGroup();
        tripTypeGroup.add(oneWayRadio);
        tripTypeGroup.add(roundTripRadio);

        if (!oneWayRadio.isSelected()) {
            formPanel.add(returnDateLabel);
            formPanel.add(returnDateDropdown, gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel numberOfPassengersLabel = new JLabel("Number of Passengers:");
        numberOfPassengersLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(numberOfPassengersLabel, gbc);

        gbc.gridx = 1;
        JSpinner numberOfPassengersSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        formPanel.add(numberOfPassengersSpinner, gbc);

        // economy or business class
        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(classLabel, gbc);

        gbc.gridx = 1;
        JRadioButton economyRadio = new JRadioButton("Economy");
        economyRadio.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(economyRadio, gbc);

        gbc.gridx = 2;
        JRadioButton businessRadio = new JRadioButton("Business");
        businessRadio.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        formPanel.add(businessRadio, gbc);

        ButtonGroup classGroup = new ButtonGroup();
        classGroup.add(economyRadio);
        classGroup.add(businessRadio);

        gbc.gridx = 0;
        gbc.gridy = 6;
        // gbc.gridwidth = 3;
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Adjusted font size
        searchButton.setPreferredSize(new Dimension(200, 40)); // Adjusted button size
        searchButton.setBackground(new Color(30, 144, 255)); // Adjusted button color
        searchButton.setForeground(Color.BLACK); // Adjusted text color
        searchButton.setFocusPainted(false); // Remove focus border
        formPanel.add(searchButton, gbc);

        searchButton.addActionListener(e -> {
            String from = (String) fromDropBox.getSelectedItem();
            String destination = (String) destinationDropdown.getSelectedItem();
            String date = (String) dateDropdown.getSelectedItem();
            String returnDate = (String) returnDateDropdown.getSelectedItem();
            int passengers = (int) numberOfPassengersSpinner.getValue();
            String tripType = oneWayRadio.isSelected() ? "One Way" : "Round Trip";
            String selectedClass = economyRadio.isSelected() ? "economy" : "business";

            // validating the data
            boolean dataIsValid = validateData(from, destination, date, passengers, tripType, returnDate, economyRadio,
                    businessRadio);

            if (dataIsValid) {
                // get the flights
                ArrayList<Integer> flights = DatabaseManager.findFlights(from, destination, date, passengers, tripType,
                        returnDate);

                if (flights.size() == 0) {
                    JOptionPane.showMessageDialog(null, "No flights found for the selected criteria.");
                } else {

                    // Open the flight list page
                    openFlightListPage(flights, roundTripRadio.isSelected(), selectedClass, passengers);
                    dispose(); // Close the ticket booking page

                }
            }
        });

        // Adding the form panel to the main panel
        panel.add(formPanel, BorderLayout.CENTER);

        return panel;
    }

    private boolean validateData(String from, String destination, String date, int passengers, String tripType,
            String returnDate, JRadioButton economyRadio, JRadioButton businessRadio) {
        if (from.equals(destination)) {
            JOptionPane.showMessageDialog(null, "From and Destination cannot be the same.");
            return false;
        }

        if (passengers < 1) {
            JOptionPane.showMessageDialog(null, "Number of passengers must be at least 1.");
            return false;
        }

        if (tripType == null) {
            JOptionPane.showMessageDialog(null, "Please select a trip type.");
            return false;
        }

        if (economyRadio.isSelected() == false && businessRadio.isSelected() == false) {
            JOptionPane.showMessageDialog(null, "Please select a class.");
            return false;
        }

        if (tripType.equals("Round Trip") && returnDate == null) {
            JOptionPane.showMessageDialog(null, "Please select a return date.");
            return false;
        }

        return true;

    }

    private void openFlightListPage(ArrayList<Integer> flights, Boolean isRoundTrip, String selectedClass,
            int passengers) {

        ArrayList<Flight> flightList = new ArrayList<Flight>();
        for (int flight : flights) {
            Flight single = DatabaseManager.getFlightById(flight);

            flightList.add(single);
        }

        SwingUtilities.invokeLater(() -> new FlightList(flightList, isRoundTrip, selectedClass, passengers));
    }
}
