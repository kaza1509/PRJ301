package model;

import entity.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOCustomer extends ConnectDB {

    public int addCustomer(Customer cus) {
        int n = 0;
        String sql = "INSERT INTO [Customers]"
                + "           ([CustomerID]"
                + "           ,[CompanyName]"
                + "           ,[ContactName]"
                + "           ,[ContactTitle]"
                + "           ,[Address]"
                + "           ,[City]"
                + "           ,[Region]"
                + "           ,[PostalCode]"
                + "           ,[Country]"
                + "           ,[Phone]"
                + "           ,[Fax])"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cus.getCustomerId());
            pre.setString(2, cus.getCompanyName());
            pre.setString(3, cus.getContactName());
            pre.setString(4, cus.getContactTitle());
            pre.setString(5, cus.getAddress());
            pre.setString(6, cus.getCity());
            pre.setString(7, cus.getRegion());
            pre.setString(8, cus.getPotalCode());
            pre.setString(9, cus.getCountry());
            pre.setString(10, cus.getPhone());
            pre.setString(11, cus.getFax());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateCustomer(Customer cus) {
        int n = 0;
        String sql = "UPDATE [dbo].[Customers]"
                + "   SET [CompanyName] = ?"
                + "      ,[ContactName] = ?"
                + "      ,[ContactTitle] = ?"
                + "      ,[Address] = ?"
                + "      ,[City] = ?"
                + "      ,[Region] = ?"
                + "      ,[PostalCode] = ? "
                + "      ,[Country] = ?"
                + "      ,[Phone] = ?"
                + "      ,[Fax] = ?"
                + " WHERE [CustomerID] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cus.getCompanyName());
            pre.setString(2, cus.getContactName());
            pre.setString(3, cus.getContactTitle());
            pre.setString(4, cus.getAddress());
            pre.setString(5, cus.getCity());
            pre.setString(6, cus.getRegion());
            pre.setString(7, cus.getPotalCode());
            pre.setString(8, cus.getCountry());
            pre.setString(9, cus.getPhone());
            pre.setString(10, cus.getFax());
            pre.setString(11, cus.getCustomerId());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Customer> listAll(String sql) {
        Vector<Customer> vector = new Vector<>();
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                String customerId = rs.getString(1);
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String potalCode = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);
                
                Customer cus = new Customer(customerId, companyName, contactName, contactTitle,
                        address, city, region, potalCode, country, phone, fax);
                vector.add(cus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int removeCustomer(String customerId) {
        int n = 0;
        String constraint01 = "select * from Customers where CustomerID not in "
                + "(select CustomerID from Orders)";
        String constraint02 = "select * from Customers where CustomerID not in "
                + "(select CustomerID from CustomerCustomerDemo)";
        //check foreign key
        if(checkRemoveRecord(customerId, constraint01, "CustomerID") == false||
            checkRemoveRecord(customerId, constraint02, "CustomerID") == false) {
            return 0;
        }
        //delete id
        String sql = "delete from Customers where CustomerID = '"+customerId+"'";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOCustomer dao = new DAOCustomer();
        int n = 0;
        //--------------- insert reccus -----------------
//        Customer cus = new Customer("AAAAA", "FPT Software", "Software","None 1","Hoa Lac 1", 
//                "Ha Noi 1", "1", "435456", "VietNam", "(+84)344555678", "");
//        n = dao.addCustomer(cus);
//        ConnectDB.getMessageSQL("Insert", n);
        //------------------ Update ---------------------
//        Customer cus = new Customer("AAAAA", "FPT Software", "Software","None 1","Hoa Lac 1", 
//                "Ha Noi 1", "1", "435456", "VietNam", "(+84)344555678", "035524243");
//        n = dao.updateCustomer(cus);
//        ConnectDB.getMessageSQL("Update", n);
        //------------------ List all -------------------
//        Vector<Customer> vector = dao.listAll();
//        for (Customer customer : vector) {
//            System.out.println(customer);
//        }
        //------------------ Remove --------------------
        n = dao.removeCustomer("AAAAA");
        ConnectDB.getMessageSQL("Remove", n);
    }
}
