package ClientFiles;

import ClientFiles.CustomerFiles.Customer;
import ClientFiles.RetailerFiles.Retailer;
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
    private ObjectInputStream objectInputStream;
    private int type;
    private Socket socket;
    private ObjectOutputStream objectOutputStream;
    @FXML
    private Button SignInButton;
    private LoginWindow logwindow;
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
    private TextArea PasswordTextArea;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String address;
    private String mobileNo;
    private String pinNo;
    private String email;
    @FXML
    private void signupcustomer()
    {
        setType(0);
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
        setType(1);
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
        setType(3);
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

        setLogwindow(new LoginWindow());
        if(getType() ==0)
        {
            Customer data = new Customer(getFirstName(), getLastName(), getUserName(), getPasswordTextArea().getText(), getAddress(), getMobileNo(), getPinNo(), getEmail(),null,null,null);
            getObjectOutputStream().writeObject(data);
            getObjectOutputStream().flush();
            getLogwindow().start((Stage) getSignInButton().getScene().getWindow());
        }
        else if(getType() ==1)
        {
            Retailer data =new Retailer(getFirstName(), getLastName(), getUserName(), getPasswordTextArea().getText(), getAddress(), getMobileNo(), getPinNo(), getEmail(),null,null,0);
            getObjectOutputStream().writeObject(data);
            getObjectOutputStream().flush();
            getLogwindow().start((Stage) getSignInButton().getScene().getWindow());

        }
        // else{ }

    }

    private void display()
    {
        setFirstName(getNamefirst().getText());
        setUserName(getNameuser().getText());
        setLastName(getNamelast().getText());
        setMobileNo(getNomobile().getText());
        setPinNo(getCodepin().getText());
        setEmail(getIdemail().getText());
        setAddress(getUseraddress().getText());
        setPassword(getPasswordTextArea().getText());
        System.out.println(getFirstName());
    }

    public void StartLogin()
    {
        LoginWindow lw = new LoginWindow();
        try {
            lw.start((Stage) getSignInButton().getScene().getWindow());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public Button getSignInButton() {
        return SignInButton;
    }

    public void setSignInButton(Button signInButton) {
        SignInButton = signInButton;
    }

    public LoginWindow getLogwindow() {
        return logwindow;
    }

    public void setLogwindow(LoginWindow logwindow) {
        this.logwindow = logwindow;
    }

    public TextArea getNamefirst() {
        return namefirst;
    }

    public void setNamefirst(TextArea namefirst) {
        this.namefirst = namefirst;
    }

    public TextArea getNameuser() {
        return nameuser;
    }

    public void setNameuser(TextArea nameuser) {
        this.nameuser = nameuser;
    }

    public TextArea getNamelast() {
        return namelast;
    }

    public void setNamelast(TextArea namelast) {
        this.namelast = namelast;
    }

    public TextArea getNomobile() {
        return nomobile;
    }

    public void setNomobile(TextArea nomobile) {
        this.nomobile = nomobile;
    }

    public TextArea getCodepin() {
        return codepin;
    }

    public void setCodepin(TextArea codepin) {
        this.codepin = codepin;
    }

    public TextArea getIdemail() {
        return idemail;
    }

    public void setIdemail(TextArea idemail) {
        this.idemail = idemail;
    }

    public TextArea getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(TextArea useraddress) {
        this.useraddress = useraddress;
    }

    public TextArea getPasswordTextArea() {
        return PasswordTextArea;
    }

    public void setPasswordTextArea(TextArea password) {
        PasswordTextArea = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPinNo() {
        return pinNo;
    }

    public void setPinNo(String pinNo) {
        this.pinNo = pinNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
