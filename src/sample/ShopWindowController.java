package sample;

import Transactions.ChangeCustomerDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopWindowController
{
    public Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Customer customer;
    Label UserNameLabel,PasswordLabel,EmailLabel,MobileNoLabel,FirstNameLabel,LasNameLabel,AddressLabel,PinNo;
    TextArea UserNameArea,PasswordArea,FirstNameArea,LastNameArea,MobileNoArea,AddressArea,EmailArea;
    public ShopWindowController()
    {

    }

    @FXML
    Button ProfileButton;
    @FXML
    Button MyCartButton;
    @FXML
    Button WishListButton;
    @FXML
    Button TrendingButton;
    @FXML
    VBox TopVBox,LeftVBox,CentreDisplay;
    @FXML
    Scene ProfileScene,CartScene,WishListScene,TrendingScene;
    @FXML
    BorderPane DisplayPane;
    @FXML
    private void ShowInCart()
    {

    }
    @FXML
    private void ShowWishList()
    {

    }
    @FXML
    private void ShowTrending()
    {

    }


    @FXML
    void SetProfileScene()
    {
        UserNameLabel = new Label("UserName");
        PasswordLabel = new Label("Password");
        UserNameArea = new TextArea();
        UserNameArea.setText(customer.getUserName());
        UserNameArea.setDisable(true);
        PasswordArea = new TextArea();
        PasswordArea.setText(customer.getPassword());
        EmailLabel = new Label("Email");
        MobileNoLabel = new Label("Mobile No");
        FirstNameLabel = new Label("First Name");
        LasNameLabel = new Label("Last Name");
        AddressLabel = new Label("Address");
        PinNo = new Label("Pin No");
        FirstNameArea = new TextArea();
        FirstNameArea.setText(customer.getFirstName());
        EmailArea = new TextArea();
        EmailArea.setText(customer.getEmail());
        LastNameArea = new TextArea();
        LastNameArea.setText(customer.getLastName());
        MobileNoArea = new TextArea();
        MobileNoArea.setText(customer.getMobileNo());
        AddressArea = new TextArea();
        AddressArea.setText(customer.getAddress());
        Button SaveProfile = new Button("Save Your Changes");
        SaveProfile.setOnAction(e -> {
            try {
                SaveChangesToProfile();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        try
        {
            CentreDisplay.getChildren().addAll(UserNameLabel, UserNameArea, PasswordLabel, PasswordArea, FirstNameLabel, FirstNameArea, LasNameLabel, LastNameArea, EmailLabel, EmailArea, MobileNoLabel, MobileNoArea, AddressLabel, AddressArea,SaveProfile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void SaveChangesToProfile() throws IOException {
        ChangeCustomerDetails ccd = new ChangeCustomerDetails();
        customer = new Customer(FirstNameArea.getText(),LastNameArea.getText(),UserNameArea.getText(),PasswordArea.getText(),AddressArea.getText(),MobileNoArea.getText(),PinNo.getText(),EmailArea.getText(),null,null,null);
        ccd.client = customer;
        oos.writeObject(ccd);
        oos.flush();

    }


}

