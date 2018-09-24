package CustomerQueries;

import MainPackage.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerProducts
{
    public Connection connection;
    public String UserName;
    public ArrayList<Product> getCustomerProduct() throws SQLException
    {
        ArrayList<Product> prodList = new ArrayList<>();
        String query = "SELECT * FROM TransactionTable WHERE CustomerUserName = '"+(UserName)+"'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet result = prepStat.executeQuery();
        while(result.next())
        {
            query = "SELECT * FROM ProductsTable WHERE ProductId = '" + result.getString("ProductId")+ "'";
            prepStat = connection.prepareStatement(query);
            ResultSet resultSet = prepStat.executeQuery();
            Product prod;
            if(resultSet.next())
            {
                prod = new Product(resultSet.getInt("ProductId"),resultSet.getString("Retailer"),resultSet.getInt("Price"),resultSet.getInt("Quantity"),resultSet.getString("Category"),resultSet.getString("Description"),resultSet.getInt("Discount"));
                prod.setStatus(result.getInt("Status"));
                prodList.add(prod);
            }

        }
        return prodList;
    }


}
