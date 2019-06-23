package ClientFiles.CustomerFiles;

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

    private Stage window;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private FXMLLoader loader;
    private ShopWindowController controller;
    private BorderPane DisplayPane;
    private Customer c;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        setLoader(new FXMLLoader(getClass().getResource("FXML_files/ShopWindow.fxml")));
        setDisplayPane((BorderPane) getLoader().load());
        setController(getLoader().getController());
        setWindow(primaryStage);
        getController().setCustomer(getC());
        getController().setSocket(this.getSocket());
        getController().setOis(this.getOis());
        getController().setOos(this.getOos());
        getController().setCurrentStage(primaryStage);
        getController().setSocket(getSocket());
        primaryStage.setTitle("Shopping Window");
        primaryStage.setScene(new Scene(getDisplayPane()));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth()));
        primaryStage.setHeight((primScreenBounds.getHeight()));
        primaryStage.show();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public ShopWindowController getController() {
        return controller;
    }

    public void setController(ShopWindowController controller) {
        this.controller = controller;
    }

    public BorderPane getDisplayPane() {
        return DisplayPane;
    }

    public void setDisplayPane(BorderPane displayPane) {
        DisplayPane = displayPane;
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }
}
