package by.bsuir.vdishp.model.data;

import by.bsuir.vdishp.model.entity.Train;
import by.bsuir.vdishp.model.exception.DAOException;
import org.springframework.stereotype.Component;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public interface TrainDao{
    Train parse() throws DAOException;
}
