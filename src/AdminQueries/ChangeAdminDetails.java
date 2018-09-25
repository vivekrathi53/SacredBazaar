package AdminQueries;

import MainPackage.Admin;
import MainPackage.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChangeAdminDetails
{
    public Admin client;
    public Connection connection;
    public void updateEntries() throws SQLException {
        String firstname=null;
        String lastname=null;
        String password=null;
        String Address=null;
        String MobileNo=null;
        String PinNo=null;
        String email=null;
        try
        {
            firstname=client.getFirstName();
            lastname=client.getLastName();
            password=client.getPassword();
            Address=client.getAddress();
            MobileNo=client.getMobileNo();
            PinNo=client.getPinNo();
            email=client.getEmail();
        }
        catch (java.lang.Exception exception)
        {
            exception.printStackTrace();
        }
        String q="UPDATE TransactionTable SET FirstName = '"+(firstname)+"',LastName = '"+(lastname)+"',Password = '"+(password)+"',Address = '"+(Address)+"',MobileNo = '"+(MobileNo)+"',PinNo = '"+(PinNo)+"',Email = '"+(email)+"' WHERE UserName =  '"+(client.getUserName())+"'";
        PreparedStatement preStat = connection.prepareStatement(q);
        preStat.executeUpdate();
    }
}
