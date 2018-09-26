package MainPackage;

import AdminQueries.GenerateGraph;
import javafx.fxml.FXML;
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
    int []arr;int i;
    int datecurrent;



    public void startgraph() throws ClassNotFoundException, SQLException, IOException
    {
        System.out.println(datecurrent);
        //CategoryAxis x=new CategoryAxis();
       // NumberAxis y=new NumberAxis();
        XYChart.Series<Number,Number> set= new XYChart.Series<Number,Number>();
        set.getData().add(new XYChart.Data<Number, Number>(1,0));
        // AdminGraph.getData().addAll(series);
        /*XYChart.Series set= new XYChart.Series();
        if(datecurrent>=7)
        {
            for (i = 6; i >= 0; i--)
            {
                System.out.println((arr[datecurrent-i]));
                set.getData().add(new XYChart.Data( "1", "2"));
            }
        }*/
        AdminGraph.getData().addAll();
        Scene scene  = new Scene(AdminGraph,800,600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
