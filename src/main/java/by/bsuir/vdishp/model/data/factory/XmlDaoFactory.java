package by.bsuir.vdishp.model.data.factory;


import by.bsuir.vdishp.model.data.BusDao;
import by.bsuir.vdishp.model.data.TrainDao;
import by.bsuir.vdishp.model.data.dao.DomXmlDaoBuses;
import by.bsuir.vdishp.model.data.dao.DomXmlDaoTrains;
import org.springframework.stereotype.Component;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class XmlDaoFactory extends DAOFactory {
    private static final XmlDaoFactory instance = new XmlDaoFactory();
    private XmlDaoFactory(){}
    public static XmlDaoFactory getInstance(){
        return instance;
    }
    @Override
    public BusDao getBusDao() {
        return DomXmlDaoBuses.getInstance();
    }

    @Override
    public TrainDao getTrainDao() {
        return DomXmlDaoTrains.getInstance();
    }
}
