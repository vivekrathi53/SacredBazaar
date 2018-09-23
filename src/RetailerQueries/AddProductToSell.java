package RetailerQueries;

import MainPackage.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProductToSell implements Serializable
{
    public Connection connection;
    public Product product;
    public void add()
    {
        System.out.println("Adding");
        String query = "INSERT INTO ProductsTable(`Retailer`,`Price`,`Quantity`,`Category`,`Description`,`Discount`) VALUES ('" +(product.getRetailer())+"','" +product.getPrice()+"','" +(product.getQuantity())+ "','" +(product.getProductCategory())+ "','" +product.getProductDescription()+"','"+ +product.getDiscount()+ "')";
        PreparedStatement prepStat = null;
        try {
            prepStat = connection.prepareStatement(query);
            prepStat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
