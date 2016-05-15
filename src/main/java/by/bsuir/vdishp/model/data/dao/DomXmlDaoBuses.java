package by.bsuir.vdishp.model.data.dao;

import by.bsuir.vdishp.model.data.BusDao;
import by.bsuir.vdishp.model.data.parser.dom_parser.buses_parser.BusNodeList;
import by.bsuir.vdishp.model.entity.Bus;
import by.bsuir.vdishp.model.exception.DAOException;
import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class DomXmlDaoBuses implements BusDao {
    private static final DomXmlDaoBuses instance = new DomXmlDaoBuses();
    private static final String FILE_PATH = "E:\\bus.xml";
    private DomXmlDaoBuses(){}
    @Override
    public List<Bus> parse() throws DAOException {
        List<Bus> list = null;
        try {
            FileInputStream in = new FileInputStream(FILE_PATH);
            Reader reader = new InputStreamReader(in);
            InputSource inputSource = new InputSource(reader);
            list = BusNodeList.getListOfRoute(inputSource);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
            return list;
        }
        return list;
    }

    public static DomXmlDaoBuses getInstance(){
        return instance;
    }
}