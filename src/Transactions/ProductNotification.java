package Transactions;

import sample.Customer;
import sample.Product;

import java.io.Serializable;

public class ProductNotification implements Serializable
{
    Customer customer;
    Product prod;
}
