package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ShopWindowController
{
    Customer customer;

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
        Label UserNameLabel = new Label("UserName");
        Label PasswordLabel = new Label("Password");
        TextArea UserNameArea = new TextArea();
        UserNameArea.setText(customer.getUserName());
        UserNameArea.setDisable(true);
        TextArea PasswordArea = new TextArea();
        PasswordArea.setText(customer.getPassword());
        Label EmailLabel = new Label("Email");
        Label MobileNoLabel = new Label("Mobile No");
        Label FirstNameLabel = new Label("First Name");
        Label LasNameLabel = new Label("Last Name");
        Label AddressLabel = new Label("Address");
        Label PinNo = new Label("Pin No");
        TextArea FirstNameArea = new TextArea();
        FirstNameArea.setText(customer.getFirstName());
        TextArea EmailArea = new TextArea();
        EmailArea.setText(customer.getEmail());
        TextArea LastNameArea = new TextArea();
        LastNameArea.setText(customer.getLastName());
        TextArea MobileNoArea = new TextArea();
        MobileNoArea.setText(customer.getMobileNo());
        TextArea AddressArea = new TextArea();
        AddressArea.setText(customer.getAddress());
        try
        {
            CentreDisplay.getChildren().addAll(UserNameLabel, UserNameArea, PasswordLabel, PasswordArea, FirstNameLabel, FirstNameArea, LasNameLabel, LastNameArea, EmailLabel, EmailArea, MobileNoLabel, MobileNoArea, AddressLabel, AddressArea);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}

