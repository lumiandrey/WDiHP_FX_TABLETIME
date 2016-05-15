package by.bsuir.vdishp.model.Iterator;

import org.springframework.stereotype.Component;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public interface Iterator {
    public boolean hasNext();
    public Object next();
}
