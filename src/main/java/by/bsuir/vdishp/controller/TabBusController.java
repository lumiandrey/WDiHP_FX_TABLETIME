package by.bsuir.vdishp.controller;

import by.bsuir.vdishp.model.data.IParceDAO;
import by.bsuir.vdishp.model.data.factory.CsvDaoFactory;
import by.bsuir.vdishp.model.data.factory.XmlDaoFactory;
import by.bsuir.vdishp.model.entity.Bus;
import by.bsuir.vdishp.model.exception.DAOException;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
public class TabBusController implements Controller, ClildrenController<Bus> {

    private Stage primaryStage;

    @FXML
    private BorderPane rootLauout;

    @FXML
    private TableView<Bus> busTableView;

    @FXML
    private TableColumn<Bus, String> brandBusTableColumn;

    @FXML
    private TableColumn<Bus, Integer> ticketCostTableColumn;

    @FXML
    private TableColumn<Bus, String> dateOfDepartureTableColumn;

    @FXML
    private TableColumn<Bus, String> dateOfArrivalTableColumn;

    @FXML
    private TableColumn<Bus, String> destinationTableColumn;


    @FXML
    private TableColumn<Bus, String> departureTableColumn;

    @FXML
    private TableColumn<Bus, Integer> platformTableColumn;

    @FXML
    private TableColumn<Bus, Double> transitTimeTableColumn;

    @FXML
    private TableColumn<Bus, Double> departureTimeTableColumn;

    @FXML
    private TableColumn<Bus, Double> arrivalTimeTableColumn;

    @FXML
    private TableColumn<Bus, String> dayTableColumn;


    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TextField timeFrom;

    @FXML
    private TextField timeTo;

    private ObservableList<Bus> busObservableList = FXCollections.observableArrayList();

    private List<Bus> busArrayList = new ArrayList<>();


    @FXML
    void initialize(){
        loadAllData();
    }

    @Override
    public void filterByDay(String day) {
        List<Bus> listByDay = new ArrayList<>();
        try{
            List<Bus> resultList = getResultList();
            for (Bus aResultList : resultList) {
                String dayFromListElement = aResultList.getDay();
                if (dayFromListElement.equals(day)) {
                    listByDay.add(aResultList);
                }
            }
            if(listByDay.isEmpty()){
                busArrayList = resultList;
            }
            else{
                busArrayList = listByDay;
            }
            showTable();
        }catch(DAOException e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    @Override
    public void filterByDestination(String destination) {
        List<Bus> listByDestination = new ArrayList<>();
        try{
            List<Bus> resultList = getResultList();
            for (Bus aResultList : resultList) {
                String destinationFromXML = aResultList.getDestination();
                if (destination.equals(destinationFromXML)) {
                    listByDestination.add(aResultList);
                }
            }
            if(listByDestination.isEmpty()){
                busArrayList = resultList;
            }
            else{
                busArrayList = listByDestination;
            }
            showTable();
        }catch(DAOException e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    @Override
    public void filterByDeparture(String departure) {
        IParceDAO dao = null;
        List<Bus> listByDeparture = new ArrayList<>();
        try{
            List<Bus> resultList = getResultList();
            for (Bus aResultList : resultList) {
                String departureFromXML = aResultList.getDeparture().toUpperCase();
                if (departure.equals(departureFromXML)) {
                    listByDeparture.add(aResultList);
                }
            }
            if(listByDeparture.isEmpty()){
                busArrayList = resultList;
            }
            else{
                busArrayList = listByDeparture;
            }
            showTable();
        }catch(DAOException e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    @FXML
    public void filterDateAndTime() {
        final String DOT = ".";
        List<Bus> tempFrom = new ArrayList<>();
        List<Bus> tempTo = new ArrayList<>();
        List<Bus> result = new ArrayList<>();
        try{
            List<Bus> list = getResultList();

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

                for (int i = 0; i < list.size(); i++) {
                    String listElemDate = list.get(i).getDateOfDeparture() + " " +list.get(i).getDepartureTime();
                    String listDate = list.get(i).getDateOfArrival() + " " + list.get(i).getArrivalTime();
                    Date res = getDateTime(listElemDate);
                    Date res2 = getDateTime(listDate);
                    if(dateFrom.before(res)&&dateTo.after(res2)){
                        tempTo.add(list.get(i));
                    }
                }
                if(tempTo != null){
                    busArrayList = tempTo;
                }
                else{
                    DialogManager.showInfoDialog("Не найдено", "По вашему запросу ничего не найдено!");
                    loadAllData();
                }
                showTable();
            } catch (ParseException e) {
                e.printStackTrace();
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
            IParceDAO daoXml = XmlDaoFactory.getInstance().getBusDao();
            IParceDAO daoCsv = CsvDaoFactory.getInstance().getBusDao();
            busArrayList = daoCsv.parse();
            showTable();
        }catch (Exception e){
            DialogManager.showErrorDialog("Исключительная ситуация!!!", e.getMessage());
        }
    }

    private void showTable()
    {
        if(busObservableList.size() >0) {
            busObservableList.clear();
            busObservableList = FXCollections.observableArrayList();
        }
        busObservableList.addAll(this.busArrayList);
        busTableView.getItems().clear();
        brandBusTableColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getBrandBus()));
        ticketCostTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<Integer>(cellData.getValue().getTicket().getCost()));
        dateOfDepartureTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDateOfDeparture()));
        dateOfArrivalTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDateOfArrival()));
        destinationTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDestination()));
        departureTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDeparture()));
        platformTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getPlatform()));
        transitTimeTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getTransitTime()));
        departureTimeTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDepartureTime()));
        arrivalTimeTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getArrivalTime()));
        dayTableColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDay()));
        busTableView.setItems(busObservableList);
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

    private static List<Bus> getResultList() throws DAOException {
        IParceDAO daoXml =  XmlDaoFactory.getInstance().getBusDao();
        IParceDAO daoCsv = CsvDaoFactory.getInstance().getBusDao();
        List<Bus> listRoutesFromCSV = daoCsv.parse();
        return listRoutesFromCSV;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void showDiagramm() {
        try {
            int workday = 0;
            int holiday = 0;
            List<Bus> resultListBus = getResultList();
            for (Bus resultListBu : resultListBus) {
                String dayFromListElement = resultListBu.getDay();
                if (dayFromListElement.equals("work day")) {
                    workday++;
                } else
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



