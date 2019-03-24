package MainPackage.ServerFiles;

import MainPackage.*;

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
    Connection connection;
    ObjectInputStream objectInputStream = null;
    ObjectOutputStream objectOutputStream = null;
    int type;

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
                Thread.currentThread().interrupt();
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
                Thread.currentThread().interrupt();
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
                if (verifyLogin() == 1)
                {
                    Transaction t = new Authentication(true, "Successfull Login");
                    objectOutputStream.writeObject(t);
                    objectOutputStream.flush();
                    System.out.println("Logged In!!");
                        if(client.getType()==1)
                        {

                            CustomerHandler ch = new CustomerHandler(ClientSocket, (LoginData) client, connection, objectInputStream, objectOutputStream);
                            ch.handle();
                            Thread.currentThread().interrupt();
                        }
                        else if(client.getType()==0)
                        {
                            RetailerHandler rh = new RetailerHandler(ClientSocket,(LoginData)client,connection,objectInputStream,objectOutputStream);
                            rh.handle();
                            Thread.currentThread().interrupt();
                        }
                        else if(client.getType()==3)
                        {
                            AdminHandler ah = new AdminHandler(ClientSocket,(LoginData)client,connection,objectInputStream,objectOutputStream);
                            ah.handle();
                            Thread.currentThread().interrupt();
                        }
                    }
                    else
                    {
                            Transaction t=new Authentication(false,"Invalid username or password");
                            objectOutputStream.writeObject(t);
                            objectOutputStream.flush();
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
        PreparedStatement preStat = connection.prepareStatement(query);
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
}