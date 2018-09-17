package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoppingWindow extends Application implements Initializable
{
    Stage window;
    static Customer customer;
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
    FXMLLoader loader;
    ShoppingWindow controller;
    public ShoppingWindow(Customer customer)
    {
        this.customer = customer;
        launch("");
    }
    public ShoppingWindow(){}
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        ProfileButton.setOnAction(e ->ShowProfile());
        MyCartButton.setOnAction(e ->ShowInCart());
        WishListButton.setOnAction(e ->ShowWishList());
        TrendingButton.setOnAction(e ->ShowTrending());
    }
    @Override
    public void start(Stage primaryStage) throws Exception
    {

        window=primaryStage;
        loader = new FXMLLoader(getClass().getResource("sample.fxml"));
        DisplayPane  = (BorderPane) loader.load();
        controller = loader.getController();
        primaryStage.setTitle("Hello World");
        SetProfileScene();
        primaryStage.setScene(new Scene(DisplayPane, 300, 275));
        primaryStage.show();

    }
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
        controller.CentreDisplay.getChildren().setAll(UserNameLabel,UserNameArea,PasswordLabel,PasswordArea,FirstNameLabel,FirstNameArea,LasNameLabel,LastNameArea,EmailLabel,EmailArea,MobileNoLabel,MobileNoArea,AddressLabel,AddressArea);

    }


    public static void main(String[] args)
    {
        Customer c = new Customer("h", "j","t","y","u","12","11","ee",null,null,null);
        customer=c;
        launch(args);
    }

    private void ShowProfile()
    {
        //SetProfileScene();
    }
    private void ShowInCart()
    {

    }
    private void ShowWishList()
    {

    }
    private void ShowTrending()
    {

    }

}
