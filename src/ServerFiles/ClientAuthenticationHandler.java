package ServerFiles;

import ClientFiles.Authentication;
import ClientFiles.Client;
import ClientFiles.CustomerFiles.Customer;
import ClientFiles.LoginData;
import ClientFiles.RetailerFiles.Retailer;
import ClientFiles.Transaction;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientAuthenticationHandler implements Runnable
{
    private Socket ClientSocket;
    private Client client;
    private Connection connection;
    private ObjectInputStream objectInputStream = null;
    private ObjectOutputStream objectOutputStream = null;
    private int type;

    public ClientAuthenticationHandler(Socket clientSocket)
    {
        setClientSocket(clientSocket);
        System.out.println("Hii");
    }

    @Override
    public void run()
    {
        try
        {
            setObjectInputStream(new ObjectInputStream(getClientSocket().getInputStream()));
            setObjectOutputStream(new ObjectOutputStream(getClientSocket().getOutputStream()));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Reading ObjectInputStream");
        Object obj=null;
        try {
            obj = getObjectInputStream().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Read the object");
        if(obj instanceof Retailer)
        {
            setClient((Retailer) obj);
            try {
                signupretailer();
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(obj instanceof Customer)
        {
            setClient((Customer) obj);
            try {
                signupcustomer();
                Thread.currentThread().interrupt();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(obj instanceof LoginData)
        {
            setClient((LoginData) obj);
            try {
                if (verifyLogin() == 1)
                {
                    Transaction t = new Authentication(true, "Successfull Login");
                    getObjectOutputStream().writeObject(t);
                    getObjectOutputStream().flush();
                    System.out.println("Logged In!!");
                        if(getClient().getType()==1)
                        {

                            CustomerHandler ch = new CustomerHandler(getClientSocket(), (LoginData) getClient(), getConnection(), getObjectInputStream(), getObjectOutputStream());
                            ch.handle();
                            Thread.currentThread().interrupt();
                        }
                        else if(getClient().getType()==0)
                        {
                            RetailerHandler rh = new RetailerHandler(getClientSocket(),(LoginData) getClient(), getConnection(), getObjectInputStream(), getObjectOutputStream());
                            rh.handle();
                            Thread.currentThread().interrupt();
                        }
                        else if(getClient().getType()==3)
                        {
                            AdminHandler ah = new AdminHandler(getClientSocket(),(LoginData) getClient(), getConnection(), getObjectInputStream(), getObjectOutputStream());
                            ah.handle();
                            Thread.currentThread().interrupt();
                        }
                    }
                    else
                    {
                            Transaction t=new Authentication(false,"Invalid username or password");
                            getObjectOutputStream().writeObject(t);
                            getObjectOutputStream().flush();
                    }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private  void  signupretailer() throws SQLException, ClassNotFoundException {
        String firstname=null;
        String lastname=null;
        String username=null;
        String password=null;
        String Address=null;
        String MobileNo=null;
        String PinNo=null;
        String Email=null;
         Retailer client = (Retailer) this.getClient();
        try
        {
            firstname=client.getFirstName();
            lastname=client.getLastName();
            username=client.getUserName();
            password=client.getPassword();
            Address=client.getAddress();
            MobileNo=client.getMobileNo();
            PinNo=client.getPinNo();
            Email=client.getEmail();
        }
        catch (java.lang.Exception exception)
        {
            exception.printStackTrace();
        }
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/SacredBazzar";
        setConnection(DriverManager.getConnection(url, "root", "password"));
        String q="INSERT INTO RetailerTable VALUES('"+(firstname)+"','"+(lastname)+"','"+(username)+"','"+(password)+"','"+(Address)+"','"+(MobileNo)+"','"+(PinNo)+"','"+(Email)+"')";
        PreparedStatement preStat = getConnection().prepareStatement(q);
        preStat.executeUpdate();
    }

    private void signupcustomer() throws SQLException, ClassNotFoundException {
        String firstname=null;
        String lastname=null;
        String username=null;
        String password=null;
        String Address=null;
        String MobileNo=null;
        String PinNo=null;
        String email=null;
        Customer client = (Customer) this.getClient();
        try
        {
            firstname=client.getFirstName();
            lastname=client.getLastName();
            username=client.getUserName();
            password=client.getPassword();
            Address=client.getAddress();
            MobileNo=client.getMobileNo();
            PinNo=client.getPinNo();
            email=client.getEmail();
        }
        catch (java.lang.Exception exception)
        {
            exception.printStackTrace();
        }
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/SacredBazzar";
        setConnection(DriverManager.getConnection(url, "root", "password"));
        String q="INSERT INTO CustomerTable VALUES('"+(firstname)+"','"+(lastname)+"','"+(username)+"','"+(password)+"','"+(Address)+"','"+(MobileNo)+"','"+(PinNo)+"','"+(email)+"','0')";
        PreparedStatement preStat = getConnection().prepareStatement(q);
        preStat.executeUpdate();
    }


    private int verifyLogin() throws ClassNotFoundException, SQLException
    {
        String query = null;
        String CheckPassword=null;
        String user=null;
        String password = null;
        int type = 0;
        try
        {
            user= getClient().getUserName();
            password= getClient().getPassword();
            type= getClient().getType();
        }
        catch (java.lang.Exception exception)
        {
            exception.printStackTrace();
        }
        System.out.println("Hii");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connected to database");
        String url = "jdbc:mysql://localhost:3306/SacredBazzar";

        setConnection(DriverManager.getConnection(url, "root", "password"));

        if(type==0)
        {
            query="SELECT Password FROM RetailerTable WHERE UserName='"+(user)+"'";
        }
        else if(type==1)
        {
            query="SELECT Password FROM CustomerTable WHERE UserName = '"+(user)+"'";
        }
        else if(type==3)
        {
            query = "SELECT Password FROM AdminTable WHERE UserName='"+(user)+"'";
        }
        PreparedStatement preStat = getConnection().prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        if(rs.next())
        {
            CheckPassword=rs.getString("Password");
            if(CheckPassword.equals(password))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        else return 0;
    }

    public Socket getClientSocket() {
        return ClientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        ClientSocket = clientSocket;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public void setObjectInputStream(ObjectInputStream objectInputStream) {
        this.objectInputStream = objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}