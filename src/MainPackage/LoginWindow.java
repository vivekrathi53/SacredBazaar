package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.Socket;

public class LoginWindow extends Application {
    Stage window;
    Scene s2;
    Socket socket;
    FXMLLoader loader;
    LoginWindowController controller;
    Transaction Authentication;
    AnchorPane MainDisplay;
    int Type;
    @Override

    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("FXML_files/LoginWindowDesign.fxml"));
        controller = loader.getController();
        MainDisplay  = (AnchorPane) loader.load();
        primaryStage.setScene(new Scene(MainDisplay));
        window = primaryStage;
        window.setTitle("Log in Page");
        window.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
