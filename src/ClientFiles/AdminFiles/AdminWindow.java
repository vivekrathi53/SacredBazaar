package ClientFiles.AdminFiles;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import static javafx.application.Application.launch;

public class AdminWindow
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    private FXMLLoader loader;
    private AdminWindowController controller;
    private Stage Loginwindow;
    private Stage window;
    private BorderPane MainDisplay;
    public void start(Stage primaryStage) throws Exception
    {
        setLoader(new FXMLLoader(getClass().getResource("FXML_files/Admin.fxml")));
        setMainDisplay((BorderPane) getLoader().load());
        setController(getLoader().getController());
        setWindow(primaryStage);
        Admin a = (Admin) getOis().readObject();
        getController().setAdmin(a);
        getController().setOis(getOis());
        getController().setOos(getOos());
        getController().setSocket(getSocket());
        getController().setAdminPane(getMainDisplay());
        getController().setLoginwindow(getLoginwindow());
        primaryStage.setScene(new Scene(getMainDisplay()));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth()));
        primaryStage.setHeight((primScreenBounds.getHeight()));
        getWindow().setTitle("Admin Window ");
        getWindow().show();
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public AdminWindowController getController() {
        return controller;
    }

    public void setController(AdminWindowController controller) {
        this.controller = controller;
    }

    public Stage getLoginwindow() {
        return Loginwindow;
    }

    public void setLoginwindow(Stage loginwindow) {
        Loginwindow = loginwindow;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public BorderPane getMainDisplay() {
        return MainDisplay;
    }

    public void setMainDisplay(BorderPane mainDisplay) {
        MainDisplay = mainDisplay;
    }
}
