package MainPackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RetailerWindowController
{

    public Retailer retailer;
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public ScrollPane CentreDisplay;

    public void ShowProfile()
    {
        RetailerProfileController controller;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RetailerProfile.fxml")) ;
        try {
            controller = loader.getController();
            CentreDisplay = (ScrollPane) loader.load();
            controller.AddressBox.setText(retailer.getAddress());
            controller.AddressDisplay.setText(retailer.getAddress());
            controller.EmailBox.setText(retailer.getEmail());
            controller.FirstNameBox.setText(retailer.getFirstName());
            controller.LastNameBox.setText(retailer.getLastName());
            controller.MobileNoBox.setText(retailer.getMobileNo());
            controller.NameDisplay.setText(retailer.getFirstName() + " " + retailer.getLastName());
            controller.PasswordBox.setText(retailer.getPassword());
            controller.rwc=this;
            controller.PinNoBox.setText(retailer.getPinNo());
            controller.UserNameDisplay.setText(retailer.getUserName());
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
    public void ChangeProfile()
    {

    }
}
