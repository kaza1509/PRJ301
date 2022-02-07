package model;

import entity.OrderDetail;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOJoin extends ConnectDB {

    public Vector<OrderDetail> listAll() {
        Vector<OrderDetail> vector = new Vector<>();
        String sql = "select ord.* from Categories cate join Products pro\n"
                + "		on cate.CategoryID = pro.CategoryID join [Order Details] ord \n"
                + "		on ord.ProductID = pro.ProductID join Orders od\n"
                + "		on od.OrderID = ord.OrderID join Customers cus\n"
                + "		on cus.CustomerID = od.CustomerID";
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int productId = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getDouble(5);

                OrderDetail ord = new OrderDetail(orderId, productId, unitPrice, quantity, discount);
                vector.add(ord);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOJoin dao = new DAOJoin();
        Vector<OrderDetail> vector = dao.listAll();
        for (OrderDetail orderDetail : vector) {
            System.out.println(orderDetail);
        }
//        dao.listAll();
    }
}
