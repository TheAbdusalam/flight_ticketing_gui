package main;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/abdifatah_db";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "sqlpass1";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
	}

	// registerUser method
	public static boolean registerUser(String username, String password, String email) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = getConnection();
			String query = "INSERT INTO users (username, email, password) VALUES (?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, email);
			preparedStatement.setString(3, password);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeConnection(connection);
		}

		return true;
	}

	// loginUser method
	public static boolean loginUser(String username, String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			String query = "SELECT * FROM users WHERE username = ? AND password = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return false;
	}

	// find flights based on the search criteria
	public static ArrayList<Integer> findFlights(String from, String destination, String date, int passengers,
			String tripType,
			String returnDate) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			String query = "SELECT * FROM flights WHERE `fromLocation` = ? AND `toLocation` = ? AND `departureDate` = ? AND `availableSeats` >= ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, from);
			preparedStatement.setString(2, destination);
			preparedStatement.setString(3, date);
			preparedStatement.setInt(4, passengers);
			resultSet = preparedStatement.executeQuery();

			// Get the return flights if the trip type is round trip
			ArrayList<Integer> flightNumbers = new ArrayList<Integer>();

			while (resultSet.next()) {
				flightNumbers.add(resultSet.getInt("id"));
			}

			if (tripType.equals("Round Trip")) {

				String returnQuery = "SELECT * FROM flights where `fromLocation` = ? AND `toLocation` = ? AND `departureDate` = ? AND `availableSeats` >= ?";
				preparedStatement = connection.prepareStatement(returnQuery);
				preparedStatement.setString(1, destination);
				preparedStatement.setString(2, from);
				preparedStatement.setString(3, returnDate);
				preparedStatement.setInt(4, passengers);
				ResultSet returnFlights = preparedStatement.executeQuery();

				// merge the two result sets
				while (returnFlights.next()) {
					flightNumbers.add(returnFlights.getInt("id"));
				}
			}

			return flightNumbers;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	// get Flight by ID
	public static Flight getFlightById(int flightId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = getConnection();
			String query = "SELECT * FROM flights WHERE id = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, flightId);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				Flight flight = new Flight();
				flight.setId(resultSet.getInt("id"));
				flight.setFlightNumber(resultSet.getString("flightNumber"));
				flight.setFromLocation(resultSet.getString("fromLocation"));
				flight.setToLocation(resultSet.getString("toLocation"));
				flight.setDepartureDate(resultSet.getString("departureDate"));
				flight.setDepartureTime(resultSet.getString("departureTime"));
				flight.setArrivalDate(resultSet.getString("arrivalDate"));
				flight.setArrivalTime(resultSet.getString("arrivalTime"));
				flight.setAvailableSeats(resultSet.getInt("availableSeats"));
				flight.setEconomyClassPrice(resultSet.getDouble("economyClassPrice"));
				flight.setBusinessClassPrice(resultSet.getDouble("businessClassPrice"));

				return flight;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return null;
	}

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
