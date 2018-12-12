package MainPackage;

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
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    public Socket socket;
    FXMLLoader loader;
    AdminWindowController controller;
    Stage Loginwindow;
    Stage window;
    BorderPane MainDisplay;
    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("FXML_files/Admin.fxml"));
        MainDisplay  = (BorderPane) loader.load();
        controller = loader.getController();
        window = primaryStage;
        Admin a = (Admin) ois.readObject();
        controller.setAdmin(a);
        controller.setOis(ois);
        controller.setOos(oos);
        controller.setSocket(socket);
        controller.AdminPane = MainDisplay;
        controller.Loginwindow=Loginwindow;
        primaryStage.setScene(new Scene(MainDisplay));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth((primScreenBounds.getWidth()));
        primaryStage.setHeight((primScreenBounds.getHeight()));
        window.setTitle("Admin Window ");
        window.show();
    }
}
