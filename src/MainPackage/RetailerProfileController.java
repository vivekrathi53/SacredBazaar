package MainPackage;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.ObjectOutputStream;

public class RetailerProfileController
{

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

    }
}
