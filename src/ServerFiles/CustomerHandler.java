package ServerFiles;

import ClientFiles.CustomerFiles.CustomerQueries.*;
import ClientFiles.CustomerFiles.Customer;
import ClientFiles.LoginData;
import ClientFiles.LogoutClient;
import ClientFiles.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerHandler 
{
    private Socket ClientSocket;
    private LoginData clientLoginDetails;
    private Connection connection;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public CustomerHandler(Socket clientSocket, LoginData client, Connection connection, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
    {
        this.setClientSocket(clientSocket);
        setClientLoginDetails(client);
        this.setConnection(connection);
        setOis(objectInputStream);
        setOos(objectOutputStream);
    }

    public void handle() throws IOException, ClassNotFoundException, SQLException {
        LoadCustomerDetails lcd = new LoadCustomerDetails();
        lcd.userName = getClientLoginDetails().getUserName();
        lcd.connection = getConnection();
        getOos().writeObject(lcd.getDetails());
        getOos().flush();int a;
        while(true)
        {
            Object transaction = getOis().readObject();//query send by Shop Window Controller
            if(transaction instanceof LoadCustomerDetails)
            {
                lcd = (LoadCustomerDetails) transaction;
                lcd.connection = getConnection();
                lcd.userName = getClientLoginDetails().getUserName();
                getOos().writeObject(lcd.getDetails());
            }
            else if(transaction instanceof TotalSpending)
            {
                TotalSpending ts=(TotalSpending) transaction;
                ts.connection= getConnection();
                a=ts.count();
                lcd.userName = getClientLoginDetails().getUserName();
                getOos().writeObject(a);
                getOos().flush();
            }
            else if(transaction instanceof ChangeCustomerDetails)
            {
                ChangeCustomerDetails ccd = (ChangeCustomerDetails) transaction;
                ccd.connection= getConnection();
                ccd.updateEntries();
                lcd.userName = getClientLoginDetails().getUserName();
                Customer c = lcd.getDetails();
                getOos().writeObject(c);
                getOos().flush();
            }
            else if(transaction instanceof SearchFor)
            {
                SearchFor sf = (SearchFor) transaction;
                sf.connection= getConnection();
                ArrayList<Product> prodList= sf.getReleventProducts();
                getOos().writeObject(prodList);
                getOos().flush();
            }
            else if(transaction instanceof BuyProduct)
            {
                BuyProduct bp = (BuyProduct) transaction;
                bp.connection= getConnection();
                bp.buyProduct();
                System.out.println("Done!!");
            }
            else if(transaction instanceof AddProductTo)
            {
                AddProductTo bp = (AddProductTo) transaction;
                bp.connection= getConnection();
                bp.buyProduct();
                System.out.println("Done!!");
            }
            else if(transaction instanceof GetTrendingList)
            {
                GetTrendingList gtl = (GetTrendingList) transaction;
                gtl.connection= getConnection();
                getOos().writeObject(gtl.getList());
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
}
