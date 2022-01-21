package controller;

import entity.OrderDetail;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ControllerOrderDetail", urlPatterns = {"/ControllerOrderDetail"})
public class ControllerOrderDetail extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControllerOrderDetail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControllerOrderDetail at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOOrderDetail dao = new DAOOrderDetail();
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
            
            if(!quantity.matches(checkInt)) {
                out.print("quantity has to number");
                return;
            }
            
            if(!discount.matches(checkDob)) {
                out.print("discount has to number");
                return;
            }
            //convert
            int orderIdValue = Integer.parseInt(orderId);
            int productIdValue = Integer.parseInt(productId);
            double unitPriceValue = Double.parseDouble(unitPrice);
            int quantityValue = Integer.parseInt(quantity);
            double discountValue = Double.parseDouble(discount);
            
            out.println(orderIdValue+" - "+productIdValue+" - "+unitPrice+" - "+quantityValue+" - "+discountValue);
            //add db
            OrderDetail ord = new OrderDetail(orderIdValue, productIdValue, 
                    unitPriceValue, quantityValue, discountValue);
            int n = dao.addOrderDetail(ord);
            if(n > 0) {
                out.println("Inserted successful");
            }
            else {
                out.println("Inserted fail");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
