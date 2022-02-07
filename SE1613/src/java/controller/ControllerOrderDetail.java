package controller;

import entity.OrderDetail;
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
import model.DAOOrderDetail;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerOrderDetail", urlPatterns = {"/controllerOrderDetail"})
public class ControllerOrderDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOOrderDetail dao = new DAOOrderDetail();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllOrderDetail";
            }
            if (service.equals("InsertOrderDetail")) {

                //get data
                String orderId = request.getParameter("orderId");
                String productId = request.getParameter("productId");
                String unitPrice = request.getParameter("unitPrice");
                String quantity = request.getParameter("quantity");
                String discount = request.getParameter(("discount"));

                //check
                String checkInt = "[1-9][0-9]*";
                String checkDob = "[0-9]+[.][0-9]+";

                if (!unitPrice.matches(checkDob)) {
                    out.print("unitPrice has to number");
                    return;
                }

                if (!quantity.matches(checkInt)) {
                    out.print("quantity has to number");
                    return;
                }

                if (!discount.matches(checkDob)) {
                    out.print("discount has to number");
                    return;
                }
                //convert
                int orderIdValue = Integer.parseInt(orderId);
                int productIdValue = Integer.parseInt(productId);
                double unitPriceValue = Double.parseDouble(unitPrice);
                int quantityValue = Integer.parseInt(quantity);
                double discountValue = Double.parseDouble(discount);

                out.println(orderIdValue + " - " + productIdValue + " - " + unitPrice + " - " + quantityValue + " - " + discountValue);
                //add db
                OrderDetail ord = new OrderDetail(orderIdValue, productIdValue,
                        unitPriceValue, quantityValue, discountValue);
                int n = dao.addOrderDetail(ord);
                response.sendRedirect("controllerOrderDetail");
            } else if (service.equals("listAllOrderDetail")) {
                Vector<OrderDetail> vector = dao.listAll("select * from [Order Details]");

                out.print("<p><a href=\"addOrderDetail.html\">Add OrderDetail</a></p>");

                out.print("<table border=\"1\">"
                        + "<caption>List of OrderDetail</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>OrderID</th>"
                        + "                <th>ProductID</th>"
                        + "                <th>UnitPrice</th>"
                        + "                <th>Quantity</th>"
                        + "                <th>Discount</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");
                for (OrderDetail od : vector) {
                    out.print("<tr>"
                            + "                <td>" + od.getOrderId() + "</td>"
                            + "                <td>" + od.getProductId() + "</td>"
                            + "                <td>" + od.getUnitPrice() + "</td>"
                            + "                <td>" + od.getQuantity() + "</td>"
                            + "                <td>" + od.getDiscount() + "</td>"
                            + "                <td><a href=\"controllerOrderDetail?do=updateOrderDetail&orderId=" + od.getOrderId() + "&productId=" + od.getProductId() + "\">update</a></td>"
                            + "                <td><a href=\"controllerOrderDetail?do=deleteOrderDetail&orderId=" + od.getOrderId() + "&productId=" + od.getProductId() + "\">delete</a></td>"
                            + "            </tr>");
                }
                out.print("</tbody>"
                        + "    </table>");
            } else if (service.equals("updateOrderDetail")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String orderId = request.getParameter("orderId");
                    String productId = request.getParameter("productId");
                    ResultSet rs = dao.getData("select * from [Order Details] where OrderID = " + orderId + " and ProductID = " + productId + "");
                    if (rs.next()) {
                        out.print("<form action=\"controllerOrderDetail?do=updateOrderDetail\" method=\"post\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update Order Detail</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>OrderID</td>"
                                + "                    <td>"
                                + "                        <select name=\"orderId\">");
                        ResultSet rs1 = dao.getData("select * from Orders");
                        while (rs1.next()) {
                            out.print("<option value=\"" + rs1.getInt(1) + "\" "+(rs1.getInt(1)==rs.getInt(1)?"selected":"")+">" + rs1.getInt(1) + "</option>");
                        }
                        out.print("                        </select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ProductID</td>"
                                + "                    <td>"
                                + "                        <select name=\"productId\">");
                        ResultSet rs2 = dao.getData("select * from Products");
                        while (rs2.next()) {
                            out.print("<option value=\"" + rs2.getInt(1) + "\""+(rs2.getInt(1)==rs.getInt(2)?"selected":"")+">" + rs2.getString(2) + "</option>");
                        }
                        out.print("                        </select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>UnitPrice</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"unitPrice\" value=\"" + rs.getString(3) + "\" step=\"0.001\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Quantity</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"quantity\" value=\"" + rs.getString(4) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Discount</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"discount\" value=\"" + rs.getDouble(5) + "\" step=\"0.001\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>"
                                + "                        <input type=\"submit\" value=\"Send\" name=\"submit\">"
                                + "                    </td>"
                                + "                    <td>"
                                + "                        <input type=\"reset\" value=\"Clear\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "            </table>"
                                + "        </form>");
                    }
                } else {
                    String orderId = request.getParameter("orderId");
                    String productId = request.getParameter("productId");
                    String unitPrice = request.getParameter("unitPrice");
                    String quantity = request.getParameter("quantity");
                    String discount = request.getParameter(("discount"));

                    int orderIdValue = Integer.parseInt(orderId);
                    int productIdValue = Integer.parseInt(productId);
                    double unitPriceValue = Double.parseDouble(unitPrice);
                    int quantityValue = Integer.parseInt(quantity);
                    double discountValue = Double.parseDouble(discount);
                    
                    OrderDetail od = new OrderDetail(orderIdValue, productIdValue, unitPriceValue, quantityValue, discountValue);
                    int n = dao.updateOrderDetail(od);
                    response.sendRedirect("controllerOrderDetail");
//                    out.print(n);
                }
            } else if (service.equals("deleteOrderDetail")) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllerOrderDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
