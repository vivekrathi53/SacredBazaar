package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {
    Stage window;
    Scene s1,s2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(25);
        FileInputStream input = new FileInputStream("C:\\Users\\Public\\Pictures\\tick.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        grid.setConstraints(imageView, 6, 12);
        TextField name = new TextField();
        name.setPromptText("Enter your username here");
        grid.setConstraints(name,6,13);
        TextField pass = new TextField();
        pass.setPromptText("Enter your password here");
        grid.setConstraints(pass,6,14);
        Button user = new Button("Log in as a user");
        Button retailer = new Button("Log in as a retailer");
        Button admin = new Button("Log in as an admin");
        Button signUp = new Button("SignUp");
        grid.setConstraints(signUp,7,5);
        grid.setConstraints(user,6,15);
        grid.setConstraints(retailer,6,16);
        grid.setConstraints(admin,6,17);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        window.setTitle("Log in Page");
        grid.getChildren().addAll(name,pass,user,retailer,admin,signUp,imageView);
        Scene s1= new Scene(grid,700,800);
        window.setScene(s1);
        window.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
