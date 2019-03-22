package MainPackage;

import RetailerQueries.AddProductToSell;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class AddProductController 
{

    public TextField productCategoryBox;
    public TextField discountBox;
    public TextField priceBox;
    public TextField quantityBox;
    public TextArea descriptionBox;
    public Button AddProductButton;
    public Retailer retailer;
    public ObjectOutputStream oos;
    public void AddProduct()
    {
        Product prod = new Product(-1,retailer.getUserName(),Integer.parseInt(priceBox.getText()),Integer.parseInt(quantityBox.getText()),productCategoryBox.getText(),descriptionBox.getText(),Integer.parseInt(discountBox.getText()));
        AddProductToSell adts = new AddProductToSell();
        adts.product=prod;
        try {
            oos.writeObject(adts);
            oos.flush();
            System.out.println("Send the product details");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
