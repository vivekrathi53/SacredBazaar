package ClientFiles.RetailerFiles;

import ClientFiles.AdminFiles.AdminQueries.ChangeProduct;
import ClientFiles.AdminFiles.AdminQueries.RemoveProduct;
import ClientFiles.LoginWindow;
import ClientFiles.LogoutClient;
import ClientFiles.Product;
import ClientFiles.RetailerFiles.RetailerQueries.GetRetailerProducts;
import ClientFiles.RetailerFiles.RetailerQueries.LoadNotifications;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private RetailerProfileController controller;
    private Retailer retailer;
    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    @FXML
    private ScrollPane CentreDisplay;
    @FXML
    private BorderPane borderPane;
    @FXML
    private
    FXMLLoader loader;
    @FXML
    private
    Stage currentStage;
    @FXML
    private VBox CDisplay;

    public void ShowProfile()
    {
        setLoader(new FXMLLoader(getClass().getResource("FXML_files/RetailerProfile.fxml")));
        try
        {
            setCentreDisplay((ScrollPane) getLoader().load());
            setController(getLoader().getController());
            getController().setRetailer(getRetailer());
            getController().setOos(getOos());
            getController().setOis(getOis());
            getController().ShowProfile();
            getBorderPane().setCenter(getCentreDisplay());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public void ShowAllProducts()
    {
        GetRetailerProducts grp = new GetRetailerProducts();
        grp.UserName= getRetailer().getUserName();
        try {
            getOos().writeObject(grp);
            getOos().flush();
            getRetailer().setAllProducts((ArrayList<Product>) getOis().readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        DisplayProducts(getRetailer().getAllProducts());
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
        getCDisplay().getChildren().clear();
        int len = prodList.size();
        SplitPane[] productDetailsDisplay = new SplitPane[len];
        for(int i=0;i<len;i++)
        {
            Product prod = prodList.get(i);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_files/ProductDisplayDesign.fxml")) ;
                productDetailsDisplay[i] = (SplitPane) loader.load();
                ProductDesignController controller = loader.getController();
                controller.getBuyButton().setVisible(false);
                controller.getAddtoCart().setVisible(false);
                controller.getAddtoWishList().setVisible(false);
                controller.getEditProductBtn().setVisible(true);
                controller.getQuantityAvail().setText(controller.getQuantityAvail().getText() + prod.getQuantity());
                controller.getDiscountLabel().setText(controller.getDiscountLabel().getText() + prod.getDiscount());
                controller.getEditProductBtn().setOnAction(e -> {
                    try {
                        EditProduct(prod);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                controller.getPrice().setText(controller.getPrice().getText() + Integer.toString(prod.getPrice()));
                controller.getProductCategory().setText(prod.getProductCategory());
                controller.getProductDescription().setText(prod.getProductDescription());
                productDetailsDisplay[i].setPrefWidth(getCentreDisplay().getPrefWidth());
            } catch (IOException e) {
                e.printStackTrace();
            }
            getCDisplay().getChildren().add(productDetailsDisplay[i]);
        }
    }

    public void ShowNotifications()
    {
        LoadNotifications ln = new LoadNotifications();
        ln.UserName= getRetailer().getUserName();
        ArrayList<PendingProducts> pp = null;
        try
        {
            getOos().writeObject(ln);
            getOos().flush();
            pp = (ArrayList<PendingProducts>) getOis().readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        NotificationDesignController ndc;
        VBox vBox = getCDisplay();
        vBox.getChildren().clear();
        System.out.println(pp.size());
        for(int i = 0; i<pp.size(); i++)
        {
            setLoader(new FXMLLoader(getClass().getResource("FXML_files/NotificationDesign.fxml")));
            try
            {
                vBox.getChildren().add(getLoader().load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            ndc = getLoader().getController();
            ndc.getAddressBox().setText(ndc.getAddressBox().getText()+pp.get(i).getAddress());
            ndc.getCustomerNameBox().setText(ndc.getCustomerNameBox().getText()+pp.get(i).getCustomerName());
            ndc.getQuantityBox().setText(ndc.getQuantityBox().getText()+Integer.toString(pp.get(i).getQuantityOrdered()));
            ndc.getProductBox().setText(pp.get(i).getProductCategory());
            ndc.getMobileNo().setText(pp.get(i).getMobileNo());
            ndc.setProductId(pp.get(i).getProductId());
            ndc.setOos(getOos());
            ndc.setOis(getOis());
            ndc.getTotalAmountBox().setText(ndc.getTotalAmountBox().getText() + Integer.toString(pp.get(i).getPrice()*pp.get(i).getQuantityOrdered()));
        }
        vBox.setFillWidth(true);
    }

    public void ShowSoldProducts()
    {
        DisplayProducts(getRetailer().getProductSold());
    }

    public void AddProduct()
    {
        setLoader(new FXMLLoader(getClass().getResource("FXML_files/AddProduct.fxml")));
        try
        {
            getBorderPane().setCenter(getLoader().load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        AddProductController apc = getLoader().getController();
        apc.setRetailer(getRetailer());
        apc.setOos(getOos());
    }

    public void Logout(ActionEvent actionEvent)
    {
        LogoutClient lc =new LogoutClient();
        try {
            getOos().writeObject(lc);
            getOos().flush();
            getSocket().close();
            LoginWindow lw = new LoginWindow();
            lw.start(getCurrentStage());
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
        controller.getCategoryBox().setText(prod.getProductCategory());
        controller.getProductIdBox().setText(Integer.toString(prod.getProductId()));
        controller.getDescriptionBox().setText(prod.getProductDescription());
        controller.getDiscountBox().setText(Integer.toString(prod.getDiscount()));
        controller.getPriceBox().setText(Integer.toString(prod.getPrice()));
        controller.getQuantityBox().setText(Integer.toString(prod.getQuantity()));
        controller.getRetailerUserNameBox().setText(prod.getRetailer());
        Product p = prod;
        controller.getDeleteProduct().setOnAction(e -> RemoveProduct(p));
        controller.getProductChanges().setOnAction(e ->
        {
            p.setProductCategory((controller.getCategoryBox().getText()));
            p.setPrice(Integer.parseInt(controller.getPriceBox().getText()));
            p.setDiscount(Integer.parseInt(controller.getDiscountBox().getText()));
            p.setQuantity(Integer.parseInt(controller.getQuantityBox().getText()));
            p.setProductDescription(controller.getDescriptionBox().getText());
            p.setRetailer(controller.getRetailerUserNameBox().getText());
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
            getOos().writeObject(cp);
            getOos().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void RemoveProduct(Product p)
    {
        RemoveProduct rp = new RemoveProduct();
        rp.ProductId = p.getProductId();
        try {
            getOos().writeObject(rp);
            getOos().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public RetailerProfileController getController() {
        return controller;
    }

    public void setController(RetailerProfileController controller) {
        this.controller = controller;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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

    public ScrollPane getCentreDisplay() {
        return CentreDisplay;
    }

    public void setCentreDisplay(ScrollPane centreDisplay) {
        CentreDisplay = centreDisplay;
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public Stage getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(Stage currentStage) {
        this.currentStage = currentStage;
    }

    public VBox getCDisplay() {
        return CDisplay;
    }

    public void setCDisplay(VBox CDisplay) {
        this.CDisplay = CDisplay;
    }
}
