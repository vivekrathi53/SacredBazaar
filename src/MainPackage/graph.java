package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;
import java.net.Socket;

public class graph extends Application
{
    Stage window;
    FXMLLoader loader;
    graphcontroller controller;
    LineChart<?,?> MainDisplay

    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("graph.fxml"));
        controller = loader.getController();
        MainDisplay  = (LineChart<?,?>) loader.load();
        primaryStage.setScene(new Scene(MainDisplay));
        window = primaryStage;
        window.setTitle("Retailer User Graph");
        window.show();
    }
    public static void main(String[] args)

    {
        launch(args);
    }
}
