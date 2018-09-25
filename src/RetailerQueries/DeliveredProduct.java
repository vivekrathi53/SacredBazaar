package RetailerQueries;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeliveredProduct implements Serializable
{
    public Connection connection;
    public int productId;
    public void mark() throws SQLException
    {
        String query = "UPDATE TransactionTable SET DeliveryStatus = '"+ 1 +"' WHERE ProductId='"+(productId)+"'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        prepStat.executeUpdate();
    }

}
