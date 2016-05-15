package by.bsuir.vdishp.controller;

import by.bsuir.vdishp.model.data.IParceDAO;
import by.bsuir.vdishp.model.data.TrainDao;
import by.bsuir.vdishp.model.data.factory.CsvDaoFactory;
import by.bsuir.vdishp.model.data.factory.XmlDaoFactory;
import by.bsuir.vdishp.model.entity.Bus;
import by.bsuir.vdishp.model.entity.Train;
import by.bsuir.vdishp.model.exception.DAOException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class TabTrainController implements Controller, ClildrenController<Train>{

    private Stage primaryStage;

    @FXML
    private BorderPane rootLauout;

    @FXML
    private TableView<Train> busTableView;

    @FXML
    private TableColumn<Train, String> typeTableColumn;
     @FXML
    private TableColumn<Train, Integer> TicketTableColumn;
     @FXML
    private TableColumn<Train, String> DestinationTableColumn;
     @FXML
    private TableColumn<Train, String> DepartureTableColumn;
     @FXML
    private TableColumn<Train, Integer> PlatformTableColumn;
     @FXML
    private TableColumn<Train, String> DateDepartureTableColumn;
     @FXML
    private TableColumn<Train, Double> DepartureTimeTableColumn;
     @FXML
    private TableColumn<Train, String> DateArrivalTableColumn;
     @FXML
    private TableColumn<Train, Double> TimeArrivalTableColumn;
     @FXML
    private TableColumn<Train, String> dayTableColumn;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TextField timeFrom;

    @FXML
    private TextField timeTo;

    private ObservableList<Train> trainObservableList = FXCollections.observableArrayList();

    private Train trainArrayList;


    @FXML
    void initialize(){
        loadAllData();
    }

    @Override
    public void filterByDay(String day) {
        Train listByDay = new Train();
        try{
            Train resultList = getResultList();
            for (int i = 0; i < resultList.getSubordinates().size(); i++) {
                String dayFromListElement = resultList.getSubordinates().get(i).getDay();
                if(dayFromListElement.equals(day)){
                    listByDay.add(resultList.getSubordinates().get(i));
                }
            }
            if(listByDay.getSubordinates().isEmpty()){
                trainArrayList = resultList;
            }
            else{
                trainArrayList = listByDay;
            }
            showTable();
        }catch(DAOException e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    @Override
    public void filterByDestination(String destination) {
        Train listByDestination = new Train();
        try{
            Train resultList = getResultList();
            for (int i = 0; i < resultList.getSubordinates().size(); i++) {
                String destinationFromXML = resultList.getSubordinates().get(i).getDestination().toUpperCase();
                if(destination.equals(destinationFromXML)){
                    listByDestination.add(resultList.getSubordinates().get(i));
                }
            }
            if(listByDestination.getSubordinates().isEmpty()){
                trainArrayList = resultList;
            }
            else{
                trainArrayList = listByDestination;
            }
            showTable();
        }catch(DAOException e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    @Override
    public void filterByDeparture(String departure) {
        IParceDAO dao = null;
        Train listByDeparture = new Train();
        try{
            Train resultList = getResultList();
            for (int i = 0; i < resultList.getSubordinates().size(); i++) {
                String departureFromXML = resultList.getSubordinates().get(i).getDeparture().toUpperCase();
                if(departure.equals(departureFromXML)){
                    listByDeparture.add(resultList.getSubordinates().get(i));
                }
            }
            if(listByDeparture.getSubordinates().isEmpty()){
                trainArrayList = resultList;
            }
            else{
                trainArrayList = listByDeparture;
            }
            showTable();
        }catch(DAOException e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    @FXML
    public void filterDateAndTime() {
        final String DOT = ".";
        Train tempFrom = new Train();
        Train tempTo = new Train();
        try{
            Train list = getResultList();

            LocalDate birthdayTFValue = dateFrom.getValue();
            Date dateFrom = Date.from(birthdayTFValue.atStartOfDay(ZoneId.systemDefault()).toInstant());
            String[] hourAndMinFrom = timeFrom.getText().split("[:]");
            dateFrom.setHours(Integer.parseInt(hourAndMinFrom[0]));
            dateFrom.setMinutes(Integer.parseInt(hourAndMinFrom[1]));
            System.out.println(dateFrom);
            LocalDate dateToLocal = dateTo.getValue();
            Date dateTo = Date.from(dateToLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
            hourAndMinFrom = timeTo.getText().split("[:]");
            dateTo.setHours(Integer.parseInt(hourAndMinFrom[0]));
            dateTo.setMinutes(Integer.parseInt(hourAndMinFrom[1]));
            System.out.println(dateTo);


            try {

                for (int i = 0; i < list.getSubordinates().size(); i++) {
                    String listElemDate = list.getSubordinates().get(i).getDateOfDeparture() + " "+list.getSubordinates().get(i).getDepartureTime();
                    Date res = getDateTime(listElemDate);
                    if(dateFrom.before(res)){
                        tempFrom.add(list.getSubordinates().get(i));
                    }
                }
                for (int i = 0; i < tempFrom.getSubordinates().size(); i++) {
                    String listElemDate = tempFrom.getSubordinates().get(i).getDateOfDeparture() + " "+tempFrom.getSubordinates().get(i).getDepartureTime();
                    Date res = getDateTime(listElemDate);
                    if(res.after(dateTo)){
                        tempTo.add(tempFrom.getSubordinates().get(i));
                    }
                }
                if(tempTo != null){
                    trainArrayList = tempTo;
                }
                else{
                    DialogManager.showInfoDialog("Не найдено", "По вашему запросу ничего не найдено!");
                    trainArrayList = list;
                }
                showTable();
            } catch (ParseException e) {
                DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
            }
        }catch(DAOException e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    @Override
    public BorderPane getLayout() {
        return rootLauout;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {

    }

    @Override
    public void loadAllData() {
        try {
            TrainDao daoXml =  XmlDaoFactory.getInstance().getTrainDao();
            TrainDao daoCsv = CsvDaoFactory.getInstance().getTrainDao();
            Train listFlightsFromCSV = daoCsv.parse();
            Train listFlightsFromXML = daoXml.parse();
            for (int i = 0; i < listFlightsFromXML.getSubordinates().size(); i++) {
                listFlightsFromCSV.add(listFlightsFromXML.getSubordinates().get(i));
            }
            trainArrayList = listFlightsFromCSV;
            showTable();
        }catch (Exception e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    private void showTable()
    {
        if(trainObservableList.size() >0) {
            trainObservableList.clear();
            trainObservableList = FXCollections.observableArrayList();
        }
        trainObservableList.addAll(this.trainArrayList.getSubordinates());
        busTableView.getItems().clear();
        //------------begin initialize collumn--------------///
        typeTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getType()));
        TicketTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(cellData.getValue().getTicket().getCost()));
        DestinationTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDestination()));
        DepartureTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDeparture()));
        PlatformTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPlatform()));
        DateDepartureTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDateOfDeparture()));
        DepartureTimeTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDepartureTime()));
        DateArrivalTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDataOfArrival()));
        TimeArrivalTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTimeOfArrival()));
        dayTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getDay()));
        //------------end initialize collumn--------------///
        busTableView.setItems(trainObservableList);
    }


    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        Date date = new Date();
        return dateFormat.format(date);

    }
    private static Date getDateTime(String strDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH.mm");
        Date date = dateFormat.parse(strDate);
        return date;

    }

    private static Train getResultList() throws DAOException {
        TrainDao daoXml =  XmlDaoFactory.getInstance().getTrainDao();
        TrainDao daoCsv = CsvDaoFactory.getInstance().getTrainDao();
        Train listFlightsFromCSV = daoCsv.parse();
        Train listFlightsFromXML = daoXml.parse();
        for (int i = 0; i < listFlightsFromXML.getSubordinates().size(); i++) {
            listFlightsFromCSV.add(listFlightsFromXML.getSubordinates().get(i));
        }
        return listFlightsFromCSV;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void showDiagramm() {
        try {
            TrainDao daoXml = XmlDaoFactory.getInstance().getTrainDao();
            TrainDao daoCsv = CsvDaoFactory.getInstance().getTrainDao();
            Train resultListTrain = daoCsv.parse();
            Train listFlightsFromXML = daoXml.parse();
            for (int i = 0; i < listFlightsFromXML.getSubordinates().size(); i++) {
                resultListTrain.add(listFlightsFromXML.getSubordinates().get(i));
            }
            int workday = 0;
            int holiday = 0;
            for (int i = 0; i < resultListTrain.getSubordinates().size(); i++) {
                String dayFromListElement = resultListTrain.getSubordinates().get(i).getDay();
                if(dayFromListElement.equals("work day")){
                    workday++;
                }
                else
                    holiday++;
            }


            Stage stage = new Stage();
            Scene scene = new Scene(new Group());
            stage.setTitle("Распределение данных для рабочих и выходных");
            stage.setWidth(500);
            stage.setHeight(500);

            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(

                            new PieChart.Data("work day", workday),
                            new PieChart.Data("holiday", holiday)
                    );

            final PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Распределение данных для рабочих и выходных");
            final Label caption = new Label("");
            caption.setTextFill(Color.DARKORANGE);
            caption.setStyle("-fx-font: 24 arial;");

            for (final PieChart.Data data : chart.getData()) {
                data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                        e -> {
                            caption.setTranslateX(e.getSceneX());
                            caption.setTranslateY(e.getSceneY());
                            caption.setText(String.valueOf(data.getPieValue())
                                    + "%");
                        });
            }

            ((Group) scene.getRoot()).getChildren().addAll(chart, caption);
            stage.setScene(scene);
            //scene.getStylesheets().add("piechartsample/Chart.css");
            stage.show();

        } catch (DAOException e) {
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }

    }
}
