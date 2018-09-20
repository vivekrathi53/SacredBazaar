package Transactions;

import sample.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;

public class BuyProduct implements Serializable
{
    public Product prod;
    public String CustomerUserName;
    public Connection connection;
    public int Quantity;
    public Time time;
    public String Address;
    public void buyProduct() throws SQLException
    {
        String query = "UPDATE ProductsTable  SET Quantity = '"+(prod.getQuantity()-1)+"' WHERE ProductId = '"+(prod.getProductId())+"'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        prepStat.executeUpdate();
        prod.setQuantity(prod.getQuantity() - Quantity);
        query = "INSERT INTO TransactionTable(`ProductId`,`CustomerUserName`,`Time`,`Address`,`Quantity`,`Status`) VALUES ('" +(prod.getProductId())+"','" +(CustomerUserName)+ "','" +(time)+ "','" +Address+"','"+ +(Quantity)+"','"+1+ "')";
        prepStat = connection.prepareStatement(query);
        prepStat.executeUpdate();
    }

}
