package controller;

import entity.Product;
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
import model.DAOProduct;

/**
 *
 * @author kazaf
 */
@WebServlet(name = "ControllerProduct", urlPatterns = {"/controllerProduct"})
public class ControlerProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOProduct dao = new DAOProduct();
            String service = request.getParameter("do");
            if (service == null) {
                service = "listAllProduct";
            }
            if (service.equals("InsertProduct")) {
                //get data
                String productName = request.getParameter("productName");
                String supplierId = request.getParameter("supplierId");
                String categoryId = request.getParameter("categoryId");
                String quantityPerUnit = request.getParameter("quantityPerUnit");
                String unitPrice = request.getParameter("unitPrice");
                String unitsInStock = request.getParameter("unitsInStock");
                String unitsOnOrder = request.getParameter("unitsOnOrder");
                String reorderLevel = request.getParameter("reorderLevel");
                String discontinued = request.getParameter("discontinued");

                //check/validate data
                String checkInt = "[1-9][0-9]*";
                String checkDob = "[0-9]+[.][0-9]+";
                if (productName.isEmpty()) {
                    out.println("Product is not empty");
                    return;
                }
                if (quantityPerUnit.isEmpty()) {
                    out.println("quantityPerUnit is not empty");
                    return;
                }
                if (!unitPrice.matches(checkDob)) {
                    out.print("UnitPrice has to number");
                    return;
                }
                if (!unitsInStock.matches(checkInt)) {
                    out.print("unitsInStock has to number");
                    return;
                }
                if (!unitsOnOrder.matches(checkInt)) {
                    out.print("unitsOnOrder has to number");
                    return;
                }
                if (!reorderLevel.matches(checkInt)) {
                    out.print("reorderLevel has to number");
                    return;
                }
                if (!discontinued.matches("[01]")) {
                    out.print("discontinued has to binary");
                    return;
                }

                //convert
                int supplierIdValue = Integer.parseInt(supplierId);
                int categoryIdValue = Integer.parseInt(categoryId);
                double unitPriceValue = Double.parseDouble(unitPrice);
                int unitsInStockValue = Integer.parseInt(unitsInStock);
                int unitsOnOrderValue = Integer.parseInt(unitsOnOrder);
                int reorderLevelValue = Integer.parseInt(reorderLevel);
                int discontinuedValue = Integer.parseInt(discontinued);

                //display
                Product pro = new Product(productName, supplierIdValue, categoryIdValue,
                        quantityPerUnit, unitPriceValue, unitsInStockValue, unitsOnOrderValue,
                        reorderLevelValue, discontinuedValue);
                int n = dao.addProduct(pro);
                response.sendRedirect("controllerProduct");
            } else if (service.equals("listAllProduct")) {
//                out.print("list All");
                Vector<Product> vector = dao.listAll("select * from Products");

                out.print("<p><a href=\"addProducts.html\">Add Product</a></p>");
                
                out.print("<table border=\"1\">"
                        + "<caption>List of Products</caption>"
                        + "        <thead>"
                        + "            <tr>"
                        + "                <th>ProductId</th>"
                        + "                <th>ProductName</th>"
                        + "                <th>SupplierID</th>"
                        + "                <th>CategoryID</th>"
                        + "                <th>QuantityPerUnit</th>"
                        + "                <th>UnitPrice</th>"
                        + "                <th>UnitsInStock</th>"
                        + "                <th>UnitsOnOrder</th>"
                        + "                <th>ReorderLevel</th>"
                        + "                <th>Discontinued</th>"
                        + "                <th>Update</th>"
                        + "                <th>Delete</th>"
                        + "            </tr>"
                        + "        </thead>"
                        + "        <tbody>");

                for (Product product : vector) {
                    out.print("<tr>"
                            + "<td>" + product.getProductId() + "</td>"
                            + "<td>" + product.getProductName() + "</td>"
                            + "<td>" + product.getSupplierId() + "</td>"
                            + "<td>" + product.getCategoryId() + "</td>"
                            + "<td>" + product.getQuantityPerUnit() + "</td>"
                            + "<td>" + product.getUnitPrice() + "</td>"
                            + "<td>" + product.getUnitsInStock() + "</td>"
                            + "<td>" + product.getUnitsOnOrder() + "</td>"
                            + "<td>" + product.getReOrderLevel() + "</td>"
                            + "<td>" + product.getDiscontinued() + "</td>"
                            + "<td><a href=\"controllerProduct?do=updateProduct&productId=" + product.getProductId() + "\">update</a></td>"
                            + "<<td><a href=\"controllerProduct?do=deleteProduct&productId=" + product.getProductId() + "\">delete</a></td>"
                            + "</tr>");
                }

                out.print("</tbody>"
                        + "    </table>");
            } else if (service.equals("updateProduct")) {
                String submit = request.getParameter("submit");
                if (submit == null) {//chưa ấn submit
                    //step 1: get record -> display form
                    int productId = Integer.parseInt(request.getParameter("productId"));
                    ResultSet rs = dao.getData("select * from Products where ProductID = " + productId);
                    if (rs.next()) {
                        out.print("<form action=\"controllerProduct?do=updateProduct\" method=\"post\">"
                                + "            <table>"
                                + "                <tr>"
                                + "                    <h1>Update Product</h1>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Product Id</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"productId\" value=\"" + rs.getInt(1) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Product Name</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"productName\" value=\"" + rs.getString(2) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>SupplierID</td>"
                                + "                    <td>"
                                + "                        <select name=\"supplierId\" id=\"SupplierID\">");
                        ResultSet rs1 = dao.getData("select * from Suppliers");
                        while (rs1.next()) {
                            out.print("<option value=\"" + rs1.getInt(1) + "\" " + (rs.getInt(3) == rs1.getInt(1) ? "selected" : "") + ">" + rs1.getString(2) + "</option>");
                        }
                        out.print("</select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>CategoryID</td>"
                                + "                    <td>");
                                out.print("<select name=\"categoryId\" id=\"CategoryID\">");
                                ResultSet rs2 = dao.getData("select * from Categories");
                                while(rs2.next()) {
                                    out.print("<option value=\""+rs2.getInt(1)+"\" "+(rs2.getInt(1) == rs.getInt(4)?"selected":"")+">"+rs2.getString(2)+"</option>");
                                }
                                out.print("</select>"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>QuantityPerUnit</td>"
                                + "                    <td>"
                                + "                        <input type=\"text\" name=\"quantityPerUnit\" value=\"" + rs.getString(5) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>UnitPrice</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"unitPrice\" value=\"" + rs.getString(6) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>UnitsInStock</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"unitsInStock\" value=\"" + rs.getString(7) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>UnitsOnOrder</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"unitsOnOrder\" value=\"" + rs.getString(8) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>ReorderLevel</td>"
                                + "                    <td>"
                                + "                        <input type=\"number\" name=\"reorderLevel\" value=\"" + rs.getString(9) + "\">"
                                + "                    </td>"
                                + "                </tr>"
                                + "                <tr>"
                                + "                    <td>Discontinued</td>"
                                + "                    <td>"
                                + "                        <input type=\"radio\" name=\"discontinued\" value=\"0\" "+(rs.getInt(10) == 0?"checked":"")+">Continue\n"
                                + "                        <input type=\"radio\" name=\"discontinued\" value=\"1\" "+(rs.getInt(10) == 1?"checked":"")+">Discontinue"
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
                    //step 2: update
                    String sproductId = request.getParameter("productId");
                    String productName = request.getParameter("productName");
                    String supplierId = request.getParameter("supplierId");
                    String categoryId = request.getParameter("categoryId");
                    String quantityPerUnit = request.getParameter("quantityPerUnit");
                    String unitPrice = request.getParameter("unitPrice");
                    String unitsInStock = request.getParameter("unitsInStock");
                    String unitsOnOrder = request.getParameter("unitsOnOrder");
                    String reorderLevel = request.getParameter("reorderLevel");
                    String discontinued = request.getParameter("discontinued");

                    //convert
                    int pid = Integer.parseInt(sproductId);
                    int supplierIdValue = Integer.parseInt(supplierId);
                    int categoryIdValue = Integer.parseInt(categoryId);
                    double unitPriceValue = Double.parseDouble(unitPrice);
                    int unitsInStockValue = Integer.parseInt(unitsInStock);
                    int unitsOnOrderValue = Integer.parseInt(unitsOnOrder);
                    int reorderLevelValue = Integer.parseInt(reorderLevel);
                    int discontinuedValue = Integer.parseInt(discontinued);

                    Product pro = new Product(pid, productName, supplierIdValue, categoryIdValue,
                            quantityPerUnit, unitPriceValue, unitsInStockValue, unitsOnOrderValue,
                            reorderLevelValue, discontinuedValue);
                    int n = dao.updateProduct(pro);
                    response.sendRedirect("controllerProduct?do=listAllProduct");
                }

            } else if (service.equals("deleteProduct")) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlerProduct.class.getName()).log(Level.SEVERE, null, ex);
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
