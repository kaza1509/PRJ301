package model;

import entity.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOOrder extends ConnectDB {

    public int addOrder(Order od) {
        int n = 0;
        String sql = "INSERT INTO [Orders]"
                + "           ([CustomerID]"
                + "           ,[EmployeeID]"
                + "           ,[OrderDate]"
                + "           ,[RequiredDate]"
                + "           ,[ShippedDate]"
                + "           ,[ShipVia]"
                + "           ,[Freight]"
                + "           ,[ShipName]"
                + "           ,[ShipAddress]"
                + "           ,[ShipCity]"
                + "           ,[ShipRegion]"
                + "           ,[ShipPostalCode]"
                + "           ,[ShipCountry])"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, od.getCustomerID());
            pre.setInt(2, od.getEmployeeId());
            pre.setString(3, od.getOrderDate());
            pre.setString(4, od.getRequiredDate());
            pre.setString(5, od.getShippedDate());
            pre.setInt(6, od.getShipVia());
            pre.setDouble(7, od.getFreight());
            pre.setString(8, od.getShipName());
            pre.setString(9, od.getShipAddress());
            pre.setString(10, od.getShipCity());
            pre.setString(11, od.getShipRegion());
            pre.setString(12, od.getShipPostalCode());
            pre.setString(13, od.getShipaCountry());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateOrder(Order od) {
        int n = 0;
        String sql = "UPDATE [dbo].[Orders]"
                + "   SET [CustomerID] = ?"
                + "      ,[EmployeeID] = ?"
                + "      ,[OrderDate] = ?"
                + "      ,[RequiredDate] = ?"
                + "      ,[ShippedDate] = ?"
                + "      ,[ShipVia] = ?"
                + "      ,[Freight] = ?"
                + "      ,[ShipName] = ?"
                + "      ,[ShipAddress] = ?"
                + "      ,[ShipCity] = ?"
                + "      ,[ShipRegion] = ?"
                + "      ,[ShipPostalCode] = ?"
                + "      ,[ShipCountry] = ?"
                + " WHERE OrderID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, od.getCustomerID());
            pre.setInt(2, od.getEmployeeId());
            pre.setString(3, od.getOrderDate());
            pre.setString(4, od.getRequiredDate());
            pre.setString(5, od.getShippedDate());
            pre.setInt(6, od.getShipVia());
            pre.setDouble(7, od.getFreight());
            pre.setString(8, od.getShipName());
            pre.setString(9, od.getShipAddress());
            pre.setString(10, od.getShipCity());
            pre.setString(11, od.getShipRegion());
            pre.setString(12, od.getShipPostalCode());
            pre.setString(13, od.getShipaCountry());
            pre.setInt(14, od.getOrderId());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Order> listAll(String sql) {
        Vector<Order> vector = new Vector<>();
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int orderId = rs.getInt(1);
                String customerID = rs.getString(2);
                int employeeId = rs.getInt(3);
                String orderDate = rs.getString(4);
                String requiredDate = rs.getString(5);
                String shippedDate = rs.getString(6);
                int shipVia = rs.getInt(7);
                double freight = rs.getDouble(8);
                String shipName = rs.getString(9);
                String shipAddress = rs.getString(10);
                String shipCity = rs.getString(11);
                String shipRegion = rs.getString(12);
                String shipPostalCode = rs.getString(13);
                String shipaCountry = rs.getString(14);

                Order od = new Order(orderId, customerID, employeeId, orderDate, requiredDate,
                        shippedDate, shipVia, freight, shipName, shipAddress, shipCity,
                        shipRegion, shipPostalCode, shipaCountry);
                vector.add(od);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int removeOrder(int orderId) {
        int n = 0;
        String constraint = "select * from Orders where OrderID not in (select OrderID from [Order Details])";
        if (checkRemoveRecord(orderId, constraint, "OrderID") == false) {
            return 0;
        }
        //delete
        String sql = "delete from Orders where OrderID = " + orderId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Order> searchOrderByCustomerId(String customerId) {
        Vector<Order> vector = new Vector<>();
        String sql = "select * from Orders where CustomerID = '" + customerId + "'";
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int orderId = rs.getInt(1);
                String customerID = rs.getString(2);
                int employeeId = rs.getInt(3);
                String orderDate = rs.getString(4);
                String requiredDate = rs.getString(5);
                String shippedDate = rs.getString(6);
                int shipVia = rs.getInt(7);
                double freight = rs.getDouble(8);
                String shipName = rs.getString(9);
                String shipAddress = rs.getString(10);
                String shipCity = rs.getString(11);
                String shipRegion = rs.getString(12);
                String shipPostalCode = rs.getString(13);
                String shipaCountry = rs.getString(14);

                Order od = new Order(orderId, customerID, employeeId, orderDate, requiredDate,
                        shippedDate, shipVia, freight, shipName, shipAddress, shipCity,
                        shipRegion, shipPostalCode, shipaCountry);
                vector.add(od);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOOrder dao = new DAOOrder();
        int n = 0;
        //--------------- insert recod -----------------
//        Order order = new Order("RICSU", 2, "20220908", "20021203", "20011212", 1, 5.3, "Express", "Hoa Lac", "Ha Noi", "Lazada", "100000", "VietNam");
//        n = dao.addOrder(order);
//        ConnectDB.getMessageSQL("Insert", n);
        //------------------ Update ---------------------
//        Order order = new Order(11082, "QUEEN", 3, "20220910", "20021203", "20011212", 2, 10, "Express VN", "Hoa Lac", "Ha Noi", "Lazada", "100000", "VietNam");
//        n = dao.updateOrder(order);
//        ConnectDB.getMessageSQL("Update", n);
        //------------------ List all -------------------
//        Vector<Order> vector = dao.listAll();
//        for (Order order : vector) {
//            System.out.println(order);
//        }
        //------------------ Remove --------------------
//        n = dao.removeOrder(11083);
//        ConnectDB.getMessageSQL("Remove", n);
        //--------- search order by CustomerId ---------
        Vector<Order> vector = dao.searchOrderByCustomerId("VICTE");
        for (Order order : vector) {
            System.out.println(order);
        }
    }
}
