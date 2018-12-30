package CustomerQueries;

import MainPackage.Product;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetTrendingList implements Serializable
{
    public Connection connection;
    public ArrayList<Product> prodList;
    public ArrayList<Integer> count;
    public ArrayList<Product> getList() throws SQLException {
        String querys = "SELECT * FROM TransactionTable ";
        PreparedStatement prepStat = connection.prepareStatement(querys);
        ResultSet result = prepStat.executeQuery();
        prodList = new ArrayList<>();
        count = new ArrayList<>();
        while(result.next())
        {
            if(result.getInt("Status")!=1) continue;
            String query = "SELECT * FROM ProductsTable WHERE ProductId = '" + result.getString("ProductId")+ "'";
            prepStat = connection.prepareStatement(query);
            ResultSet resultSet = prepStat.executeQuery();
            Product prod;
            if(resultSet.next())
            {
                prod = new Product(resultSet.getInt("ProductId"),resultSet.getString("Retailer"),resultSet.getInt("Price"),resultSet.getInt("Quantity"),resultSet.getString("Category"),resultSet.getString("Description"),resultSet.getInt("Discount"));
                if(!prodList.contains(prod))
                    prodList.add(prod);
                else count.add(prodList.indexOf(prod),count.get(prodList.indexOf(prod))+1);
            }
        }
        /*SortProducts sp = new SortProducts();
        sp.count=count;
        sp.prodList=prodList;
        Collections.sort(prodList, sp);*/
        return prodList;
    }

}
/*class SortProducts implements Comparator<Product> {

    ArrayList<Integer> count;
    ArrayList<Product> prodList;

    @Override
    public int compare(Product o1, Product o2) {
        if (count.get(prodList.indexOf(o1)) > count.get(prodList.indexOf(o2)))
            return 0;
        else return 1;
    }
}*/

