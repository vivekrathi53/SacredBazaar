package sample;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ClientAuthenticationHandler implements Runnable
{
    Thread thread;
    private Socket ClientSocket;
    private Client client;
    Connection connection;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;
    public ClientAuthenticationHandler(Socket clientSocket)
    {
        ClientSocket = clientSocket;
        System.out.println("Hii");
    }

    @Override
    public void run()
    {

        try
        {
            objectInputStream = new ObjectInputStream(ClientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(ClientSocket.getOutputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        System.out.println("Reading ObjectInputStream");
        Object obj=null;
        try {
            obj = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Read the object");
        if(obj instanceof Retailer)
        {
            client = (Retailer) obj;
            try {
                signupretailer();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(obj instanceof Customer)
        {
            client = (Customer) obj;
            try {
                signupcustomer();


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(obj instanceof LoginData)
        {
            client = (LoginData) obj;
            try {
                System.out.println("Calling verifyLogin()");
                verifyLogin();
                Transaction t = new Authentication(true,"Successfull Login");
                objectOutputStream.writeObject(t);
                objectOutputStream.flush();
                System.out.println("Logged In!!");
                CustomerHandler ch = new CustomerHandler(ClientSocket, (LoginData) client, connection, objectInputStream,objectOutputStream);
                ch.handle();
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
         Retailer client = (Retailer) this.client;
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
        connection = DriverManager.getConnection(url, "root", "password");
        String q="INSERT INTO RetailerTable VALUES('"+(firstname)+"','"+(lastname)+"','"+(username)+"','"+(password)+"','"+(Address)+"','"+(MobileNo)+"','"+(PinNo)+"','"+(Email)+"')";
        PreparedStatement preStat = connection.prepareStatement(q);
        preStat.executeUpdate();
        thread.stop();
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
        Customer client = (Customer) this.client;
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
        connection = DriverManager.getConnection(url, "root", "password");
        String q="INSERT INTO CustomerTable VALUES('"+(firstname)+"','"+(lastname)+"','"+(username)+"','"+(password)+"','"+(Address)+"','"+(MobileNo)+"','"+(PinNo)+"','"+(email)+"','0')";
        PreparedStatement preStat = connection.prepareStatement(q);
        preStat.executeUpdate();
        thread.stop();
    }

    private void verifyLogin()throws ClassNotFoundException, SQLException
    {
        String query = null;
        String CheckPassword=null;
        String user=null;
        String password = null;
        int type = 0;
        try
        {
            user=client.getUserName();
            password=client.getPassword();
            type=client.getType();
        }
        catch (java.lang.Exception exception)
        {
            exception.printStackTrace();
        }
        System.out.println("Hii");
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connected to database");
        String url = "jdbc:mysql://localhost:3306/SacredBazzar";
        connection = DriverManager.getConnection(url, "root", "password");
        if(type==0)
        {
            query="SELECT Password FROM RetailerTable WHERE USERNAME='"+(user)+"'";
        }
        else if(type==1)
        {
            query="SELECT Password FROM CustomerTable WHERE UserName = '"+(user)+"'";
        }
        else if(type==3)
        {
            query = "SELECT Password FROM LOGINADMIN WHERE USERNAME='"+(user)+"'";
        }
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        rs.next();
        CheckPassword=rs.getString("Password");
        if(CheckPassword.equals(password))
        {
            return ;
        }
        else
        {
        }
    }
}