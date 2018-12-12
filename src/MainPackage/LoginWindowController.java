package MainPackage;

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
    public TextField ServerIP;
    public TextField PortNo;
    Socket socket;
    @FXML
    TextField name;
    @FXML
    TextField pass;
    @FXML
    Button customer;
    @FXML
    Button retailer;
    @FXML
    Button admin;
    int type;

    public Stage window;

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
        window = (Stage)name.getScene().getWindow();
        socket = new Socket(ServerIP.getText(),Integer.parseInt(PortNo.getText()));
        System.out.println("Connected to server");
        LoginData data = new LoginData(name.getText(),pass.getText(),type);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(data);
        oos.flush();
        System.out.println("Waiting for Approval!");
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        try
        {
            System.out.println("reading letter");
            Transaction a = (Authentication) ois.readObject();
            if(((Authentication) a).auth)
            {
                if(type==0)
                {
                    System.out.println("Login Retailer Approved");
                    RetailerWindow rw = new RetailerWindow();
                    rw.retailer = (Retailer) ois.readObject();
                    rw.ois=ois;
                    rw.oos=oos;
                    rw.socket=socket;
                    rw.start(window);
                }
                else if(type==1)
                {
                    System.out.println("Approved!!");
                    ShopWindow sw = new ShopWindow();
                    sw.c = (Customer) ois.readObject();
                    sw.ois = ois;
                    sw.oos = oos;
                    sw.socket = socket;
                    try
                    {
                        sw.start(window);
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
                    aw.oos = oos;
                    aw.ois = ois;
                    aw.socket = socket;
                    aw.Loginwindow=window;
                    aw.start(window);

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
        window = (Stage)name.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/Signup.fxml"));
        Parent root = loader.load();
        Signup controllers = loader.getController();
        window.setTitle("SignUp Window");
    socket = new Socket(ServerIP.getText(),Integer.parseInt(PortNo.getText()));

        System.out.println("Connected to server");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        controllers.socket = socket;
        controllers.objectOutputStream = oos;
        controllers.objectInputStream = ois;
        window.setScene(new Scene(root));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setWidth((primScreenBounds.getWidth()));
        window.setHeight((primScreenBounds.getHeight()));
        window.show();
    }

}
