/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Shipper;
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
import model.DAOShipper;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerShipper", urlPatterns = {"/controllerShipper"})
public class ControllerShipper extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOShipper dao = new DAOShipper();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllShipper";
            }
            if (service.equals("InsertShipper")) {
                //get data
                String companyName = request.getParameter("companyName");
                String phone = request.getParameter("phone");

                //check data
                if (companyName.isEmpty()) {
                    out.print("Company name is empty");
                    return;
                }
                if (phone.isEmpty()) {
                    out.print("Company name is empty");
                    return;
                }
                //convert
                //add db
                Shipper ship = new Shipper(companyName, phone);
                int n = dao.addShipper(ship);
                response.sendRedirect("controllerShipper");
            } else if (service.equals("listAllShipper")) {
                Vector<Shipper> vector = dao.listAll("select * from Shippers");
                
                out.print("<p><a href=\"addShipper.html\">Add Shipper</a></p>");
                
                out.print("<table border=\"1\">"
                        + "<caption>List of Shippers</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>ShipperID</th>"
                        + "                <th>CompanyName</th>"
                        + "                <th>Phone</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                for (Shipper shipper : vector) {
                    out.print("<tr>"
                            + "                <td>" + shipper.getShipperId() + "</td>"
                            + "                <td>" + shipper.getCompanyName() + "</td>"
                            + "                <td>" + shipper.getPhone() + "</td>"
                            + "<td><a href=\"controllerShipper?do=updateShipper&ShipperId=" + shipper.getShipperId() + "\">update</a></td>"
                            + "<<td><a href=\"controllerShipper?do=deleteShipper&ShipperId=" + shipper.getShipperId() + "\">delete</a></td>"
                            + "            </tr>");
                }
                out.print("</tbody>"
                        + "    </table>");
            } else if (service.equals("updateShipper")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    int ShipperId = Integer.parseInt(request.getParameter("ShipperId"));
                    ResultSet rs = dao.getData("select * from Shippers where ShipperID = " + ShipperId);
                    if (rs.next()) {
                        out.print("<form action=\"controllerShipper?do=updateShipper\" method=\"POST\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update Shipper</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipperID</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"shipperId\" value=\""+rs.getString(1)+"\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Company Name</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"companyName\" value=\""+rs.getString(2)+"\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Phone</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"phone\" value=\""+rs.getString(3)+"\">"
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
                    int shipperId = Integer.parseInt(request.getParameter("shipperId"));
                    String companyName = request.getParameter("companyName");
                    String phone = request.getParameter("phone");
                    
                    Shipper ship = new Shipper(shipperId, companyName, phone);
                    int n = dao.updateShipper(ship);
                    response.sendRedirect("controllerShipper");
                }
            } else if (service.equals("deleteShipper")) {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerShipper.class.getName()).log(Level.SEVERE, null, ex);
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
