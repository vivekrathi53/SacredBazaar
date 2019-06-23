package ClientFiles.RetailerFiles;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class ProductDesignController
{
    private Button BuyButton;
    private Button AddtoCart;
    private Button AddtoWishList;
    private ImageView imageView;
    private Label productCategory;
    private Label price;
    private Label productDescription;
    private Button EditProductBtn;
    private Label QuantityAvail;
    private Label DiscountLabel;

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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
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

    public Label getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(Label productDescription) {
        this.productDescription = productDescription;
    }

    public Button getEditProductBtn() {
        return EditProductBtn;
    }

    public void setEditProductBtn(Button editProductBtn) {
        EditProductBtn = editProductBtn;
    }

    public Label getQuantityAvail() {
        return QuantityAvail;
    }

    public void setQuantityAvail(Label quantityAvail) {
        QuantityAvail = quantityAvail;
    }

    public Label getDiscountLabel() {
        return DiscountLabel;
    }

    public void setDiscountLabel(Label discountLabel) {
        DiscountLabel = discountLabel;
    }
}
