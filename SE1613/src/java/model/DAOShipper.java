package model;

import entity.Shipper;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kazaf
 */
public class DAOShipper extends ConnectDB {

    public int addShipper(Shipper ship) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Shippers]"
                + "           ([CompanyName]"
                + "           ,[Phone])"
                + "     VALUES(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, ship.getCompanyName());
            pre.setString(2, ship.getPhone());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateShipper(Shipper ship) {
        int n = 0;
        String sql = "UPDATE [dbo].[Shippers]"
                + "        SET [CompanyName] = ?"
                + "           ,[Phone] = ?"
                + "      WHERE ShipperID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setString(1, ship.getCompanyName());
            pre.setString(2, ship.getPhone());
            pre.setInt(3, ship.getShipperId());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Shipper> listAll() {
        Vector<Shipper> vector = new Vector<>();
        String sql = "select * from Shippers";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()) {
                int shipperId = rs.getInt(1);
                String companyName = rs.getString(2);
                String phone = rs.getString(3);
                
                Shipper ship = new Shipper(shipperId, companyName, phone);
                vector.add(ship);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int removeShipper(int ShipperId) {
        int n = 0;
        String constraint = "select * from Shippers where ShipperID not in (select ShipVia from Orders)";
        if(checkRemoveRecord(ShipperId, constraint, "ShipperID") == false) return 0;
        String sql = "delete from Shippers where ShipperID = "+ShipperId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOShipper dao = new DAOShipper();
        int n = 0;
        //--------------- insert recship -----------------
//        n = dao.addShipper(new Shipper("Vin Press", "(+84)355164504"));
//        ConnectDB.getMessageSQL("Insert", n);

        //------------------ Update ---------------------
//        Shipper ship = new Shipper(4, "Viettel Pay", "0974983253");
//        n = dao.updateShipper(ship);
//        ConnectDB.getMessageSQL("Update", n);

        //------------------ List all -------------------
//        Vector<Shipper> vector = dao.listAll();
//        for (Shipper shipper : vector) {
//            System.out.println(shipper);
//        }

        //------------------ Remove --------------------
        n = dao.removeShipper(1);
        ConnectDB.getMessageSQL("Remove", n);
    }
}
