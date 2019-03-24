package MainPackage;

import java.io.*;
import java.net.Socket;

public class FileReciever
{
    public static void FileReciever(Socket s,String location,long size) throws IOException {
        byte[] file = new byte[100000];
        FileOutputStream fos = new FileOutputStream(location);
        long len = size/100000;
        for(int i=0;i<len;i++)
        {
            InputStream is = s.getInputStream();
            is.read(file);
            fos.write(file);
        }
    }
}
