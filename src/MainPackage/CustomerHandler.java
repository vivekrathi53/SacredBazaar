package MainPackage;

import CustomerQueries.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
        while(true)
        {
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
            else if(transaction instanceof SearchFor)
            {
                SearchFor sf = (SearchFor) transaction;
                sf.connection=connection;
                ArrayList<Product> prodList= sf.getReleventProducts();
                oos.writeObject(prodList);
                oos.flush();
            }
            else if(transaction instanceof BuyProduct)
            {
                BuyProduct bp = (BuyProduct) transaction;
                bp.connection=connection;
                bp.buyProduct();
                System.out.println("Done!!");
            }
            else if(transaction instanceof AddProductTo)
            {
                AddProductTo bp = (AddProductTo) transaction;
                bp.connection=connection;
                bp.buyProduct();
                System.out.println("Done!!");
            }
            else if(transaction instanceof GetTrendingList)
            {
                GetTrendingList gtl = (GetTrendingList) transaction;
                gtl.connection=connection;
                oos.writeObject(gtl.getList());
                oos.flush();
            }
        }

    }
}
