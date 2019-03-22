package AdminQueries;

import MainPackage.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeProduct implements Serializable
{
    public Product product;
    public Connection connection;
    public void saveChanges() throws SQLException
    {
        String q="UPDATE ProductsTable SET ProductId = '"+(product.getProductId())+"',Retailer = '"+(product.getRetailer())+"',Price = '"+(product.getPrice())+"',Discount = '"+(product.getDiscount())+"', Description = '"+(product.getProductDescription())+"',Category = '"+(product.getProductCategory())+"',Quantity = '"+(product.getQuantity())+"' WHERE ProductId =  '"+(product.getProductId())+"'";
        PreparedStatement prepStat = connection.prepareStatement(q);
        prepStat.executeUpdate();
    }
}
