package by.bsuir.vdishp.model.entity;


import by.bsuir.vdishp.model.observer.Observers;

import java.util.Date;

/**
 * Created by andrey on 14.05.2016.
 */
public class Bus {
    Observers observers = new Observers();

    private static final String DATE_OF_DEPARTURE = "01.01.2016";
    private static final double DEPARTURE_TIME = 00.00;
    private static final double TRANSIT_TIME = 00.00;
    private static final double ARRIVAL_TIME = 00.00;
    private static final String DESTINATION = "Минск";
    private static final String STATION_OF_DEPARTURE = "Минск";
    private static final String DEPARTURE = "Минск";
    private static final int PLATFORM = 1;
    private static final String STATION_OF_ARRIVAL = "Минск";
    private static final int ROUTE_ID = 0;
    private static final String DATE_OF_ARRIVAL = "01.01.2016";
    private static final String DAY = "work day";
    public static final String BRAND = "ecoline";

    private int routeId;

    public String getBrandBus() {
        return brandBus;
    }

    public void setBrandBus(String brandBus) {
        this.brandBus = brandBus;
    }

    private String brandBus;
    private String dateOfDeparture;
    private String dateOfArrival;
    private String destination;
    private String stationOfDeparture;
    private String departure;
    private int platform;
    private String stationOfArrival;
    private Ticket ticket;
    private double transitTime;
    private double departureTime;
    private double arrivalTime;
    private String day;
    private Date fullDate;

    public Bus(){
        this.routeId =ROUTE_ID;
        this.brandBus = BRAND;
        this.dateOfDeparture = DATE_OF_DEPARTURE;
        this.departureTime =DEPARTURE_TIME;
        this.destination =DESTINATION;
        this.dateOfArrival = DATE_OF_ARRIVAL;
        this.stationOfDeparture = STATION_OF_DEPARTURE;
        this.departure = DEPARTURE;
        this.platform = PLATFORM;
        this.stationOfArrival = STATION_OF_ARRIVAL;
        this.ticket = new Ticket();
        this.transitTime = TRANSIT_TIME;
        this.arrivalTime = ARRIVAL_TIME;
        this.day=DAY;
        this.fullDate = new Date();
    }

    public Bus(int routeId,String dateOfArrival,String dateOfDeparture,
                 double departureTime, String destination, String stationOfDeparture,
                 String departure, int platform, String stationOfArrival, Ticket ticket,
                 double transitTime,double arrivalTime,String day) {
        this.routeId = routeId;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.departureTime = departureTime;
        this.destination = destination;
        this.stationOfDeparture = stationOfDeparture;
        this.platform = platform;
        this.stationOfArrival = stationOfArrival;
        this.ticket = ticket;
        this.transitTime = transitTime;
        this.departure = departure;
        this.arrivalTime = arrivalTime;
        this.day = day;
    }

    public Date getFullDate() {
        return fullDate;
    }

    public void setFullDate(Date fullDate) {
        this.fullDate = fullDate;
        observers.notifyObjectModified(this);
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
        observers.notifyObjectModified(this);
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(double arrivalTime) {
        this.arrivalTime = arrivalTime;
        observers.notifyObjectModified(this);
    }

    public String getDateOfArrival() {
        return dateOfArrival;
    }

    public void setDateOfArrival(String dateOfArrival) {
        this.dateOfArrival = dateOfArrival;
        observers.notifyObjectModified(this);
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
        observers.notifyObjectModified(this);
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
        observers.notifyObjectModified(this);
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
        observers.notifyObjectModified(this);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
        observers.notifyObjectModified(this);
    }

    public String getStationOfDeparture() {
        return stationOfDeparture;
    }

    public void setStationOfDeparture(String stationOfDeparture) {
        this.stationOfDeparture = stationOfDeparture;
        observers.notifyObjectModified(this);
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
        observers.notifyObjectModified(this);
    }

    public String getStationOfArrival() {
        return stationOfArrival;
    }

    public void setStationOfArrival(String stationOfArrival) {
        this.stationOfArrival = stationOfArrival;
        observers.notifyObjectModified(this);
    }

    public Ticket getTicket() {
        return ticket;
    }
    public Integer getTicketCost() {
        return ticket.getCost();
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
        observers.notifyObjectModified(this);
    }

    public double getTransitTime() {
        return transitTime;
    }

    public void setTransitTime(double transitTime) {
        this.transitTime = transitTime;
        observers.notifyObjectModified(this);
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
        observers.notifyObjectModified(this);
    }

    @Override
    public String toString() {

        return "Route{" +
                "routeId=" + routeId +
                ", dateOfDeparture='" + dateOfDeparture + '\'' +
                ", dateOfArrival='" + dateOfArrival + '\'' +
                ", destination='" + destination + '\'' +
                ", stationOfDeparture='" + stationOfDeparture + '\'' +
                ", departure='" + departure + '\'' +
                ", platform=" + platform +
                ", stationOfArrival='" + stationOfArrival + '\'' +
                ", ticket=" + ticket +
                ", transitTime=" + transitTime +
                ", departureTime=" + departureTime +
                '}';
    }
}
