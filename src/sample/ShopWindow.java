package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopWindow extends Application implements Initializable
{
    Stage window;
    FXMLLoader loader;
    ShopWindowController controller;
    BorderPane DisplayPane;
    static Customer c;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        DisplayPane  = (BorderPane) loader.load();
        controller = loader.getController();
        window=primaryStage;
        controller.customer= c;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(DisplayPane, 300, 275));
        primaryStage.show();

    }

    public static void main(String[] args)
    {
        c = new Customer("h", "j", "t", "y", "u", "12", "11", "ee", null, null, null);
        launch(args);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

}
