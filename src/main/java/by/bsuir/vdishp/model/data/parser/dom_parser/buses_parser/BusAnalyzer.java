package by.bsuir.vdishp.model.data.parser.dom_parser.buses_parser;

import by.bsuir.vdishp.model.entity.Bus;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 24.02.2016.
 */
public class BusAnalyzer {
    private static final String LACK_OF_ELEMENT = "0";
    private static final String ELEMENT_ROUTE = "route";
    private static final String ELEMENT_ROUTE_ID = "route-id";
    private static final String ELEMENT_BRAND_BUS = "brand-bus";
    private static final String ELEMENT_DATE_OF_DEPARTURE = "date-of-departure";
    private static final String ELEMENT_DATE_OF_ARRIVAL = "date-of-arrival";
    private static final String ELEMENT_DESTINATION = "destination";
    private static final String ELEMENT_STATION_OF_DEPARTURE = "station-of-departure";
    private static final String ELEMENT_DEPARTURE = "departure";
    private static final String ELEMENT_PLATFORM = "platform";
    private static final String ELEMENT_STATION_OF_ARRIVAL = "station-of-arrival";
    private static final String ELEMENT_TICKET_COST = "ticket-cost";
    private static final String ELEMENT_ARRIVAL_TIME = "arrival-time";
    private static final String ELEMENT_DEPARTURE_TIME = "departure-time";
    private static final String ELEMENT_TRANSIT_TIME = "transit-time";
    private static final String DAY = "day";

    public static List<Bus> buildList(Element root){
        ArrayList<Bus> buses = new ArrayList<>();
        NodeList BusNodes = root.getElementsByTagName(ELEMENT_ROUTE);
        Bus tempBus;

        for (int i = 0; i < BusNodes.getLength(); i++) {
            Element routeNode = (Element) BusNodes.item(i);
            tempBus = new Bus();
            tempBus.setBrandBus(getChildValue(routeNode, ELEMENT_BRAND_BUS));
            tempBus.setRouteId(Integer.parseInt(getChildValue(routeNode, ELEMENT_ROUTE_ID)));
            tempBus.setDateOfDeparture(getChildValue(routeNode, ELEMENT_DATE_OF_DEPARTURE));
            tempBus.setDateOfArrival(getChildValue(routeNode, ELEMENT_DATE_OF_ARRIVAL));
            tempBus.setDestination(getChildValue(routeNode, ELEMENT_DESTINATION));
            tempBus.setStationOfDeparture(getChildValue(routeNode, ELEMENT_STATION_OF_DEPARTURE));
            tempBus.setDeparture(getChildValue(routeNode, ELEMENT_DEPARTURE));
            tempBus.setPlatform(Integer.parseInt(getChildValue(routeNode, ELEMENT_PLATFORM)));
            tempBus.setStationOfArrival(getChildValue(routeNode, ELEMENT_STATION_OF_ARRIVAL));
            tempBus.getTicket().setCost(Integer.parseInt(getChildValue(routeNode, ELEMENT_TICKET_COST)));
            tempBus.setArrivalTime(Double.parseDouble(getChildValue(routeNode, ELEMENT_ARRIVAL_TIME)));
            tempBus.setDepartureTime(Double.parseDouble(getChildValue(routeNode, ELEMENT_DEPARTURE_TIME)));
            tempBus.setTransitTime(Double.parseDouble(getChildValue(routeNode,ELEMENT_TRANSIT_TIME)));
            tempBus.setDay(getChildValue(routeNode,DAY));
            buses.add(tempBus);
        }
        return buses;
    }
    private static Element getChildElement(Element parent, String childName){
        NodeList nodes = parent.getElementsByTagName(childName);
        Element child = (Element) nodes.item(0);
        return child;
    }

    private static String getChildValue(Element parent, String childElement){
        Element child = getChildElement(parent, childElement);
        if(child != null){
            Node node  = child.getFirstChild();
            String value = node.getNodeValue().trim();
            return value;
        }else{
            return LACK_OF_ELEMENT;
        }
    }
}
