package CustomerQueries;

import MainPackage.Product;

import java.io.Serializable;
import java.sql.*;


public class AddProductTo implements Serializable
{
    public Product prod;
    public String CustomerUserName;
    public Connection connection;
    public int Quantity;
    public Timestamp time;
    public String Address;
    public int type;
    public void buyProduct() throws SQLException
    {
        String query;
        PreparedStatement prepStat;
        query = "INSERT INTO TransactionTable(`ProductId`,`CustomerUserName`,`Time`,`Address`,`Quantity`,`Status`) VALUES ('" +(prod.getProductId())+"','" +(CustomerUserName)+ "','" +(time)+ "','" +Address+"','"+ +(Quantity)+"','"+type+ "')";
        prepStat = connection.prepareStatement(query);
        prepStat.executeUpdate();
    }

}
