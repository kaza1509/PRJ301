package model;

import entity.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOCategory extends ConnectDB {

    public int addCategory(Category cate) {
        int n = 0;
        String sql = "INSERT INTO [Categories]"
                + "           ([CategoryName]"
                + "           ,[Description])"
                + "     VALUES(?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateCategory(Category cate) {
        int n = 0;
        String sql = "UPDATE [dbo].[Categories]"
                + "   SET [CategoryName] = ?"
                + "      ,[Description] = ?"
                + " WHERE [CategoryID] = ? ";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, cate.getCategoryName());
            pre.setString(2, cate.getDescription());
            pre.setInt(3, cate.getCategoryId());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Category> listAll() {
        Vector<Category> vector = new Vector<>();
        String sql = "select * from Categories";
        ResultSet rs = getData(sql);
        try {
            while (rs.next()) {
                int categoryId = rs.getInt(1);
                String categoryName = rs.getString(2);
                String description = rs.getString(3);
                String picture = rs.getString(4);

                Category cate = new Category(categoryId, categoryName, description, picture);
                vector.add(cate);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int removeCategory(int categoryId) {
        int n = 0;
        //check constraint foreign key conflict
        String constraint = "select * from Categories where CategoryID not in (select CategoryID from Products)";
        if (checkRemoveRecord(categoryId, constraint, "CategoryID") == false) {
            return 0;
        }
        //delete record by id
        String sql = "delete from Categories where CategoryID = " + categoryId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        int n = 0;
        //--------------- insert record -----------------
//        Category cate = new Category("Sasimi", "Fish, seafood");
//        n = dao.addCategory(cate);
//        ConnectDB.getMessageSQL("Insert", n);

        //------------------ Update ---------------------
//        Category cate = new Category(9, "Sasimi update", "Fish, seafood");
//        n = dao.updateCategory(cate);
//        ConnectDB.getMessageSQL("Update", n);

        //------------------ List all -------------------
        Vector<Category> vector = dao.listAll();
        for (Category cate : vector) {
            System.out.println(cate);
        }

        //------------------ Remove --------------------
//        n = dao.removeCategory(9);
//        ConnectDB.getMessageSQL("Remove", n);
    }
}
