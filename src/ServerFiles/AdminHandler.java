package ServerFiles;

import ClientFiles.AdminFiles.AdminQueries.*;
import ClientFiles.CustomerFiles.CustomerQueries.*;
import ClientFiles.CustomerFiles.Customer;
import ClientFiles.LoginData;
import ClientFiles.LogoutClient;
import ClientFiles.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminHandler
{
    private Socket ClientSocket;
    private LoginData clientLoginDetails;
    private Connection connection;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private LoadCustomerDetails lcd;
    private Customer c;
    public AdminHandler(Socket clientSocket, LoginData client, Connection connection, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
    {
        this.setClientSocket(clientSocket);
        setClientLoginDetails(client);
        this.setConnection(connection);
        setOis(objectInputStream);
        setOos(objectOutputStream);
    }

    public void handle() throws IOException, ClassNotFoundException, SQLException {
        LoadAdminDetails lad = new LoadAdminDetails();
        lad.connection= getConnection();
        lad.UserName= getClientLoginDetails().getUserName();
        getOos().writeObject(lad.getDetails());
        getOos().flush();
        while(true)
        {
            Object transaction = getOis().readObject();//query send by Admin Controller
            if(transaction instanceof LoadAdminDetails)
            {
                lad = (LoadAdminDetails) transaction;
                lad.connection = getConnection();
                getOos().writeObject(lad.getDetails());
                getOos().flush();
            }
            else if(transaction instanceof Customershow)
            {

                Customershow cs=(Customershow) transaction;
                cs.connection= getConnection();
                ArrayList<Customer> topcustomerlist=cs.show();
                getOos().writeObject(topcustomerlist);
                getOos().flush();
            }
            else if(transaction instanceof ChangeAdminDetails)
            {
                ChangeAdminDetails cad = (ChangeAdminDetails) transaction ;
                cad.connection= getConnection();
                cad.updateEntries();
                getOos().writeObject(lad.getDetails());
                getOos().flush();
            }
            else if(transaction instanceof ChangeProduct)
            {
                ChangeProduct cp = (ChangeProduct) transaction;
                cp.connection = getConnection();
                cp.saveChanges();
            }
            else if(transaction instanceof SearchFor)
            {
                SearchFor sf = (SearchFor) transaction;
                sf.connection= getConnection();
                ArrayList<Product> prodList= sf.getReleventProducts();
                getOos().writeObject(prodList);
                getOos().flush();
            }
            else if(transaction instanceof RemoveProduct)
            {
                RemoveProduct rp = (RemoveProduct) transaction;
                rp.connection = getConnection();
                rp.remove();
            }
            else if(transaction instanceof LoadCustomerDetails)
            {
                LoadCustomerDetails lcd = (LoadCustomerDetails) transaction;
                lcd.connection = getConnection();
                getOos().writeObject(lcd.getDetails());
                getOos().flush();
            }
            else if(transaction instanceof ChangeCustomerDetails)
            {
                ChangeCustomerDetails ccd = (ChangeCustomerDetails) transaction;
                ccd.connection= getConnection();
                ccd.updateEntries();
                LoadCustomerDetails lcd = new LoadCustomerDetails();
                lcd.userName = ccd.client.getUserName();
                lcd.connection = getConnection();
                Customer c = lcd.getDetails();
                getOos().writeObject(c);
                getOos().flush();
            }
            else if(transaction instanceof GenerateGraph)
            {
                GenerateGraph gg=(GenerateGraph)  transaction;
                gg.connection= getConnection();
                getOos().writeObject((Serializable)gg.generate());
                getOos().flush();
            }
            else if(transaction instanceof LogoutClient)
            {
                return;
            }
        }

    }

    public Socket getClientSocket() {
        return ClientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        ClientSocket = clientSocket;
    }

    public LoginData getClientLoginDetails() {
        return clientLoginDetails;
    }

    public void setClientLoginDetails(LoginData clientLoginDetails) {
        this.clientLoginDetails = clientLoginDetails;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public LoadCustomerDetails getLcd() {
        return lcd;
    }

    public void setLcd(LoadCustomerDetails lcd) {
        this.lcd = lcd;
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }
}
