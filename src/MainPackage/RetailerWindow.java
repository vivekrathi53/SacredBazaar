package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RetailerWindow extends Application
{
    private FXMLLoader loader;
    private BorderPane DisplayPane;
    private RetailerWindowController controller;
    private Stage window;
    protected Retailer retailer;
    protected Socket socket;
    protected ObjectOutputStream oos;
    protected ObjectInputStream ois;
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        loader = new FXMLLoader(getClass().getResource("FXML_files/RetailerWindow.fxml"));
        try
        {
            DisplayPane  = (BorderPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        controller = loader.getController();
        window=primaryStage;
        controller.retailer= retailer;
        controller.socket = this.socket;
        controller.ois = this.ois;
        controller.oos = this.oos;
        controller.currentStage = primaryStage;
        primaryStage.setTitle("Retailer Window");
        primaryStage.setScene(new Scene(DisplayPane));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth()));
        primaryStage.setHeight((primScreenBounds.getHeight()));
        primaryStage.show();
    }
}
