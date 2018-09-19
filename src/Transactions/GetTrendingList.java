package Transactions;

import sample.Product;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.util.ArrayList;

public class GetTrendingList implements Serializable
{
    Connection connection;

    public ArrayList<Product> getList()
    {
        return null;
    }
}
