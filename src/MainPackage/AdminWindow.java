package MainPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
    Stage window;
    BorderPane MainDisplay;
    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("Admin.fxml"));
        MainDisplay  = (BorderPane) loader.load();
        controller = loader.getController();
        window = primaryStage;
        Admin a = (Admin) ois.readObject();
        controller.setAdmin(a);
        controller.setOis(ois);
        controller.setOos(oos);
        controller.setSocket(socket);
        controller.AdminPane = MainDisplay;
        primaryStage.setScene(new Scene(MainDisplay));
        window.setTitle("Admin Window");
        window.show();
    }
}
