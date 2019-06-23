package ClientFiles.RetailerFiles;

import ClientFiles.RetailerFiles.RetailerQueries.ChangeRetailerDetails;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RetailerProfileController
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    @FXML
    private TextArea PasswordBox;
    @FXML
    private TextArea EmailBox;
    @FXML
    private TextArea PinNoBox;
    @FXML
    private TextArea MobileNoBox;
    @FXML
    private TextArea AddressBox;
    @FXML
    private TextArea LastNameBox;
    @FXML
    private TextArea FirstNameBox;
    @FXML
    private ImageView ProfileImage;
    @FXML
    private Label NameDisplay;
    @FXML
    private Label UserNameDisplay;
    @FXML
    private Label AddressDisplay;

    private Retailer retailer;
    public void ShowProfile()
    {
        getAddressBox().setText(getRetailer().getAddress());
        getAddressDisplay().setText(getRetailer().getAddress());
        getEmailBox().setText(getRetailer().getEmail());
        getFirstNameBox().setText(getRetailer().getFirstName());
        getLastNameBox().setText(getRetailer().getLastName());
        getMobileNoBox().setText(getRetailer().getMobileNo());
        getNameDisplay().setText(getRetailer().getFirstName() + " " + getRetailer().getLastName());
        getPasswordBox().setText(getRetailer().getPassword());
        getPinNoBox().setText(getRetailer().getPinNo());
        getUserNameDisplay().setText(getRetailer().getUserName());
    }

    public void ChangeProfile()
    {
        ChangeRetailerDetails crd = new ChangeRetailerDetails();
        getRetailer().setAddress(getAddressBox().getText());
        getRetailer().setEmail(getEmailBox().getText());
        getRetailer().setFirstName(getFirstNameBox().getText());
        getRetailer().setLastName(getLastNameBox().getText());
        getRetailer().setPinNo(getPinNoBox().getText());
        getRetailer().setMobileNo(getMobileNoBox().getText());
        getRetailer().setPassword(getPasswordBox().getText());
        crd.client= getRetailer();
        try {
            getOos().writeObject(crd);
            getOos().flush();
            setRetailer((Retailer) getOis().readObject());
            ShowProfile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public TextArea getPasswordBox() {
        return PasswordBox;
    }

    public void setPasswordBox(TextArea passwordBox) {
        PasswordBox = passwordBox;
    }

    public TextArea getEmailBox() {
        return EmailBox;
    }

    public void setEmailBox(TextArea emailBox) {
        EmailBox = emailBox;
    }

    public TextArea getPinNoBox() {
        return PinNoBox;
    }

    public void setPinNoBox(TextArea pinNoBox) {
        PinNoBox = pinNoBox;
    }

    public TextArea getMobileNoBox() {
        return MobileNoBox;
    }

    public void setMobileNoBox(TextArea mobileNoBox) {
        MobileNoBox = mobileNoBox;
    }

    public TextArea getAddressBox() {
        return AddressBox;
    }

    public void setAddressBox(TextArea addressBox) {
        AddressBox = addressBox;
    }

    public TextArea getLastNameBox() {
        return LastNameBox;
    }

    public void setLastNameBox(TextArea lastNameBox) {
        LastNameBox = lastNameBox;
    }

    public TextArea getFirstNameBox() {
        return FirstNameBox;
    }

    public void setFirstNameBox(TextArea firstNameBox) {
        FirstNameBox = firstNameBox;
    }

    public ImageView getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(ImageView profileImage) {
        ProfileImage = profileImage;
    }

    public Label getNameDisplay() {
        return NameDisplay;
    }

    public void setNameDisplay(Label nameDisplay) {
        NameDisplay = nameDisplay;
    }

    public Label getUserNameDisplay() {
        return UserNameDisplay;
    }

    public void setUserNameDisplay(Label userNameDisplay) {
        UserNameDisplay = userNameDisplay;
    }

    public Label getAddressDisplay() {
        return AddressDisplay;
    }

    public void setAddressDisplay(Label addressDisplay) {
        AddressDisplay = addressDisplay;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }
}
