package AdminQueries;

import CustomerQueries.LoadCustomerDetails;
import CustomerQueries.TotalSpending;
import MainPackage.Customer;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


class  Sortbyspend implements Comparator<Customer>
{
public int compare(Customer a, Customer b)
{
        return b.getTotalspending() -a.getTotalspending();
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
         String query="SELECT UserName FROM CustomerTable";
         PreparedStatement preStat = connection.prepareStatement(query);
         ResultSet rs = preStat.executeQuery(query);
         LoadCustomerDetails lcd=new LoadCustomerDetails();
         lcd.connection = connection;
         while(rs.next())
         {
                user=rs.getString("UserName");
                lcd.userName = user;
                c=lcd.getDetails();
             TotalSpending ts = new TotalSpending();
             ts.connection = connection;
             ts.UserName = c.getUserName();
             c.setTotalspending(ts.count());
            // customersort cse=new customersort(c.getUserName(),c.getTotalspending());
             spending.add(c) ;
         }
         Collections.sort(spending,new Sortbyspend());
         return spending;
     }
}
