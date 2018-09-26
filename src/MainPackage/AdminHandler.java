package MainPackage;

import AdminQueries.ChangeAdminDetails;
import AdminQueries.ChangeProduct;
import AdminQueries.LoadAdminDetails;
import AdminQueries.RemoveProduct;
import CustomerQueries.*;
import MainPackage.LoginData;
import MainPackage.LogoutClient;
import MainPackage.Product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminHandler
{
    Socket ClientSocket;
    LoginData clientLoginDetails;
    Connection connection;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public AdminHandler(Socket clientSocket, LoginData client, Connection connection, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
    {
        this.ClientSocket = clientSocket;
        clientLoginDetails=client;
        this.connection=connection;
        ois = objectInputStream;
        oos = objectOutputStream;
    }

    public void handle() throws IOException, ClassNotFoundException, SQLException {
        LoadAdminDetails lad = new LoadAdminDetails();
        lad.connection=connection;
        lad.UserName=clientLoginDetails.getUserName();
        oos.writeObject(lad.getDetails());
        oos.flush();
        while(true)
        {
            Object transaction = ois.readObject();//query send by Shop Window Controller
            if(transaction instanceof LoadAdminDetails)
            {
                lad = (LoadAdminDetails) transaction;
                lad.connection = connection;
                oos.writeObject(lad.getDetails());
                oos.flush();
            }
            else if(transaction instanceof ChangeAdminDetails)
            {
                ChangeAdminDetails cad = (ChangeAdminDetails) transaction ;
                cad.connection=connection;
                cad.updateEntries();
                oos.writeObject(lad.getDetails());
                oos.flush();
            }
            else if(transaction instanceof ChangeProduct)
            {
                ChangeProduct cp = (ChangeProduct) transaction;
                cp.connection = connection;
                cp.saveChanges();
            }
            else if(transaction instanceof SearchFor)
            {
                SearchFor sf = (SearchFor) transaction;
                sf.connection=connection;
                ArrayList<Product> prodList= sf.getReleventProducts();
                oos.writeObject(prodList);
                oos.flush();
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
