package Transactions;

import sample.Product;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchFor
{
    String searchProduct;
    Connection connection;
    public ArrayList<Product> getReleventProducts() throws SQLException
    {
        ArrayList<Product> prodList = new ArrayList<>();
        String query = "SELECT * FROM ProductsTable WHERE Category ='" + (searchProduct) + "'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet resultSet = prepStat.executeQuery();
        while(resultSet.next())
        {
            Product prod = new Product(resultSet.getString("ProductId"),resultSet.getInt("Retailer"),resultSet.getInt("Price"),resultSet.getInt("Quantity"),resultSet.getString("Category"),resultSet.getString("Description"),resultSet.getInt("Discount"));
            prodList.add(prod);
        }
        return prodList;
    }
}
