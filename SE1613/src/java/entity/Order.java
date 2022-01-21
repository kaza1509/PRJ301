package entity;

/**
 *
 * @author kazaf
 */
public class Order {

    private int orderId;//auto number
    private String customerID;
    private int employeeId;
    private String orderDate, requiredDate, shippedDate;
    private int shipVia;
    private double freight;
    private String shipName, shipAddress, shipCity, shipRegion, shipPostalCode, shipaCountry;

    public Order() {
    }

    public Order(int orderId, String customerID, int employeeId, String orderDate, String requiredDate,
            String shippedDate, int shipVia, double freight, String shipName, String shipAddress,
            String shipCity, String shipRegion, String shipPostalCode, String shipaCountry) {
        this.orderId = orderId;
        this.customerID = customerID;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipaCountry = shipaCountry;
    }

    public Order(String customerID, int employeeId, String orderDate, String requiredDate, String shippedDate,
            int shipVia, double freight, String shipName, String shipAddress, String shipCity, String shipRegion,
            String shipPostalCode, String shipaCountry) {
        this.customerID = customerID;
        this.employeeId = employeeId;
        this.orderDate = orderDate;
        this.requiredDate = requiredDate;
        this.shippedDate = shippedDate;
        this.shipVia = shipVia;
        this.freight = freight;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.shipCity = shipCity;
        this.shipRegion = shipRegion;
        this.shipPostalCode = shipPostalCode;
        this.shipaCountry = shipaCountry;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(String shippedDate) {
        this.shippedDate = shippedDate;
    }

    public int getShipVia() {
        return shipVia;
    }

    public void setShipVia(int shipVia) {
        this.shipVia = shipVia;
    }

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipaCountry() {
        return shipaCountry;
    }

    public void setShipaCountry(String shipaCountry) {
        this.shipaCountry = shipaCountry;
    }

    @Override
    public String toString() {
        return "Order{" + "orderId=" + orderId + ", customerID=" + customerID + ", employeeId=" + employeeId + ", orderDate=" + orderDate + ", requiredDate=" + requiredDate + ", shippedDate=" + shippedDate + ", shipVia=" + shipVia + ", freight=" + freight + ", shipName=" + shipName + ", shipAddress=" + shipAddress + ", shipCity=" + shipCity + ", shipRegion=" + shipRegion + ", shipPostalCode=" + shipPostalCode + ", shipaCountry=" + shipaCountry + '}';
    }

    
}
