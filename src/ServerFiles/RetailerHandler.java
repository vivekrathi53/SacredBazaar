package ServerFiles;

import ClientFiles.AdminFiles.AdminQueries.ChangeProduct;
import ClientFiles.AdminFiles.AdminQueries.GenerateGraph;
import ClientFiles.AdminFiles.AdminQueries.RemoveProduct;
import ClientFiles.LoginData;
import ClientFiles.LogoutClient;
import ClientFiles.RetailerFiles.Retailer;
import ClientFiles.RetailerFiles.RetailerQueries.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;

public class RetailerHandler
{
    private Socket ClientSocket;
    private LoginData clientLoginDetails;
    private Connection connection;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    public RetailerHandler(Socket clientSocket, LoginData client, Connection connection, ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream)
    {
        this.setClientSocket(clientSocket);
        setClientLoginDetails(client);
        this.setConnection(connection);
        setOis(objectInputStream);
        setOos(objectOutputStream);
    }

    public void handle() throws IOException, ClassNotFoundException, SQLException
    {
        LoadRetailerDetails lrd = new LoadRetailerDetails(getConnection(), getClientLoginDetails().getUserName());
        getOos().writeObject(lrd.getDetails());
        getOos().flush();
        System.out.println("Send the details");
        while(true)
        {
            Object transaction = getOis().readObject();
            System.out.println("Recieving Request");
            if(transaction instanceof LoadRetailerDetails)
            {
                lrd = (LoadRetailerDetails) transaction;
                lrd.connection= getConnection();
                lrd.userName= getClientLoginDetails().getUserName();
                getOos().writeObject(lrd.getDetails());
                getOos().flush();
            }
            //else if(transaction instanceof G)
            else if(transaction instanceof ChangeRetailerDetails)
            {
                ChangeRetailerDetails ccd = (ChangeRetailerDetails) transaction;
                ccd.connection= getConnection();
                ccd.updateEntries();
                Retailer r = lrd.getDetails();
                getOos().writeObject(r);
                getOos().flush();
            }
            else if(transaction instanceof AddProductToSell)
            {
                AddProductToSell aps = (AddProductToSell) transaction;
                aps.connection= getConnection();
                aps.add();
                System.out.println("Added");
            }
            else if(transaction instanceof GetRetailerProducts)
            {
                GetRetailerProducts grp = (GetRetailerProducts) transaction;
                grp.connection= getConnection();
                getOos().writeObject(grp.getCustomerProduct());
                getOos().flush();
            }
            else if(transaction instanceof LoadNotifications)
            {
                LoadNotifications ln = (LoadNotifications) transaction;
                ln.connection = getConnection();
                getOos().writeObject(ln.load());
                getOos().flush();
            }
            else if(transaction instanceof DeliveredProduct)
            {
                DeliveredProduct dp = (DeliveredProduct) transaction;
                dp.connection = getConnection();
                dp.mark();
            }
            else if(transaction instanceof GenerateGraph)
            {
                GenerateGraph gg = (GenerateGraph) transaction;
                gg.connection= getConnection();
                getOos().writeObject(gg.generate());
            }
            else if(transaction instanceof ChangeProduct)
            {
                ChangeProduct cp = (ChangeProduct) transaction;
                cp.connection = getConnection();
                cp.saveChanges();
            }
            else if(transaction instanceof RemoveProduct)
            {
                RemoveProduct rp = (RemoveProduct) transaction;
                rp.connection = getConnection();
                rp.remove();
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
