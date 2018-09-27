package MainPackage;

import AdminQueries.GenerateGraph;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class graphcontroller
{
    @FXML
    public LineChart<?, ?> AdminGraph;
    @FXML
    public CategoryAxis x;
    @FXML
    public NumberAxis y;
    int Time;
    int []arr;
    int i;
    int datecurrent;



    public void startgraph() throws ClassNotFoundException, SQLException, IOException
    {
        System.out.println(datecurrent);
        NumberAxis x=new NumberAxis();
        NumberAxis y=new NumberAxis();
        x.labelProperty().setValue("Time in days");
        y.labelProperty().setValue("No. of products Bought");
        LineChart lineChart = new LineChart(x,y);
        lineChart.setTitle("Customer Monitoring");
        XYChart.Series set = new XYChart.Series();
        set.setName("My portfolio");
        if(datecurrent>=7)
        {
            for (i = 6; i >= 0; i--)
            {
                System.out.println((arr[datecurrent-i]));
                set.getData().add(new XYChart.Data( datecurrent-i, arr[datecurrent-i]));
            }
        }
        lineChart.getData().addAll(set);
        Group root = new Group(lineChart);
        Scene scene  = new Scene(root,800,600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
