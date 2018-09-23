package MainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class LoginWindow extends Application {
    Stage window;
    Scene s2;
    Socket socket;
    FXMLLoader loader;
    LoginWindowController controller;
    Transaction Authentication;
    AnchorPane DisplayPane;
    int Type;
    @Override

    public void start(Stage primaryStage) throws Exception
    {
        loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
        DisplayPane  = (AnchorPane) loader.load();
        controller = loader.getController();
        primaryStage.setScene(new Scene(DisplayPane));
        window = primaryStage;
        System.out.println(Type);
        controller.lw=this;

        //controller.socket = this.socket;
        //controller.ois = this.ois;
        //controller.oos = this.oos;
        //FileInputStream input = new FileInputStream("/home/dell/Desktop/FinalYearSeniors.jpg");

        window.setTitle("Log in Page");
        window.show();
    }

    public void Login(int type) throws IOException
    {
        socket = new Socket("127.0.0.1",8188);
        System.out.println("Connected to server");
        LoginData data = new LoginData(controller.name.getText(),controller.pass.getText(),type);
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
                    System.out.println("Login Retalier Approved");
                    RetailerWindow rw = new RetailerWindow();
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
                    try
                    {
                        sw.start(window);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                else if(type==3);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root = loader.load();
        Signup controller = loader.getController();
        window.setTitle("SignUp Window");
        socket = new Socket("127.0.0.1",8188);
        System.out.println("Connected to server");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        controller.socket = socket;
        controller.objectOutputStream = oos;
        controller.objectInputStream = ois;
        controller.logwindow = this;
        window.setScene(new Scene(root,700,800));
        window.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
