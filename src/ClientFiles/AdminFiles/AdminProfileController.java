package ClientFiles.AdminFiles;

import ClientFiles.AdminFiles.AdminQueries.ChangeAdminDetails;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminProfileController
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

    private Admin admin;
    @FXML
    private ScrollPane displayPane;
    public void ShowProfile()
    {
        getAddressBox().setText(getAdmin().getAddress());
        getAddressDisplay().setText(getAdmin().getAddress());
        getEmailBox().setText(getAdmin().getEmail());
        getFirstNameBox().setText(getAdmin().getFirstName());
        getLastNameBox().setText(getAdmin().getLastName());
        getMobileNoBox().setText(getAdmin().getMobileNo());
        getNameDisplay().setText(getAdmin().getFirstName() + " " + getAdmin().getLastName());
        getPasswordBox().setText(getAdmin().getPassword());
        getPinNoBox().setText(getAdmin().getPinNo());
        getUserNameDisplay().setText(getAdmin().getUserName());
    }

    public void ChangeProfile()
    {
        ChangeAdminDetails cad = new ChangeAdminDetails();
        getAdmin().setAddress(getAddressBox().getText());
        getAdmin().setEmail(getEmailBox().getText());
        getAdmin().setFirstName(getFirstNameBox().getText());
        getAdmin().setLastName(getLastNameBox().getText());
        getAdmin().setPinNo(getPinNoBox().getText());
        getAdmin().setMobileNo(getMobileNoBox().getText());
        getAdmin().setPassword(getPasswordBox().getText());
        cad.client= getAdmin();
        try {
            getOos().writeObject(cad);
            getOos().flush();
            setAdmin((Admin) getOis().readObject());
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

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ScrollPane getDisplayPane() {
        return displayPane;
    }

    public void setDisplayPane(ScrollPane displayPane) {
        this.displayPane = displayPane;
    }
}
