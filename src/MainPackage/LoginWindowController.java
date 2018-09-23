package MainPackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.io.IOException;

public class LoginWindowController
{
    @FXML
    TextField name;
    @FXML
    TextField pass;
    @FXML
    Button customerButton;
    @FXML
    Button retalierButton;
    @FXML
    Button adminButton;
    @FXML
    Button signUpButton;
    String Name;
    String Pass;
    int type;
    LoginWindow lw;
    LoginWindowController()
    {

    }

    public void logincustomer()
    {
        try {
            lw.Login(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loginretalier()
    {
        type=0;
    }
    public void loginadmin()
    {
        type=3;
    }
    public void signUp()
    {
        type=4;
    }
    //FXMLLoader loader= loader = new FXMLLoader(getClass().getResource("LoginWindow.fxml"));
}
