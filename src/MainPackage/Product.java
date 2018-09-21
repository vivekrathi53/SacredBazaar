package MainPackage;

import java.io.Serializable;

public class Product implements Serializable
{
    private String ProductId;
    private int retailer;
    private int Price;
    private int Quantity;
    private String ProductCategory;
    private String ProductDescription;
    private int Status;// 1 for bought 2 for in cart 3 for in wishlist

    public int getStatus()
    {
        return Status;
    }

    public void setStatus(int status)
    {
        Status = status;
    }

    // public Image
    private int Discount;
    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        this.ProductId = productId;
    }

    public int getRetailer() {
        return retailer;
    }

    public void setRetailer(int retailer) {
        this.retailer = retailer;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity = quantity;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        this.ProductCategory = productCategory;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        this.ProductDescription = productDescription;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        this.Discount = discount;
    }
    public Product(String Productid, int retailer, int price, int Quantity, String ProductCategory, String ProductDescription, int Discount)
    {
        this.ProductId =Productid;
        this.Discount=Discount;
        this.Price=Price;
        this.Quantity=Quantity;
        this.ProductCategory=ProductCategory;
        this.ProductDescription=ProductDescription;
        this.retailer=retailer;
    }
}