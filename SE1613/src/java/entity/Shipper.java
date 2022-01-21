package entity;

/**
 *
 * @author kazaf
 */
public class Shipper {

    private int shipperId;//auto number
    private String companyName, phone;

    public Shipper() {
    }

    public Shipper(int shipperId, String companyName, String phone) {
        this.shipperId = shipperId;
        this.companyName = companyName;
        this.phone = phone;
    }

    public Shipper(String companyName, String phone) {
        this.companyName = companyName;
        this.phone = phone;
    }

    public int getShipperId() {
        return shipperId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Shipper{" + "shipperId=" + shipperId + ", companyName=" + companyName + ", phone=" + phone + '}';
    }

    
}
