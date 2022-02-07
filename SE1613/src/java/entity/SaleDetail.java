package entity;

/**
 *
 * @author kazaf
 */
public class SaleDetail {
    private int orderId, productId;
    private double unitPrice;
    private int quantity;
    private double discount;
    private int categoryId;
    private String categoryName;
    private String ProductName;
    private String customerId, companyName;

    public SaleDetail() {
    }

    public SaleDetail(int orderId, int productId, double unitPrice, int quantity, double discount, int categoryId, String categoryName, String ProductName, String customerId, String companyName) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.ProductName = ProductName;
        this.customerId = customerId;
        this.companyName = companyName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "SaleDetail{" + "orderId=" + orderId + ", productId=" + productId + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", discount=" + discount + ", categoryId=" + categoryId + ", categoryName=" + categoryName + ", ProductName=" + ProductName + ", customerId=" + customerId + ", companyName=" + companyName + '}';
    }

}
