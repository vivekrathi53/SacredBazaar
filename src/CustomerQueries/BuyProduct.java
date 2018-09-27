package CustomerQueries;

import MainPackage.Product;

import java.io.Serializable;
import java.sql.*;


public class BuyProduct implements Serializable
{
    public Product prod;
    public String CustomerUserName;
    public Connection connection;
    public int Quantity;
    public Timestamp time;
    public String Address;
    public void buyProduct() throws SQLException
    {
        String query = "UPDATE ProductsTable  SET Quantity = '"+(prod.getQuantity()-Quantity)+"' WHERE ProductId = '"+(prod.getProductId())+"'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        prepStat.executeUpdate();
        prod.setQuantity(prod.getQuantity() - Quantity);
        query = "INSERT INTO TransactionTable (`ProductId`,`CustomerUserName`,`Time`,`Address`,`Quantity`,`Status`) VALUES ('" +(prod.getProductId())+"','" +(CustomerUserName)+ "','" +(time)+ "','" +Address+"','"+ +(Quantity)+"','"+1+ "')";
        prepStat = connection.prepareStatement(query);
        prepStat.executeUpdate();
        query = "DELETE FROM TransactionTable WHERE ProductId ='"+(prod.getProductId())+"' AND CustomerUserName='"+(CustomerUserName)+"' AND Status='" +2+ "' OR Status='"+3+"'";
        prepStat = connection.prepareStatement(query);
        prepStat.executeUpdate();
    }

}
