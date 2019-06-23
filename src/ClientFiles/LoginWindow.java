package ClientFiles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginWindow extends Application {
    private Stage window;
    private FXMLLoader loader;
    private LoginWindowController controller;
    private AnchorPane MainDisplay;
    @Override

    public void start(Stage primaryStage) throws Exception
    {
        setLoader(new FXMLLoader(getClass().getResource("FXML_files/LoginWindowDesign.fxml")));
        setController(getLoader().getController());
        setMainDisplay((AnchorPane) getLoader().load());
        primaryStage.setScene(new Scene(getMainDisplay()));
        setWindow(primaryStage);
        getWindow().setTitle("Log in Page");
        getWindow().show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public FXMLLoader getLoader() {
        return loader;
    }

    public void setLoader(FXMLLoader loader) {
        this.loader = loader;
    }

    public LoginWindowController getController() {
        return controller;
    }

    public void setController(LoginWindowController controller) {
        this.controller = controller;
    }

    public AnchorPane getMainDisplay() {
        return MainDisplay;
    }

    public void setMainDisplay(AnchorPane mainDisplay) {
        MainDisplay = mainDisplay;
    }
}
