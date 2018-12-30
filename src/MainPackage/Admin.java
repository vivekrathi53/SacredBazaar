package MainPackage;

import java.io.Serializable;

public class Admin implements Client, Serializable
{
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private String Address;
    private String MobileNo;
    private String PinNo;
    private String Email;
    private int totalspending;

    public String getFirstName() {
        return FirstName;
    }
    public int getTotalspending(){return  totalspending;}
    public void setTotalspending(int Totalspending){totalspending=Totalspending;}

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
        return 3;
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

    public Admin(String firstName, String lastName, String userName, String password, String address, String mobileNo, String pinNo, String email)
    {
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Password = password;
        Address = address;
        MobileNo = mobileNo;
        PinNo = pinNo;
        Email = email;
    }
}
