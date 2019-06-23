package ClientFiles.RetailerFiles;

import ClientFiles.RetailerFiles.RetailerQueries.DeliveredProduct;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NotificationDesignController
{
    private Label MobileNo;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private Label productBox;
    private Label customerNameBox;
    private Label AddressBox;
    private Label QuantityBox;
    private Label TotalAmountBox;
    private int ProductId;
    public void Delivered()
    {
        DeliveredProduct dp = new DeliveredProduct();
        dp.productId = getProductId();
        try {
            getOos().writeObject(dp);
            getOos().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CancelDelivery()
    {

    }

    public Label getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(Label mobileNo) {
        MobileNo = mobileNo;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public Label getProductBox() {
        return productBox;
    }

    public void setProductBox(Label productBox) {
        this.productBox = productBox;
    }

    public Label getCustomerNameBox() {
        return customerNameBox;
    }

    public void setCustomerNameBox(Label customerNameBox) {
        this.customerNameBox = customerNameBox;
    }

    public Label getAddressBox() {
        return AddressBox;
    }

    public void setAddressBox(Label addressBox) {
        AddressBox = addressBox;
    }

    public Label getQuantityBox() {
        return QuantityBox;
    }

    public void setQuantityBox(Label quantityBox) {
        QuantityBox = quantityBox;
    }

    public Label getTotalAmountBox() {
        return TotalAmountBox;
    }

    public void setTotalAmountBox(Label totalAmountBox) {
        TotalAmountBox = totalAmountBox;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }
}
