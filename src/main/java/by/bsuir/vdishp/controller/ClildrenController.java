package by.bsuir.vdishp.controller;

import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Created by andrey on 14.05.2016.
 */
public interface ClildrenController<T> {
    void filterByDay(String day);
    void filterByDestination(String destination);
    void filterByDeparture(String departure);
    void loadAllData();
    Pane getLayout();
    void showDiagramm();
}
