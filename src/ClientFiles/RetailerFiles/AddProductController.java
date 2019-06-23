package ClientFiles.RetailerFiles;

import ClientFiles.Product;
import ClientFiles.RetailerFiles.RetailerQueries.AddProductToSell;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class AddProductController 
{

    private TextField productCategoryBox;
    private TextField discountBox;
    private TextField priceBox;
    private TextField quantityBox;
    private TextArea descriptionBox;
    private Button AddProductButton;
    private Retailer retailer;
    private ObjectOutputStream oos;
    public void AddProduct()
    {
        Product prod = new Product(-1, getRetailer().getUserName(),Integer.parseInt(getPriceBox().getText()),Integer.parseInt(getQuantityBox().getText()), getProductCategoryBox().getText(), getDescriptionBox().getText(),Integer.parseInt(getDiscountBox().getText()));
        AddProductToSell adts = new AddProductToSell();
        adts.product=prod;
        try {
            getOos().writeObject(adts);
            getOos().flush();
            System.out.println("Send the product details");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextField getProductCategoryBox() {
        return productCategoryBox;
    }

    public void setProductCategoryBox(TextField productCategoryBox) {
        this.productCategoryBox = productCategoryBox;
    }

    public TextField getDiscountBox() {
        return discountBox;
    }

    public void setDiscountBox(TextField discountBox) {
        this.discountBox = discountBox;
    }

    public TextField getPriceBox() {
        return priceBox;
    }

    public void setPriceBox(TextField priceBox) {
        this.priceBox = priceBox;
    }

    public TextField getQuantityBox() {
        return quantityBox;
    }

    public void setQuantityBox(TextField quantityBox) {
        this.quantityBox = quantityBox;
    }

    public TextArea getDescriptionBox() {
        return descriptionBox;
    }

    public void setDescriptionBox(TextArea descriptionBox) {
        this.descriptionBox = descriptionBox;
    }

    public Button getAddProductButton() {
        return AddProductButton;
    }

    public void setAddProductButton(Button addProductButton) {
        AddProductButton = addProductButton;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }
}
