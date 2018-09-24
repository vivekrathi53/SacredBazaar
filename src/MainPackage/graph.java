package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;

import java.net.Socket;

public class graph extends Application {
    Stage window;
    Scene s2;
    Socket socket;
    FXMLLoader loader;
    graphcontroller controller;
    Transaction Authentication;
    LineChart MainDisplay;
    int Type;

    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("graph.fxml"));
        MainDisplay  = (LineChart) loader.load();
        primaryStage.setScene(new Scene(MainDisplay));
        controller = loader.getController();

        window = primaryStage;
        //controller.socket = this.socket;
        //controller.ois = this.ois;
        //controller.oos = this.oos;
        //FileInputStream input = new FileInputStream("/home/dell/Desktop/FinalYearSeniors.jpg");

        window.setTitle("Retailer User Graph");
        window.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
