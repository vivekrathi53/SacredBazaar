package MainPackage;

import java.io.Serializable;
import java.util.ArrayList;

public class Retailer implements Client, Serializable
{
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private String Address;
    private String MobileNo;
    private String PinNo;
    private String Email;

    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
    }

    private ArrayList<Product> AllProducts;
    private ArrayList<Product> ProductSold;
    private int TurnOver;
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public int getType() {
        return 0;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getPinNo() {
        return PinNo;
    }

    public void setPinNo(String pinNo) {
        PinNo = pinNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public ArrayList<Product> getAllProducts() {
        return AllProducts;
    }

    public void setAllProducts(ArrayList<Product> allProducts) {
        AllProducts = allProducts;
    }

    public ArrayList<Product> getProductSold() {
        return ProductSold;
    }

    public void setProductSold(ArrayList<Product> productSold) {
        ProductSold = productSold;
    }

    public int getTurnOver() {
        return TurnOver;
    }

    public void setTurnOver(int turnOver) {
        TurnOver = turnOver;
    }

    public Retailer(String firstName, String lastName, String userName, String password, String address, String mobileNo, String pinNo, String email, ArrayList<Product> productsPending, ArrayList<Product> productSold, int turnOver) {
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Password = password;
        Address = address;
        MobileNo = mobileNo;
        PinNo = pinNo;
        Email = email;
        AllProducts = productsPending;
        ProductSold = productSold;
        TurnOver = turnOver;
    }
}
