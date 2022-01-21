package entity;

/**
 *
 * @author kazaf
 */
public class OrderDetail {

    private int orderId, productId;
    private double unitPrice;
    private int quantity;
    private double discount;

    public OrderDetail() {
    }

    public OrderDetail(int orderId, int productId, double unitPrice, int quantity, double discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.discount = discount;
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

    @Override
    public String toString() {
        return "OrderDetail{" + "orderId=" + orderId + ", productId=" + productId + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", discount=" + discount + '}';
    }

    
}
