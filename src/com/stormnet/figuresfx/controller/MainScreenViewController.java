package com.stormnet.figuresfx.controller;

import com.stormnet.figuresfx.customExceptions.NoFigureException;
import com.stormnet.figuresfx.customExceptions.NoShapesCleanException;
import com.stormnet.figuresfx.customExceptions.ReturnFigureException;
import com.stormnet.figuresfx.customExceptions.UnknownTypeException;
import com.stormnet.figuresfx.drawutils.Drawer;
import com.stormnet.figuresfx.figures.*;
import com.stormnet.figuresfx.figures.Rectangle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class MainScreenViewController implements Initializable {

    private ArrayList<Figure> figures;
    private Figure lastFigure;
    private Random random;
    private static final Logger logger = Logger.getLogger(MainScreenViewController.class);


    @FXML
    private Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logger.info("App is initialized!");
        figures = new ArrayList<>();
        random = new Random(System.currentTimeMillis());
    }

    private void addFigure(Figure figure) {

        figures.add(figure);
    }

    private Figure createFigure(double x, double y) throws UnknownTypeException {

        Figure figure;

        switch (random.nextInt(4)) {
            case Figure.FIGURE_TYPE_CIRCLE:
                figure = new Circle(x, y, random.nextInt(10), Color.GREEN, random.nextInt(40));
                logger.info("Сircle is drawn");
                break;
            case Figure.FIGURE_TYPE_RECTANGLE:
                figure = new Rectangle(x, y, random.nextInt(10), Color.PINK, random.nextInt(35), random.nextInt(60));
                logger.info("Rectangle is drawn");
                break;
            case Figure.FIGURE_TYPE_TRIANGLE:
                figure = new Triangle(x, y, random.nextInt(10), Color.RED, random.nextInt(40));
                logger.info("Triangle is drawn");
                break;
            case Figure.FIGURE_TYPE_HEXAGON:
                figure = new Hexagon(x, y, random.nextInt(10), Color.BLUEVIOLET, random.nextInt(45));
                logger.info("Hexagon is drawn");
                break;
            default:
                throw new UnknownTypeException();
        }

        return figure;
    }

    private void repaint() {

        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());//чистка канвы
        Drawer<Figure> drawer = new Drawer<>(figures);
        drawer.draw(canvas.getGraphicsContext2D());
    }

    @FXML
    private void onMouseClicked(MouseEvent mouseEvent) {

        try {
            addFigure(createFigure(mouseEvent.getX(), mouseEvent.getY()));
            repaint();
        } catch (UnknownTypeException ex) {

            logger.error("Figure can not created!");
        }
    }

    @FXML
    public void onClickMethod(ActionEvent event) throws IOException {

        logger.info("CLICK AHD DRAW!");

        Stage stage;
        Parent root;

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/view/MainScreenViewSecond.fxml"));
        root.getStylesheets().add(getClass().getResource("/back.css").toString());
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteLastShape(ActionEvent actionEvent) {

        try {
            if (figures.size() == 0) {
                throw new NoFigureException("ERROR");
            }
            lastFigure = figures.get(figures.size() - 1);
            figures.remove(figures.size() - 1);
            logger.info("The shape has been deleted.");
            repaint();
        } catch (NoFigureException exp) {
            logger.error("No shapes to delete");
        }
    }

    @FXML
    public void returnLastShape(ActionEvent actionEvent) {

        try {
            if (lastFigure == null) {
                throw new ReturnFigureException();
            }

            figures.add(lastFigure);
            lastFigure = null;
            logger.info("The last figure is displayed!");
            repaint();

        } catch (ReturnFigureException exp) {
            logger.error("No figures to return!");
        }
    }

    @FXML
    public void clearField(ActionEvent actionEvent) {

        try {
            if (figures.size() == 0) {
                throw new NoShapesCleanException();
            }
            figures.clear();
            logger.info("Field cleared.");
            repaint();
        } catch (NoShapesCleanException e) {
            e.getMessage();
            logger.error("No shapes to clean the canvas");
        }
    }
}
