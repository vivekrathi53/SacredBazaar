package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadRetailerDetails implements Serializable
{
    String username;
    Connection connection;
    @FXML
    private Label nameuser;
    public LoadRetailerDetails(Connection connection) throws SQLException {
        connection=this.connection;
    }
    public void loaddetail() throws SQLException {
        String query = "SELECT * FROM retailertable WHERE UserName='" + (username) + "'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet rs = prepStat.executeQuery();
        rs.next();
        nameuser.setText(rs.getString("FirstName"));
    }

}
