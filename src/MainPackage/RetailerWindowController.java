package MainPackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RetailerWindowController
{
    public RetailerProfileController controller;
    public Retailer retailer;
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public ScrollPane CentreDisplay;
    public BorderPane borderPane;

    public void ShowProfile()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RetailerProfile.fxml")) ;
        try
        {
            CentreDisplay = (ScrollPane) loader.load();
            controller = loader.getController();
            controller.retailer= retailer;
            controller.ShowProfile();
            borderPane.setCenter(CentreDisplay);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public void ShowAllProducts()
    {

    }

    public void ShowNotifications()
    {

    }

    public void ShowSoldProducts()
    {

    }

    public void AddProduct()
    {

    }
}
