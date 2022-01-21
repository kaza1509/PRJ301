package controller;

import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
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
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlerProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlerProduct at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        try (PrintWriter out = response.getWriter()) {
            DAOProduct dao = new DAOProduct();
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
            if(quantityPerUnit.isEmpty()) {
                out.println("quantityPerUnit is not empty");
                return;
            }
            if(!unitPrice.matches(checkDob)) {
                out.print("UnitPrice has to number");
                return;
            }
            if(!unitsInStock.matches(checkInt)) {
                out.print("unitsInStock has to number");
                return;
            }
            if(!unitsOnOrder.matches(checkInt)) {
                out.print("unitsOnOrder has to number");
                return;
            }
            if(!reorderLevel.matches(checkInt)) {
                out.print("reorderLevel has to number");
                return;
            }
            if(!discontinued.matches("[01]")) {
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
            if(n > 0) {
                out.print("Inserted");
            }
            else {
                out.print("Error");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
