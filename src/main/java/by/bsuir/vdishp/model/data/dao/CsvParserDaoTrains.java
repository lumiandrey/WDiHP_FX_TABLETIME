package by.bsuir.vdishp.model.data.dao;



import by.bsuir.vdishp.model.data.TrainDao;
import by.bsuir.vdishp.model.data.parser.csv_parser.CsvAnalyserTrains;
import by.bsuir.vdishp.model.entity.Train;
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
public class CsvParserDaoTrains implements TrainDao {
    private static final CsvParserDaoTrains instance = new CsvParserDaoTrains();
    private static final String FILE_PATH = "E:\\train.csv";
    private CsvParserDaoTrains(){}

    @Override
    public Train parse() throws DAOException {
        List<String> list = new ArrayList<>();
        Train routeList = null;
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                list.add(line);
            }
            System.out.println(list);
            routeList = CsvAnalyserTrains.analyse(list);
        } catch (IOException e) {
            throw new DAOException("DOM Exception",e);
        }
        return routeList;
    }

    public static CsvParserDaoTrains getInstance(){
        return instance;
    }
}
