package by.bsuir.vdishp.model.data.parser.dom_parser.trains_parser;

import by.bsuir.vdishp.model.entity.Train;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by HP on 31.01.2016.
 */
public class TrainNodeList {
    public static Train getListOfFlight(InputSource inputSourse) throws ParserConfigurationException, IOException, SAXException {
        Train flights;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(inputSourse);

        Element root = document.getDocumentElement();
        flights = TrainAnalyzer.buildList(root);

        return flights;
    }
}
