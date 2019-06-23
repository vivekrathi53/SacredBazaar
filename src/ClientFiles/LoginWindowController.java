package ClientFiles;

import ClientFiles.AdminFiles.AdminWindow;
import ClientFiles.CustomerFiles.Customer;
import ClientFiles.CustomerFiles.ShopWindow;
import ClientFiles.RetailerFiles.Retailer;
import ClientFiles.RetailerFiles.RetailerWindow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginWindowController
{
    @FXML
    private TextField ServerIP;
    @FXML
    private TextField PortNo;
    private Socket socket;
    @FXML
    private TextField name;
    @FXML
    private TextField pass;
    @FXML
    private
    Button customer;
    @FXML
    private
    Button retailer;
    @FXML
    private
    Button admin;
    private int type;

    private Stage window;

    public void logincustomer()
    {
        try {
            Login(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loginretalier()
    {
        try {
            Login(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loginadmin()
    {
        try
        {
            Login(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void signUp()
    {
        try {
            sign();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Login(int type) throws IOException
    {
        setWindow((Stage) getName().getScene().getWindow());
        setSocket(new Socket(getServerIP().getText(),Integer.parseInt(getPortNo().getText())));
        System.out.println("Connected to server");
        LoginData data = new LoginData(getName().getText(), getPass().getText(),type);
        ObjectOutputStream oos = new ObjectOutputStream(getSocket().getOutputStream());
        oos.writeObject(data);
        oos.flush();
        System.out.println("Waiting for Approval!");
        ObjectInputStream ois = new ObjectInputStream(getSocket().getInputStream());
        try
        {
            System.out.println("reading letter");
            Transaction a = (Authentication) ois.readObject();
            if(((Authentication) a).isAuth())
            {
                if(type==0)
                {
                    System.out.println("Login Retailer Approved");
                    RetailerWindow rw = new RetailerWindow();
                    rw.setRetailer((Retailer) ois.readObject());
                    rw.setOis(ois);
                    rw.setOos(oos);
                    rw.setSocket(getSocket());
                    rw.start(getWindow());
                }
                else if(type==1)
                {
                    System.out.println("Approved!!");
                    ShopWindow sw = new ShopWindow();
                    sw.setC((Customer) ois.readObject());
                    sw.setOis(ois);
                    sw.setOos(oos);
                    sw.setSocket(getSocket());
                    try
                    {
                        sw.start(getWindow());
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(type==3)
                {
                    System.out.println("Admin Approved!!");
                    AdminWindow aw = new AdminWindow();
                    aw.setOos(oos);
                    aw.setOis(ois);
                    aw.setSocket(getSocket());
                    aw.setLoginwindow(getWindow());
                    aw.start(getWindow());

                }
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Authentication Failed");
                alert.setHeaderText("Please Check Your Login Credentials");
                alert.setContentText("Wrong UserName or Password");
                alert.show();
            }
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Class Not Found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sign() throws IOException {
        setWindow((Stage) getName().getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/Signup.fxml"));
        Parent root = loader.load();
        Signup controllers = loader.getController();
        getWindow().setTitle("SignUp Window");
    setSocket(new Socket(getServerIP().getText(),Integer.parseInt(getPortNo().getText())));

        System.out.println("Connected to server");
        ObjectOutputStream oos = new ObjectOutputStream(getSocket().getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(getSocket().getInputStream());
        controllers.setSocket(getSocket());
        controllers.setObjectOutputStream(oos);
        controllers.setObjectInputStream(ois);
        getWindow().setScene(new Scene(root));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        getWindow().setWidth((primScreenBounds.getWidth()));
        getWindow().setHeight((primScreenBounds.getHeight()));
        getWindow().show();
    }

    public TextField getServerIP() {
        return ServerIP;
    }

    public void setServerIP(TextField serverIP) {
        ServerIP = serverIP;
    }

    public TextField getPortNo() {
        return PortNo;
    }

    public void setPortNo(TextField portNo) {
        PortNo = portNo;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public TextField getName() {
        return name;
    }

    public void setName(TextField name) {
        this.name = name;
    }

    public TextField getPass() {
        return pass;
    }

    public void setPass(TextField pass) {
        this.pass = pass;
    }

    public Button getCustomer() {
        return customer;
    }

    public void setCustomer(Button customer) {
        this.customer = customer;
    }

    public Button getRetailer() {
        return retailer;
    }

    public void setRetailer(Button retailer) {
        this.retailer = retailer;
    }

    public Button getAdmin() {
        return admin;
    }

    public void setAdmin(Button admin) {
        this.admin = admin;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }
}
