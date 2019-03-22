package MainPackage.ServerFiles;

import AdminQueries.ChangeProduct;
import AdminQueries.GenerateGraph;
import AdminQueries.RemoveProduct;
import MainPackage.LoginData;
import MainPackage.LogoutClient;
import MainPackage.Retailer;
import RetailerQueries.*;

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
            //else if(transaction instanceof G)
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
            else if(transaction instanceof LoadNotifications)
            {
                LoadNotifications ln = (LoadNotifications) transaction;
                ln.connection = connection;
                oos.writeObject(ln.load());
                oos.flush();
            }
            else if(transaction instanceof DeliveredProduct)
            {
                DeliveredProduct dp = (DeliveredProduct) transaction;
                dp.connection = connection;
                dp.mark();
            }
            else if(transaction instanceof GenerateGraph)
            {
                GenerateGraph gg = (GenerateGraph) transaction;
                gg.connection=connection;
                oos.writeObject(gg.generate());
            }
            else if(transaction instanceof ChangeProduct)
            {
                ChangeProduct cp = (ChangeProduct) transaction;
                cp.connection = connection;
                cp.saveChanges();
            }
            else if(transaction instanceof RemoveProduct)
            {
                RemoveProduct rp = (RemoveProduct) transaction;
                rp.connection = connection;
                rp.remove();
            }
            else if(transaction instanceof LogoutClient)
            {
                return;
            }
        }

    }
}
