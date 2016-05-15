package by.bsuir.vdishp.model.data.dao;


import by.bsuir.vdishp.model.data.BusDao;
import by.bsuir.vdishp.model.data.parser.csv_parser.CsvAnalyserBuses;
import by.bsuir.vdishp.model.entity.Bus;
import by.bsuir.vdishp.model.exception.DAOException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class CsvParserDaoBuses implements BusDao {
    private static final CsvParserDaoBuses instance = new CsvParserDaoBuses();
    private static final String FILE_PATH = "E:\\bus1.csv";
    private CsvParserDaoBuses(){}
    @Override
    public List<Bus> parse() throws DAOException {
        List<String> list = new ArrayList<>();
        List<Bus> routeList = null;
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                list.add(line);
            }
            System.out.println(list);
            routeList = CsvAnalyserBuses.analyse(list);
        } catch (IOException  e) {
            e.printStackTrace();
            return routeList;
        }
        return routeList;
    }

    public static CsvParserDaoBuses getInstance(){
        return instance;
    }
}