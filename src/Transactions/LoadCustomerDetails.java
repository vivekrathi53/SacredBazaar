package Transactions;

import sample.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadCustomerDetails
{
    Connection connection;
    Boolean solved;

    public LoadCustomerDetails(Connection connection) {
        this.connection = connection;
    }

    public Customer getDetails(String userName) throws SQLException {
        String query = "SELECT * FROM CustomerTable WHERE UserName='" + userName + "'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet rs = prepStat.executeQuery();
        rs.next();
        Customer c = new Customer(rs.getString("FirstName"),rs.getString("LastName"),rs.getString("UserName"),rs.getString("Password"),rs.getString("Address"),rs.getString("MobileNo"),rs.getString("PinNo"),rs.getString("Email"),null,null,null);
        solved=true;
        return c;
    }
}
