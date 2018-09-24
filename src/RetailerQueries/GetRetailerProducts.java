package RetailerQueries;

import MainPackage.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetRetailerProducts implements Serializable
{
    public Connection connection;
    public String UserName;

    public GetRetailerProducts(Connection connection, String userName) {
        this.connection = connection;
        UserName = userName;
    }
    public GetRetailerProducts(){}
    public ArrayList<Product> getCustomerProduct() throws SQLException
    {
        ArrayList<Product> prodList = new ArrayList<>();
        String query = "SELECT * FROM ProductsTable WHERE Retailer='"+(UserName)+"'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet rs = prepStat.executeQuery();
        while(rs.next())
        {
            Product prod = new Product(rs.getInt("ProductId"),rs.getString("Retailer"),rs.getInt("Price"),rs.getInt("Quantity"),rs.getString("Category"),rs.getString("Description"),rs.getInt("Discount"));
            prodList.add(prod);
        }
        return prodList;
    }


}
