package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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

        });
        grid.setConstraints(signUp,7,5);
        grid.setConstraints(Customer,6,15);
        grid.setConstraints(retailer,6,16);
        grid.setConstraints(admin,6,17);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Log in Page");
        grid.getChildren().addAll(name,pass,Customer,retailer,admin,signUp,imageView);
        Scene s1= new Scene(grid,700,800);
        window.setScene(s1);
        window.show();
    }

    private void Login(int type) throws IOException
    {
        Socket socket = new Socket("127.0.0.1",8281);
        System.out.println("Connected to server");
        LoginData data = new LoginData(name.getText(),pass.getText(),type);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(data);
        oos.flush();
        oos.close();
    }



    public static void main(String[] args)
    {
        launch(args);
    }
}
