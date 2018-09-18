package sample;

import java.io.Serializable;
import java.util.List;

public class Customer implements Client, Serializable
{
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private String Address;
    private String MobileNo;
    private String PinNo;
    private String Email;
    private List<Product> ProductsInCart;
    private List<Product> ProductsBought;
    private List<Product> ProductsWishList;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

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
        return 1;
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

    public List<Product> getProductsInCart() {
        return ProductsInCart;
    }

    public void setProductsInCart(List<Product> productsInCart) {
        ProductsInCart = productsInCart;
    }

    public List<Product> getProductsBought() {
        return ProductsBought;
    }

    public void setProductsBought(List<Product> productsBought) {
        ProductsBought = productsBought;
    }

    public List<Product> getProductsWishList() {
        return ProductsWishList;
    }

    public void setProductsWishList(List<Product> productsWishList) {
        ProductsWishList = productsWishList;
    }

    public Customer(String firstName, String lastName, String userName, String password, String address, String mobileNo, String pinNo, String email, List<Product> productsInCart, List<Product> productsBought, List<Product> productsWishList)
    {
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Password = password;
        Address = address;
        MobileNo = mobileNo;
        PinNo = pinNo;
        Email = email;
        ProductsInCart = productsInCart;
        ProductsBought = productsBought;
        ProductsWishList = productsWishList;
    }
}
