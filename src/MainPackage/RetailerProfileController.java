package MainPackage;

import RetailerQueries.ChangeRetailerDetails;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RetailerProfileController
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
    public Retailer retailer;
    public void ShowProfile()
    {
        AddressBox.setText(retailer.getAddress());
        AddressDisplay.setText(retailer.getAddress());
        EmailBox.setText(retailer.getEmail());
        FirstNameBox.setText(retailer.getFirstName());
        LastNameBox.setText(retailer.getLastName());
        MobileNoBox.setText(retailer.getMobileNo());
        NameDisplay.setText(retailer.getFirstName() + " " + retailer.getLastName());
        PasswordBox.setText(retailer.getPassword());
        PinNoBox.setText(retailer.getPinNo());
        UserNameDisplay.setText(retailer.getUserName());
    }

    public void ChangeProfile()
    {
        ChangeRetailerDetails crd = new ChangeRetailerDetails();
        retailer.setAddress(AddressBox.getText());
        retailer.setEmail(EmailBox.getText());
        retailer.setFirstName(FirstNameBox.getText());
        retailer.setLastName(LastNameBox.getText());
        retailer.setPinNo(PinNoBox.getText());
        retailer.setMobileNo(MobileNoBox.getText());
        retailer.setPassword(PasswordBox.getText());
        crd.client=retailer;
        try {
            oos.writeObject(crd);
            oos.flush();
            retailer = (Retailer) ois.readObject();
            ShowProfile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
