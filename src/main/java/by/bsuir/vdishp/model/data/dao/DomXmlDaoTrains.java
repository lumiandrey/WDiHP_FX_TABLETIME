package by.bsuir.vdishp.model.data.dao;

import by.bsuir.vdishp.model.data.TrainDao;
import by.bsuir.vdishp.model.data.parser.dom_parser.trains_parser.TrainNodeList;
import by.bsuir.vdishp.model.entity.Train;
import by.bsuir.vdishp.model.exception.DAOException;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class DomXmlDaoTrains implements TrainDao {
    private static final DomXmlDaoTrains instance = new DomXmlDaoTrains();
    private static final String FILE_PATH = "E:\\train.xml";
    private DomXmlDaoTrains(){}
    @Override
    public Train parse() throws DAOException {
        Train list = null;
        try {
            FileInputStream in = new FileInputStream(FILE_PATH);
            Reader reader = new InputStreamReader(in);
            InputSource inputSource = new InputSource(reader);
            list = TrainNodeList.getListOfFlight(inputSource);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new DAOException("DOM Exception",e);
        }
        return list;
    }

    public static DomXmlDaoTrains getInstance(){
        return instance;
    }
}