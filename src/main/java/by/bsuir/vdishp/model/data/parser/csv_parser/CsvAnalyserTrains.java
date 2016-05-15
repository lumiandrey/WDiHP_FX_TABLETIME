package by.bsuir.vdishp.model.data.parser.csv_parser;

import by.bsuir.vdishp.model.entity.Train;

import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
public class CsvAnalyserTrains {
    public CsvAnalyserTrains(){}

    public static Train analyse(List<String> list){
        String cvsSplitBy = ",";
        int flag=0;
        Train trainList = new Train();
        for (String str : list){
            Train train = new Train();
            String[] element = str.split(cvsSplitBy);
            if(flag==0){
                trainList.setId(element[0]);
                trainList.setType(element[1]);
                trainList.setDateOfDeparture(element[2]);
                trainList.setDepartureTime(Double.parseDouble(element[3]));
                trainList.setStationOfDeparture(element[4]);
                trainList.setDeparture(element[5]);
                trainList.setDestination(element[6]);
                trainList.setPlatform(Integer.parseInt(element[7]));
                trainList.setStationOfArrival(element[8]);
                trainList.getTicket().setCost(Integer.parseInt(element[9]));
                trainList.setDataOfArrival(element[10]);
                trainList.setTimeOfArrival(Double.parseDouble(element[11]));
                trainList.setDay(element[12]);
                flag=1;
            }
            else {
                train.setId(element[0]);
                train.setType(element[1]);
                train.setDateOfDeparture(element[2]);
                train.setDepartureTime(Double.parseDouble(element[3]));
                train.setStationOfDeparture(element[4]);
                train.setDeparture(element[5]);
                train.setDestination(element[6]);
                train.setPlatform(Integer.parseInt(element[7]));
                train.setStationOfArrival(element[8]);
                train.getTicket().setCost(Integer.parseInt(element[9]));
                train.setDataOfArrival(element[10]);
                train.setTimeOfArrival(Double.parseDouble(element[11]));
                train.setDay(element[12]);

                trainList.add(train);
            }
        }
        return trainList;
    }
}
