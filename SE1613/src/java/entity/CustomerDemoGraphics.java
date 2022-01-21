package entity;

/**
 *
 * @author kazaf
 */
public class CustomerDemoGraphics {

    private String customerTypeId;
    private String customerDesc;

    public CustomerDemoGraphics() {
    }

    public CustomerDemoGraphics(String customerTypeId, String customerDesc) {
        this.customerTypeId = customerTypeId;
        this.customerDesc = customerDesc;
    }

    public String getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(String customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerDesc() {
        return customerDesc;
    }

    public void setCustomerDesc(String customerDesc) {
        this.customerDesc = customerDesc;
    }

}
