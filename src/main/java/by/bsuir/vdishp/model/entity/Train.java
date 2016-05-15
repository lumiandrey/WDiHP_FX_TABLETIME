package by.bsuir.vdishp.model.entity;



import by.bsuir.vdishp.model.Iterator.Container;
import by.bsuir.vdishp.model.Iterator.Iterator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
public class Train implements Container {
    // Реализация паттерна Composite
    private List<Train> trainList;

    private static final String DATE_OF_DEPARTURE = "01.01.2016";
    private static final double DEPARTURE_TIME = 00.00;
    private static final String DESTINATION = "Минск";
    private static final String DEPARTURE = "Минск";
    private static final String STATION_OF_DEPARTURE = "Минск";
    private static final int PLATFORM = 1;
    private static final String STATION_OF_ARRIVAL = "Минск";
    private static final String DATA_OF_ARRIVAL = "Минск";
    private static final double TIME_OF_ARRIVAL = 00.00;
    private static final String DAY = "work day";
    private static final String ID = "0";
    private static final String TYPE = "плацкартный";

    //Composite
    public void add(Train e) {
        trainList.add(e);
    }
    //Composite
    public void remove(Train e) {
        trainList.remove(e);
    }
    //Composite
    public List<Train> getSubordinates(){
        return trainList;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String id ;
    private String type;


    private String dateOfDeparture;
    private double departureTime;
    private String destination;
    private String stationOfDeparture;
    private String departure;
    private int platform;
    private String stationOfArrival;
    private String dataOfArrival;
    private double timeOfArrival;
    private Ticket ticket;
    private Date fullDate;
    private String day;

    public Train(){
        this.dateOfDeparture = DATE_OF_DEPARTURE;
        this.departureTime = DEPARTURE_TIME;
        this.destination = DESTINATION;
        this.stationOfDeparture=STATION_OF_DEPARTURE;
        this.departure = DEPARTURE;
        this.platform = PLATFORM;
        this.stationOfArrival = STATION_OF_ARRIVAL;
        this.dataOfArrival = DATA_OF_ARRIVAL;
        this.timeOfArrival = TIME_OF_ARRIVAL;
        this.id = ID;
        this.type = TYPE;
        this.ticket = new Ticket();
        this.fullDate = new Date();
        this.day = DAY;
        this.trainList = new ArrayList<Train>();
    };

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Date getFullDate() {
        return fullDate;
    }

    public void setFullDate(Date fullDate) {
        this.fullDate = fullDate;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(String dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public double getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(double departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStationOfDeparture() {
        return stationOfDeparture;
    }

    public void setStationOfDeparture(String stationOfDeparture) {
        this.stationOfDeparture = stationOfDeparture;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getStationOfArrival() {
        return stationOfArrival;
    }

    public void setStationOfArrival(String stationOfArrival) {
        this.stationOfArrival = stationOfArrival;
    }


    public String getDataOfArrival() {
        return dataOfArrival;
    }

    public void setDataOfArrival(String dataOfArrival) {
        this.dataOfArrival = dataOfArrival;
    }

    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(double timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public Ticket getTicket() {
        return ticket;
    }
    public Integer getTicketCost() {
        return ticket.getCost();
    }


    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "dateOfDeparture='" + dateOfDeparture + '\'' +
                ", departureTime=" + departureTime +
                ", destination='" + destination + '\'' +
                ", stationOfDeparture='" + stationOfDeparture + '\'' +
                ", departure='" + departure + '\'' +
                ", platform=" + platform +
                ", stationOfArrival='" + stationOfArrival + '\'' +
                ", dataOfArrival='" + dataOfArrival + '\'' +
                ", timeOfArrival=" + timeOfArrival +
                ", ticket=" + ticket +
                ", day="+ day +
                "}\n";
    }

    //Iterator
    @Override
    public Iterator getIterator() {
            return new TrainIterator();
    }

    //Iterator
    private class TrainIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {

            if(index < trainList.size()){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {

            if(this.hasNext()){
                return trainList.get(index++);
            }

            return null;
        }
    }
}

