package MainPackage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class Adminwindow
{
    FXMLLoader loader;
    AdminwindowController controller;
    Stage window;
    BorderPane MainDisplay;
    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("AdminwindowController.fxml"));
        controller = loader.getController();
        window = primaryStage;
        MainDisplay  = (BorderPane) loader.load();
        primaryStage.setScene(new Scene(MainDisplay));
        window.setTitle("Log in Page");
        window.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
