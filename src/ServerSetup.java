import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;

public class ServerSetup extends Frame implements ActionListener {
    Label UserName,Password;
    TextField un,pas;
    Button submit;
    public void launch()
    {
        setLayout(new GridLayout(3,2));
        setSize(500,300);
        UserName = new Label("UserName");
        Password = new Label("Password");
        un = new TextField();
        pas = new TextField();
        submit = new Button("Submit");
        setTitle("Enter your SQL server Details");
        add(UserName);
        add(un);
        add(Password);
        add(pas);
        add(submit);
        submit.addActionListener(this);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        setVisible(true);
    }

    public static void main(String[] args)
    {
        ServerSetup ss = new ServerSetup();
        ss.launch();
    }
    public void setup() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/";
        Connection connection = DriverManager.getConnection(url, un.getText(), pas.getText());
        PreparedStatement s = connection.prepareStatement("CREATE DATABASE SacredBazzar");
        s.execute();
        s = connection.prepareStatement("USE SacredBazzar");
        s.execute();
        s = connection.prepareStatement(" CREATE TABLE `AdminTable` ( `FirstName` varchar(100) NOT NULL, `LastName` varchar(100) NOT NULL, `UserName` varchar(100) NOT NULL, `Password` varchar(100) NOT NULL, `Address` text NOT NULL, `MobileNo` varchar(11) NOT NULL, `PinNo` varchar(10) NOT NULL, `Email` varchar(100) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=latin1; ");
        s.execute();
        s = connection.prepareStatement("CREATE TABLE `CustomerTable` (\n" +
                "  `FirstName` varchar(100) NOT NULL,\n" +
                "  `LastName` varchar(100) NOT NULL,\n" +
                "  `UserName` varchar(100) NOT NULL,\n" +
                "  `Password` varchar(100) NOT NULL,\n" +
                "  `Address` text NOT NULL,\n" +
                "  `MobileNo` varchar(10) NOT NULL,\n" +
                "  `PinNo` int(10) NOT NULL,\n" +
                "  `Email` varchar(100) NOT NULL,\n" +
                "  `Products` int(100) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
        s.execute();
        s=connection.prepareStatement("CREATE TABLE `ProductsTable` (\n" +
                "  `ProductId` int(100) NOT NULL,\n" +
                "  `Retailer` varchar(100) NOT NULL,\n" +
                "  `Price` int(100) NOT NULL,\n" +
                "  `Quantity` int(11) NOT NULL,\n" +
                "  `Category` varchar(100) NOT NULL,\n" +
                "  `Description` text NOT NULL,\n" +
                "  `Discount` int(11) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
        s.execute();
        s = connection.prepareStatement("CREATE TABLE `RetailerTable` (\n" +
                "  `FirstName` varchar(1000) NOT NULL,\n" +
                "  `LastName` varchar(1000) NOT NULL,\n" +
                "  `UserName` varchar(1000) NOT NULL,\n" +
                "  `Password` varchar(1000) NOT NULL,\n" +
                "  `Address` text NOT NULL,\n" +
                "  `MobileNo` int(11) NOT NULL,\n" +
                "  `PinNo` int(10) NOT NULL,\n" +
                "  `Email` varchar(100) NOT NULL\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
        s.execute();
        s = connection.prepareStatement("CREATE TABLE `TransactionTable` (\n" +
                "  `TransactionId` int(11) NOT NULL,\n" +
                "  `ProductId` int(11) NOT NULL,\n" +
                "  `CustomerUserName` varchar(11) NOT NULL,\n" +
                "  `Time` datetime NOT NULL,\n" +
                "  `Address` text NOT NULL,\n" +
                "  `Quantity` int(11) NOT NULL,\n" +
                "  `Status` int(11) NOT NULL,\n" +
                "  `DeliveryStatus` int(11) NOT NULL DEFAULT '0'\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=latin1;");
        s.execute();
        s = connection.prepareStatement("INSERT INTO `AdminTable` (`FirstName`, `LastName`, `UserName`, `Password`, `Address`, `MobileNo`, `PinNo`, `Email`) VALUES\n" +
                "('oo', 'oo', 'admin', 'admin', '27/1', 'oo', 'oo', 'oo');");
        s.execute();
        JOptionPane.showMessageDialog(null, "Server Setup Successful! Please note that Default " +
                "Admin username is 'admin' (without quotes) and password is 'admin' (without quotes) and you can change this" +
                " by login."
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            setup();
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
            return;
        }
    }
}
