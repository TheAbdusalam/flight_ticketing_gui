package main;

public class Flight {
	private int Id;
	private String flightNumber;
	private String fromLocation;
	private String toLocation;
	private String departureDate;
	private String departureTime;
	private String arrivalDate;
	private String arrivalTime;
	private int availableSeats;
	private double economyClassPrice;
	private double businessClassPrice;
	private int passengers;

	// Constructor
	public Flight() {
		this.flightNumber = flightNumber;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.availableSeats = availableSeats;
		this.economyClassPrice = economyClassPrice;
		this.businessClassPrice = businessClassPrice;
	}

	// getter and setter methods
	public String getFlightDetails() {
		return "Flight Number: " + flightNumber + "\nFrom: " + fromLocation + "\nTo: " + toLocation
				+ "\nDeparture Date: "
				+ departureDate + "\nDeparture Time: " + departureTime + "\nArrival Date: " + arrivalDate
				+ "\nArrival Time: " + arrivalTime + "\nAvailable Seats: " + availableSeats + "\nEconomy Class Price: "
				+ economyClassPrice + "\nBusiness Class Price: " + businessClassPrice;
	}

	public void setId(int id) {
		this.Id = id;
	}

	public int getId() {
		return Id;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

	public double getEconomyClassPrice() {
		return economyClassPrice;
	}

	public void setEconomyClassPrice(double economyClassPrice) {
		this.economyClassPrice = economyClassPrice;
	}

	public double getBusinessClassPrice() {
		return businessClassPrice;
	}

	public void setBusinessClassPrice(double businessClassPrice) {
		this.businessClassPrice = businessClassPrice;
	}
}
