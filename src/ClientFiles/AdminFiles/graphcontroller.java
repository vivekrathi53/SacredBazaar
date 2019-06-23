package ClientFiles.AdminFiles;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class graphcontroller
{
    @FXML
    private LineChart<?, ?> AdminGraph;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    private int Time;
    private int []arr;
    private int i;
    private int datecurrent;



    public void startgraph() throws ClassNotFoundException, SQLException, IOException
    {
        System.out.println(getDatecurrent());
        NumberAxis x=new NumberAxis();
        NumberAxis y=new NumberAxis();
        x.labelProperty().setValue("Time in days");
        y.labelProperty().setValue("No. of products Bought");
        LineChart lineChart = new LineChart(x,y);
        lineChart.setTitle("Customer Monitoring");
        XYChart.Series set = new XYChart.Series();
        set.setName("My portfolio");
        if(getDatecurrent() >=7)
        {
            for (setI(6); getI() >= 0; setI(getI() - 1))
            {
                System.out.println((getArr()[getDatecurrent() - getI()]));
                set.getData().add(new XYChart.Data( getDatecurrent() - getI(), getArr()[getDatecurrent() - getI()]));
            }
        }
        lineChart.getData().addAll(set);
        Group root = new Group(lineChart);
        Scene scene  = new Scene(root,800,600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public LineChart<?, ?> getAdminGraph() {
        return AdminGraph;
    }

    public void setAdminGraph(LineChart<?, ?> adminGraph) {
        AdminGraph = adminGraph;
    }

    public CategoryAxis getX() {
        return x;
    }

    public void setX(CategoryAxis x) {
        this.x = x;
    }

    public NumberAxis getY() {
        return y;
    }

    public void setY(NumberAxis y) {
        this.y = y;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getDatecurrent() {
        return datecurrent;
    }

    public void setDatecurrent(int datecurrent) {
        this.datecurrent = datecurrent;
    }
}
