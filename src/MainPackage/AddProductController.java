package MainPackage;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AddProductController 
{

    public TextField productCategoryBox;
    public TextField discountBox;
    public TextField priceBox;
    public TextField quantityBox;
    public TextArea descriptionBox;
    public Button AddProductButton;
    Retailer retailer;
    public void AddProduct()
    {
        Product prod = new Product(null,retailer.getUserName(),Integer.parseInt(priceBox.getText()),Integer.parseInt(quantityBox.getText()),productCategoryBox.getText(),descriptionBox.getText(),Integer.parseInt(discountBox.getText()));

    }
}
