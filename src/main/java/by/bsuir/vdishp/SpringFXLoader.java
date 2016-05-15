package by.bsuir.vdishp;

import by.bsuir.vdishp.controller.ClildrenController;
import by.bsuir.vdishp.controller.Controller;
import javafx.fxml.FXMLLoader;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by andrey on 20.04.2016.
 */
public class SpringFXLoader {

    private static final GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:spring/applicationConfig.xml");

    public static Controller load(String url) {
        InputStream fxmlStream = null;
        try {
            fxmlStream = SpringFXLoader.class.getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(aClass -> ctx.getBean(aClass));
            loader.load(fxmlStream);
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fxmlStream != null) {
                try {
                    fxmlStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
    public static ClildrenController loadChildren(String url) {
        InputStream fxmlStream = null;
        try {
            fxmlStream = SpringFXLoader.class.getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(aClass -> ctx.getBean(aClass));
            loader.load(fxmlStream);
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fxmlStream != null) {
                try {
                    fxmlStream.close();
                } catch (IOException e) {
                }
            }
        }
    }

}
