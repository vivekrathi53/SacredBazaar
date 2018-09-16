package sample;

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
        
    }
}
