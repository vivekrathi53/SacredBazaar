package ClientFiles.RetailerFiles;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class ProductDisplayController
{
    @FXML
    private Button BuyButton;
    @FXML
    private Button AddtoCart;
    @FXML
    private Button AddtoWishList;
    @FXML
    private ImageView ProductImage;
    @FXML
    private Label productCategory;
    @FXML
    private Label price;
    @FXML
    private TextArea productDescription;
    @FXML
    private Label DiscountLabel;
    @FXML
    private Label RetailerName;

    public Button getBuyButton() {
        return BuyButton;
    }

    public void setBuyButton(Button buyButton) {
        BuyButton = buyButton;
    }

    public Button getAddtoCart() {
        return AddtoCart;
    }

    public void setAddtoCart(Button addtoCart) {
        AddtoCart = addtoCart;
    }

    public Button getAddtoWishList() {
        return AddtoWishList;
    }

    public void setAddtoWishList(Button addtoWishList) {
        AddtoWishList = addtoWishList;
    }

    public ImageView getProductImage() {
        return ProductImage;
    }

    public void setProductImage(ImageView productImage) {
        ProductImage = productImage;
    }

    public Label getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Label productCategory) {
        this.productCategory = productCategory;
    }

    public Label getPrice() {
        return price;
    }

    public void setPrice(Label price) {
        this.price = price;
    }

    public TextArea getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(TextArea productDescription) {
        this.productDescription = productDescription;
    }

    public Label getDiscountLabel() {
        return DiscountLabel;
    }

    public void setDiscountLabel(Label discountLabel) {
        DiscountLabel = discountLabel;
    }

    public Label getRetailerName() {
        return RetailerName;
    }

    public void setRetailerName(Label retailerName) {
        RetailerName = retailerName;
    }
}
