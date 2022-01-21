package model;

import entity.OrderDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOOrderDetail extends ConnectDB {
    
    public int addOrderDetail(OrderDetail ord) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[Order Details]"
                + "           ([OrderID]"
                + "           ,[ProductID]"
                + "           ,[UnitPrice]"
                + "           ,[Quantity]"
                + "           ,[Discount])"
                + "     VALUES(?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, ord.getOrderId());
            pre.setInt(2, ord.getProductId());
            pre.setDouble(3, ord.getUnitPrice());
            pre.setInt(4, ord.getQuantity());
            pre.setDouble(5, ord.getDiscount());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public int updateOrderDetail(OrderDetail ord) {
        int n = 0;
        String sql = "UPDATE [dbo].[Order Details]"
                + "   SET [UnitPrice] = ?"
                + "      ,[Quantity] = ?"
                + "      ,[Discount] = ?"
                + " WHERE OrderID = ? and ProductID=?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            
            pre.setDouble(1, ord.getUnitPrice());
            pre.setInt(2, ord.getQuantity());
            pre.setDouble(3, ord.getDiscount());
            pre.setInt(4, ord.getOrderId());
            pre.setInt(5, ord.getProductId());
            
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<OrderDetail> listAll() {
        Vector<OrderDetail> vector = new Vector<>();
        String sql = "select * from [Order Details]";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int orderId = rs.getInt(1);                
                int productId = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getInt(5);
                
                OrderDetail ord = new OrderDetail(orderId, productId, unitPrice, quantity, discount);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }
    
    public int removeOrderDetail(int orderId, int productId) {
        int n = 0;
        String sql = "delete from [Order Details] where OrderID = "+orderId+" and ProductID = "+productId+"";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public static void main(String[] args) {
        DAOOrderDetail dao = new DAOOrderDetail();
        int n = 0;
        //--------------- insert record -----------------
        n = dao.addOrderDetail(new OrderDetail(10248, 11, 30, 34, 0));
        ConnectDB.getMessageSQL("Insert", n);

        //------------------ Update ---------------------
//        n = dao.updateOrderDetail(new OrderDetail(10249, 11, 3000, 3400, 0));
//        ConnectDB.getMessageSQL("Update", n);

        //------------------ List all -------------------
//        Vector<OrderDetail> vector = dao.listAll();
//        for (OrderDetail orderDetail : vector) {
//            System.out.println(orderDetail);
//        }

        //------------------ Remove --------------------
//        n = dao.removeOrderDetail(10248, 11);
//        ConnectDB.getMessageSQL("Remove", n);
    }
}
