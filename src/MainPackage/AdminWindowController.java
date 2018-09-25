package MainPackage;

import AdminQueries.LoadAdminDetails;
import CustomerQueries.SearchFor;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class AdminWindowController
{

    public BorderPane AdminPane;
    public VBox LeftVBox;
    public VBox TopVBox;
    public TextArea SearchBar;
    FXMLLoader loader;
    public ScrollPane CentreDisplay;
    private Admin admin;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public ObjectOutputStream getOos() {
        return oos;
    }

    public void setOos(ObjectOutputStream oos) {
        this.oos = oos;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void SearchProduct()
    {
        SearchFor sf = new SearchFor();
        sf.searchProduct=SearchBar.getText();
        ArrayList<Product> prodList = new ArrayList<>();
        try {
            oos.writeObject(sf);
            oos.flush();
            prodList = (ArrayList<Product>) ois.readObject();
            showProductList(prodList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void showProductList(ArrayList<Product> prodList) {
        VBox vBox = new VBox();
        for (int i = 0; i < prodList.size(); i++)
        {

        }
    }

    public void ShowProfile() throws IOException, ClassNotFoundException {
        LoadAdminDetails lad = new LoadAdminDetails();
        lad.UserName=admin.getUserName();
        oos.writeObject(lad);
        oos.flush();
        admin = (Admin) ois.readObject();
        loader = new FXMLLoader(getClass().getResource("AdminProfile.fxml")) ;
        AdminProfileController controller;
        try
        {
            CentreDisplay= (ScrollPane) loader.load();
            controller = loader.getController();
            controller.admin =  admin;
            controller.oos = oos;
            controller.ois = ois;
            controller.ShowProfile();
            AdminPane.setCenter(CentreDisplay);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
