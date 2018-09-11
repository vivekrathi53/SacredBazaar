package sample;

public class Product
{
    private String ProductId;
    private Retailer retailer;
    private int Price;
    private int Quantity;
    private String ProductCategory;
    private String ProductDescription;
    // public Image
    private int Discount;
    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        this.ProductId = productId;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
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
    Product(String Productid,Retailer retailer,int price,int Quantity,String ProductCategory,String ProductDescription,int Discount)
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