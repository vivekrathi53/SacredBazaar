package CustomerQueries;

import MainPackage.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchFor implements Serializable
{
    public String searchProduct;
    public Connection connection;
    public ArrayList<Product> getReleventProducts() throws SQLException
    {
        ArrayList<Product> prodList = new ArrayList<>();
        String query = "SELECT * FROM ProductsTable WHERE Category ='" + (searchProduct) + "'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet resultSet = prepStat.executeQuery();
        while(resultSet.next())
        {
            Product prod = new Product(resultSet.getInt("ProductId"),resultSet.getString("Retailer"),resultSet.getInt("Price"),resultSet.getInt("Quantity"),resultSet.getString("Category"),resultSet.getString("Description"),resultSet.getInt("Discount"));
            prodList.add(prod);
        }
        return prodList;
    }
}
