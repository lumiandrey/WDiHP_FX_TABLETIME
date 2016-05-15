package by.bsuir.vdishp.controller;

import by.bsuir.vdishp.SpringFXLoader;
import by.bsuir.vdishp.model.entity.Bus;
import by.bsuir.vdishp.model.entity.Train;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
@Component
public class ApplicationCarcaseController implements Controller {

    private Stage primaryStage;


    @FXML
    private ChoiceBox<String> dayChoiseBox;

    @FXML
    private ChoiceBox<String> destinationChoiseBox;

    @FXML
    private ChoiceBox<String> departureChoiseBox;

    @FXML
    private BorderPane rootLayout;

    private ClildrenController chieldController;

    private final static String MESSAGE_BODY_WARNING_NO_DATA = "Вы не подгрузили данные, пожалуйста сначала выберите " +
            "тип тринспорта по которому будет производиться фильтрация";
    private final static String MESSAGE_TITLE_WARNING_NO_DATA = "Ошибка погрузки данных";


    @FXML
    void initialize() {
        chieldController = null;
        dayChoiseBox.getItems().addAll("Рабочий", "выходной");
        String[] stantion = {"Minsk","Brest","Grodno","Polozk","Mogilev","Gomel"};
        destinationChoiseBox.getItems().addAll(stantion);
        departureChoiseBox.getItems().addAll(stantion);
    }

    @FXML
    public void filterByDay() {
        if (chieldController == null)
            DialogManager.showWarningDialog(MESSAGE_TITLE_WARNING_NO_DATA, MESSAGE_BODY_WARNING_NO_DATA);
        else {
            String dayFilter = dayChoiseBox.getValue();
            switch (dayFilter){
                case "Рабочий": {
                    dayFilter = "work day";
                    chieldController.filterByDay(dayFilter);
                    break;
                }
                case "выходной": {
                    dayFilter = "holiday";
                    chieldController.filterByDay(dayFilter);
                    break;
                }
            }
        }
    }

    @FXML
    public void filterByDestination() {
        if (chieldController == null)
            DialogManager.showWarningDialog(MESSAGE_TITLE_WARNING_NO_DATA, MESSAGE_BODY_WARNING_NO_DATA);
        else {
            String destinationFilter = destinationChoiseBox.getValue();
            chieldController.filterByDestination(destinationFilter);
        }
    }

    @FXML
    public void filterByDeparture() {
        if (chieldController == null)
            DialogManager.showWarningDialog(MESSAGE_TITLE_WARNING_NO_DATA, MESSAGE_BODY_WARNING_NO_DATA);
        else {
            String departureFilter = departureChoiseBox.getValue();
            chieldController.filterByDeparture(departureFilter);
        }
    }

    @FXML
    public void showBus()
    {
        chieldController = SpringFXLoader.loadChildren("/view/TabBusView.fxml");
        rootLayout.setCenter(chieldController.getLayout());
        primaryStage.show();
    }

    @FXML
    public void showTrains()
    {
        chieldController = SpringFXLoader.loadChildren("/view/TableTrainView.fxml");
        rootLayout.setCenter(chieldController.getLayout());
        primaryStage.show();
    }

    @FXML
    public void showDiagramm()
    {
        if(chieldController !=null)
            chieldController.showDiagramm();
        else DialogManager.showWarningDialog(MESSAGE_TITLE_WARNING_NO_DATA, MESSAGE_BODY_WARNING_NO_DATA);
    }

    @Override
    public BorderPane getLayout() {
        return rootLayout;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
