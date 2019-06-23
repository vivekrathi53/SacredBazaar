package ClientFiles.AdminFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class CustomerAdminController
{
    @FXML
    private Label customern;
    @FXML
    private Label UserName;
    @FXML
    private Label CustomerSpendings;

    public Label getCustomern() {
        return customern;
    }

    public void setCustomern(Label customern) {
        this.customern = customern;
    }

    public Label getUserName() {
        return UserName;
    }

    public void setUserName(Label userName) {
        UserName = userName;
    }

    public Label getCustomerSpendings() {
        return CustomerSpendings;
    }

    public void setCustomerSpendings(Label customerSpendings) {
        CustomerSpendings = customerSpendings;
    }
}
