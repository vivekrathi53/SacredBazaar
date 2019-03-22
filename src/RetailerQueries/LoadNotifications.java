package RetailerQueries;

import MainPackage.PendingProducts;
import MainPackage.Product;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoadNotifications implements Serializable
{
    public Connection connection;
    public String UserName;
    public ArrayList<PendingProducts> load() throws SQLException {
        GetRetailerProducts grp = new GetRetailerProducts();
        grp.connection=connection;
        grp.UserName=UserName;
        ArrayList<Product>  allprod = null ;
        ArrayList<PendingProducts> pendProd = new ArrayList<>();
        allprod =  grp.getCustomerProduct();
        PreparedStatement prepStat;
        for(int i=0;i<allprod.size();i++)
        {
            String query = "SELECT * FROM TransactionTable WHERE ProductId='"+(allprod.get(i).getProductId())+"'";
            prepStat = connection.prepareStatement(query);
            ResultSet resultSet = prepStat.executeQuery();
            //resultSet.next();
            if(!resultSet.next()||resultSet.getInt("DeliveryStatus")==1) continue;
            PendingProducts pp = new PendingProducts(allprod.get(i).getProductId(),allprod.get(i).getRetailer(),allprod.get(i).getPrice(),allprod.get(i).getQuantity(),allprod.get(i).getProductCategory(),allprod.get(i).getProductDescription(),allprod.get(i).getDiscount());
            query = "SELECT * FROM CustomerTable WHERE UserName='"+(resultSet.getString("CustomerUserName"))+"'";
            prepStat = connection.prepareStatement(query);
            ResultSet rs = prepStat.executeQuery();
            if(rs.next())
            {
                pp.setCustomerName(rs.getString("FirstName") + " " + rs.getString("LastName"));
                pp.setMobileNo(rs.getString("MobileNo"));
            }
            else pp.setCustomerName("Customer Name Not Found");
            pp.setDeliveryStatus(resultSet.getInt("DeliveryStatus"));
            pp.setTime(resultSet.getTime("Time"));
            pp.setTransactionId(resultSet.getInt("TransactionId"));
            pp.setAddress(resultSet.getString("Address"));
            pp.setQuantityOrdered(resultSet.getInt("Quantity"));
            pendProd.add(pp);
        }
        return pendProd;
    }
}
