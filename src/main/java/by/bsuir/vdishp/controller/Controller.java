package by.bsuir.vdishp.controller;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by andrey on 20.04.2016.
 */
public interface Controller{
    Pane getLayout();
    void setPrimaryStage(Stage primaryStage);
}
