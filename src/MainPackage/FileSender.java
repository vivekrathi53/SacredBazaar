package MainPackage;

import java.io.*;
import java.net.Socket;

public class FileSender
{
    public static void FileTransfer(Socket destination, File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[100000];
        long len = (file.length()/100000);
        for(int i=0;i<len;i++)
        {
            fis.read(data);
            OutputStream os = destination.getOutputStream();
            os.write(data);
        }
    }

}
