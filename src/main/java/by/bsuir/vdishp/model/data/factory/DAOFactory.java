package by.bsuir.vdishp.model.data.factory;

import by.bsuir.vdishp.model.data.BusDao;
import by.bsuir.vdishp.model.data.TrainDao;
import org.springframework.stereotype.Component;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public abstract class DAOFactory {
    public abstract BusDao getBusDao();
    public abstract TrainDao getTrainDao();
}
