package ClientFiles.RetailerFiles;

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
    private Retailer retailer;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

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
        controller.setRetailer(retailer);
        controller.setSocket(this.socket);
        controller.setOis(this.ois);
        controller.setOos(this.oos);
        controller.setCurrentStage(primaryStage);
        primaryStage.setTitle("Retailer Window");
        primaryStage.setScene(new Scene(DisplayPane));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth()));
        primaryStage.setHeight((primScreenBounds.getHeight()));
        primaryStage.show();
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public BorderPane getDisplayPane() {
        return DisplayPane;
    }

    public void setDisplayPane(BorderPane displayPane) {
        DisplayPane = displayPane;
    }

    public RetailerWindowController getController() {
        return controller;
    }

    public void setController(RetailerWindowController controller) {
        this.controller = controller;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

}
