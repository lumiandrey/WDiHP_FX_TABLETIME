package by.bsuir.vdishp.model.comparator;



import by.bsuir.vdishp.model.entity.Train;

import java.util.Comparator;

/**
 * Created by andrey on 14.05.2016.
 */
public class DepartureTimeComparatorTrains implements Comparator<Train> {
    @Override
    public int compare(Train flight1, Train flight2) {
        String str1 = String.valueOf(flight1.getFullDate());
        String str2  = String.valueOf(flight2.getFullDate());
        return str1.compareTo(str2);
    }


}
