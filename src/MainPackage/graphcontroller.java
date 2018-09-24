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
    ArrayList<Integer> Time;
    public void startgraph() throws ClassNotFoundException, SQLException
    {
       /* Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connected to database");
        String url = "jdbc:mysql://localhost:3306/SacredBazzar";
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date date = new Date();
        currentdate=formatter.format(date);
        connection = DriverManager.getConnection(url, "root", "password");
        String query="SELECT * FROM TRANSACTION WHERE CustomerUserName='"+(username)+"'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        rs.next();
        while(!rs.next())
        {
            Time.add(rs.getTime("Time").getDate());
        }
        Collections.sort(Time);
        */
        XYChart.Series set1=new XYChart.Series();
        set1.getData().add(Integer.parseInt("23"),89);
        set1.getData().add(Integer.parseInt("22"),90);
        RetailerGraph.getData().addAll(set1);
    }
}
