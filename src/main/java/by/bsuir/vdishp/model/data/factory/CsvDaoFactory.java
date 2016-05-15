package by.bsuir.vdishp.model.data.factory;

import by.bsuir.vdishp.model.data.BusDao;
import by.bsuir.vdishp.model.data.TrainDao;
import by.bsuir.vdishp.model.data.dao.CsvParserDaoBuses;
import by.bsuir.vdishp.model.data.dao.CsvParserDaoTrains;
import org.springframework.stereotype.Component;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class CsvDaoFactory extends DAOFactory {
    private static final CsvDaoFactory instance = new CsvDaoFactory();
    private CsvDaoFactory(){}
    public static CsvDaoFactory getInstance(){
        return instance;
    }

    @Override
    public BusDao getBusDao() {
        return CsvParserDaoBuses.getInstance();
    }

    @Override
    public TrainDao getTrainDao() {
        return CsvParserDaoTrains.getInstance();
    }
}
