package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopWindow extends Application implements Initializable
{
    Stage window;
    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    FXMLLoader loader;
    ShopWindowController controller;
    BorderPane DisplayPane;
    Customer c;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("ShopWindow.fxml"));
        DisplayPane  = (BorderPane) loader.load();
        controller = loader.getController();
        window=primaryStage;
        controller.customer= c;
        controller.socket = this.socket;
        controller.ois = this.ois;
        controller.oos = this.oos;
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(DisplayPane, 300, 275));
        primaryStage.show();

    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

}
