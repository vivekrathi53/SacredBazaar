package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RetalierWindow extends Application
{
   public Retailer r;
   public ObjectOutputStream oos;
   public ObjectInputStream ois;
   public Stage window;
   public Socket socket;
   public  FXMLLoader loader;
   public  RetalierWindowController controller;
   public BorderPane DisplayPane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        loader = new FXMLLoader(getClass().getResource("RetalierPage.fxml"));
        DisplayPane  = (BorderPane) loader.load();
        controller = loader.getController();
        window=primaryStage;
        controller.retalier= r;
        controller.socket = this.socket;
        //System.out.println(this.socket + "##" + socket + "##" + controller.socket);
        controller.ois = this.ois;
        controller.oos = this.oos;

    }
}
