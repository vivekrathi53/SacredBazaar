package RetailerQueries;

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
    public int[] generate() throws SQLException {
        int []arr=new int[40];
        int Time;
        int datecurrent=Integer.parseInt(currentdate);
        String query="SELECT * FROM TRANSACTION WHERE CustomerUserName='"+(username)+"'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        rs.next();
        while(!rs.next())
        {
            Time=rs.getTime("Time").getDate();
            if(Time==datecurrent-1)
                arr[datecurrent-1]++;
            else if(Time==datecurrent-2)
                arr[datecurrent-2]++;
            else if(Time==datecurrent-3)
                arr[datecurrent-3]++;
            else if(Time==datecurrent-4)
                arr[datecurrent-4]++;
            else if (Time==datecurrent-5)
                arr[datecurrent-5]++;
            else if (Time==datecurrent-6)
                arr[datecurrent-6]++;
            else if(Time==datecurrent)
                arr[datecurrent]++;
        }
        return arr;
    }
}
