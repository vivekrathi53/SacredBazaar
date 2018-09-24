package MainPackage;

import MainPackage.Retailer;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class graphcontroller
{
    @FXML
    private LineChart<?, ?>RetailerGraph;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    Connection connection;
    Retailer username;
    String currentdate;
    int Time;
    public void startgraph() throws ClassNotFoundException, SQLException
    {
        int []arr=new int[40];
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connected to database");
        String url = "jdbc:mysql://localhost:3306/SacredBazzar";
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date date = new Date();
        String currentdate = formatter.format(date);
        int datecurrent=Integer.parseInt(currentdate);
        connection = DriverManager.getConnection(url, "root", "password");
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
        XYChart.Series set1=new XYChart.Series();
        set1.getData().add(new XYChart.Data(" "+(datecurrent-6)+" ",arr[datecurrent-6]));
        set1.getData().add(new XYChart.Data(" "+(datecurrent-5)+" ",arr[datecurrent-5]));
        set1.getData().add(new XYChart.Data(" "+(datecurrent-4)+" ",arr[datecurrent-4]));
        set1.getData().add(new XYChart.Data(" "+(datecurrent-3)+" ",arr[datecurrent-3]));
        set1.getData().add(new XYChart.Data(" "+(datecurrent-2)+" ",arr[datecurrent-2]));
        set1.getData().add(new XYChart.Data(" "+(datecurrent-1)+" ",arr[datecurrent-1]));
        set1.getData().add(new XYChart.Data(" "+datecurrent+" ",arr[datecurrent]));
        RetailerGraph.getData().addAll(set1);
    }
}
