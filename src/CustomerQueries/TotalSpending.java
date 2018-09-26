package CustomerQueries;

import MainPackage.Customer;

import java.io.Serializable;
import java.sql.*;

public class TotalSpending implements Serializable
{
    public String customer;
    public Connection connection;
    public int count() throws ClassNotFoundException, SQLException
    {
        int q;
        int p;
        int sum=0;
        String query="SELECT ProductId,Quantity FROM TransactionTable WHERE CustomerUserName='"+(customer)+"' AND Status='"+(1)+"'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        String query2;
        int product;
        while(rs.next())
        {
            product=rs.getInt("ProductId");
            q=rs.getInt("ProductId");
            query2="SELECT Price FROM ProductsTable WHERE ProductId='"+(product)+"'";
            preStat = connection.prepareStatement(query2);
            ResultSet res = preStat.executeQuery(query2);
            if(!res.next()) continue;
            p=res.getInt("Price");
            sum+=p*q;
        }
        return sum;
    }
}
