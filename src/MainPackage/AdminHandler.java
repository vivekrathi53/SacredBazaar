package MainPackage;

import AdminQueries.ChangeAdminDetails;
import AdminQueries.LoadAdminDetails;
import CustomerQueries.*;

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
            else if(transaction instanceof LogoutClient)
            {
                return;
            }
        }

    }
}
