package model;

import entity.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOSupplier extends ConnectDB {

    //insert data  to suppliers table
    public int addSupplier(Supplier sup) {
        int n = 0;
        String sql = "INSERT INTO [Suppliers]([CompanyName],[ContactName],"
                + "[ContactTitle],[Address],[City],[Region],[PostalCode],"
                + "[Country],[Phone],[Fax],[HomePage])VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getContactName());
            pre.setString(3, sup.getContactTitle());
            pre.setString(4, sup.getAddress());
            pre.setString(5, sup.getCity());
            pre.setString(6, sup.getRegion());
            pre.setString(7, sup.getPostalCode());
            pre.setString(8, sup.getCountry());
            pre.setString(9, sup.getPhone());
            pre.setString(10, sup.getFax());
            pre.setString(11, sup.getHomePage());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //update record supplier table
    public int updateSupplier(Supplier sup) {
        int n = 0;
        String sql = "UPDATE [Suppliers]  SET [CompanyName] = ?"
                + "      ,[ContactName] = ?"
                + "      ,[ContactTitle] = ?"
                + "      ,[Address] = ?"
                + "      ,[City] = ?"
                + "      ,[Region] = ?"
                + "      ,[PostalCode] = ?"
                + "      ,[Country] = ?"
                + "      ,[Phone] = ?"
                + "      ,[Fax] = ?"
                + "      ,[HomePage] = ?"
                + " WHERE SupplierID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, sup.getCompanyName());
            pre.setString(2, sup.getContactName());
            pre.setString(3, sup.getContactTitle());
            pre.setString(4, sup.getAddress());
            pre.setString(5, sup.getCity());
            pre.setString(6, sup.getRegion());
            pre.setString(7, sup.getPostalCode());
            pre.setString(8, sup.getCountry());
            pre.setString(9, sup.getPhone());
            pre.setString(10, sup.getFax());
            pre.setString(11, sup.getHomePage());
            pre.setInt(12, sup.getSupplierId());
            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    //select all the recored in suppliers table
    public Vector<Supplier> listAll(String sql) {
        Vector<Supplier> vector = new Vector<>();
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int supplierId = rs.getInt(1);
                String companyName = rs.getString(2);
                String contactName = rs.getString(3);
                String contactTitle = rs.getString(4);
                String address = rs.getString(5);
                String city = rs.getString(6);
                String region = rs.getString(7);
                String postalCode = rs.getString(8);
                String country = rs.getString(9);
                String phone = rs.getString(10);
                String fax = rs.getString(11);
                String homePage = rs.getString(12);

                Supplier sup = new Supplier(supplierId, companyName, contactName, contactTitle,
                        address, city, region, postalCode, country, phone, fax, homePage);
                vector.add(sup);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    //delete a recored in suppliers table
    public int removeSupplier(int id) {
        int n = 0;
        //check constraint - removable
        String constaint = "select * from Suppliers where SupplierID not in (select SupplierID from Products)";
        if(checkRemoveRecord(id, constaint, "SupplierID") == false) return 0;

        //delete
        String sql = "delete from Suppliers where SupplierID=" + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOSupplier dao = new DAOSupplier();
        //--------------- insert record -----------------
//        Supplier sup = new Supplier("FPT Software 11fe", "FPT Mod", "Contact here", "Hoa Lac",
//                "Ha Noi", "North", "100000", "Viet Nam", "(+84)355166404", "#eeetrt", "#index");
//        int n = dao.addSupplier(sup);
//        ConnectDB.getMessageSQL("Insert", n);

        //------------------ Update ---------------------
//        Supplier sup = new Supplier(33, "Viettel Pay", "Nguyen Tien Khoi", "FPTS",
//        "Hoa Lac", "Ha Nam", "Bac", "100000", "Viet Nam","(+843245543567)", 
//        "#ERE34", "%rutyr");
//        int n = dao.updateSupplier(sup);
//        ConnectDB.getMessageSQL("Update", n);

        //------------------ List all -------------------
//        Vector<Supplier> listAll = dao.listAll();
//        for (Supplier supplier : listAll) {
//            System.out.println(supplier);
//        }

        //------------------ Remove --------------------
        int n = dao.removeSupplier(29);
        ConnectDB.getMessageSQL("Remove", n);
    }
}
