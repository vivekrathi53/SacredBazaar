package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        loader = new FXMLLoader(getClass().getResource("LoginWindowDesign.fxml"));
        controller = loader.getController();
        MainDisplay  = (AnchorPane) loader.load();

        primaryStage.setScene(new Scene(MainDisplay));
        window = primaryStage;
        System.out.println(Type);
        //controller.socket = this.socket;
        //controller.ois = this.ois;
        //controller.oos = this.oos;
        //FileInputStream input = new FileInputStream("/home/dell/Desktop/FinalYearSeniors.jpg");

        window.setTitle("Log in Page");
        window.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
