package controller;

import entity.Order;
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
import model.DAOOrder;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerOrder", urlPatterns = {"/controllerOrder"})
public class ControllerOrder extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOOrder dao = new DAOOrder();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllOrder";
            }
            if (service.equals("InsertOrder")) {
                //get data
                String customerID = request.getParameter("customerId");
                String employeeId = request.getParameter("employeeId");
                String orderDate = request.getParameter("orderDate");
                String requiredDate = request.getParameter("requiredDate");
                String shippedDate = request.getParameter("shippedDate");
                String shipVia = request.getParameter("shipVia");
                String freight = request.getParameter("freight");
                String shipName = request.getParameter("shipName");
                String shipAddress = request.getParameter("shipAddress");
                String shipCity = request.getParameter("shipCity");
                String shipRegion = request.getParameter("shipRegion");
                String shipPostalCode = request.getParameter("shipPostalCode");
                String shipCountry = request.getParameter("shipCountry");

                //check data
                String checkInt = "[1-9][0-9]*";
                String checkDob = "[0-9]+[.][0-9]+";
                String checkShipCode = "[0-9-]+";

                LocalDate orderDateValue = LocalDate.parse(orderDate);
                LocalDate requiredDateValue = LocalDate.parse(requiredDate);
                LocalDate shippedDateValue = LocalDate.parse(shippedDate);

                if (!orderDateValue.isBefore(shippedDateValue)) {
                    out.print("Orderdate < shipDate");
                    return;
                }
                if (!shippedDateValue.isBefore(requiredDateValue)) {
                    out.print("shippedDate < requiredDate");
                    return;
                }
                if (!freight.matches(checkDob)) {
                    out.print("Freight is number");
                    return;
                }
                if (shipName.isEmpty()) {
                    out.print("ShipName is empty");
                    return;
                }
                if (shipAddress.isEmpty()) {
                    out.print("shipAddress is empty");
                    return;
                }
                if (shipCity.isEmpty()) {
                    out.print("shipCity is empty");
                    return;
                }
                if (shipRegion.isEmpty()) {
                    out.print("shipRegion is empty");
                    return;
                }
                if (!shipPostalCode.matches(checkShipCode)) {
                    out.print("shipPostalCode is invalid");
                    return;
                }
                if (shipCountry.isEmpty()) {
                    out.print("shipCountry is empty");
                    return;
                }
                //convert data
                double freightValue = Double.parseDouble(freight);
                int employeeIdValue = Integer.parseInt(employeeId);
                //add db
                Order od = new Order(customerID, employeeIdValue, String.valueOf(orderDateValue),
                        String.valueOf(requiredDateValue), String.valueOf(shippedDateValue),
                        Integer.parseInt(shipVia), freightValue, shipName, shipAddress, shipCity,
                        shipRegion, shipPostalCode, shipCountry);
                int n = dao.addOrder(od);
                response.sendRedirect("controllerOrder");
            } else if (service.equals("listAllOrder")) {
                Vector<Order> vector = dao.listAll("select * from Orders");

                out.print("<p><a href=\"addOrder.html\">Add Order</a></p>");

                out.print("<table border=\"1\">"
                        + "<caption>List of Orders</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>OrderID</th>"
                        + "                <th>CustomerID</th>"
                        + "                <th>EmployeeID</th>"
                        + "                <th>OrderDate</th>"
                        + "                <th>RequiredDate</th>"
                        + "                <th>ShippedDate</th>"
                        + "                <th>ShipVia</th>"
                        + "                <th>Freight</th>"
                        + "                <th>ShipName</th>"
                        + "                <th>ShipAddress</th>"
                        + "                <th>ShipCity</th>"
                        + "                <th>ShipRegion</th>"
                        + "                <th>ShipPostalCode</th>"
                        + "                <th>ShipCountry</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                for (Order order : vector) {
                    out.print("<tr>"
                            + "                <td>" + order.getOrderId() + "</td>"
                            + "                <td>" + order.getCustomerID() + "</td>"
                            + "                <td>" + order.getEmployeeId() + "</td>"
                            + "                <td>" + order.getOrderDate() + "</td>"
                            + "                <td>" + order.getRequiredDate() + "</td>"
                            + "                <td>" + order.getShippedDate() + "</td>"
                            + "                <td>" + order.getShipVia() + "</td>"
                            + "                <td>" + order.getFreight() + "</td>"
                            + "                <td>" + order.getShipName() + "</td>"
                            + "                <td>" + order.getShipAddress() + "</td>"
                            + "                <td>" + order.getShipCity() + "</td>"
                            + "                <td>" + order.getShipRegion() + "</td>"
                            + "                <td>" + order.getShipPostalCode() + "</td>"
                            + "                <td>" + order.getShipaCountry() + "</td>"
                            + "                <td><a href=\"controllerOrder?do=updateOrder&orderId=" + order.getOrderId() + "\">update</a></td>"
                            + "                <td><a href=\"controllerOrder?do=deleteOrder&orderId=" + order.getOrderId() + "\">delete</a></td>"
                            + "            </tr>");
                }
                out.print("</tbody>"
                        + "    </table>");
            } else if (service.equals("updateOrder")) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                String submit = request.getParameter("submit");
                ResultSet rs = dao.getData("select * from Orders where OrderID = " + orderId);
                if (submit == null) {
                    if (rs.next()) {
                        out.print("<form action=\"controllerOrder?do=updateOrder\" method=\"post\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update orders</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>OrderID</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"orderId\" value=\"" + rs.getString(1) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>CustomerID</td>"
                                + "                    <td>"
                                + "                        <select name=\"customerId\">");
                        ResultSet rs1 = dao.getData("select * from Customers");
                        while (rs1.next()) {
                            out.print("<option value=\"" + rs1.getString(1) + "\" " + (rs1.getString(1).equals(rs.getString(2)) ? "selected" : "") + ">" + rs1.getString(2) + "</option>");
                        }
                        out.print("                        </select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>EmployeeID</td>"
                                + "                    <td>"
                                + "                        <select name=\"employeeId\">");
                        ResultSet rs2 = dao.getData("select * from Employees");
                        while (rs2.next()) {
                            out.print("<option value=\"" + rs2.getString(1) + "\" " + (rs.getInt(3) == rs2.getInt(1) ? "selected" : "") + ">" + rs2.getString(2).concat(" " + rs2.getString(3)) + "</option>");
                        }
                        out.print("                        </select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr><td><b>OrderDate < ShipDate < RequiredDate</b></td></tr>"
                                + "                <tr>"
                                + "                    <td>OrderDate</td>"
                                + "                    <td>"
                                + "                        <input type=\"date\" name=\"orderDate\" value=\"" + rs.getString(4).substring(0, 10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>RequiredDate</td>"
                                + "                    <td>"
                                + "                        <input type=\"date\" name=\"requiredDate\" value=\"" + rs.getString(5).substring(0, 10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShippedDate</td>"
                                + "                    <td>"
                                + "                        <input type=\"date\" name=\"shippedDate\" value=\"" + rs.getString(6).substring(0, 10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipVia</td>"
                                + "                    <td>"
                                + "                        <select name=\"shipVia\">");
                        ResultSet rs3 = dao.getData("select * from Shippers");
                        while (rs3.next()) {
                            out.print("<option value=\"" + rs3.getString(1) + "\" " + (rs3.getInt(1) == rs.getInt(7) ? "selected" : "") + ">" + rs3.getString(2) + "</option>");
                        }
                        out.print("                        </select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Freight</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"freight\"  value=\"" + rs.getString(8) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipName</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"shipName\" value=\"" + rs.getString(9) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipAddress</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"shipAddress\" value=\"" + rs.getString(10) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipCity</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"shipCity\" value=\"" + rs.getString(11) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipRegion</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"shipRegion\" value=\"" + rs.getString(12) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipPostalCode</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"shipPostalCode\" value=\"" + rs.getString(13) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ShipCountry</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"shipCountry\" value=\"" + rs.getString(14) + "\">"
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
                    
                    String customerID = request.getParameter("customerId");
                    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                    String orderDate = request.getParameter("orderDate");
                    String requiredDate = request.getParameter("requiredDate");
                    String shippedDate = request.getParameter("shippedDate");
                    int shipVia = Integer.parseInt(request.getParameter("shipVia"));
                    double freight = Double.parseDouble(request.getParameter("freight"));
                    String shipName = request.getParameter("shipName");
                    String shipAddress = request.getParameter("shipAddress");
                    String shipCity = request.getParameter("shipCity");
                    String shipRegion = request.getParameter("shipRegion");
                    String shipPostalCode = request.getParameter("shipPostalCode");
                    String shipCountry = request.getParameter("shipCountry");
                    
                    Order od = new Order(orderId, customerID, employeeId, orderDate, requiredDate,
                        shippedDate, shipVia, freight, shipName, shipAddress, shipCity,
                        shipRegion, shipPostalCode, shipCountry);
                    int n = dao.updateOrder(od);
                    response.sendRedirect("controllerOrder");
                }
            } else if (service.equals("deleteOrder")) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerOrder.class.getName()).log(Level.SEVERE, null, ex);
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
    }
}
