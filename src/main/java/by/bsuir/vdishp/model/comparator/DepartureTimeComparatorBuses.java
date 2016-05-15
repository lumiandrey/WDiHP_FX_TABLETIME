package by.bsuir.vdishp.model.comparator;

import by.bsuir.vdishp.model.entity.Bus;

import java.util.Comparator;

/**
 * Created by andrey on 14.05.2016.
 */
public class DepartureTimeComparatorBuses implements Comparator<Bus> {
    @Override
    public int compare(Bus route1, Bus route2) {
        String str1 = String.valueOf(route1.getFullDate());
        String str2  = String.valueOf(route2.getFullDate());
        return str1.compareTo(str2);
    }


}
