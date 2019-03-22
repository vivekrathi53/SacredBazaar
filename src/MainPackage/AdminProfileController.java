package MainPackage;

import AdminQueries.ChangeAdminDetails;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminProfileController
{
    public ObjectOutputStream oos;
    public ObjectInputStream ois;
    public TextArea PasswordBox;
    public TextArea EmailBox;
    public TextArea PinNoBox;
    public TextArea MobileNoBox;
    public TextArea AddressBox;
    public TextArea LastNameBox;
    public TextArea FirstNameBox;
    public ImageView ProfileImage;
    public Label NameDisplay;
    public Label UserNameDisplay;
    public Label AddressDisplay;
    public Admin admin;
    public ScrollPane displayPane;
    public void ShowProfile()
    {
        AddressBox.setText(admin.getAddress());
        AddressDisplay.setText(admin.getAddress());
        EmailBox.setText(admin.getEmail());
        FirstNameBox.setText(admin.getFirstName());
        LastNameBox.setText(admin.getLastName());
        MobileNoBox.setText(admin.getMobileNo());
        NameDisplay.setText(admin.getFirstName() + " " + admin.getLastName());
        PasswordBox.setText(admin.getPassword());
        PinNoBox.setText(admin.getPinNo());
        UserNameDisplay.setText(admin.getUserName());
    }

    public void ChangeProfile()
    {
        ChangeAdminDetails cad = new ChangeAdminDetails();
        admin.setAddress(AddressBox.getText());
        admin.setEmail(EmailBox.getText());
        admin.setFirstName(FirstNameBox.getText());
        admin.setLastName(LastNameBox.getText());
        admin.setPinNo(PinNoBox.getText());
        admin.setMobileNo(MobileNoBox.getText());
        admin.setPassword(PasswordBox.getText());
        cad.client=admin;
        try {
            oos.writeObject(cad);
            oos.flush();
            admin = (Admin) ois.readObject();
            ShowProfile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
