package MainPackage;

import MainPackage.Retailer;
import RetailerQueries.GenerateGraph;
import javafx.fxml.FXML;
import javafx.scene.chart.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    String username;
    int Time;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    public void startgraph() throws ClassNotFoundException, SQLException, IOException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        Date date = new Date();
        String currentdate = formatter.format(date);
        GenerateGraph gg = new GenerateGraph();
        gg.currentdate = currentdate;
        gg.username = username;
        oos.writeObject(gg);
        oos.flush();
        int[] arr = (int[]) ois.readObject();
        XYChart.Series set1=new XYChart.Series();
        int datecurrent = Integer.parseInt(currentdate);
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
