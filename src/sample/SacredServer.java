package sample;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SacredServer
{

    public static void main(String[] args) throws IOException {
        ArrayList<Socket> ActiveUsers = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8281);
        while(true)
        {
            Socket soc = serverSocket.accept();
            ActiveUsers.add(soc);
            Thread t = new Thread(new ClientHandler(soc));
            System.out.println("New Client Connected");
        }
    }

}
