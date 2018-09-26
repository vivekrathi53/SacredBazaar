package AdminQueries;

import CustomerQueries.LoadCustomerDetails;
import MainPackage.Customer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class Sortbyroll implements Comparator<Customer>, Serializable
{
    @Override
    public int compare(Customer c1, Customer c2)
    {
        if(c1.getTotalspending()>c2.getTotalspending())
            return 1;
        return 0;
    }
}

public class Customershow implements Serializable
{
     public  Connection connection;
     public LoadCustomerDetails lcd;
     Customer c;

     public ArrayList<Customer> show() throws SQLException, IOException, ClassNotFoundException
     {
         ArrayList<Customer> spending = new ArrayList<>();
         String user;
         String query="SELECT UserName FROM CUSTOMERTABLE";
         PreparedStatement preStat = connection.prepareStatement(query);
         ResultSet rs = preStat.executeQuery(query);
         LoadCustomerDetails lcd=new LoadCustomerDetails(connection);
         while(rs.next())
         {
                user=rs.getString("UserName");
                c=lcd.getDetails(user);
                spending.add(c) ;
         }
         Collections.sort(spending,new Sortbyroll());
         return spending;
     }
}
