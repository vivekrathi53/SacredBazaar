package ClientFiles.AdminFiles;

import ClientFiles.AdminFiles.AdminQueries.*;
import ClientFiles.LoginWindow;
import ClientFiles.CustomerFiles.CustomerQueries.ChangeCustomerDetails;
import ClientFiles.CustomerFiles.CustomerQueries.LoadCustomerDetails;
import ClientFiles.CustomerFiles.CustomerQueries.SearchFor;
import ClientFiles.CustomerFiles.Customer;
import ClientFiles.Product;
import ClientFiles.RetailerFiles.ProductInfoController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminWindowController
{
    @FXML
    private
    LineChart<?, ?> AdminGraph;
    @FXML
    private
    CategoryAxis x;
    @FXML
    private
    NumberAxis y;
    @FXML
    private BorderPane AdminPane;
    @FXML
    private VBox LeftVBox;
    @FXML
    private VBox TopVBox;
    @FXML
    private TextArea SearchBar;
    private Stage window;
    private Stage Loginwindow;
    private FXMLLoader loader;
    @FXML
    private ScrollPane CentreDisplay;
    private Admin admin;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    @FXML
    private VBox vBox;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
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

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void graphcustomer(){

    }

    public void showcustomer() throws IOException, ClassNotFoundException
    {
        System.out.println("Giving List of customers");
        Customershow cs=new Customershow();
        getOos().writeObject(cs);
        getOos().flush();
        ArrayList<Customer> show=(ArrayList<Customer>) getOis().readObject();
        ShowProductList(show);
    }

    private void ShowProductList(ArrayList<Customer>customerList) throws IOException
    {
        VBox vBox = new VBox();
        int len=customerList.size();


        HBox customerDetailsDisplay = new HBox();
        customerDetailsDisplay.getChildren().add(new Label("Rank"));
        customerDetailsDisplay.getChildren().add(new Label("Customer UserName"));
        customerDetailsDisplay.getChildren().add(new Label("Total Spending"));
        vBox.getChildren().add(customerDetailsDisplay);
        for(int i=0;i<len;i++)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/CustomerDisplayAdmin.fxml")) ;
            customerDetailsDisplay = (HBox) loader.load();
            CustomerAdminController controller = loader.getController();
            Customer con = customerList.get(i);
            controller.getCustomern().setText(""+(i+1)+"");
            controller.getUserName().setText(con.getUserName());
            controller.getCustomerSpendings().setText("Rs. "+con.getTotalspending());
            vBox.getChildren().add(customerDetailsDisplay);
        }
        getAdminPane().setCenter(vBox);
    }

    public void SearchProduct()
    {
        SearchFor sf = new SearchFor();
        sf.searchProduct= getSearchBar().getText();
        ArrayList<Product> prodList = null;
        try {
            getOos().writeObject(sf);
            getOos().flush();
            prodList = (ArrayList<Product>) getOis().readObject();
            showProductList(prodList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showProductList(ArrayList<Product> prodList)
    {
        VBox displayForProducts = new VBox();
        for (int i = 0; i < prodList.size(); i++)
        {
            VBox vBox = null;
            setLoader(new FXMLLoader(getClass().getResource("FXML_files/ProductInfo.fxml")));
            ProductInfoController controller;
            try {
                vBox = getLoader().load();
                controller = getLoader().getController();
                controller.getCategoryBox().setText(prodList.get(i).getProductCategory());
                controller.getProductIdBox().setText(Integer.toString(prodList.get(i).getProductId()));
                controller.getDescriptionBox().setText(prodList.get(i).getProductDescription());
                controller.getDiscountBox().setText(Integer.toString(prodList.get(i).getDiscount()));
                controller.getPriceBox().setText(Integer.toString(prodList.get(i).getPrice()));
                controller.getQuantityBox().setText(Integer.toString(prodList.get(i).getQuantity()));
                controller.getRetailerUserNameBox().setText(prodList.get(i).getRetailer());
                Product p = prodList.get(i);
                controller.getDeleteProduct().setOnAction(e -> RemoveProduct(p));
                controller.getProductChanges().setOnAction(e ->
                {
                    p.setProductCategory((controller.getCategoryBox().getText()));
                    p.setPrice(Integer.parseInt(controller.getPriceBox().getText()));
                    p.setDiscount(Integer.parseInt(controller.getDiscountBox().getText()));
                    p.setQuantity(Integer.parseInt(controller.getQuantityBox().getText()));
                    p.setProductDescription(controller.getDescriptionBox().getText());
                    p.setRetailer(controller.getRetailerUserNameBox().getText());
                    SaveChanges(p);
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
            displayForProducts.getChildren().add(vBox);
        }
        getAdminPane().setCenter(displayForProducts);
    }

    private void RemoveProduct(Product p)
    {
        RemoveProduct rp = new RemoveProduct();
        rp.ProductId = p.getProductId();
        try {
            getOos().writeObject(rp);
            getOos().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void SaveChanges(Product prod)
    {
        ChangeProduct cp = new ChangeProduct();
        cp.product = prod;
        try {
            getOos().writeObject(cp);
            getOos().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ShowProfile() throws IOException, ClassNotFoundException {
        LoadAdminDetails lad = new LoadAdminDetails();
        lad.UserName= getAdmin().getUserName();
        getOos().writeObject(lad);
        getOos().flush();
        setAdmin((Admin) getOis().readObject());
        setLoader(new FXMLLoader(getClass().getResource("FXML_files/AdminProfile.fxml")));
        AdminProfileController controller;
        try
        {
            setCentreDisplay((ScrollPane) getLoader().load());
            controller = getLoader().getController();
            controller.setAdmin(getAdmin());
            controller.setOos(getOos());
            controller.setOis(getOis());
            controller.ShowProfile();
            getAdminPane().setCenter(getCentreDisplay());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getUserInfo() throws IOException, ClassNotFoundException, SQLException
    {
        GenerateGraph gg = new GenerateGraph();
        graphcontroller gc=new graphcontroller();
        TextField tf = new TextField();
        tf.setPromptText("Search User");
        Button btn = new Button();
        Button graph=new Button();
        graph.setText("Graph");
        btn.setText("Search");
       // gg.connection;
        btn.setOnAction(e-> {
            try {
                displayInfo(tf.getText());
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        graph.setOnAction((ActionEvent e) ->
        {

            gg.username=tf.getText();
            int []arr=new int[40];
            try {
                getOos().writeObject(gg);
                getOos().flush();
                arr = (int[]) getOis().readObject();
            }  catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            gc.setArr(arr);
            SimpleDateFormat formatter = new SimpleDateFormat("dd");
            Date date = new Date();
            String currentdate = formatter.format(date);
            int datecurrent=Integer.parseInt(currentdate);
            gc.setDatecurrent(datecurrent);
            try {
                gc.startgraph();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        setvBox(new VBox());
        HBox hBox = new HBox();
        hBox.getChildren().addAll(tf,btn,graph);
        getvBox().getChildren().add(hBox);
        getAdminPane().setCenter(getvBox());
    }

    public String usernamerequire(String UserName) throws IOException, ClassNotFoundException {
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = UserName;
        getOos().writeObject(lcd);
        getOos().flush();
        Customer customer = (Customer) getOis().readObject();
        return customer.getUserName();
    }

    public void displayInfo(String UserName) throws IOException, ClassNotFoundException {
        getvBox().getChildren().clear();
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = UserName;
        getOos().writeObject(lcd);
        getOos().flush();
        Customer customer = (Customer) getOis().readObject();
        Label UserNameLabel,PasswordLabel,EmailLabel,MobileNoLabel,FirstNameLabel,LasNameLabel,AddressLabel,PinNoLabel;
        TextArea UserNameArea,PasswordArea,FirstNameArea,LastNameArea,MobileNoArea,AddressArea,EmailArea,PinNoArea;
        Button SaveProfile;
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
                ChangeCustomerDetails ccd = new ChangeCustomerDetails();
                Customer c = new Customer(FirstNameArea.getText(),LastNameArea.getText(),UserNameArea.getText(),PasswordArea.getText(),AddressArea.getText(),MobileNoArea.getText(),PinNoArea.getText(),EmailArea.getText(),null,null,null);
                ccd.client = c;
                getOos().writeObject(ccd);
                getOos().flush();
                c = (Customer) getOis().readObject();
                displayInfo(UserName);
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        getvBox().getChildren().addAll(UserNameLabel, UserNameArea, PasswordLabel, PasswordArea, FirstNameLabel, FirstNameArea, LasNameLabel, LastNameArea, EmailLabel, EmailArea, MobileNoLabel, MobileNoArea, AddressLabel, AddressArea,PinNoLabel,PinNoArea,SaveProfile);
        return ;
    }

    public void Logout(ActionEvent actionEvent)
    {
        LoginWindow lg = new LoginWindow();
        try {
            lg.start(getLoginwindow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LineChart<?, ?> getAdminGraph() {
        return AdminGraph;
    }

    public void setAdminGraph(LineChart<?, ?> adminGraph) {
        AdminGraph = adminGraph;
    }

    public CategoryAxis getX() {
        return x;
    }

    public void setX(CategoryAxis x) {
        this.x = x;
    }

    public NumberAxis getY() {
        return y;
    }

    public void setY(NumberAxis y) {
        this.y = y;
    }

    public BorderPane getAdminPane() {
        return AdminPane;
    }

    public void setAdminPane(BorderPane adminPane) {
        AdminPane = adminPane;
    }

    public VBox getLeftVBox() {
        return LeftVBox;
    }

    public void setLeftVBox(VBox leftVBox) {
        LeftVBox = leftVBox;
    }

    public VBox getTopVBox() {
        return TopVBox;
    }

    public void setTopVBox(VBox topVBox) {
        TopVBox = topVBox;
    }

    public TextArea getSearchBar() {
        return SearchBar;
    }

    public void setSearchBar(TextArea searchBar) {
        SearchBar = searchBar;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public Stage getLoginwindow() {
        return Loginwindow;
    }

    public void setLoginwindow(Stage loginwindow) {
        Loginwindow = loginwindow;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public ScrollPane getCentreDisplay() {
        return CentreDisplay;
    }

    public void setCentreDisplay(ScrollPane centreDisplay) {
        CentreDisplay = centreDisplay;
    }

    public VBox getvBox() {
        return vBox;
    }

    public void setvBox(VBox vBox) {
        this.vBox = vBox;
    }
}
