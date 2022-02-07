package controller;

import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOCustomer;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerCustomer", urlPatterns = {"/controllerCustomer"})
public class ControllerCustomer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOCustomer dao = new DAOCustomer();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllCustomer";
            }
            if (service.equals("InsertCustomer")) {
                //get data
                String customerId = request.getParameter("customerId");
                String companyName = request.getParameter("companyName");
                String contactName = request.getParameter("contactName");
                String contactTitle = request.getParameter("contactTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String potalCode = request.getParameter("potalCode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");
                //check data
                String code = "[0-9-]+";
                String phoneFax = "[0-9()-]+";
                if (customerId.length() > 5 || customerId.length() <= 0) {
                    out.print("Customer id is Invalid");
                    return;
                }
                if (companyName.isEmpty()) {
                    out.print("companyName is empty");
                    return;
                }
                if (contactName.isEmpty()) {
                    out.print("contactName is empty");
                    return;
                }
                if (contactTitle.isEmpty()) {
                    out.print("contactTitle is empty");
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
//            if(region.isEmpty()) {
//                out.print("region is empty");
//                return;
//            }
                if (potalCode.isEmpty()) {
                    out.print("potalCode is empty");
                    return;
                }
                if (country.isEmpty()) {
                    out.print("country is empty");
                    return;
                }
                if (phone.isEmpty()) {
                    out.print("Company name is empty");
                    return;
                }
//            if(fax.matches(code)) {
//                out.print("Invalid fax");
//                return;
//            }
                //conver data
                //add db
                Customer cus = new Customer(customerId, companyName, contactName, contactTitle, address,
                        city, region, potalCode, country, phone, fax);
                int n = dao.addCustomer(cus);
                response.sendRedirect("controllerCustomer");
            } else if (service.equals("listAllCustomer")) {
                Vector<Customer> vector = dao.listAll("select * from Customers");
                out.print("<p><a href=\"addCustomer.html\">Add Customer</a></p>");
                out.print("<table border=\"1\">"
                        + "<caption>List of Customer</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>CustomerID</th>"
                        + "                <th>CompanyName</th>"
                        + "                <th>ContactName</th>"
                        + "                <th>ContactTitle</th>"
                        + "                <th>Address</th>"
                        + "                <th>City</th>"
                        + "                <th>Region</th>"
                        + "                <th>PostalCode</th>"
                        + "                <th>Country</th>"
                        + "                <th>Phone</th>"
                        + "                <th>Fax</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                int i = 1;
                for (Customer cus : vector) {
                    out.print("<tr>"
                            + "                <td>" + cus.getCustomerId() + "</td>"
                            + "                <td>" + cus.getCompanyName() + "</td>"
                            + "                <td>" + cus.getContactName() + "</td>"
                            + "                <td>" + cus.getContactTitle() + "</td>"
                            + "                <td>" + cus.getAddress() + "</td>"
                            + "                <td>" + cus.getCity() + "</td>"
                            + "                <td>" + cus.getRegion() + "</td>"
                            + "                <td>" + cus.getPotalCode() + "</td>"
                            + "                <td>" + cus.getCountry() + "</td>"
                            + "                <td>" + cus.getPhone() + "</td>"
                            + "                <td>" + cus.getFax() + "</td>"
                            + "<td><a href=\"controllerCustomer?do=updateCustomer&CustomerId=" + cus.getCustomerId() + "\">update</a></td>"
                            + "<<td><a href=\"controllerCustomer?do=deleteCustomer&CustomerId=" + cus.getCustomerId() + "\">delete</a></td>"
                            + "            </tr>");
                }
                out.print("</tbody>"
                        + "    </table>");
            } else if (service.equals("updateCustomer")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String customerId = request.getParameter("CustomerId");
                    ResultSet rs = dao.getData("select * from Customers where CustomerID = '" + customerId + "'");
                    if (rs.next()) {
                        out.print("<form action=\"controllerCustomer?do=updateCustomer\" method=\"POST\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update Customer</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Customer id</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"customerId\" value=\"" + rs.getString(1) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Company Name</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"companyName\" value=\"" + rs.getString(2) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Contact Name</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"contactName\" value=\"" + rs.getString(3) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Contact Title</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"contactTitle\" value=\"" + rs.getString(4) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Address</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"address\" value=\"" + rs.getString(5) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>City</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"city\" value=\"" + rs.getString(5) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Region</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"region\" value=\"" + rs.getString(7) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Potal Code</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"potalCode\" value=\"" + rs.getString(8) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Country</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"country\" value=\"" + rs.getString(9) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Phone</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"phone\" value=\"" + rs.getString(10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Fax</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"fax\" value=\"" + rs.getString(11) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>"
                                + "                         <input type=\"submit\" value=\"Send\" name=\"submit\">"
                                + "                    </td>"
                                + "                    <td>"
                                + "                        <input type=\"reset\" value=\"Clear\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "            </table>"
                                + "        </form>");
                    }
                } else {
                    String customerId = request.getParameter("customerId");
                    String companyName = request.getParameter("companyName");
                    String contactName = request.getParameter("contactName");
                    String contactTitle = request.getParameter("contactTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String potalCode = request.getParameter("potalCode");
                    String country = request.getParameter("country");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    Customer cus = new Customer(customerId, companyName, contactName, contactTitle, address,
                            city, region, potalCode, country, phone, fax);
                    int n = dao.updateCustomer(cus);
                    response.sendRedirect("controllerCustomer?do=listAllCustomer");
                }
            } else if (service.equals("deleteCustomer")) {

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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
