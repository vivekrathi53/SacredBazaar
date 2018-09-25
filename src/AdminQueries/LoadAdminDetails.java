package AdminQueries;

import MainPackage.Admin;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadAdminDetails implements Serializable
{
    public Connection connection;
    public String UserName;
    public Admin getDetails() throws SQLException {
        String query = "SELECT * FROM AdminTable WHERE UserName = '"+(UserName)+"'";
        PreparedStatement prepStat = connection.prepareStatement(query);
        ResultSet rs = prepStat.executeQuery();
        if(rs.next())
        {
            Admin a = new Admin(rs.getString("FirstName"),rs.getString("LastName"),rs.getString("UserName"),rs.getString("Password"),rs.getString("Address"),rs.getString("MobileNo"),rs.getString("PinNo"),rs.getString("Email"));
            return a;
        }
        else return null;
    }
}
