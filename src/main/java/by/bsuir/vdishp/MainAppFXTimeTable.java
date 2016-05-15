package by.bsuir.vdishp;

import by.bsuir.vdishp.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by andrey on 14.05.2016.
 */
public class MainAppFXTimeTable extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    Controller controller ;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            this.primaryStage = primaryStage;
            controller = SpringFXLoader.load("/view/ApplicationCarcase.fxml");
            Scene scene = new Scene(controller.getLayout());
            controller.setPrimaryStage(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.exit(0);
    }
}
