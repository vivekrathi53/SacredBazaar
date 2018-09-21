package MainPackage;

import java.io.Serializable;

public class LoginData implements Client , Serializable
{
    private String UserName;
    private String Password;
    private int type;// 0 for retailer 1 for customer and 3 for admin

    @Override
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    @Override
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public LoginData(String userName, String password, int type) {
        UserName = userName;
        Password = password;
        this.type = type;
    }
}
