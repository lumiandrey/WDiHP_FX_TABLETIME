package by.bsuir.vdishp.model.data.parser.csv_parser;


import by.bsuir.vdishp.model.entity.Bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
public class CsvAnalyserBuses {
    public CsvAnalyserBuses(){}

    public static List<Bus> analyse(List<String> list){
        String cvsSplitBy = ",";
        List<Bus> busList = new ArrayList<>();
        for (String str : list){
            Bus bus = new Bus();
            String[] element = str.split(cvsSplitBy);

            bus.setRouteId(Integer.parseInt(element[0]));
            bus.setDateOfDeparture(element[1]);
            bus.setDateOfArrival(element[2]);
            bus.setDestination(element[3]);
            bus.setDeparture(element[4]);
            bus.setStationOfDeparture(element[5]);
            bus.setPlatform(Integer.parseInt(element[6]));
            bus.setStationOfArrival(element[7]);
            bus.getTicket().setCost(Integer.parseInt(element[8]));
            bus.setBrandBus(element[9]);
            bus.setTransitTime(Double.parseDouble(element[10]));
            bus.setDepartureTime(Double.parseDouble(element[11]));
            bus.setDay(element[12]);
            bus.setArrivalTime(Double.parseDouble(element[13]));

            busList.add(bus);
        }
        return busList;
    }
}
