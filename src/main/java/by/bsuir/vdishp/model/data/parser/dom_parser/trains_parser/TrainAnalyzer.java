package by.bsuir.vdishp.model.data.parser.dom_parser.trains_parser;

import by.bsuir.vdishp.model.entity.Train;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class TrainAnalyzer {
    private static final String LACK_OF_ELEMENT = "0";
    private static final String ELEMENT_FLIGHT = "flight";
    private static final String ELEMENT_TRAIN_ID = "train-id";
    private static final String ELEMENT_CAR_TYPE = "car-type";
    private static final String ELEMENT_DATE_OF_DEPARTURE = "date-of-departure";
    private static final String ELEMENT_DEPARTURE_TIME = "departure-time";
    private static final String ELEMENT_DESTINATION = "destination";
    private static final String ELEMENT_DEPARTURE = "departure";
    private static final String ELEMENT_STATION_OF_DEPARTURE = "station-of-departure";
    private static final String ELEMENT_PLATFORM = "platform";
    private static final String ELEMENT_STATION_OF_ARRIVAL = "station-of-arrival";
    private static final String ELEMENT_TICKET_COST = "ticket-cost";
    private static final String ELEMENT_DATE_OF_ARRIVAL = "date-of-arrival";
    private static final String ELEMENT_TIME_OF_ARRIVAL = "time-of-arrival";
    private static final String DAY = "day";

    public static Train buildList(Element root){
        Train trains = new Train();
        NodeList trainsNodes = root.getElementsByTagName(ELEMENT_FLIGHT);
        Train tempTrain;
        int flag = 0;
        for (int i = 0; i < trainsNodes.getLength(); i++) {
            Element flightNode = (Element) trainsNodes.item(i);
            if(flag==0){
                trains.setId(getChildValue(flightNode, ELEMENT_TRAIN_ID));
                trains.setType(getChildValue(flightNode, ELEMENT_CAR_TYPE));
                trains.setDateOfDeparture(getChildValue(flightNode, ELEMENT_DATE_OF_DEPARTURE));
                trains.setDepartureTime(Double.parseDouble(getChildValue(flightNode, ELEMENT_DEPARTURE_TIME)));
                trains.setDestination(getChildValue(flightNode, ELEMENT_DESTINATION));
                trains.setStationOfDeparture(getChildValue(flightNode, ELEMENT_STATION_OF_DEPARTURE));
                trains.setDeparture(getChildValue(flightNode, ELEMENT_DEPARTURE));
                trains.setPlatform(Integer.parseInt(getChildValue(flightNode, ELEMENT_PLATFORM)));
                trains.setStationOfArrival(getChildValue(flightNode, ELEMENT_STATION_OF_ARRIVAL));
                trains.getTicket().setCost(Integer.parseInt(getChildValue(flightNode, ELEMENT_TICKET_COST)));
                trains.setDataOfArrival(getChildValue(flightNode, ELEMENT_DATE_OF_ARRIVAL));
                trains.setTimeOfArrival(Double.parseDouble(getChildValue(flightNode, ELEMENT_TIME_OF_ARRIVAL)));
                trains.setDay(getChildValue(flightNode,DAY));
                flag=1;
            }
            else {
                tempTrain = new Train();
                tempTrain.setId(getChildValue(flightNode, ELEMENT_TRAIN_ID));
                tempTrain.setType(getChildValue(flightNode, ELEMENT_CAR_TYPE));
                tempTrain.setDateOfDeparture(getChildValue(flightNode, ELEMENT_DATE_OF_DEPARTURE));
                tempTrain.setDepartureTime(Double.parseDouble(getChildValue(flightNode, ELEMENT_DEPARTURE_TIME)));
                tempTrain.setDestination(getChildValue(flightNode, ELEMENT_DESTINATION));
                tempTrain.setStationOfDeparture(getChildValue(flightNode, ELEMENT_STATION_OF_DEPARTURE));
                tempTrain.setDeparture(getChildValue(flightNode, ELEMENT_DEPARTURE));
                tempTrain.setPlatform(Integer.parseInt(getChildValue(flightNode, ELEMENT_PLATFORM)));
                tempTrain.setStationOfArrival(getChildValue(flightNode, ELEMENT_STATION_OF_ARRIVAL));
                tempTrain.getTicket().setCost(Integer.parseInt(getChildValue(flightNode, ELEMENT_TICKET_COST)));
                tempTrain.setDataOfArrival(getChildValue(flightNode, ELEMENT_DATE_OF_ARRIVAL));
                tempTrain.setTimeOfArrival(Double.parseDouble(getChildValue(flightNode, ELEMENT_TIME_OF_ARRIVAL)));
                tempTrain.setDay(getChildValue(flightNode, DAY));
                trains.add(tempTrain);
            }
        }
        return trains;
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
