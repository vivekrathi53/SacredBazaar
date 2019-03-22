package MainPackage.ServerFiles;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SacredServer
{

    public static void main(String[] args) throws IOException {
        ArrayList<Socket> ActiveUsers = new ArrayList<>();
        ArrayList<ClientAuthenticationHandler> OnlineClients = new ArrayList<>();
        ServerSocket serverSocket = new ServerSocket(8188);
        while(true)
        {
            Socket soc = serverSocket.accept();
            ActiveUsers.add(soc);//list of socket
            ClientAuthenticationHandler cah= new ClientAuthenticationHandler(soc);
            Thread t = new Thread(cah);
            t.start();
            OnlineClients.add(cah);//list of ClientAuthenticationHandler
            System.out.println("New Client Connected");
        }
    }

}
