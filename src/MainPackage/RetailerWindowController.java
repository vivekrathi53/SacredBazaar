package MainPackage;

import RetailerQueries.GetRetailerProducts;
import RetailerQueries.LoadNotifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class RetailerWindowController
{
    public RetailerProfileController controller;
    public Retailer retailer;
    public Socket socket;
    public ObjectInputStream ois;
    public ObjectOutputStream oos;
    public ScrollPane CentreDisplay;
    public BorderPane borderPane;
    FXMLLoader loader;
    public void ShowProfile()
    {
        loader = new FXMLLoader(getClass().getResource("RetailerProfile.fxml")) ;
        try
        {
            CentreDisplay = (ScrollPane) loader.load();
            controller = loader.getController();
            controller.retailer= retailer;
            controller.oos = oos;
            controller.ois = ois;
            controller.ShowProfile();
            borderPane.setCenter(CentreDisplay);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public void ShowAllProducts()
    {
        GetRetailerProducts grp = new GetRetailerProducts();
        grp.UserName=retailer.getUserName();
        try {
            oos.writeObject(grp);
            oos.flush();
            retailer.setAllProducts((ArrayList<Product>) ois.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DisplayProducts(retailer.getAllProducts());
    }
    public void DisplayProducts(ArrayList<Product> prodList)
    {
        VBox CDisplay = new VBox();
        int len = prodList.size();
        SplitPane[] productDetailsDisplay = new SplitPane[len];
        for(int i=0;i<len;i++)
        {
            Product prod = prodList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ProductDisplayDesign.fxml")) ;
                productDetailsDisplay[i] = (SplitPane) loader.load();
                ProductDesignController controller = loader.getController();
                controller.BuyButton.setVisible(false);
                controller.AddtoCart.setVisible(false);
                controller.AddtoWishList.setVisible(false);
                controller.price.setText(controller.price.getText() + Integer.toString(prod.getPrice()));
                controller.productCategory.setText(prod.getProductCategory());
                controller.productDescription.setText(prod.getProductDescription());
                productDetailsDisplay[i].setPrefWidth(CentreDisplay.getPrefWidth());
            } catch (IOException e) {
                e.printStackTrace();
            }
            CDisplay.getChildren().add(productDetailsDisplay[i]);

        }
        borderPane.setCenter(CDisplay);
    }

    public void ShowNotifications()
    {
        LoadNotifications ln = new LoadNotifications();
        ln.UserName=retailer.getUserName();
        ArrayList<PendingProducts> pp = null;
        try
        {
            oos.writeObject(ln);
            oos.flush();
            pp = (ArrayList<PendingProducts>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        NotificationDesignController ndc;
        VBox vBox = new VBox();
        for(int i=0;i<pp.size();i++)
        {
            loader = new FXMLLoader(getClass().getResource("NotificationDesign.fxml"));
            try
            {
                vBox.getChildren().add(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ndc = loader.getController();
            ndc.AddressBox.setText(pp.get(i).getAddress());
            ndc.customerNameBox.setText(pp.get(i).getCustomerName());
            ndc.QuantityBox.setText(Integer.toString(pp.get(i).getQuantityOrdered()));
            ndc.productBox.setText(pp.get(i).getProductCategory());
            ndc.TotalAmountBox.setText(Integer.toString(pp.get(i).getPrice()*pp.get(i).getQuantityOrdered()));
        }
        borderPane.setCenter(vBox);
    }

    public void ShowSoldProducts()
    {
        DisplayProducts(retailer.getProductSold());
    }

    public void AddProduct()
    {
        loader = new FXMLLoader(getClass().getResource("AddProduct.fxml"));
        try
        {
            borderPane.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        AddProductController apc = loader.getController();
        apc.retailer=retailer;
        apc.oos=oos;


    }
}
