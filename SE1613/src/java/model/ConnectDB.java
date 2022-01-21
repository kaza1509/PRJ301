/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author kazaf
 */
public class ConnectDB {

    public Connection conn = null;

    public ConnectDB(String URL, String userName, String passWord) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //call 
            conn = DriverManager.getConnection(URL, userName, passWord);
            System.out.println("Connect");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ConnectDB() {
        this("jdbc:sqlserver://localhost:1433;databaseName=SE1613", "sa", "123456");
    }

    /**
     * Get ResultSet to select
     * @param sql sql
     * @return ResultSet
     */
    public ResultSet getData(String sql) {
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rs;
    }
    //----------------- Check remove --------------------
    /**
     * Check conflict constraint foreign key
     * @param id id input to remove
     * @param sql check id not exist in foreign key table
     * @param nameId name of id in table sql
     * @return boolean
     */
    public boolean checkRemoveRecord(int id, String sql, String nameId) {
        boolean isRemove = false;
        ResultSet rs = getData(sql);
        try {
            while(rs.next()) {
                if(rs.getInt(nameId) == id) isRemove = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isRemove;
    }
    
    /**
     * Check conflict constraint foreign key
     * @param id id input to remove
     * @param sql check id not exist in foreign key table
     * @param nameId name of id in table sql
     * @return boolean
     */
    public boolean checkRemoveRecord(String id, String sql, String nameId) {
        boolean isRemove = false;
        ResultSet rs = getData(sql);
        try {
            while(rs.next()) {
                if(rs.getString(nameId).equals(id)) isRemove = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isRemove;
    }

    //--------------- Message ----------------
    public static void getMessageSQL(String type, int n) {
        if(n > 0) System.out.println(type+" Successfull!!!");
        else System.out.println(type+" Fail!!!");
    }
    //-------------------------------------
    public static void main(String[] args) {
        new ConnectDB();
    }
}
