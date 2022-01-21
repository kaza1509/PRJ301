package entity;

/**
 *
 * @author kazaf
 */
public class Product {

    private int productId;//auto number
    private String productName;
    private int supplierId, categoryId;
    private String quantityPerUnit;
    private double unitPrice;
    private int unitsInStock, unitsOnOrder, reOrderLevel;
    private int discontinued;

    public Product() {
    }

    public Product(int productId, String productName, int supplierId, int categoryId, String quantityPerUnit,
            double unitPrice, int unitsInStock, int unitsOnOrder, int reOrderLevel, int discontinued) {
        this.productId = productId;
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reOrderLevel = reOrderLevel;
        this.discontinued = discontinued;
    }

    public Product(String productName, int supplierId, int categoryId, String quantityPerUnit, double unitPrice,
            int unitsInStock, int unitsOnOrder, int reOrderLevel, int discontinued) {
        this.productName = productName;
        this.supplierId = supplierId;
        this.categoryId = categoryId;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.unitsInStock = unitsInStock;
        this.unitsOnOrder = unitsOnOrder;
        this.reOrderLevel = reOrderLevel;
        this.discontinued = discontinued;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getQuantityPerUnit() {
        return quantityPerUnit;
    }

    public void setQuantityPerUnit(String quantityPerUnit) {
        this.quantityPerUnit = quantityPerUnit;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public int getUnitsOnOrder() {
        return unitsOnOrder;
    }

    public void setUnitsOnOrder(int unitsOnOrder) {
        this.unitsOnOrder = unitsOnOrder;
    }

    public int getReOrderLevel() {
        return reOrderLevel;
    }

    public void setReOrderLevel(int reOrderLevel) {
        this.reOrderLevel = reOrderLevel;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", productName=" + productName + ", supplierId=" + supplierId + ", categoryId=" + categoryId + ", quantityPerUnit=" + quantityPerUnit + ", unitPrice=" + unitPrice + ", unitsInStock=" + unitsInStock + ", unitsOnOrder=" + unitsOnOrder + ", reOrderLevel=" + reOrderLevel + ", discontinued=" + discontinued + '}';
    }

    
}
