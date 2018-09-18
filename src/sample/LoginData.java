package sample;

import java.io.Serializable;

public class LoginData implements Client , Serializable
{
    private String UserName;
    private String Password;
    private int type;// 0 for retailer 1 for customer and 3 for admin

    public LoginData(String userName, String password, int type) {
        UserName = userName;
        Password = password;
        this.type = type;
    }
}
