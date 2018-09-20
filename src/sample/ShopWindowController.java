package sample;

import Transactions.BuyProduct;
import Transactions.ChangeCustomerDetails;
import Transactions.SearchFor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sun.font.Decoration;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShopWindowController
{
    public Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Customer customer;
    Label UserNameLabel,PasswordLabel,EmailLabel,MobileNoLabel,FirstNameLabel,LasNameLabel,AddressLabel,PinNoLabel;
    TextArea UserNameArea,PasswordArea,FirstNameArea,LastNameArea,MobileNoArea,AddressArea,EmailArea,PinNoArea;
    Button SaveProfile;
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
    ScrollPane scrollPane;
    @FXML
    TextArea SearchBar;
    @FXML
    private void ShowInCart()
    {
        ShowProductList(customer.getProductsInCart());
    }
    @FXML
    private void ShowWishList()
    {
        ShowProductList(customer.getProductsWishList());
    }
    @FXML
    private void ShowTrending()
    {
        ArrayList<Product> prodList = getTending();
        ShowProductList(prodList);
    }
    @FXML
    private void ShowHistory()
    {
        ShowProductList(customer.getProductsBought());
    }
    private void ShowProductList(ArrayList<Product> prodList)
    {
        CentreDisplay.getChildren().clear();
        int len = prodList.size();
        Label[] ProductCategory = new Label[len];
        Label[] ProductDescription = new Label[len];
        Label[] ProductPrice = new Label[len];
        Label[] ProductDiscount = new Label[len];
        HBox[] productDetailsDisplay = new HBox[len];
        for(int i=0;i<len;i++)
        {
            Product prod = prodList.get(i);
            ProductCategory[i] = new Label();
            ProductCategory[i].setText(prod.getProductCategory());
            ProductDescription[i] = new Label();
            ProductDescription[i].setText(prod.getProductDescription());
            ProductPrice[i]= new Label();
            ProductPrice[i].setText(Integer.toString(prod.getPrice()));
            ProductDiscount[i] = new Label();
            ProductDiscount[i].setText(Integer.toString(prod.getDiscount()));
            Button BuyButton = new Button("Buy");
            BuyButton.setOnAction(e -> BuyProducts(prod));
            Button AddWishListButton = new Button("Add To WishList");
            AddWishListButton.setOnAction(e -> AddToWishListProduct(prod));
            Button AddToCart = new Button(" Add To Cart");
            AddToCart.setOnAction(e -> AddToCartProduct(prod));
            productDetailsDisplay[i] = new HBox();
            productDetailsDisplay[i].getChildren().addAll(ProductCategory[i],ProductDescription[i],ProductPrice[i],ProductDiscount[i],BuyButton,AddWishListButton,AddToCart);
            CentreDisplay.getChildren().clear();
            CentreDisplay.getChildren().add(productDetailsDisplay[i]);
        }
    }

    private void BuyProducts(Product prod)
    {
        BuyProduct bp = new BuyProduct();
        bp.Quantity=1;
        bp.prod=prod;
        bp.CustomerUserName=customer.getUserName();
        bp.time= new Time(new Date().getTime());
        bp.Address = customer.getAddress();
        try {
            oos.writeObject(bp);
            oos.flush();
            System.out.println("Done!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void AddToCartProduct(Product prod)
    {
    }

    private void AddToWishListProduct(Product prod)
    {

    }

    private ArrayList<Product> getTending()
    {
        return null;
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
        PinNoLabel = new Label("Pin No");
        PinNoArea = new TextArea();
        PinNoArea.setText(customer.getPinNo());
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
        SaveProfile = new Button("Save Your Changes");
        SaveProfile.setOnAction(e -> {
            try {
                SaveChangesToProfile();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        try
        {

            CentreDisplay.getChildren().addAll(UserNameLabel, UserNameArea, PasswordLabel, PasswordArea, FirstNameLabel, FirstNameArea, LasNameLabel, LastNameArea, EmailLabel, EmailArea, MobileNoLabel, MobileNoArea, AddressLabel, AddressArea,PinNoLabel,PinNoArea,SaveProfile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void SaveChangesToProfile() throws IOException, ClassNotFoundException {
        ChangeCustomerDetails ccd = new ChangeCustomerDetails();
        customer = new Customer(FirstNameArea.getText(),LastNameArea.getText(),UserNameArea.getText(),PasswordArea.getText(),AddressArea.getText(),MobileNoArea.getText(),PinNoArea.getText(),EmailArea.getText(),null,null,null);
        ccd.client = customer;
        ccd.connection=null;
        if(oos==null)System.out.println(oos);
        oos.writeObject(ccd);
        oos.flush();
        customer= (Customer)ois.readObject();
        //SetProfileScene();
    }

    @FXML
    public void SearchProduct() throws IOException, ClassNotFoundException
    {
        SearchFor sf = new SearchFor();
        sf.searchProduct = SearchBar.getText();
        oos.writeObject(sf);
        oos.flush();
        ArrayList<Product> prodlist = (ArrayList<Product>) ois.readObject();
        ShowProductList(prodlist);
    }


}

