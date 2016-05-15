package by.bsuir.vdishp.model.observer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class Observers<T extends Observer> extends ArrayList<T> {
    public void notifyObjectCreated(Object obj) {
        for (Iterator<T> iter = (Iterator<T>) iterator(); iter.hasNext();)
            iter.next().objectCreated(obj);
    }
    public void notifyObjectModified(Object obj) {
        for (Iterator<T> iter = (Iterator<T>) iterator(); iter.hasNext();)
            iter.next().objectModified(obj);
    }
}