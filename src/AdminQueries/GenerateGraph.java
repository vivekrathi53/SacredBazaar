package AdminQueries;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateGraph implements Serializable
{
    public Connection connection;
    public String username;
    public String currentdate;
    public int type;
    public int datecurrent;
    int []arr=new int[40];
    String Time;
    int i;
    String query;
    public int[] generate() throws SQLException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date date = new Date();
        String currentdate = formatter.format(date);
        datecurrent=Integer.parseInt(currentdate);
        //System.out.println(datecurrent);
        System.out.println(username);
        query="SELECT * FROM TransactionTable WHERE CustomerUserName='"+(username)+"'";// type is 1 for customer graph generation
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        rs.next();
        while(rs.next())
        {
            Time=rs.getString("Time");
            int m=Integer.parseInt(String.valueOf(Time.charAt(8)));
            int n=Integer.parseInt(String.valueOf(Time.charAt(9)));;
            int d=m*10+n;
            if(d==datecurrent-1)
                arr[datecurrent-1]++;
            else if(d==datecurrent-2)
                arr[datecurrent-2]++;
            else if(d==datecurrent-3)
                arr[datecurrent-3]++;
            else if(d==datecurrent-4)
                arr[datecurrent-4]++;
            else if (d==datecurrent-5)
                arr[datecurrent-5]++;
            else if (d==datecurrent-6)
                arr[datecurrent-6]++;
            else if(d==datecurrent)
                arr[datecurrent]++;
        }
        //for (i = 31; i >=1; i--) {
          //  System.out.println(arr[i] + "so");
        //System.out.println(datecurrent);
        return arr;
    }
}
