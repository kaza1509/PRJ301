package model;

import entity.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOProduct extends ConnectDB {

    public int addProduct(Product pro) {
        int n = 0;
        String sql = "INSERT INTO [Products]"
                + "           ([ProductName]"
                + "           ,[SupplierID]"
                + "           ,[CategoryID]"
                + "           ,[QuantityPerUnit]"
                + "           ,[UnitPrice]"
                + "           ,[UnitsInStock]"
                + "           ,[UnitsOnOrder]"
                + "           ,[ReorderLevel]"
                + "           ,[Discontinued])"
                + "     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierId());
            pre.setInt(3, pro.getCategoryId());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReOrderLevel());
            pre.setInt(9, pro.getDiscontinued());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateProduct(Product pro) {
        int n = 0;
        String sql = "UPDATE [Products]"
                + "   SET [ProductName] = ?"
                + "      ,[SupplierID] = ?"
                + "      ,[CategoryID] = ?"
                + "      ,[QuantityPerUnit] = ?"
                + "      ,[UnitPrice] = ?"
                + "      ,[UnitsInStock] = ?"
                + "      ,[UnitsOnOrder] = ?"
                + "      ,[ReorderLevel] = ?"
                + "      ,[Discontinued] = ?"
                + " WHERE ProductID = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, pro.getProductName());
            pre.setInt(2, pro.getSupplierId());
            pre.setInt(3, pro.getCategoryId());
            pre.setString(4, pro.getQuantityPerUnit());
            pre.setDouble(5, pro.getUnitPrice());
            pre.setInt(6, pro.getUnitsInStock());
            pre.setInt(7, pro.getUnitsOnOrder());
            pre.setInt(8, pro.getReOrderLevel());
            pre.setInt(9, pro.getDiscontinued());
            pre.setInt(10, pro.getProductId());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Product> listAll(String sql) {
        Vector<Product> vector = new Vector<>();
//        String sql = "select * from Products";
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int productId = rs.getInt(1);
                String productName = rs.getString(2);
                int supplierId = rs.getInt(3);
                int categoryId = rs.getInt(4);
                String quantityPerUnit = rs.getString(5);
                double unitPrice = rs.getDouble(6);
                int unitsInStock = rs.getInt(7);
                int unitsOnOrder = rs.getInt(8);
                int reOrderLevel = rs.getInt(9);
                int discontinued = rs.getInt(10);

                Product pro = new Product(productId, productName, supplierId, categoryId,
                        quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reOrderLevel, discontinued);

                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int removeProduct(int id) {
        int n = 0;
        //check constraint foreign key conflict
        String constraint = "select * from Products where ProductID not in(select ProductID from [Order Details])";
        if (checkRemoveRecord(id, constraint, "ProductID") == false) {
            return 0;
        }
        //delete record by id
        String sql = "delete from Products where ProductID = " + id;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<Product> searchProductByName(String productName) {
        Vector<Product> vector = new Vector<>();
        String sql = "select * from Products where ProductName = '"+productName+"'";
        ResultSet rs = getData(sql);
        try {
            while(rs.next()) {
                int productId = rs.getInt(1);
                String productNameTemp = rs.getString(2);
                int supplierId = rs.getInt(3);
                int categoryId = rs.getInt(4);
                String quantityPerUnit = rs.getString(5);
                double unitPrice = rs.getDouble(6);
                int unitsInStock = rs.getInt(7);
                int unitsOnOrder = rs.getInt(8);
                int reOrderLevel = rs.getInt(9);
                int discontinued = rs.getInt(10);

                Product pro = new Product(productId, productNameTemp, supplierId, categoryId,
                        quantityPerUnit, unitPrice, unitsInStock, unitsOnOrder, reOrderLevel, discontinued);

                vector.add(pro);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public static void main(String[] args) {
        DAOProduct dao = new DAOProduct();
        int n = 0;
        //--------------- insert record -----------------
//        Product pro = new Product("Computer 79", 2, 4, "1 boxs", 20, 40, 20, 100, 20);
//        n = dao.addProduct(pro);
//        ConnectDB.getMessageSQL("Insert", n);

        //------------------ Update ---------------------
//        Product pro = new Product(79, "Cookie update", 2, 4, "1 boxs", 20, 40, 20, 100, 20);
//        n = dao.updateProduct(pro);
//        ConnectDB.getMessageSQL("Update", n);

        //------------------ List all -------------------
        Vector<Product> vector = dao.listAll("select * from Products where ProductID = 1");
        for (Product product : vector) {
            System.out.println(product);
        }

        //------------------ Remove --------------------
//        n = dao.removeProduct(79);
//        ConnectDB.getMessageSQL("Remove", n);
        //----------------- Search Product name -------
//        Vector<Product> vector = dao.searchProductByName("Gnocchi di nonna Alice");
//        if(vector.isEmpty()) System.out.println("Not exist Product");
//        else {
//            for (Product product : vector) {
//                System.out.println(product);
//            }
//        }
    }
}
