package MainPackage;

import RetailerQueries.AddProductToSell;
import RetailerQueries.ChangeRetailerDetails;
import RetailerQueries.GetRetailerProducts;
import RetailerQueries.LoadRetailerDetails;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class RetailerHandler
{
    Socket ClientSocket;
    LoginData clientLoginDetails;
    Connection connection;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public RetailerHandler(Socket clientSocket, LoginData client, Connection connection, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
    {
        this.ClientSocket = clientSocket;
        clientLoginDetails=client;
        this.connection=connection;
        ois = objectInputStream;
        oos = objectOutputStream;
    }

    public void handle() throws IOException, ClassNotFoundException, SQLException
    {
        LoadRetailerDetails lrd = new LoadRetailerDetails(connection, clientLoginDetails.getUserName());
        oos.writeObject(lrd.getDetails());
        oos.flush();
        System.out.println("Send the details");
        while(true)
        {
            Object transaction = ois.readObject();
            System.out.println("Recieving Request");
            if(transaction instanceof LoadRetailerDetails)
            {
                lrd = (LoadRetailerDetails) transaction;
                lrd.connection=connection;
                lrd.userName=clientLoginDetails.getUserName();
                oos.writeObject(lrd.getDetails());
                oos.flush();
            }
            else if(transaction instanceof ChangeRetailerDetails)
            {
                ChangeRetailerDetails ccd = (ChangeRetailerDetails) transaction;
                ccd.connection=connection;
                ccd.updateEntries();
                Retailer r = lrd.getDetails();
                oos.writeObject(r);
                oos.flush();
            }
            else if(transaction instanceof AddProductToSell)
            {
                AddProductToSell aps = (AddProductToSell) transaction;
                aps.connection=connection;
                aps.add();
                System.out.println("Added");
            }
            else if(transaction instanceof GetRetailerProducts)
            {
                GetRetailerProducts grp = (GetRetailerProducts) transaction;
                grp.connection=connection;
                oos.writeObject(grp.getCustomerProduct());
                oos.flush();
            }
        }

    }
}
