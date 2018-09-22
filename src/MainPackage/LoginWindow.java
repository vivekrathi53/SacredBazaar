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
    Scene s1,s2;
    TextArea name,pass;
    Socket socket;
    Transaction Authentication;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(25);
        FileInputStream input = new FileInputStream("/home/dell/Desktop/FinalYearSeniors.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(200);
        imageView.setFitWidth(200);
        grid.setConstraints(imageView, 6, 12);
        name = new TextArea();
        name.setPromptText("Enter your username here");
        grid.setConstraints(name,6,13);
        pass = new TextArea();
        pass.setPromptText("Enter your password here");
        grid.setConstraints(pass,6,14);
        Button Customer = new Button("Log in as a Customer");
        Customer.setOnAction(e -> {
            try {
                Login(1);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        Button retailer = new Button("Log in as a retailer");
        retailer.setOnAction(e -> {
            try {
                Login(0);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        Button admin = new Button("Log in as an admin");
        admin.setOnAction(e -> {
            try {
                Login(3);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        Button signUp = new Button("SignUp");
        signUp.setOnAction(e ->
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
                Parent root = loader.load();
                Signup controller = loader.getController();
                window.setTitle("SignUp Window");
                socket = new Socket("192.168.219.1",8188);
                System.out.println("Connected to server");
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                controller.socket = socket;
                controller.objectOutputStream = oos;
                controller.objectInputStream = ois;
                controller.logwindow = this;
                window.setScene(new Scene(root,700,800));
                window.show();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        grid.setConstraints(signUp,7,5);
        grid.setConstraints(Customer,6,15);
        grid.setConstraints(retailer,6,16);
        grid.setConstraints(admin,6,17);

        window.setTitle("Log in Page");
        grid.getChildren().addAll(name,pass,Customer,retailer,admin,signUp,imageView);
        Scene s1= new Scene(grid);
        window.setScene(s1);
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        window.setWidth((primScreenBounds.getWidth()));
        window.setHeight((primScreenBounds.getHeight()));
        window.show();
    }
    private void Login(int type) throws IOException{
        socket = new Socket("127.0.0.1",8188);
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
                    System.out.println("Login Retalier Approved");

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



    public static void main(String[] args)
    {
        launch(args);
    }
}
