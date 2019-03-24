package MainPackage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static javax.swing.UIManager.getString;
import static sun.management.Agent.getText;

public class Signup
{
    public ObjectInputStream objectInputStream;
    public int type;
    public Socket socket;
    public ObjectOutputStream objectOutputStream;
    @FXML
    public Button SignInButton;
    public LoginWindow logwindow;
    @FXML
    private TextArea namefirst;
    @FXML
    private TextArea nameuser;
    @FXML
    private TextArea namelast;
    @FXML
    private TextArea nomobile;
    @FXML
    private TextArea codepin;
    @FXML
    private TextArea idemail;
    @FXML
    private TextArea useraddress;
    @FXML
    private TextArea Password;
    String firstName;
    String lastName;
    String userName;
    String password;
    String address;
    String mobileNo;
    String pinNo;
    String email;
    @FXML
    private void signupcustomer()
    {
        type=0;
        try {
            sign();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void signupretalier() {
        type=1;
        try {
            sign();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void signupadmin()
    {
        type=3;
        try {
            sign();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void sign() throws Exception
    {
        display();

        logwindow = new LoginWindow();
        if(type==0)
        {
            Customer data = new Customer(firstName,lastName,userName,password,address,mobileNo,pinNo,email,null,null,null);
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            logwindow.start((Stage)SignInButton.getScene().getWindow());
        }
        else if(type==1)
        {
            Retailer data =new Retailer(firstName,lastName,userName,password,address,mobileNo,pinNo,email,null,null,0);
            objectOutputStream.writeObject(data);
            objectOutputStream.flush();
            logwindow.start((Stage)SignInButton.getScene().getWindow());

        }
        // else{ }

    }

    private void display()
    {
        firstName=namefirst.getText();
        userName=nameuser.getText();
        lastName=namelast.getText();
        mobileNo=nomobile.getText();
        pinNo=codepin.getText();
        email=idemail.getText();
        address=useraddress.getText();
        password=Password.getText();
        System.out.println(firstName);
    }

    public void StartLogin()
    {
        LoginWindow lw = new LoginWindow();
        try {
            lw.start((Stage)SignInButton.getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
