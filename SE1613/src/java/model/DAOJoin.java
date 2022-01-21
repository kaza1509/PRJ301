/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        String sql = "select od.OrderID, od.ProductID, od.UnitPrice, od.Quantity, od.Discount from [Order Details] od"
                + " join Orders o on o.OrderID = od.OrderID"
                + " join Products pro on od.ProductID = pro.ProductID"
                + " join Suppliers sup on pro.SupplierID = sup.SupplierID"
                + " join Customers cus on o.CustomerID = cus.CustomerID"
                + " join Employees emp on o.EmployeeID = emp.EmployeeID";
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
        dao.listAll();
    }
}
