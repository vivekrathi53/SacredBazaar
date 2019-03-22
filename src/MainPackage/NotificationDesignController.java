package MainPackage;

import RetailerQueries.DeliveredProduct;
import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class NotificationDesignController
{
    public Label MobileNo;
    protected ObjectInputStream ois;
    protected ObjectOutputStream oos;
    public Label productBox;
    public Label customerNameBox;
    public Label AddressBox;
    public Label QuantityBox;
    public Label TotalAmountBox;
    public int ProductId;
    public void Delivered()
    {
        DeliveredProduct dp = new DeliveredProduct();
        dp.productId = ProductId;
        try {
            oos.writeObject(dp);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CancelDelivery()
    {

    }
}
