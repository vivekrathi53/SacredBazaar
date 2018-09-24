package MainPackage;

import CustomerQueries.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;

public class ShopWindowController
{
    public Socket socket;
    ObjectOutputStream oos;
    ObjectInputStream ois;
    Customer customer;
    Label UserNameLabel,PasswordLabel,EmailLabel,MobileNoLabel,FirstNameLabel,LasNameLabel,AddressLabel,PinNoLabel;
    TextArea UserNameArea,PasswordArea,FirstNameArea,LastNameArea,MobileNoArea,AddressArea,EmailArea,PinNoArea;
    Button SaveProfile;
    public Stage currentStage;
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
        SplitPane[] productDetailsDisplay = new SplitPane[len];
        for(int i=0;i<len;i++)
        {
            Product prod = prodList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDisplayDesign.fxml")) ;
                productDetailsDisplay[i] = (SplitPane) loader.load();
                ProductDesignController controller = loader.getController();
                controller.BuyButton.setOnAction(e -> BuyProducts(prod));
                controller.AddtoCart.setOnAction(e -> AddToCartProduct(prod));
                controller.AddtoWishList.setOnAction(e -> AddToWishListProduct(prod));
                controller.price.setText(controller.price.getText() + prod.getPrice());
                controller.productCategory.setText(prod.getProductCategory());
                controller.productDescription.setText(prod.getProductDescription());
                productDetailsDisplay[i].setPrefWidth(CentreDisplay.getPrefWidth());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
        AddProductTo bp = new AddProductTo();
        bp.Quantity=1;
        bp.prod=prod;
        bp.CustomerUserName=customer.getUserName();
        bp.time= new Time(new Date().getTime());
        bp.Address = customer.getAddress();
        bp.type=2;
        try {
            oos.writeObject(bp);
            oos.flush();
            System.out.println("Done!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AddToWishListProduct(Product prod)
    {
        AddProductTo bp = new AddProductTo();
        bp.Quantity=1;
        bp.prod=prod;
        bp.CustomerUserName=customer.getUserName();
        bp.time= new Time(new Date().getTime());
        bp.Address = customer.getAddress();
        bp.type=3;
        try {
            oos.writeObject(bp);
            oos.flush();
            System.out.println("Done!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Product> getTending()
    {
        GetTrendingList gtl = new GetTrendingList();
        try {
            oos.writeObject(gtl);
            oos.flush();
            ArrayList<Product> prodList = (ArrayList<Product>) ois.readObject();
            return prodList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
            CentreDisplay.getChildren().clear();
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

    public void Logout(ActionEvent actionEvent)
    {
        LogoutClient lc =new LogoutClient();
        try {
            oos.writeObject(lc);
            oos.flush();
            socket.close();
            LoginWindow lw = new LoginWindow();
            lw.start(currentStage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

