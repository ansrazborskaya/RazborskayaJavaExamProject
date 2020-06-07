package com.stormnet.figuresfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;


public class FiguresFxApp extends Application {


    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch();
    }

    @Override
    public void start(Stage window) throws Exception {

        window.setTitle("FXFigures");
        Parent root2 = FXMLLoader.load(getClass().getResource("/view/MainScreenViewFirst.fxml"));
        root2.getStylesheets().add(getClass().getResource("/back.css").toString());
        window.setScene(new Scene(root2, 1024, 600));
        window.setResizable(false);
        window.show();
    }
}
