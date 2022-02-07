package model;

import entity.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author kazaf
 */
public class DAOEmployee extends ConnectDB {

    public int addEmployee(Employee e) {
        int n = 0;
        String sql = "INSERT INTO [Employees]"
                + "           ([LastName]"
                + "           ,[FirstName]"
                + "           ,[Title]"
                + "           ,[TitleOfCourtesy]"
                + "           ,[BirthDate]"
                + "           ,[HireDate]"
                + "           ,[Address]"
                + "           ,[City]"
                + "           ,[Region]"
                + "           ,[PostalCode]"
                + "           ,[Country]"
                + "           ,[HomePhone]"
                + "           ,[Extension]"
                + "           ,[Notes]"
                + "           ,[ReportsTo]"
                + "           ,[PhotoPath])"
                + "     VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, e.getLastName());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getTitle());
            pre.setString(4, e.getTitleOfCourtesy());
            pre.setString(5, e.getBirthDate());
            pre.setString(6, e.getHireDate());
            pre.setString(7, e.getAddress());
            pre.setString(8, e.getCity());
            pre.setString(9, e.getRegion());
            pre.setString(10, e.getPostalCode());
            pre.setString(11, e.getCountry());
            pre.setString(12, e.getHomePhone());
            pre.setString(13, e.getExtension());
            pre.setString(14, e.getNotes());
            pre.setInt(15, e.getReportsTo());
            pre.setString(16, e.getPhotoPath());

            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int updateEmployee(Employee e) {
        int n = 0;
        String sql = "UPDATE [Employees]"
                + "   SET [LastName] = ?"
                + "      ,[FirstName] = ?"
                + "      ,[Title] = ?"
                + "      ,[TitleOfCourtesy] = ?"
                + "      ,[BirthDate] = ?"
                + "      ,[HireDate] = ?"
                + "      ,[Address] = ?"
                + "      ,[City] = ?"
                + "      ,[Region] = ?"
                + "      ,[PostalCode] = ?"
                + "      ,[Country] = ?"
                + "      ,[HomePhone] = ?"
                + "      ,[Extension] = ?"
                + "      ,[Notes] = ?"
                + "      ,[ReportsTo] = ?"
                + "      ,[PhotoPath] = ?"
                + " WHERE employeeId = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, e.getLastName());
            pre.setString(2, e.getFirstName());
            pre.setString(3, e.getTitle());
            pre.setString(4, e.getTitleOfCourtesy());
            pre.setString(5, e.getBirthDate());
            pre.setString(6, e.getHireDate());
            pre.setString(7, e.getAddress());
            pre.setString(8, e.getCity());
            pre.setString(9, e.getRegion());
            pre.setString(10, e.getPostalCode());
            pre.setString(11, e.getCountry());
            pre.setString(12, e.getHomePhone());
            pre.setString(13, e.getExtension());
            pre.setString(14, e.getNotes());
            pre.setInt(15, e.getReportsTo());
            pre.setString(16, e.getPhotoPath());
            pre.setInt(17, e.getEmployeeId());
            //run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public Vector<Employee> listAll(String sql) {
        Vector<Employee> vector = new Vector<>();
        ResultSet rs = getData(sql);

        try {
            while (rs.next()) {
                int employeeId = rs.getInt(1);
                String lastName = rs.getString(2);
                String firstName = rs.getString(3);
                String title = rs.getString(4);
                String titleOfCourtesy = rs.getString(5);
                String birthDate = rs.getString(6);
                String hireDate = rs.getString(7);
                String address = rs.getString(8);
                String city = rs.getString(9);
                String region = rs.getString(10);
                String postalCode = rs.getString(11);
                String country = rs.getString(12);
                String homePhone = rs.getString(13);
                String extension = rs.getString(14);
                String photo = rs.getString(15);
                String Notes = rs.getString(16);
                int reportsTo = rs.getInt(17);
                String photoPath = rs.getString(18);
                
                Employee e = new Employee(employeeId, lastName, firstName, title, titleOfCourtesy,
                        birthDate, hireDate, address, city, region, postalCode, country, homePhone,
                        extension, photo, Notes, reportsTo, photoPath);
                vector.add(e);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return vector;
    }

    public int removeEmployee(int employeeId) {
        int n = 0;
        String constraint01 = "select distinct(ReportsTo) from Employees";
        ResultSet rs01 = getData(constraint01);
        try {
            while(rs01.next()) {
                if(rs01.getInt("ReportsTo") == employeeId) return 0;
            }
            
            String constraint02 = "select * from Employees where EmployeeID not in (select EmployeeID from Orders)";
            String constraint03 = "select * from Employees where EmployeeID not in (select EmployeeID from EmployeeTerritories)";
            
            if(checkRemoveRecord(employeeId, constraint02, "EmployeeID") == false ||
                    checkRemoveRecord(employeeId, constraint03, "EmployeeID") == false) {
                return 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        String sql = "delete from Employees where EmployeeID = "+employeeId;
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public static void main(String[] args) {
        DAOEmployee dao = new DAOEmployee();
        int n = 0;
        //--------------- insert record -----------------
//        Employee e = new Employee("Khoi", "Nguyen", "IT Leader", "Mr", "20010109", "20230901",
//                "My Dinh", "Tokyo", "WA", "13268", "VietNam", "(+84)355166404", "3453", "None", 14, "xxxxxxxxxxxxxxxx");
//        n = dao.addEmployee(e);
//        ConnectDB.getMessageSQL("Insert", n);
        //------------------ Update ---------------------
//        Employee e = new Employee(14, "Thanh", "Tung", "IT Leader", "Mr", "20010109", "20230901",
//                "My Dinh", "Tokyo", "WA", "13268", "VietNam", "(+84)355166404", "3453", "None", 3, "xxxxxxxxxxxxxxxx");
//        n = dao.updateEmployee(e);
//        ConnectDB.getMessageSQL("Update", n);
        //------------------ List all -------------------
//        Vector<Employee> vector = dao.listAll();
//        for (Employee employee : vector) {
//            System.out.println(employee);
//        }
        //------------------ Remove --------------------
        n = dao.removeEmployee(14);
        ConnectDB.getMessageSQL("Remove", n);
    }
}
