package by.bsuir.vdishp.model.observer;

import org.springframework.stereotype.Component;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public interface Observer {
    void objectCreated(Object obj);
    void objectModified(Object obj);
}
