package ClientFiles.CustomerFiles;

import ClientFiles.LoginWindow;
import ClientFiles.RetailerFiles.ProductDisplayController;
import ClientFiles.CustomerFiles.CustomerQueries.*;
import ClientFiles.LogoutClient;
import ClientFiles.Product;
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
    private int flagadd;
    private int flaghistory;
    private int flagwishlist;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Customer customer;
    @FXML
    private Label UserNameLabel;
    @FXML
    private Label PasswordLabel;
    @FXML
    private Label EmailLabel;
    @FXML
    private Label MobileNoLabel;
    @FXML
    private Label FirstNameLabel;
    @FXML
    private Label LasNameLabel;
    @FXML
    private Label AddressLabel;
    @FXML
    private Label PinNoLabel;
    @FXML
    private TextArea UserNameArea;
    @FXML
    private TextArea PasswordArea;
    @FXML
    private TextArea FirstNameArea;
    @FXML
    private TextArea LastNameArea;
    @FXML
    private TextArea MobileNoArea;
    @FXML
    private TextArea AddressArea;
    @FXML
    private TextArea EmailArea;
    @FXML
    private TextArea PinNoArea;
    @FXML
    private Button SaveProfile;
    @FXML
    private Stage currentStage;
    @FXML
    private
    Button ProfileButton;
    @FXML
    private
    Button MyCartButton;
    @FXML
    private
    Button WishListButton;
    @FXML
    private
    Button TrendingButton;
    @FXML
    private
    VBox TopVBox;
    @FXML
    private VBox LeftVBox;
    @FXML
    private VBox CentreDisplay;
    @FXML
    private
    BorderPane DisplayPane;
    @FXML
    private
    ScrollPane scrollPane;
    @FXML
    private
    TextArea SearchBar;
    @FXML
    private
    Label Spending;
    @FXML
    private
    Label Totalspending;
    @FXML
    private void ShowInCart() throws IOException, ClassNotFoundException {
        setFlagadd(1);
        setFlagwishlist(0);
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = getCustomer().getUserName();
        getOos().writeObject(lcd);
        getOos().flush();
        setCustomer((Customer) getOis().readObject());
        ShowProductList(getCustomer().getProductsInCart());
    }
    @FXML
    private void ShowWishList() throws IOException, ClassNotFoundException {
        setFlagwishlist(1);
        setFlagadd(0);
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = getCustomer().getUserName();
        getOos().writeObject(lcd);
        getOos().flush();
        setCustomer((Customer) getOis().readObject());
        ShowProductList(getCustomer().getProductsWishList());
    }
    @FXML
    private void ShowTrending()
    {
        ArrayList<Product> prodList = getTending();
        ShowProductList(prodList);
    }
    @FXML
    private void ShowHistory() throws IOException, ClassNotFoundException {
        setFlagadd(0);
        setFlagwishlist(0);
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = getCustomer().getUserName();
        getOos().writeObject(lcd);
        getOos().flush();
        setCustomer((Customer) getOis().readObject());
        ShowProductList(getCustomer().getProductsBought());
    }
    private void ShowProductList(ArrayList<Product> prodList)
    {
        getCentreDisplay().getChildren().clear();
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
                controller.getBuyButton().setOnAction(e -> BuyProducts(prod));
                if(getFlagadd() ==0)
                    controller.getAddtoCart().setOnAction(e -> AddToCartProduct(prod));
                else
                {
                    controller.getAddtoCart().setText("Remove from Cart");
                    controller.getAddtoCart().setOnAction(e-> RemoveFromCart(prod));
                }
                if(getFlagwishlist() ==0)
                    controller.getAddtoWishList().setOnAction(e -> AddToWishListProduct(prod));
                else
                {
                    controller.getAddtoWishList().setText("Remove From WishList");
                    controller.getAddtoWishList().setOnAction(e-> RemoveFromWishList(prod));
                }
                controller.getRetailerName().setText(prod.getRetailer());
                controller.getProductDescription().setText(controller.getProductDescription().getText() +"\n"+ prod.getQuantity());
                controller.getPrice().setText(controller.getPrice().getText() + prod.getPrice());
                controller.getProductCategory().setText(prod.getProductCategory());
                controller.getProductDescription().setText(prod.getProductDescription());
                controller.getDiscountLabel().setText(controller.getDiscountLabel().getText() + prod.getDiscount());
                productDetailsDisplay[i].setMinWidth(getCentreDisplay().getWidth());
            } catch (IOException e) {
                e.printStackTrace();
            }
            getCentreDisplay().getChildren().add(productDetailsDisplay[i]);
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
            bp.CustomerUserName = getCustomer().getUserName();
            bp.time = new Timestamp(new Date().getTime());
            bp.Address = getCustomer().getAddress();
            try {
                getOos().writeObject(bp);
                getOos().flush();
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
            bp.CustomerUserName = getCustomer().getUserName();
            bp.time = new Timestamp(new Date().getTime());
            bp.Address = getCustomer().getAddress();
            bp.type=2;
            try {
                getOos().writeObject(bp);
                getOos().flush();
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
            bp.CustomerUserName = getCustomer().getUserName();
            bp.time = new Timestamp(new Date().getTime());
            bp.Address = getCustomer().getAddress();
            bp.type=3;
            try {
                getOos().writeObject(bp);
                getOos().flush();
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
        setFlagwishlist(0);
        setFlaghistory(0);
        setFlagadd(0);
        GetTrendingList gtl = new GetTrendingList();
        try {
            getOos().writeObject(gtl);
            getOos().flush();
            ArrayList<Product> prodList = (ArrayList<Product>) getOis().readObject();
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
        setFlagadd(0);
        setFlagwishlist(0);
        setFlaghistory(0);
        SetSpendings();
        setUserNameLabel(new Label("UserName"));
        setPasswordLabel(new Label("Password"));
        setUserNameArea(new TextArea());
        getUserNameArea().setText(getCustomer().getUserName());
        getUserNameArea().setDisable(true);
        setPasswordArea(new TextArea());
        getPasswordArea().setText(getCustomer().getPassword());
        setEmailLabel(new Label("Email"));
        setMobileNoLabel(new Label("Mobile No"));
        setFirstNameLabel(new Label("First Name"));
        setLasNameLabel(new Label("Last Name"));
        setAddressLabel(new Label("Address"));
        setPinNoLabel(new Label("Pin No"));
        setPinNoArea(new TextArea());
        getPinNoArea().setText(getCustomer().getPinNo());
        setFirstNameArea(new TextArea());
        getFirstNameArea().setText(getCustomer().getFirstName());
        setEmailArea(new TextArea());
        getEmailArea().setText(getCustomer().getEmail());
        setLastNameArea(new TextArea());
        getLastNameArea().setText(getCustomer().getLastName());
        setMobileNoArea(new TextArea());
        getMobileNoArea().setText(getCustomer().getMobileNo());
        setAddressArea(new TextArea());
        getAddressArea().setText(getCustomer().getAddress());
        setSaveProfile(new Button("Save Your Changes"));
        getSaveProfile().setOnAction(e -> {
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
            getCentreDisplay().getChildren().clear();
            getCentreDisplay().getChildren().addAll(getUserNameLabel(), getUserNameArea(), getPasswordLabel(), getPasswordArea(), getFirstNameLabel(), getFirstNameArea(), getLasNameLabel(), getLastNameArea(), getEmailLabel(), getEmailArea(), getMobileNoLabel(), getMobileNoArea(), getAddressLabel(), getAddressArea(), getPinNoLabel(), getPinNoArea(), getSaveProfile());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void SaveChangesToProfile() throws IOException, ClassNotFoundException {
        ChangeCustomerDetails ccd = new ChangeCustomerDetails();
        setCustomer(new Customer(getFirstNameArea().getText(), getLastNameArea().getText(), getUserNameArea().getText(), getPasswordArea().getText(), getAddressArea().getText(), getMobileNoArea().getText(), getPinNoArea().getText(), getEmailArea().getText(),null,null,null));
        ccd.client = getCustomer();
        getOos().writeObject(ccd);
        getOos().flush();
        setCustomer((Customer) getOis().readObject());
        //SetProfileScene();
    }

    @FXML
    public void SearchProduct()
    {
        SearchFor sf = new SearchFor();
        sf.searchProduct = getSearchBar().getText();
        try {
            getOos().writeObject(sf);
            getOos().flush();
        } catch (IOException e) {
            Alert error = new Alert(Alert.AlertType.INFORMATION);
            error.setContentText("Error in Sending Data To Server");
            error.show();
        }

        ArrayList<Product> prodlist = null;
        try {
            prodlist = (ArrayList<Product>) getOis().readObject();
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
            getOos().writeObject(lc);
            getOos().flush();
            getSocket().close();
            LoginWindow lw = new LoginWindow();
            lw.start(getCurrentStage());
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
            ts.UserName = getCustomer().getUserName();
            getOos().writeObject(ts);
            getOos().flush();
            int spending = (int) getOis().readObject();
            getTotalspending().setText("Rs "+(spending)+"");
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

    public int getFlagadd() {
        return flagadd;
    }

    public void setFlagadd(int flagadd) {
        this.flagadd = flagadd;
    }

    public int getFlaghistory() {
        return flaghistory;
    }

    public void setFlaghistory(int flaghistory) {
        this.flaghistory = flaghistory;
    }

    public int getFlagwishlist() {
        return flagwishlist;
    }

    public void setFlagwishlist(int flagwishlist) {
        this.flagwishlist = flagwishlist;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Label getUserNameLabel() {
        return UserNameLabel;
    }

    public void setUserNameLabel(Label userNameLabel) {
        UserNameLabel = userNameLabel;
    }

    public Label getPasswordLabel() {
        return PasswordLabel;
    }

    public void setPasswordLabel(Label passwordLabel) {
        PasswordLabel = passwordLabel;
    }

    public Label getEmailLabel() {
        return EmailLabel;
    }

    public void setEmailLabel(Label emailLabel) {
        EmailLabel = emailLabel;
    }

    public Label getMobileNoLabel() {
        return MobileNoLabel;
    }

    public void setMobileNoLabel(Label mobileNoLabel) {
        MobileNoLabel = mobileNoLabel;
    }

    public Label getFirstNameLabel() {
        return FirstNameLabel;
    }

    public void setFirstNameLabel(Label firstNameLabel) {
        FirstNameLabel = firstNameLabel;
    }

    public Label getLasNameLabel() {
        return LasNameLabel;
    }

    public void setLasNameLabel(Label lasNameLabel) {
        LasNameLabel = lasNameLabel;
    }

    public Label getAddressLabel() {
        return AddressLabel;
    }

    public void setAddressLabel(Label addressLabel) {
        AddressLabel = addressLabel;
    }

    public Label getPinNoLabel() {
        return PinNoLabel;
    }

    public void setPinNoLabel(Label pinNoLabel) {
        PinNoLabel = pinNoLabel;
    }

    public TextArea getUserNameArea() {
        return UserNameArea;
    }

    public void setUserNameArea(TextArea userNameArea) {
        UserNameArea = userNameArea;
    }

    public TextArea getPasswordArea() {
        return PasswordArea;
    }

    public void setPasswordArea(TextArea passwordArea) {
        PasswordArea = passwordArea;
    }

    public TextArea getFirstNameArea() {
        return FirstNameArea;
    }

    public void setFirstNameArea(TextArea firstNameArea) {
        FirstNameArea = firstNameArea;
    }

    public TextArea getLastNameArea() {
        return LastNameArea;
    }

    public void setLastNameArea(TextArea lastNameArea) {
        LastNameArea = lastNameArea;
    }

    public TextArea getMobileNoArea() {
        return MobileNoArea;
    }

    public void setMobileNoArea(TextArea mobileNoArea) {
        MobileNoArea = mobileNoArea;
    }

    public TextArea getAddressArea() {
        return AddressArea;
    }

    public void setAddressArea(TextArea addressArea) {
        AddressArea = addressArea;
    }

    public TextArea getEmailArea() {
        return EmailArea;
    }

    public void setEmailArea(TextArea emailArea) {
        EmailArea = emailArea;
    }

    public TextArea getPinNoArea() {
        return PinNoArea;
    }

    public void setPinNoArea(TextArea pinNoArea) {
        PinNoArea = pinNoArea;
    }

    public Button getSaveProfile() {
        return SaveProfile;
    }

    public void setSaveProfile(Button saveProfile) {
        SaveProfile = saveProfile;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public Button getProfileButton() {
        return ProfileButton;
    }

    public void setProfileButton(Button profileButton) {
        ProfileButton = profileButton;
    }

    public Button getMyCartButton() {
        return MyCartButton;
    }

    public void setMyCartButton(Button myCartButton) {
        MyCartButton = myCartButton;
    }

    public Button getWishListButton() {
        return WishListButton;
    }

    public void setWishListButton(Button wishListButton) {
        WishListButton = wishListButton;
    }

    public Button getTrendingButton() {
        return TrendingButton;
    }

    public void setTrendingButton(Button trendingButton) {
        TrendingButton = trendingButton;
    }

    public VBox getTopVBox() {
        return TopVBox;
    }

    public void setTopVBox(VBox topVBox) {
        TopVBox = topVBox;
    }

    public VBox getLeftVBox() {
        return LeftVBox;
    }

    public void setLeftVBox(VBox leftVBox) {
        LeftVBox = leftVBox;
    }

    public VBox getCentreDisplay() {
        return CentreDisplay;
    }

    public void setCentreDisplay(VBox centreDisplay) {
        CentreDisplay = centreDisplay;
    }

    public BorderPane getDisplayPane() {
        return DisplayPane;
    }

    public void setDisplayPane(BorderPane displayPane) {
        DisplayPane = displayPane;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public TextArea getSearchBar() {
        return SearchBar;
    }

    public void setSearchBar(TextArea searchBar) {
        SearchBar = searchBar;
    }

    public Label getSpending() {
        return Spending;
    }

    public void setSpending(Label spending) {
        Spending = spending;
    }

    public Label getTotalspending() {
        return Totalspending;
    }

    public void setTotalspending(Label totalspending) {
        Totalspending = totalspending;
    }
}