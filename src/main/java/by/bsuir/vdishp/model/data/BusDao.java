package by.bsuir.vdishp.model.data;

import by.bsuir.vdishp.model.entity.Bus;
import by.bsuir.vdishp.model.exception.DAOException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public interface BusDao extends IParceDAO<Bus> {
    @Override
    List<Bus> parse() throws DAOException;
}
