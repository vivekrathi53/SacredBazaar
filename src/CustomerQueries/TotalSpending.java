package CustomerQueries;

import MainPackage.Customer;

import java.io.Serializable;
import java.sql.*;

public class TotalSpending implements Serializable
{
    public String customer;
    public Connection connection;
    TotalSpending() {

    }

    public int count() throws ClassNotFoundException, SQLException
    {
        String price;int q;int p;String quantity;int sum=0;
        String query="SELECT ProductId,Quantity FROM TRANSACTION WHERE CustomerUserName='"+(customer)+"'";
        PreparedStatement preStat = connection.prepareStatement(query);
        ResultSet rs = preStat.executeQuery(query);
        rs.next();
        String query2,product;
        while(!rs.next())
        {
            product=rs.getString("ProductId");
            quantity=rs.getString("ProductId");
            query2="SELECT Price FROM Productstable WHERE ProductId='"+(product)+"'";
            preStat = connection.prepareStatement(query2);
            ResultSet res = preStat.executeQuery(query2);
            res.next();
            q=Integer.parseInt(quantity);
            price=res.getString("Price");
            p=Integer.parseInt(price);
            sum+=p*q;
        }
        return sum;
    }
}
