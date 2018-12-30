package CustomerQueries;

import java.io.Serializable;
import java.sql.*;

public class TotalSpending implements Serializable
{
    public String UserName;
    public Connection connection;
    public int count() throws  SQLException
    {
        int sum=0;
        String q = "SELECT * FROM TransactionTable WHERE CustomerUserName = '"+UserName+"' AND Status='" +(1)+"'";
        PreparedStatement prepStat = connection.prepareStatement(q);
        ResultSet rs = prepStat.executeQuery();
        while(rs.next())
        {
            String qe = "SELECT Price FROM ProductsTable WHERE ProductId='" +rs.getInt("ProductId")+"'";
            PreparedStatement ps = connection.prepareStatement(qe);
            ResultSet re = ps.executeQuery();
            if(re.next())
            {
                sum+=rs.getInt("Quantity") * re.getInt("Price");
            }
        }
        return sum;
    }
}
