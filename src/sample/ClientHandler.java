package sample;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Client, Runnable
{
    private Socket ClientSocket;
    private Client client;
    public ClientHandler(Socket clientSocket)
    {
        ClientSocket = clientSocket;
    }

    @Override
    public void run()
    {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try
        {
            objectInputStream = new ObjectInputStream(ClientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(ClientSocket.getOutputStream());
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Object obj=null;
        try {
            obj = objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(obj instanceof Retailer)
        {
            client = (Retailer) obj;
        }
        else if(obj instanceof Customer)
        {
            client = (Customer) obj;
        }
        else if(obj instanceof LoginData)
        {
            client = (LoginData) obj;
            verifyLogin();
        }
    }

    private void verifyLogin()
    {

    }
}
