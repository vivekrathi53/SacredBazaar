package MainPackage.ServerFiles;

import AdminQueries.*;
import CustomerQueries.*;
import MainPackage.Customer;
import MainPackage.LoginData;
import MainPackage.LogoutClient;
import MainPackage.Product;

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
    Socket ClientSocket;
    LoginData clientLoginDetails;
    Connection connection;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    LoadCustomerDetails lcd;
    Customer c;
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
            Object transaction = ois.readObject();//query send by Admin Controller
            if(transaction instanceof LoadAdminDetails)
            {
                lad = (LoadAdminDetails) transaction;
                lad.connection = connection;
                oos.writeObject(lad.getDetails());
                oos.flush();
            }
            else if(transaction instanceof Customershow)
            {

                Customershow cs=(Customershow) transaction;
                cs.connection=connection;
                ArrayList<Customer> topcustomerlist=cs.show();
                oos.writeObject(topcustomerlist);
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
            else if(transaction instanceof LoadCustomerDetails)
            {
                LoadCustomerDetails lcd = (LoadCustomerDetails) transaction;
                lcd.connection = connection;
                oos.writeObject(lcd.getDetails());
                oos.flush();
            }
            else if(transaction instanceof ChangeCustomerDetails)
            {
                ChangeCustomerDetails ccd = (ChangeCustomerDetails) transaction;
                ccd.connection=connection;
                ccd.updateEntries();
                LoadCustomerDetails lcd = new LoadCustomerDetails();
                lcd.userName = ccd.client.getUserName();
                lcd.connection = connection;
                Customer c = lcd.getDetails();
                oos.writeObject(c);
                oos.flush();
            }
            else if(transaction instanceof GenerateGraph)
            {
                GenerateGraph gg=(GenerateGraph)  transaction;
                gg.connection=connection;
                oos.writeObject((Serializable)gg.generate());
                oos.flush();
            }
            else if(transaction instanceof LogoutClient)
            {
                return;
            }
        }

    }
}
