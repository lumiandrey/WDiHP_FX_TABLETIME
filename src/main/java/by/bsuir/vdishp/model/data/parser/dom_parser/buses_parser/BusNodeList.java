package by.bsuir.vdishp.model.data.parser.dom_parser.buses_parser;

import by.bsuir.vdishp.model.entity.Bus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class BusNodeList {
    public static List<Bus> getListOfRoute(InputSource inputSourse) throws ParserConfigurationException, IOException, SAXException {
        List<Bus> routes;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(inputSourse);

        Element root = document.getDocumentElement();
        routes = BusAnalyzer.buildList(root);

        return routes;
    }
}
