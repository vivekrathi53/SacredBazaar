package MainPackage;

import java.sql.Time;

public class PendingProducts extends Product
{
    private int DeliveryStatus;
    private Time time;
    private int TransactionId;
    private String CustomerName;
    private String Address;
    private int QuantityOrdered;
    private String MobileNo;

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public int getQuantityOrdered() {
        return QuantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        QuantityOrdered = quantityOrdered;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getDeliveryStatus()
    {
        return DeliveryStatus;
    }

    public void setDeliveryStatus(int deliveryStatus)
    {
        DeliveryStatus = deliveryStatus;
    }

    public Time getTime()
    {
        return time;
    }

    public void setTime(Time time)
    {
        this.time = time;
    }

    public int getTransactionId()
    {
        return TransactionId;
    }

    public void setTransactionId(int transactionId)
    {
        TransactionId = transactionId;
    }

    public String getCustomerName()
    {
        return CustomerName;
    }

    public void setCustomerName(String customerName)
    {
        CustomerName = customerName;
    }

    public PendingProducts(int Productid, String retailer, int price, int Quantity, String ProductCategory, String ProductDescription, int Discount)
    {
        super(Productid, retailer, price, Quantity, ProductCategory, ProductDescription, Discount);
    }

}
