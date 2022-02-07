package model;

import entity.SaleDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOSaleDetail extends ConnectDB {

    public Vector<SaleDetail> getAll() {
        Vector<SaleDetail> list = new Vector<>();
        PreparedStatement ps;
        String sql = "select \n"
                + "	ord.OrderID, \n"
                + "	ord.ProductID, \n"
                + "	ord.UnitPrice, \n"
                + "	ord.Quantity, \n"
                + "	ord.Discount,\n"
                + "	c.CategoryID,\n"
                + "	c.CategoryName,\n"
                + "	pro.ProductName,\n"
                + "	cus.CustomerID,\n"
                + "	cus.CompanyName\n"
                + "from Categories c join Products pro \n"
                + "		on c.CategoryID = pro.CategoryID\n"
                + "	join [Order Details] ord\n"
                + "		on pro.ProductID = ord.ProductID\n"
                + "	join Orders o \n"
                + "		on ord.OrderID = o.OrderID\n"
                + "	join Customers cus\n"
                + "		on cus.CustomerID = o.CustomerID";
        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1), productId = rs.getInt(2);
                double unitPrice = rs.getDouble(3);
                int quantity = rs.getInt(4);
                double discount = rs.getDouble(5);
                int categoryId = rs.getInt(6);
                String categoryName = rs.getString(7);
                String ProductName = rs.getString(8);
                String customerId = rs.getString(9), companyName = rs.getString(10);
                SaleDetail s = new SaleDetail(orderId, productId, unitPrice, quantity, discount,
                        categoryId, categoryName, ProductName, customerId, companyName);
                list.add(s);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        Vector<SaleDetail> list = new DAOSaleDetail().getAll();
        for (SaleDetail saleDetail : list) {
            System.out.println(saleDetail);
        }
    }
}
