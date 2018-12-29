package MainPackage;

import AdminQueries.ChangeProduct;
import AdminQueries.RemoveProduct;
import RetailerQueries.GetRetailerProducts;
import RetailerQueries.LoadNotifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    Stage currentStage;
    public VBox CDisplay;
    public void ShowProfile()
    {
        loader = new FXMLLoader(getClass().getResource("FXML_files/RetailerProfile.fxml")) ;
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

    /*public void buildGraph() throws IOException, SQLException, ClassNotFoundException
    {
        LineChart<?,?> MainDisplay;
        loader = new FXMLLoader(getClass().getResource("graph.fxml")) ;
        MainDisplay  = (LineChart<?,?>) loader.load();
        graphcontroller controller = loader.getController();

        controller.ois=ois;
        controller.oos=oos;
        controller.username = retailer.getUserName();
        controller.startgraph();
        borderPane.setCenter(MainDisplay);
    }*/

    public void DisplayProducts(ArrayList<Product> prodList)
    {
        CDisplay.getChildren().clear();
        int len = prodList.size();
        SplitPane[] productDetailsDisplay = new SplitPane[len];
        for(int i=0;i<len;i++)
        {
            Product prod = prodList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/ProductDisplayDesign.fxml")) ;
                productDetailsDisplay[i] = (SplitPane) loader.load();
                ProductDesignController controller = loader.getController();
                controller.BuyButton.setVisible(false);
                controller.AddtoCart.setVisible(false);
                controller.AddtoWishList.setVisible(false);
                controller.EditProductBtn.setVisible(true);
                controller.QuantityAvail.setText(controller.QuantityAvail.getText() + prod.getQuantity());
                controller.DiscountLabel.setText(controller.DiscountLabel.getText() + prod.getDiscount());
                controller.EditProductBtn.setOnAction(e -> {
                    try {
                        EditProduct(prod);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                controller.price.setText(controller.price.getText() + Integer.toString(prod.getPrice()));
                controller.productCategory.setText(prod.getProductCategory());
                controller.productDescription.setText(prod.getProductDescription());
                productDetailsDisplay[i].setPrefWidth(CentreDisplay.getPrefWidth());
            } catch (IOException e) {
                e.printStackTrace();
            }
            CDisplay.getChildren().add(productDetailsDisplay[i]);
        }
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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        NotificationDesignController ndc;
        VBox vBox = CDisplay;
        vBox.getChildren().clear();
        System.out.println(pp.size());
        for(int i = 0; i<pp.size(); i++)
        {
            loader = new FXMLLoader(getClass().getResource("FXML_files/NotificationDesign.fxml"));
            try
            {
                vBox.getChildren().add(loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ndc = loader.getController();
            ndc.AddressBox.setText(ndc.AddressBox.getText()+pp.get(i).getAddress());
            ndc.customerNameBox.setText(ndc.customerNameBox.getText()+pp.get(i).getCustomerName());
            ndc.QuantityBox.setText(ndc.QuantityBox.getText()+Integer.toString(pp.get(i).getQuantityOrdered()));
            ndc.productBox.setText(pp.get(i).getProductCategory());
            ndc.MobileNo.setText(pp.get(i).getMobileNo());
            ndc.ProductId = pp.get(i).getProductId();
            ndc.oos=oos;
            ndc.ois=ois;
            ndc.TotalAmountBox.setText(ndc.TotalAmountBox.getText() + Integer.toString(pp.get(i).getPrice()*pp.get(i).getQuantityOrdered()));
        }
        vBox.setFillWidth(true);
    }

    public void ShowSoldProducts()
    {
        DisplayProducts(retailer.getProductSold());
    }

    public void AddProduct()
    {
        loader = new FXMLLoader(getClass().getResource("FXML_files/AddProduct.fxml"));
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

    public void Logout(ActionEvent actionEvent)
    {
        LogoutClient lc =new LogoutClient();
        try {
            oos.writeObject(lc);
            oos.flush();
            socket.close();
            LoginWindow lw = new LoginWindow();
            lw.start(currentStage);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void EditProduct(Product prod) throws IOException {
        Stage s = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/ProductInfo.fxml"));
        VBox display = loader.load();
        ProductInfoController controller = loader.getController();
        controller.CategoryBox.setText(prod.getProductCategory());
        controller.ProductIdBox.setText(Integer.toString(prod.getProductId()));
        controller.DescriptionBox.setText(prod.getProductDescription());
        controller.DiscountBox.setText(Integer.toString(prod.getDiscount()));
        controller.PriceBox.setText(Integer.toString(prod.getPrice()));
        controller.QuantityBox.setText(Integer.toString(prod.getQuantity()));
        controller.RetailerUserNameBox.setText(prod.getRetailer());
        Product p = prod;
        controller.DeleteProduct.setOnAction(e -> RemoveProduct(p));
        controller.ProductChanges.setOnAction(e ->
        {
            p.setProductCategory((controller.CategoryBox.getText()));
            p.setPrice(Integer.parseInt(controller.PriceBox.getText()));
            p.setDiscount(Integer.parseInt(controller.DiscountBox.getText()));
            p.setQuantity(Integer.parseInt(controller.QuantityBox.getText()));
            p.setProductDescription(controller.DescriptionBox.getText());
            p.setRetailer(controller.RetailerUserNameBox.getText());
            SaveChanges(p);
        });
        s.setScene(new Scene(display));
        s.show();
    }

    private void SaveChanges(Product prod)
    {
        ChangeProduct cp = new ChangeProduct();
        cp.product = prod;
        try {
            oos.writeObject(cp);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RemoveProduct(Product p)
    {
        RemoveProduct rp = new RemoveProduct();
        rp.ProductId = p.getProductId();
        try {
            oos.writeObject(rp);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
