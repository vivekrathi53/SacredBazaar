package AdminQueries;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RemoveProduct implements Serializable
{
    public Connection connection;
    public int ProductId;
    public void remove() throws SQLException {
        String q = "DELETE FROM ProductsTable WHERE ProductId='"+(ProductId)+"'";
        PreparedStatement prepStat = connection.prepareStatement(q);
        prepStat.executeUpdate();
    }

}
