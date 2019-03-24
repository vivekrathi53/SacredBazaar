package RetailerQueries;

import MainPackage.Product;
import MainPackage.Retailer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadRetailerDetails 
{
    public Connection connection;
    public String userName;
    public LoadRetailerDetails(Connection connection, String userName)
    {
        this.connection=connection;
        this.userName = userName;
    }

    public Retailer getDetails() throws SQLException {
        String querys = "SELECT * FROM RetailerTable WHERE UserName='" + userName + "'";
        PreparedStatement prepStats = connection.prepareStatement(querys);
        ResultSet rs = prepStats.executeQuery();
        ArrayList<Product> soldList = new LoadSoldProducts(connection,userName).loadProducts();
        ArrayList<Product> allList = new GetRetailerProducts(connection,userName).getCustomerProduct();
        rs.next();
        Retailer r = new Retailer(rs.getString("FirstName"),rs.getString("LastName"),rs.getString("UserName"),rs.getString("Password"),rs.getString("Address"),rs.getString("MobileNo"),rs.getString("PinNo"),rs.getString("Email"),allList,soldList,0);
        return r;
    }
}

