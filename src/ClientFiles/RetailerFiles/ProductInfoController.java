package ClientFiles.RetailerFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ProductInfoController
{
    @FXML
    private TextField ProductIdBox;
    @FXML
    private TextField RetailerUserNameBox;
    @FXML
    private TextField PriceBox;
    @FXML
    private TextField QuantityBox;
    @FXML
    private TextField CategoryBox;
    @FXML
    private TextField DescriptionBox;
    @FXML
    private TextField DiscountBox;
    @FXML
    private Button ProductChanges;
    @FXML
    private Button DeleteProduct;

    public TextField getProductIdBox() {
        return ProductIdBox;
    }

    public void setProductIdBox(TextField productIdBox) {
        ProductIdBox = productIdBox;
    }

    public TextField getRetailerUserNameBox() {
        return RetailerUserNameBox;
    }

    public void setRetailerUserNameBox(TextField retailerUserNameBox) {
        RetailerUserNameBox = retailerUserNameBox;
    }

    public TextField getPriceBox() {
        return PriceBox;
    }

    public void setPriceBox(TextField priceBox) {
        PriceBox = priceBox;
    }

    public TextField getQuantityBox() {
        return QuantityBox;
    }

    public void setQuantityBox(TextField quantityBox) {
        QuantityBox = quantityBox;
    }

    public TextField getCategoryBox() {
        return CategoryBox;
    }

    public void setCategoryBox(TextField categoryBox) {
        CategoryBox = categoryBox;
    }

    public TextField getDescriptionBox() {
        return DescriptionBox;
    }

    public void setDescriptionBox(TextField descriptionBox) {
        DescriptionBox = descriptionBox;
    }

    public TextField getDiscountBox() {
        return DiscountBox;
    }

    public void setDiscountBox(TextField discountBox) {
        DiscountBox = discountBox;
    }

    public Button getProductChanges() {
        return ProductChanges;
    }

    public void setProductChanges(Button productChanges) {
        ProductChanges = productChanges;
    }

    public Button getDeleteProduct() {
        return DeleteProduct;
    }

    public void setDeleteProduct(Button deleteProduct) {
        DeleteProduct = deleteProduct;
    }
}
