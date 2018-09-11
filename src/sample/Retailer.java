package sample;

import java.util.List;

public class Retailer
{
    private String UserName;
    private String Password;
    private String Address;
    private String MobileNo;
    private String PinNo;
    private String Email;
    private List<Product> ProductsPending;
    private int ProductSold;
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

    public List<Product> getProductsPending() {
        return ProductsPending;
    }

    public void setProductsPending(List<Product> productsPending) {
        ProductsPending = productsPending;
    }

    public int getProductSold() {
        return ProductSold;
    }

    public void setProductSold(int productSold) {
        ProductSold = productSold;
    }

    public int getTurnOver() {
        return TurnOver;
    }

    public void setTurnOver(int turnOver) {
        TurnOver = turnOver;
    }

    public Retailer(String userName, String password, String address, String mobileNo, String pinNo, String email, List<Product> productsPending, int productSold, int turnOver) {
        UserName = userName;
        Password = password;
        Address = address;
        MobileNo = mobileNo;
        PinNo = pinNo;
        Email = email;
        ProductsPending = productsPending;
        ProductSold = productSold;
        TurnOver = turnOver;
    }
}
