package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
        loader = new FXMLLoader(getClass().getResource("FXML_files/ShopWindow.fxml"));
        DisplayPane  = (BorderPane) loader.load();
        controller = loader.getController();
        window=primaryStage;
        controller.customer= c;
        controller.socket = this.socket;
        controller.ois = this.ois;
        controller.oos = this.oos;
        controller.currentStage = primaryStage;
        controller.socket = socket;
        primaryStage.setTitle("Shopping Window");
        primaryStage.setScene(new Scene(DisplayPane));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth()));
        primaryStage.setHeight((primScreenBounds.getHeight()));
        primaryStage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

}
