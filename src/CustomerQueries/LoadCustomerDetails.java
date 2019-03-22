package CustomerQueries;

import MainPackage.Customer;
import MainPackage.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadCustomerDetails implements Serializable
{
    public Connection connection;
    Boolean solved;
    public String userName;
    public Customer getDetails() throws SQLException
    {
        String query = "SELECT * FROM CustomerTable WHERE UserName='" + userName + "'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet rs = prepStat.executeQuery();
        rs.next();
        ArrayList<Product> prodList;
        CustomerProducts Cprods = new CustomerProducts();
        Cprods.connection=connection;
        Cprods.UserName=userName;
        prodList = Cprods.getCustomerProduct();
        int len = prodList.size();
        ArrayList<Product> prodBought = new ArrayList<>();
        ArrayList<Product> prodInCart = new ArrayList<>();
        ArrayList<Product> prodInWishList = new ArrayList<>();
        for(int i=0;i<len;i++)
        {
            if(prodList.get(i).getStatus()==1) prodBought.add(prodList.get(i));
            if(prodList.get(i).getStatus()==2) prodInCart.add(prodList.get(i));
            if(prodList.get(i).getStatus()==3) prodInWishList.add(prodList.get(i));
        }
        Customer c = new Customer(rs.getString("FirstName"),rs.getString("LastName"),rs.getString("UserName"),rs.getString("Password"),rs.getString("Address"),rs.getString("MobileNo"),rs.getString("PinNo"),rs.getString("Email"),prodInCart,prodBought,prodInWishList);
        solved=true;
        return c;
    }
}
