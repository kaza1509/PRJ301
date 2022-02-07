package controller;

import entity.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOEmployee;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerEmployee", urlPatterns = {"/controllerEmployee"})
public class ControllerEmployee extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOEmployee dao = new DAOEmployee();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllEmployee";
            }
            if (service.equals("InsertEmployee")) {
                //get data
                String lastName = request.getParameter("lastName");
                String firstName = request.getParameter("firstName");
                String title = request.getParameter("title");
                String titleOfCourtesy = request.getParameter("titleOfCourtesy");
                String birthDate = request.getParameter("birthDate");
                String hireDate = request.getParameter("hireDate");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String postalCode = request.getParameter("postalCode");
                String country = request.getParameter("country");
                String homePhone = request.getParameter("homePhone");
                String extension = request.getParameter("extension");
                String photo = request.getParameter("photo");
                String Notes = request.getParameter("Notes");;
                int reportsTo = Integer.parseInt(request.getParameter("reportsTo"));
                String photoPath = request.getParameter("photoPath");
                //check data
                if (lastName.isEmpty()) {
                    out.print("Last Name is empty");
                    return;
                }
                if (firstName.isEmpty()) {
                    out.print("First Name is empty");
                    return;
                }
                if (title.isEmpty()) {
                    out.print("title is empty");
                    return;
                }
                if (titleOfCourtesy.isEmpty()) {
                    out.print("titleOfCourtesy is empty");
                    return;
                }
                LocalDate birthDateValue = LocalDate.parse(birthDate);
                LocalDate hireDateValue = LocalDate.parse(hireDate);
                if (!birthDateValue.isBefore(hireDateValue)) {
                    out.print("birthDate have to before hireDate");
                    return;
                }
                if (address.isEmpty()) {
                    out.print("address is empty");
                    return;
                }
                if (city.isEmpty()) {
                    out.print("city is empty");
                    return;
                }
                if (region.isEmpty()) {
                    out.print("region is empty");
                    return;
                }
                if (postalCode.isEmpty()) {
                    out.print("postalCode is empty");
                    return;
                }
                if (address.isEmpty()) {
                    out.print("address is empty");
                    return;
                }
                if (country.isEmpty()) {
                    out.print("country is empty");
                    return;
                }
                if (homePhone.isEmpty()) {
                    out.print("homePhone is empty");
                    return;
                }
                if (extension.isEmpty()) {
                    out.print("extension is empty");
                    return;
                }
                if (photo.isEmpty()) {
                    out.print("photo is empty");
                    return;
                }
                if (Notes.isEmpty()) {
                    out.print("Notes is empty");
                    return;
                }
                if (photoPath.isEmpty()) {
                    out.print("photoPath is empty");
                    return;
                }
                //add db
                Employee e = new Employee(lastName, firstName, title, titleOfCourtesy,
                        birthDate, hireDate, address, city, region, postalCode, country,
                        homePhone, extension, Notes, reportsTo, photoPath);
                int n = dao.addEmployee(e);
                response.sendRedirect("controllerEmployee");
            } else if (service.equals("listAllEmployee")) {
                Vector<Employee> vector = dao.listAll("select * from Employees");

                out.print("<p><a href=\"addEmployee.html\">Add Employee</a></p>");

                out.print("<table border=\"1\">"
                        + "<caption>List of Employee</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>EmployeeID</th>"
                        + "                <th>LastName</th>"
                        + "                <th>FirstName</th>"
                        + "                <th>Title</th>"
                        + "                <th>TitleOfCourtesy</th>"
                        + "                <th>BirthDate</th>"
                        + "                <th>HireDate</th>"
                        + "                <th>Address</th>"
                        + "                <th>City</th>"
                        + "                <th>Region</th>"
                        + "                <th>PostalCode</th>"
                        + "                <th>Country</th>"
                        + "                <th>HomePhone</th>"
                        + "                <th>Extension</th>"
                        + "                <th>Photo</th>"
                        + "                <th>Notes</th>"
                        + "                <th>ReportsTo</th>"
                        + "                <th>PhotoPath</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                for (Employee e : vector) {
                    out.print("<tr>"
                            + "                <td>" + e.getEmployeeId() + "</td>"
                            + "                <td>" + e.getLastName() + "</td>"
                            + "                <td>" + e.getFirstName() + "</td>"
                            + "                <td>" + e.getTitle() + "</td>"
                            + "                <td>" + e.getTitleOfCourtesy() + "</td>"
                            + "                <td>" + e.getBirthDate() + "</td>"
                            + "                <td>" + e.getHireDate() + "</td>"
                            + "                <td>" + e.getAddress() + "</td>"
                            + "                <td>" + e.getCity() + "</td>"
                            + "                <td>" + e.getRegion() + "</td>"
                            + "                <td>" + e.getPostalCode() + "</td>"
                            + "                <td>" + e.getCountry() + "</td>"
                            + "                <td>" + e.getHomePhone() + "</td>"
                            + "                <td>" + e.getExtension() + "</td>"
                            + "                <td>photo</td>"
                            + "                <td>" + e.getNotes() + "</td>"
                            + "                <td>" + e.getReportsTo() + "</td>"
                            + "                <td>" + e.getPhotoPath() + "</td>"
                            + "<td><a href=\"controllerEmployee?do=updateEmployee&EmployeeId=" + e.getEmployeeId() + "\">update</a></td>"
                            + "<td><a href=\"controllerEmployee?do=deleteEmployee&EmployeeId=" + e.getEmployeeId() + "\">delete</a></td>"
                            + "            </tr>");
                }
                out.print("</tbody>"
                        + "    </table>");
            } else if (service.equals("updateEmployee")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int employeeId = Integer.parseInt(request.getParameter("EmployeeId"));
                    ResultSet rs = dao.getData("select * from Employees where EmployeeID = " + employeeId);
                    if (rs.next()) {
                        out.print("<form action=\"controllerEmployee?do=updateEmployee\" method=\"POST\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update employee</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>EmployeeID</td>"
                                + "                    <td>"
                                + "                       <input type=\"text\" name=\"employeeId\" value=\"" + rs.getString(1) + "\">"
                                + "                   </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>LastName</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"lastName\" value=\"" + rs.getString(2) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>FirstName</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"firstName\" value=\"" + rs.getString(3) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Title</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"title\" value=\"" + rs.getString(4) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>TitleOfCourtesy</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"titleOfCourtesy\" value=\"" + rs.getString(5) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>BirthDate</td>"
                                + "                    <td>"
                                + "                        <input type=\"date\" name=\"birthDate\" value=\"" + rs.getString(6).substring(0, 10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>HireDate</td>"
                                + "                    <td>"
                                + "                        <input type=\"date\" name=\"hireDate\" value=\"" + rs.getString(7).substring(0, 10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Address</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"address\" value=\"" + rs.getString(8) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>City</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"city\" value=\"" + rs.getString(9) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Region</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"region\" value=\"" + rs.getString(10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>PostalCode</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"postalCode\" value=\"" + rs.getString(11) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Country</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"country\" value=\"" + rs.getString(12) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>HomePhone</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"homePhone\" value=\"" + rs.getString(13) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Extension</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"extension\" value=\"" + rs.getString(14) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Photo</td>"
                                + "                    <td>"
                                + "                        <input type=\"file\" name=\"photo\" value=\"" + rs.getString(15) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Notes</td>"
                                + "                    <td>"
//                                + "                        <input type=\"text\" name=\"Notes\" value=\"" + rs.getString(16) + "\">"
                                + "<textarea name=\"Notes\" cols=\"21\" rows=\"10\">" + rs.getString(16) + "</textarea>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ReportsTo</td>"
                                + "                    <td>"
                                + "                        <select name=\"reportsTo\">");
                                ResultSet rs1 = dao.getData("select * from Employees");
                                while(rs1.next()) {
                                    out.print( "<option value=\""+rs1.getString(1)+"\" "+(rs.getInt(17)==rs1.getInt(1)?"selected":"")+">"+rs1.getString(2).concat(" "+rs1.getString(3))+"</option>");
                                }
                                out.print( "                        </select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>PhotoPath</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"photoPath\" value=\"" + rs.getString(18) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>"
                                + "                        <input type=\"submit\" value=\"Update\" name=\"submit\">"
                                + "                    </td>"
                                + "                    <td>"
                                + "                        <input type=\"reset\" value=\"Clear\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "            </table>"
                                + "        </form>");
                    }
                } else {
                    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                    String lastName = request.getParameter("lastName");
                    String firstName = request.getParameter("firstName");
                    String title = request.getParameter("title");
                    String titleOfCourtesy = request.getParameter("titleOfCourtesy");
                    String birthDate = request.getParameter("birthDate");
                    String hireDate = request.getParameter("hireDate");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String country = request.getParameter("country");
                    String homePhone = request.getParameter("homePhone");
                    String extension = request.getParameter("extension");
                    String photo = request.getParameter("photo");
                    String Notes = request.getParameter("Notes");;
                    int reportsTo = Integer.parseInt(request.getParameter("reportsTo"));
                    String photoPath = request.getParameter("photoPath");

                    Employee e = new Employee(employeeId, lastName, firstName, title, titleOfCourtesy, birthDate,
                            hireDate, address, city, region, postalCode, country, homePhone, extension, Notes,
                            reportsTo, photoPath);
                    int n = dao.updateEmployee(e);
                    out.print(n);
                    response.sendRedirect("controllerEmployee");
                }
            } else if (service.equals("deleteEmployee")) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
