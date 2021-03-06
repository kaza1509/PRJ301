/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAOSupplier;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerSupplier", urlPatterns = {"/ControllerSupplier"})
public class ControllerSupplier extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOSupplier dao = new DAOSupplier();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllSupplier";
            }
            if (service.equals("insertSupplier")) {
                //get data
                String companyName = request.getParameter("companyName");
                String contactName = request.getParameter("contactName");
                String contactTitle = request.getParameter("contactTitle");
                String address = request.getParameter("address");
                String city = request.getParameter("city");
                String region = request.getParameter("region");
                String postalCode = request.getParameter("postalCode");
                String country = request.getParameter("country");
                String phone = request.getParameter("phone");
                String fax = request.getParameter("fax");
                String homePage = request.getParameter("homePage");
                //check  data
                String checkNum = "[0-9-]+";
                if (companyName.isEmpty()) {
                    out.print("companyName is empty");
//                response.sendRedirect("addSupplier.html");
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

                if (region.isEmpty()) {
                    out.print("region is empty");
                    return;
                }

                if (!postalCode.matches(checkNum)) {
                    out.print("postalCode is invalid");
                    return;
                }

                if (country.isEmpty()) {
                    out.print("country is empty");
                    return;
                }

                if (!phone.matches(checkNum)) {
                    out.print("phone is invalid");
                    return;
                }

                if (!fax.matches(checkNum)) {
                    out.print("fax is invalid");
                    return;
                }

                if (homePage.isEmpty()) {
                    out.print("homePage is empty");
                    return;
                }
                //convert
                //add db
                Supplier sup = new Supplier(companyName, contactName, contactTitle, address,
                        city, region, postalCode, country, phone, fax, homePage);
                int n = dao.addSupplier(sup);
                response.sendRedirect("ControllerSupplier");
            } else if (service.equals("listAllSupplier")) {
//                out.print("List All Supplier");
                Vector<Supplier> vector = dao.listAll("select * from Suppliers");
                out.print("<p><a href=\"addSupplier.html\">Add Suppliers</a></p>");
                out.print("<table border=\"1\" style=\"width: 100vw\">"
                        + "<caption>List of Supplier</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>SupplierID</th>"
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
                        + "                <th>HomePage</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                for (Supplier sup : vector) {
                    out.print("<tr>"
                            + "                <td>" + sup.getSupplierId() + "</td>"
                            + "                <td>" + sup.getCompanyName() + "</td>"
                            + "                <td>" + sup.getContactName() + "</td>"
                            + "                <td>" + sup.getContactTitle() + "</td>"
                            + "                <td>" + sup.getAddress() + "</td>"
                            + "                <td>" + sup.getCity() + "</td>"
                            + "                <td>" + sup.getRegion() + "</td>"
                            + "                <td>" + sup.getPostalCode() + "</td>"
                            + "                <td>" + sup.getCountry() + "</td>"
                            + "                <td>" + sup.getPhone() + "</td>"
                            + "                <td>" + sup.getFax() + "</td>"
                            + "                <td>" + sup.getHomePage() + "</td>"
                            + "                <td><a href=\"ControllerSupplier?do=updateSupplier&supplierId=" + sup.getSupplierId() + "\">update</a></td>"
                            + "                <td><a href=\"ControllerSupplier?do=deleteSupplier&supplierId=" + sup.getSupplierId() + "\">delete</a></td>"
                            + "            </tr>");
                }
                out.print("</tbody>"
                        + "    </table>");

            } else if (service.equals("updateSupplier")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int supplierId = Integer.parseInt(request.getParameter("supplierId"));
                    ResultSet rs = dao.getData("select * from Suppliers where SupplierID = " + supplierId);
                    if (rs.next()) {
                        out.print("<form action=\"ControllerSupplier?do=updateSupplier\" method=\"post\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update suppliers</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>SupplierID</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"supplierId\" value=\"" + rs.getString(1) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>CompanyName</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"companyName\" value=\"" + rs.getString(2) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ContactName</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"contactName\" value=\"" + rs.getString(3) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ContactTitle</td>"
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
                                + "                        <input type=\"text\" name=\"city\" value=\"" + rs.getString(6) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Region</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"region\" value=\"" + rs.getString(7) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>PostalCode</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"postalCode\" value=\"" + rs.getString(8) + "\">"
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
                                + "                        <input type=\"tel\" name=\"phone\" value=\"" + rs.getString(10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Fax</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"fax\" value=\"" + rs.getString(11) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>HomePage</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"homePage\" value=\"" + rs.getString(12) + "\">"
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
                    int supId = Integer.parseInt(request.getParameter("supplierId"));
                    String companyName = request.getParameter("companyName");
                    String contactName = request.getParameter("contactName");
                    String contactTitle = request.getParameter("contactTitle");
                    String address = request.getParameter("address");
                    String city = request.getParameter("city");
                    String region = request.getParameter("region");
                    String postalCode = request.getParameter("postalCode");
                    String country = request.getParameter("country");
                    String phone = request.getParameter("phone");
                    String fax = request.getParameter("fax");
                    String homePage = request.getParameter("homePage");
                    
                    Supplier sup = new Supplier(supId, companyName, contactName, contactTitle, address, 
                            city, region, postalCode, country, phone, fax, homePage);
                    int n = dao.updateSupplier(sup);
                    response.sendRedirect("ControllerSupplier");
                }

            } else if (service.equals("deleteSupplier")) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerSupplier.class.getName()).log(Level.SEVERE, null, ex);
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
        response.setContentType("text/html;charset=UTF-8");
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
