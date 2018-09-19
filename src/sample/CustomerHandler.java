package sample;

import Transactions.BuyProduct;
import Transactions.ChangeCustomerDetails;
import Transactions.LoadCustomerDetails;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomerHandler 
{
    Socket ClientSocket;
    LoginData clientLoginDetails;
    Connection connection;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public CustomerHandler(Socket clientSocket, LoginData client, Connection connection, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
    {
        this.ClientSocket = clientSocket;
        clientLoginDetails=client;
        this.connection=connection;
        ois = objectInputStream;
        oos = objectOutputStream;
    }

    public void handle() throws IOException, ClassNotFoundException, SQLException {
        LoadCustomerDetails lcd = new LoadCustomerDetails(connection);
        oos.writeObject(lcd.getDetails(clientLoginDetails.getUserName()));
        oos.flush();
        Object transaction = ois.readObject();
        if(transaction instanceof LoadCustomerDetails)
        {
            lcd = (LoadCustomerDetails) ois.readObject();
            lcd.getDetails(clientLoginDetails.getUserName());
        }
        else if(transaction instanceof ChangeCustomerDetails)
        {
            ChangeCustomerDetails ccd = (ChangeCustomerDetails) transaction;
            ccd.connection=connection;
            ccd.updateEntries();
            Customer c = lcd.getDetails(clientLoginDetails.getUserName());
        }
    }
}
