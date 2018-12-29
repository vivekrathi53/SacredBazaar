package MainPackage;

import CustomerQueries.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Optional;

public class ShopWindowController
{
    int flagadd;int flaghistory;int flagwishlist;
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
    BorderPane DisplayPane;
    @FXML
    ScrollPane scrollPane;
    @FXML
    TextArea SearchBar;
    @FXML
    Label Spending;
    @FXML
    Label Totalspending;
    @FXML
    private void ShowInCart() throws IOException, ClassNotFoundException {
        flagadd=1;flagwishlist=0;
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = customer.getUserName();
        oos.writeObject(lcd);
        oos.flush();
        customer = (Customer) ois.readObject();
        ShowProductList(customer.getProductsInCart());
    }
    @FXML
    private void ShowWishList() throws IOException, ClassNotFoundException {
        flagwishlist=1;flagadd=0;
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = customer.getUserName();
        oos.writeObject(lcd);
        oos.flush();
        customer = (Customer) ois.readObject();
        ShowProductList(customer.getProductsWishList());
    }
    @FXML
    private void ShowTrending()
    {
        ArrayList<Product> prodList = getTending();
        ShowProductList(prodList);
    }
    @FXML
    private void ShowHistory() throws IOException, ClassNotFoundException {
        flagadd=0;flagwishlist=0;
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = customer.getUserName();
        oos.writeObject(lcd);
        oos.flush();
        customer = (Customer) ois.readObject();
        ShowProductList(customer.getProductsBought());
    }
    private void ShowProductList(ArrayList<Product> prodList)
    {
        CentreDisplay.getChildren().clear();
        int len=0;
        if(prodList!=null)len= prodList.size();
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Product not found Please use better search ");
            alert.show();
        }
        AnchorPane[] productDetailsDisplay = new AnchorPane[len];
        for(int i=0;i<len;i++)
        {
            Product prod = prodList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/ProductDisplay.fxml")) ;
                productDetailsDisplay[i] = (AnchorPane) loader.load();
                ProductDisplayController controller = loader.getController();
                controller.BuyButton.setOnAction(e -> BuyProducts(prod));
                if(flagadd==0)
                    controller.AddtoCart.setOnAction(e -> AddToCartProduct(prod));
                else
                {
                    controller.AddtoCart.setText("Remove from Cart");
                    controller.AddtoCart.setOnAction(e-> RemoveFromCart(prod));
                }
                if(flagwishlist==0)
                    controller.AddtoWishList.setOnAction(e -> AddToWishListProduct(prod));
                else
                {
                    controller.AddtoWishList.setText("Remove From WishList");
                    controller.AddtoWishList.setOnAction(e-> RemoveFromWishList(prod));
                }
                controller.RetailerName.setText(prod.getRetailer());
                controller.productDescription.setText(controller.productDescription.getText() +"\n"+ prod.getQuantity());
                controller.price.setText(controller.price.getText() + prod.getPrice());
                controller.productCategory.setText(prod.getProductCategory());
                controller.productDescription.setText(prod.getProductDescription());
                controller.DiscountLabel.setText(controller.DiscountLabel.getText() + prod.getDiscount());
                productDetailsDisplay[i].setMinWidth(CentreDisplay.getWidth());
            } catch (IOException e) {
                e.printStackTrace();
            }
            CentreDisplay.getChildren().add(productDetailsDisplay[i]);
        }
    }

    private void RemoveFromWishList(Product prod)
    {

    }

    private void RemoveFromCart(Product prod)
    {

    }

    private void BuyProducts(Product prod)
    {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Confirmation");
        dialog.setHeaderText("Do you want place order?");
        dialog.setContentText("Please Enter Quantity?");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            String quan = result.get();
            int quantity = Integer.parseInt(quan);
            alert.setContentText("Total Amount - Rs."+Integer.toString(quantity*prod.getPrice()));
            alert.show();
            BuyProduct bp = new BuyProduct();
            bp.Quantity = quantity;
            bp.prod = prod;
            bp.CustomerUserName = customer.getUserName();
            bp.time = new Timestamp(new Date().getTime());
            bp.Address = customer.getAddress();
            try {
                oos.writeObject(bp);
                oos.flush();
                System.out.println("Done!!");
            } catch (IOException e) {
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setContentText("Error in Sending Data To Server");
                error.show();
            }
        }
        SetSpendings();
    }

    private void AddToCartProduct(Product prod)
    {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Confirmation");
        dialog.setHeaderText("Do you want place order?");
        dialog.setContentText("Please Enter Quantity?");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            String quan = result.get();
            int quantity = Integer.parseInt(quan);
            alert.setContentText("Total Amount - Rs."+Integer.toString(quantity*prod.getPrice()));
            alert.show();
            AddProductTo bp = new AddProductTo();
            System.out.println(quantity);
            bp.Quantity = quantity;
            bp.prod = prod;
            bp.CustomerUserName = customer.getUserName();
            bp.time = new Timestamp(new Date().getTime());
            bp.Address = customer.getAddress();
            bp.type=2;
            try {
                oos.writeObject(bp);
                oos.flush();
                System.out.println("Done!!");
            } catch (IOException e) {
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setContentText("Error in Sending Data To Server");
                error.show();
            }
        }
    }

    private void AddToWishListProduct(Product prod)
    {
        TextInputDialog dialog = new TextInputDialog("1");
        dialog.setTitle("Confirmation");
        dialog.setHeaderText("Do you want place order?");
        dialog.setContentText("Please Enter Quantity?");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            String quan = result.get();
            int quantity = Integer.parseInt(quan);
            alert.setContentText("Total Amount - Rs." + Integer.toString(quantity*prod.getPrice()));
            alert.show();
            AddProductTo bp = new AddProductTo();
            System.out.println(quantity);
            bp.Quantity = quantity;
            bp.prod = prod;
            bp.CustomerUserName = customer.getUserName();
            bp.time = new Timestamp(new Date().getTime());
            bp.Address = customer.getAddress();
            bp.type=3;
            try {
                oos.writeObject(bp);
                oos.flush();
                System.out.println("Done!!");
            } catch (IOException e) {
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setContentText("Error in Sending Data To Server");
                error.show();
            }
        }
    }

    private ArrayList<Product> getTending()
    {
        flagwishlist=0;
        flaghistory=0;
        flagadd=0;
        GetTrendingList gtl = new GetTrendingList();
        try {
            oos.writeObject(gtl);
            oos.flush();
            ArrayList<Product> prodList = (ArrayList<Product>) ois.readObject();
            return prodList;
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Error in Sending Data To Server");
            error.show();
        } catch (ClassNotFoundException e) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Error in  Connection To Server");
            error.show();
        }
        return null;
    }

    @FXML
    void SetProfileScene() throws IOException, ClassNotFoundException
    {
        flagadd=0;
        flagwishlist=0;
        flaghistory=0;
        SetSpendings();
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
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setContentText("Error in Sending Data To Server");
                error.show();
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
        oos.writeObject(ccd);
        oos.flush();
        customer= (Customer)ois.readObject();
        //SetProfileScene();
    }

    @FXML
    public void SearchProduct()
    {
        SearchFor sf = new SearchFor();
        sf.searchProduct = SearchBar.getText();
        try {
            oos.writeObject(sf);
            oos.flush();
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Error in Sending Data To Server");
            error.show();
        }

        ArrayList<Product> prodlist = null;
        try {
            prodlist = (ArrayList<Product>) ois.readObject();
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Error in Sending Data To Server");
            error.show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ShowProductList(prodlist);
    }
    @FXML
    public void Logout()
    {
        LogoutClient lc =new LogoutClient();
        try {
            oos.writeObject(lc);
            oos.flush();
            socket.close();
            LoginWindow lw = new LoginWindow();
            lw.start(currentStage);
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Error in Sending Data To Server but Logging Out Locally");
            error.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void SetSpendings()
    {
        try
        {
            TotalSpending ts = new TotalSpending();
            ts.UserName = customer.getUserName();
            oos.writeObject(ts);
            oos.flush();
            int spending = (int)ois.readObject();
            Totalspending.setText("Rs "+(spending)+"");
        }
        catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Error in Sending Data To Server");
            error.show();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}